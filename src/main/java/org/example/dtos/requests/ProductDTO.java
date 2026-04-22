package org.example.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String pname;
    private String pdesc;
    private Long categoryId;
    MultipartFile mainImage;
    MultipartFile[] images;
    private String mainImagePath;
    private List<String> imagePaths;
    private boolean isNew;
    private boolean isBestSeller;
}
