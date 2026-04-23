package org.example.controllers.renderControllers;

import org.example.dtos.requests.ProductDTO;
import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.repositories.V2CategoriesRepository;
import org.example.services.CloudinaryService;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final V2CategoriesService v2CategoriesService;

    private final ProductService productService;

    private final CloudinaryService cloudinaryService;

    @ModelAttribute("categoriesMenuTop")
    public List<V2Categories> populateCategories() {
        return v2CategoriesService.getAll();
    }

    @Autowired
    public ProductController(ProductService productService, V2CategoriesRepository v2CategoriesRepository, V2CategoriesService v2CategoriesService, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.v2CategoriesService = v2CategoriesService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        List<V2Categories> categories = v2CategoriesService.getAll();
        model.addAttribute("categories", categories);
        return "addProd";
    }

    @GetMapping("/{id}")
    public String redirectToSlug(@PathVariable Long id) {
        var product = productService.getProductById(id);
        return "redirect:/san-pham/" + product.getSlug();
    }

    @PostMapping("/save")
    public String saveProduct(
            @RequestParam String pname,
            @RequestParam String pdesc,
            @RequestParam MultipartFile mainImage,
            @RequestParam MultipartFile[] files,
            @RequestParam Long categoryId,
            Model model) {

        ProductDTO dto = new ProductDTO();
        dto.setPname(pname);
        dto.setPdesc(pdesc);
        dto.setCategoryId(categoryId);

        // upload main image
        String mainImageUrl = cloudinaryService.uploadFile(mainImage);
        dto.setMainImagePath(mainImageUrl);

        // upload sub images
        List<String> imageUrls = Arrays.stream(files)
                .filter(file -> !file.isEmpty())
                .map(cloudinaryService::uploadFile)
                .toList();

        dto.setImagePaths(imageUrls);

        model.addAttribute("product", productService.addProduct(dto));
        return "product-success";
    }

    // Hiển thị form update
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        ProductEntity product = productService.getProductById(id);
        List<V2Categories> categories = v2CategoriesService.getAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "product-update";
    }

    // Xử lý cập nhật
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute ProductDTO product,
                                @RequestParam MultipartFile mainImage,
                                @RequestParam MultipartFile[] pimages) {
        productService.updateProduct(id, product, mainImage, pimages);
        return "redirect:/product/list";
    }

    // Hiển thị danh sách sản phẩm với phân trang
    @GetMapping("/list")
    public String listProducts(@RequestParam(defaultValue = "0", required = false) Integer page,
                               @RequestParam(defaultValue = "9", required = false) Integer size,
                               Model model) {
        Page<ProductEntity> productPage = productService.getAllProducts(
                PageRequest.of(page, size, Sort.by("id").descending()));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "product-list";
    }

    // Xử lý xóa sản phẩm
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/product/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String search,
                         @RequestParam(required = false) Integer size,
                         @RequestParam(required = false) Integer page,
                         Model model) {
        size = size == null ? 9 : size;
        page = (page == null || page < 0) ? 0 : page;
        Page<ProductEntity> productPage = productService.search(search, PageRequest.of(page, size));
        model.addAttribute("dataList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("baseUrl", "/product/search");
        model.addAttribute("keyword", search); // để bind lại input
        return "shop";
    }

}
