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

            // set image từ URL (đã upload ở controller)
            product.setMainImagePath(request.getMainImagePath());
            product.setPimages(request.getImagePaths());

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


}
