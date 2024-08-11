package com.artical.portal.api.controllers;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.dto.CommentDto;
import com.artical.portal.api.service.CommentService;
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

@CrossOrigin
@RestController
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/article/{id}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto commentDto, @PathVariable int id , Principal principal) {
        return ResponseEntity.ok(commentService.addComment(commentDto,id,principal));
    }
    @GetMapping("article/{id}/comment")
    public ResponseEntity<List<CommentDto>> articleDetail(@PathVariable int id) {
        return ResponseEntity.ok(commentService.findCommentByArticleId(id));
    }
}