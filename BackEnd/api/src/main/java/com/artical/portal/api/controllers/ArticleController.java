package com.artical.portal.api.controllers;


import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.models.Article;
import com.artical.portal.api.models.PaginatedArticleResponse;
import com.artical.portal.api.service.ArticleService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableMethodSecurity

@CrossOrigin
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/article")
    public ResponseEntity<ArticleDto> createArticle(@Valid @RequestBody ArticleDto articleDto , Principal principal) {
        return ResponseEntity.ok(articleService.creatArticle(articleDto,principal));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/article/disabeled")
    public ResponseEntity<List<ArticleDto>> getDisabeledArticle(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize){
        return ResponseEntity.ok(articleService.getDisabeledArticle(pageNo,pageSize));
    }
    @GetMapping("article/{id}")
    public ResponseEntity<ArticleDto> articleDetail(@PathVariable("id") int articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }
    @GetMapping("/article")
    public ResponseEntity<PaginatedArticleResponse> getArticle(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(articleService.getAllArticle(pageNo, pageSize));
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("article/{id}")
    public ResponseEntity< Map<String, String>> deleteArticle(@PathVariable("id") int articleId, Principal principal){

        Map<String, String> response = new HashMap<>();
       if( articleService.deleteArticleId(articleId,principal))
           response.put("message", "Article deleted successfully");
       else
           response.put("message", "Article can not be deleted");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/article/{id}/like")
    public ResponseEntity<ArticleDto> increaseLikes(@PathVariable("id") int articleId) {
        return ResponseEntity.ok(articleService.increaseLikes(articleId));
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/article/{id}/dislike")
    public ResponseEntity<ArticleDto> increaseDislikes(@PathVariable("id") int articleId) {
        return ResponseEntity.ok(articleService.increaseDislikes(articleId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/article/{id}/disable")
    public ResponseEntity<ArticleDto> disableArticle(@PathVariable("id") int articleId) {
        return ResponseEntity.ok(articleService.disableArticle(articleId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/article/{id}/enable")
    public ResponseEntity<ArticleDto> enableArticle(@PathVariable("id") int articleId) {
        return ResponseEntity.ok(articleService.enableArticle(articleId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("try")
    public String test ( Principal principal) {
        return "hello"+principal.getName();
    }
}