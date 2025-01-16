package org.example.controllers.renderControllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.example.entities.Product;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/product")
public class ProductController {

    // @Value("${upload.path}") // Đường dẫn thư mục lưu ảnh (có thể cấu hình trong
    // application.properties)
    // private String uploadDir;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {

        return "addProd";
    }

    @GetMapping
    public String getProductPage(Model model) {
        model.addAttribute("dataList", productRepository.findAll());

        return "san-pham";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model) {
        // Giả sử bạn có một phương thức để lấy sản phẩm từ database
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        model.addAttribute("product", product);
        model.addAttribute("dataList", productRepository.findTop8ByOrderById());
        
        return "product-details"; // Trả về trang chi tiết sản phẩm
    }

    @PostMapping("/save")
    public String saveProduct(
            @RequestParam("pname") String pname,
            @RequestParam("pdesc") String pdesc,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("pimages") MultipartFile[] files,
            Model model) {

        try {
            // Kiểm tra thư mục upload nếu chưa có thì tạo
            String uploadDir = System.getProperty("user.dir") + "" + File.separator + "uploads";
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Lưu ảnh chính
            String mainImageName = UUID.randomUUID().toString() + "-" + mainImage.getOriginalFilename();
            Path mainImagePathFile = path.resolve(mainImageName);
            mainImage.transferTo(mainImagePathFile.toFile());
            String mainImagePath = "/uploads/" + mainImageName;

            // Danh sách lưu các đường dẫn ảnh
            List<String> imagePaths = new ArrayList<>();
            // Lưu từng ảnh vào thư mục và thêm đường dẫn vào danh sách
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
                Path filePath = path.resolve(fileName);
                file.transferTo(filePath.toFile());

                // Lưu đường dẫn ảnh (có thể là /uploads/filename.jpg)
                imagePaths.add("/uploads/" + fileName);
            }

            // Tạo và lưu sản phẩm vào cơ sở dữ liệu
            Product product = new Product();
            product.setPname(pname);
            product.setPdesc(pdesc);
            product.setMainImagePath(mainImagePath);
            product.setPimages(imagePaths); // Lưu danh sách đường dẫn ảnh

            productRepository.save(product); // Lưu vào DB
            // Thêm sản phẩm vào model để hiển thị
            model.addAttribute("product", product);
            return "product-success"; // Chuyển tới trang thành công
        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // Xử lý lỗi nếu upload thất bại
        }
    }
}
