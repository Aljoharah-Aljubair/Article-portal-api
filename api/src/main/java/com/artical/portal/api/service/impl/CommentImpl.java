package com.artical.portal.api.service.impl;


import com.artical.portal.api.dto.CommentDto;
import com.artical.portal.api.exceptions.ArticleNotFoundException;
import com.artical.portal.api.exceptions.UserNotFoundException;
import com.artical.portal.api.mapppers.CommentMapper;
import com.artical.portal.api.models.Article;
import com.artical.portal.api.models.Comment;
import com.artical.portal.api.models.UserEntity;
import com.artical.portal.api.repository.ArticleRepository;
import com.artical.portal.api.repository.CommentRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.service.CommentService;
import jakarta.transaction.Transactional;
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
    private CommentMapper commentMapper;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    @Autowired
    public CommentImpl(CommentRepository commentRepository, CommentMapper commentMapper, ArticleRepository articleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }


    public CommentDto addComment(CommentDto commentDto, int articleId, Principal principal) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = now.format(formatter);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + articleId + " does not exist."));

        UserEntity user = userRepository.findByUsername(principal.getName());
        if(user==null)
            throw new UserNotFoundException("User with username " + principal.getName() + " not found.");

        Comment comment = new Comment();
        comment.setText(commentDto.getContent());
        commentDto.setCreatedAT(formattedDate);
        comment.setCreatedAT(commentDto.getCreatedAT());
        comment.setUser(user);
        comment.setUserUsername(user.getUsername());
        comment.setArticle(article);

        article.getComments().add(comment);
        articleRepository.save(article);

        Comment savedComment = article.getComments().get(article.getComments().size() - 1);

        return new CommentDto(savedComment.getId(),
                savedComment.getText(),
                savedComment.getUser().getId(),
                savedComment.getUserUsername(),
                savedComment.getArticle().getId(),
                savedComment.getCreatedAT());
    }
    @Override
    public List<CommentDto> findCommentByArticleId(int id) {
        List<Comment> comments = commentRepository.findAllByArticleId(id);

        List<CommentDto> commentDtos = comments.stream()
                .map(commentMapper::Comment2Dto)
                .collect(Collectors.toList());
        return commentDtos;
    }
}
