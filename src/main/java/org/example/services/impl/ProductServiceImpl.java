package org.example.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.dtos.requests.ProductDTO;
import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.repositories.ProductRepository;
import org.example.repositories.V2CategoriesRepository;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
import org.example.utils.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final V2CategoriesRepository v2CategoriesRepository;
    private final V2CategoriesService v2CategoriesService;

    private String uploadDir = System.getProperty("user.dir") + "" + File.separator + "uploads";

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, V2CategoriesRepository v2CategoriesRepository, V2CategoriesService v2CategoriesService) {
        this.productRepository = productRepository;
        this.v2CategoriesRepository = v2CategoriesRepository;
        this.v2CategoriesService = v2CategoriesService;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Long id) {
        // Giả sử bạn có một phương thức để lấy sản phẩm từ database
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return product;
    }

    @Override
    public List<ProductEntity> getTop8() {
        return productRepository.findTop8ByOrderByIdDesc();
    }

    @Override
    public ProductEntity addProduct(ProductDTO request) {
        try {
            // validate category
            Optional<V2Categories> categoryOpt = v2CategoriesRepository.findById(request.getCategoryId());
            if (categoryOpt.isEmpty()) {
                throw new RuntimeException("Category not found");
            }

            // map entity
            ProductEntity product = new ProductEntity();
            product.setPname(request.getPname());
            product.setPdesc(request.getPdesc());
            product.setCategory(categoryOpt.get());

            product.setMainImagePath(request.getMainImagePath());
            product.setPimages(request.getImagePaths());
            product.setNew(request.isNew());
            product.setBestSeller(request.isBestSeller());
            product.setSlug(generateUniqueSlug(request.getPname(), null));
            product.setMetaTitle(resolveMetaTitle(request.getMetaTitle(), request.getPname()));
            product.setMetaDescription(resolveMetaDesc(request.getMetaDescription(), request.getPdesc()));

            // save DB
            return productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ProductEntity> getByCategoryId(long categoryId, Pageable pageable) {
        return productRepository.findByCategory_CategoryId(categoryId, pageable);
    }

    public void updateProduct(Long id, ProductDTO updatedProduct, MultipartFile mainImage, MultipartFile[] pimages) {
        try {
            ProductEntity product = getProductById(id);
            // Cập nhật các trường
            product.setPname(updatedProduct.getPname());
            product.setPdesc(updatedProduct.getPdesc());
            product.setCategory(v2CategoriesService.getById(updatedProduct.getCategoryId()));

            String productFolderName = updatedProduct.getPname().replaceAll("[^a-zA-Z0-9-_]", "_");
            File productFolder = new File(uploadDir + "/" + productFolderName);
            if (!productFolder.exists()) {
                productFolder.mkdir();
            }

            // Xử lý ảnh chính
            if (ObjectUtils.isNotEmpty(mainImage) && StringUtils.isNotEmpty(mainImage.getOriginalFilename())) {
                String mainImageName = UUID.randomUUID() + "-" + updatedProduct.getMainImage().getOriginalFilename();
                File mainImageFile = new File(productFolder, mainImageName);
                mainImage.transferTo(mainImageFile);
                product.setMainImagePath("/uploads/" + productFolderName + "/" + mainImageName);
            }

            // Xử lý ảnh khác
            if (pimages != null && pimages.length > 0) {
                List<String> newImagePaths = new ArrayList<>();
                for (MultipartFile image : pimages) {
                    if (ObjectUtils.isNotEmpty(image.getOriginalFilename())) {
                        String imageName = UUID.randomUUID() + "-" + image.getOriginalFilename();
                        File imageFile = new File(productFolder, imageName);
                        image.transferTo(imageFile);
                        newImagePaths.add("/uploads/" + productFolderName + "/" + imageName);
                    }
                }
                if (ObjectUtils.isNotEmpty(newImagePaths)) {
                    product.setPimages(newImagePaths);
                }
            }

            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String saveFile(MultipartFile file, String uploadDir) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = uploadDir + "/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        return filePath;
    }


    @Override
    public Page<ProductEntity> getAllProducts(Pageable page) {
        return productRepository.findAll(page);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductEntity> search(String search, Pageable pageable) {
        return productRepository.findByPnameLike(search, pageable);
    }

    @Override
    public ProductEntity getBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Product not found: " + slug));
    }

    @Override
    public ProductEntity updateProductAdmin(Long id, String pname, String pdesc, Long categoryId,
                                            String mainImageUrl, List<String> pimageUrls,
                                            boolean isNew, boolean isBestSeller,
                                            String metaTitle, String metaDescription) {
        ProductEntity product = getProductById(id);
        product.setPname(pname);
        product.setPdesc(pdesc);
        product.setCategory(v2CategoriesService.getById(categoryId));
        if (mainImageUrl != null) product.setMainImagePath(mainImageUrl);
        if (pimageUrls != null && !pimageUrls.isEmpty()) product.setPimages(pimageUrls);
        product.setNew(isNew);
        product.setBestSeller(isBestSeller);

        // Slug: regenerate nếu tên thay đổi, đảm bảo unique
        String newSlug = generateUniqueSlug(pname, id);
        product.setSlug(newSlug);

        product.setMetaTitle(resolveMetaTitle(metaTitle, pname));
        product.setMetaDescription(resolveMetaDesc(metaDescription, pdesc));
        return productRepository.save(product);
    }

    private String generateUniqueSlug(String pname, Long excludeId) {
        String base = SlugUtil.toSlug(pname);
        String candidate = base;
        int suffix = 1;
        while (excludeId == null
                ? productRepository.existsBySlug(candidate)
                : productRepository.existsBySlugAndIdNot(candidate, excludeId)) {
            candidate = base + "-" + suffix++;
        }
        return candidate;
    }

    private String resolveMetaTitle(String input, String pname) {
        if (input != null && !input.isBlank()) return input.length() > 120 ? input.substring(0, 120) : input;
        String auto = pname + " - Biswell";
        return auto.length() > 120 ? auto.substring(0, 120) : auto;
    }

    private String resolveMetaDesc(String input, String pdesc) {
        if (input != null && !input.isBlank()) return input.length() > 160 ? input.substring(0, 160) : input;
        if (pdesc != null && !pdesc.isBlank()) return pdesc.length() > 155 ? pdesc.substring(0, 155) + "…" : pdesc;
        return "";
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public List<ProductEntity> getNewProducts() {
        return productRepository.findByIsNewTrueOrderByIdDesc();
    }

    @Override
    public List<ProductEntity> getBestSellerProducts() {
        return productRepository.findByIsBestSellerTrueOrderByIdDesc();
    }
}
