package org.example.services;

import org.example.dtos.requests.ProductDTO;
import org.example.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProducts();

    ProductEntity getProductById(Long id);

    List<ProductEntity> getTop8();

    ProductEntity addProduct(ProductDTO product);

    Page<ProductEntity> getByCategoryId(long categoryId, Pageable pageable);

    void updateProduct(Long id, ProductDTO product, MultipartFile mainImage, MultipartFile[] pimages);

    Page<ProductEntity> getAllProducts(Pageable page);

    void deleteById(Long id);

    Page<ProductEntity> search(String search, Pageable pageable);

    ProductEntity updateProductAdmin(Long id, String pname, String pdesc, Long categoryId,
                                     String mainImageUrl, List<String> pimageUrls,
                                     boolean isNew, boolean isBestSeller,
                                     String metaTitle, String metaDescription);

    ProductEntity save(ProductEntity product);
    ProductEntity getBySlug(String slug);
    long count();
    List<ProductEntity> getNewProducts();
    List<ProductEntity> getBestSellerProducts();
}
