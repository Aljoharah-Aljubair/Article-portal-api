package com.artical.portal.api.controllers;


import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.models.Article;
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
import java.util.List;

@RestController
@EnableMethodSecurity
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

    @GetMapping("article/{id}")
    public ResponseEntity<ArticleDto> articleDetail(@PathVariable("id") int articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }
    @GetMapping("/article")
    public ResponseEntity<List<ArticleDto>> getArticle(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize){
    return ResponseEntity.ok(articleService.getAllArticle(pageNo,pageSize));
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") int articleId, Principal principal){
       if( articleService.deleteArticleId(articleId,principal))
           return ResponseEntity.ok("Article deleted");
       else
           return ResponseEntity.badRequest().body("Article can not be deleted");
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