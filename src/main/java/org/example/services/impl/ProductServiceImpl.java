package org.example.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dtos.requests.ProductDTO;
import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.repositories.ProductRepository;
import org.example.repositories.V2CategoriesRepository;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final V2CategoriesRepository v2CategoriesRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, V2CategoriesRepository v2CategoriesRepository) {
        this.productRepository = productRepository;
        this.v2CategoriesRepository = v2CategoriesRepository;
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
        return productRepository.findTop8ByOrderById();
    }

    @Override
    public ProductEntity addProduct(ProductDTO request) {
        try {
            // Kiểm tra thư mục upload nếu chưa có thì tạo
            String uploadDir = System.getProperty("user.dir") + "" + File.separator + "uploads";
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Lưu ảnh chính
            String mainImageName = UUID.randomUUID().toString() + "-" + request.getMainImage().getOriginalFilename();
            Path mainImagePathFile = path.resolve(mainImageName);
            request.getMainImage().transferTo(mainImagePathFile.toFile());
            String mainImagePath = "/uploads/" + mainImageName;

            // Danh sách lưu các đường dẫn ảnh
            List<String> imagePaths = new ArrayList<>();
            // Lưu từng ảnh vào thư mục và thêm đường dẫn vào danh sách
            for (MultipartFile file : request.getImages()) {
                String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
                Path filePath = path.resolve(fileName);
                file.transferTo(filePath.toFile());

                // Lưu đường dẫn ảnh (có thể là /uploads/filename.jpg)
                imagePaths.add("/uploads/" + fileName);
            }

            // Lấy danh mục từ cơ sở dữ liệu
            Optional<V2Categories> categoryOpt = v2CategoriesRepository.findById(request.getCategoryId());
            if (!categoryOpt.isPresent()) {
                throw new RuntimeException("Category not found");
            }

            // Tạo và lưu sản phẩm vào cơ sở dữ liệu
            ProductEntity product = new ProductEntity();
            product.setPname(request.getPname());
            product.setPdesc(request.getPdesc());
            product.setCategory(categoryOpt.get());
            product.setMainImagePath(mainImagePath);
            product.setPimages(imagePaths); // Lưu danh sách đường dẫn ảnh

            // Lưu vào DB
            return productRepository.save(product);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Xử lý lỗi nếu upload thất bại
        }
    }


}
