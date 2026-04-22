package org.example.controllers.adminControllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminLoginController {

    private final JwtUtil jwtUtil;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public AdminLoginController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/admin/login")
    public String loginPage(HttpServletRequest request) {
        // Redirect to dashboard if already logged in
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("admin_jwt".equals(c.getName())) {
                    try {
                        if (jwtUtil.validateToken(c.getValue())) return "redirect:/admin";
                    } catch (Exception ignored) {}
                }
            }
        }
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpServletResponse response,
                          RedirectAttributes ra) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            try {
                String token = jwtUtil.generateToken(username);
                Cookie cookie = new Cookie("admin_jwt", token);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                cookie.setMaxAge(7200); // 2 hours
                response.addCookie(cookie);
                return "redirect:/admin";
            } catch (Exception e) {
                ra.addFlashAttribute("error", "Lỗi xác thực. Vui lòng thử lại.");
            }
        } else {
            ra.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu.");
        }
        return "redirect:/admin/login";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("admin_jwt", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/admin/login";
    }
}
