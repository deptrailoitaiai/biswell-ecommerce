package org.example.controllers.adminControllers;

import org.example.entities.ProductEntity;
import org.example.dtos.requests.ProductDTO;
import org.example.services.CloudinaryService;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;
    private final V2CategoriesService v2CategoriesService;
    private final CloudinaryService cloudinaryService;

    public AdminProductController(ProductService productService,
                                  V2CategoriesService v2CategoriesService,
                                  CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.v2CategoriesService = v2CategoriesService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "15") int size,
                       Model model) {
        Page<ProductEntity> productPage = productService.getAllProducts(
                PageRequest.of(page, size, Sort.by("id").descending()));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin/products/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("categories", v2CategoriesService.getAll());
        model.addAttribute("action", "add");
        return "admin/products/form";
    }

    @PostMapping("/save")
    public String save(@RequestParam String pname,
                       @RequestParam(required = false) String pdesc,
                       @RequestParam Long categoryId,
                       @RequestParam MultipartFile mainImage,
                       @RequestParam(required = false) MultipartFile[] pimages,
                       @RequestParam(defaultValue = "false") boolean isNew,
                       @RequestParam(defaultValue = "false") boolean isBestSeller,
                       RedirectAttributes ra) {
        try {
            ProductDTO dto = new ProductDTO();
            dto.setPname(pname);
            dto.setPdesc(pdesc);
            dto.setCategoryId(categoryId);
            dto.setNew(isNew);
            dto.setBestSeller(isBestSeller);
            dto.setMainImagePath(cloudinaryService.uploadFile(mainImage));

            if (pimages != null) {
                List<String> urls = Arrays.stream(pimages)
                        .filter(f -> !f.isEmpty())
                        .map(cloudinaryService::uploadFile)
                        .toList();
                dto.setImagePaths(urls);
            }

            productService.addProduct(dto);
            ra.addFlashAttribute("success", "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/products/add";
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", v2CategoriesService.getAll());
        model.addAttribute("action", "edit");
        return "admin/products/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @RequestParam String pname,
                         @RequestParam(required = false) String pdesc,
                         @RequestParam Long categoryId,
                         @RequestParam(required = false) MultipartFile mainImage,
                         @RequestParam(required = false) MultipartFile[] pimages,
                         @RequestParam(defaultValue = "false") boolean isNew,
                         @RequestParam(defaultValue = "false") boolean isBestSeller,
                         RedirectAttributes ra) {
        try {
            String mainImageUrl = (mainImage != null && !mainImage.isEmpty())
                    ? cloudinaryService.uploadFile(mainImage) : null;

            List<String> pimageUrls = null;
            if (pimages != null) {
                pimageUrls = Arrays.stream(pimages)
                        .filter(f -> !f.isEmpty())
                        .map(cloudinaryService::uploadFile)
                        .toList();
                if (pimageUrls.isEmpty()) pimageUrls = null;
            }

            productService.updateProductAdmin(id, pname, pdesc, categoryId, mainImageUrl, pimageUrls, isNew, isBestSeller);
            ra.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/toggle-new")
    public String toggleNew(@PathVariable Long id,
                            @RequestParam(defaultValue = "0") int page,
                            RedirectAttributes ra) {
        try {
            ProductEntity p = productService.getProductById(id);
            p.setNew(!p.isNew());
            productService.save(p);
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/products?page=" + page;
    }

    @PostMapping("/{id}/toggle-bestseller")
    public String toggleBestSeller(@PathVariable Long id,
                                   @RequestParam(defaultValue = "0") int page,
                                   RedirectAttributes ra) {
        try {
            ProductEntity p = productService.getProductById(id);
            p.setBestSeller(!p.isBestSeller());
            productService.save(p);
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/products?page=" + page;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("success", "Đã xóa sản phẩm!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/products";
    }
}
