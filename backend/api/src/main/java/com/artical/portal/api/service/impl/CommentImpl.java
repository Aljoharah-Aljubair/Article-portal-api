package com.artical.portal.api.service.impl;


import com.artical.portal.api.dto.CommentDto;
import com.artical.portal.api.exceptions.NotFoundException;
import com.artical.portal.api.mapper.CommentMapper;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.Comment;
import com.artical.portal.api.entity.UserEntity;
import com.artical.portal.api.repository.ArticleRepository;
import com.artical.portal.api.repository.CommentRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentImpl implements CommentService {

    private CommentRepository commentRepository;
    //private CommentMapper commentMapper;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    private CommentMapper commentMapper;
    @Autowired
    public CommentImpl(CommentRepository commentRepository, ArticleRepository articleRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        //this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDto addComment(CommentDto commentDto, int articleId, Principal principal) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = now.format(formatter);

        UserEntity user = userRepository.findByUsername(principal.getName());
        if(user==null)
            throw new NotFoundException("User with username " + principal.getName() + " not found.");

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article with ID " + articleId + " does not exist."));

        Comment comment = commentMapper.toEntity(commentDto,formattedDate,user,article);

        article.getComments().add(comment);
        articleRepository.save(article);

        Comment savedComment = article.getComments().get(article.getComments().size() - 1);
        return commentMapper.toDto(savedComment);
    }
    @Override
    public List<CommentDto> findCommentByArticleId(int id) {
        List<Comment> comments = commentRepository.findAllByArticleId(id);
        List<CommentDto> commentDtos = comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        return commentDtos;
    }
}
