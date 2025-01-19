package org.example.controllers.renderControllers;

import org.example.dtos.requests.ProductDTO;
import org.example.entities.V2Categories;
import org.example.repositories.V2CategoriesRepository;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final V2CategoriesRepository v2CategoriesRepository;

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService, V2CategoriesRepository v2CategoriesRepository) {
        this.productService = productService;
        this.v2CategoriesRepository = v2CategoriesRepository;
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        List<V2Categories> categories = v2CategoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "addProd";
    }

    @GetMapping
    public String getProductPage(Model model) {
        model.addAttribute("dataList", productService.getAllProducts());
        return "san-pham";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("dataList", productService.getTop8());

        return "product-details"; // Trả về trang chi tiết sản phẩm
    }

    @PostMapping("/save")
    public String saveProduct(
            @RequestParam("pname") String pname,
            @RequestParam("pdesc") String pdesc,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("pimages") MultipartFile[] files,
            @RequestParam("categoryId") Long categoryId,
            Model model) {

        ProductDTO dto = new ProductDTO();
        dto.setPname(pname);
        dto.setPdesc(pdesc);
        dto.setCategoryId(categoryId);
        dto.setMainImage(mainImage);
        dto.setImages(files);
        // Thêm sản phẩm vào model để hiển thị
        model.addAttribute("product", productService.addProduct(dto));
        return "product-success"; // Chuyển tới trang thành công
    }

}
