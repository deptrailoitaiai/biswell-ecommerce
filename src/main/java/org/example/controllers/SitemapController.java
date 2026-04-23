package org.example.controllers;

import org.example.entities.ArticlesEntity;
import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.repositories.ArticleRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.V2CategoriesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SitemapController {

    private final ProductRepository productRepository;
    private final V2CategoriesRepository categoriesRepository;
    private final ArticleRepository articleRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    public SitemapController(ProductRepository productRepository,
                             V2CategoriesRepository categoriesRepository,
                             ArticleRepository articleRepository) {
        this.productRepository = productRepository;
        this.categoriesRepository = categoriesRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String sitemap() {
        String today = LocalDate.now().toString();
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

        // Trang tĩnh
        addUrl(xml, baseUrl + "/", today, "daily", "1.0");
        addUrl(xml, baseUrl + "/shop", today, "daily", "0.8");
        addUrl(xml, baseUrl + "/categories", today, "weekly", "0.7");
        addUrl(xml, baseUrl + "/introduction", today, "monthly", "0.5");
        addUrl(xml, baseUrl + "/news", today, "weekly", "0.6");
        addUrl(xml, baseUrl + "/contact", today, "monthly", "0.4");
        addUrl(xml, baseUrl + "/chinh-sach-bao-mat", today, "monthly", "0.4");
        addUrl(xml, baseUrl + "/chinh-sach-mua-hang", today, "monthly", "0.4");

        // Danh mục
        List<V2Categories> categories = categoriesRepository.findAll();
        for (V2Categories cat : categories) {
            addUrl(xml, baseUrl + "/categories/" + cat.getCategoryId(), today, "weekly", "0.6");
        }

        // Sản phẩm — chỉ lấy sản phẩm đã có slug
        List<ProductEntity> products = productRepository.findAll();
        for (ProductEntity p : products) {
            if (p.getSlug() != null && !p.getSlug().isBlank()) {
                addUrl(xml, baseUrl + "/san-pham/" + p.getSlug(), today, "weekly", "0.8");
            }
        }

        // Tin tức
        List<ArticlesEntity> articles = articleRepository.findAll();
        for (ArticlesEntity a : articles) {
            if (a.getArticleId() != null) {
                addUrl(xml, baseUrl + "/news/" + a.getArticleId(), today, "monthly", "0.6");
            }
        }

        xml.append("</urlset>");
        return xml.toString();
    }

    private void addUrl(StringBuilder xml, String loc, String lastmod, String changefreq, String priority) {
        xml.append("  <url>\n");
        xml.append("    <loc>").append(escapeXml(loc)).append("</loc>\n");
        xml.append("    <lastmod>").append(lastmod).append("</lastmod>\n");
        xml.append("    <changefreq>").append(changefreq).append("</changefreq>\n");
        xml.append("    <priority>").append(priority).append("</priority>\n");
        xml.append("  </url>\n");
    }

    private String escapeXml(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
