package com.artical.portal.api.service.impl;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.exceptions.ArticleNotFoundException;
import com.artical.portal.api.exceptions.ArticleOwnerException;
import com.artical.portal.api.mapppers.ArticleMapper;
import com.artical.portal.api.models.Article;
import com.artical.portal.api.repository.ArticleRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    private ArticleMapper articleMapper;


    @Autowired
    public ArticleImpl(ArticleRepository articleRepository,UserRepository userRepository , ArticleMapper articleMapper){
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.articleMapper=articleMapper;

    }

    @Override
    public ArticleDto creatArticle(ArticleDto articleDto,Principal principal) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = now.format(formatter);

        articleDto.setCreatedAt(formattedDate);
        articleDto.setNumberOfLikes(0);
        articleDto.setNumberOfDislikes(0);
        articleDto.setAuthorUsername(principal.getName());
        Article article = articleMapper.toEntity(articleDto);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    @Override
    public List<ArticleDto> getAllArticle(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Article> articles = articleRepository.findAllByDisabledFalse(pageable);
        List<Article> ListOfArticle = articles.getContent();
        return ListOfArticle.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public ArticleDto getArticleById(int id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("cant find article id"));
        return articleMapper.toDto(article);
    }

    @Override
    public boolean deleteArticleId(int id , Principal principal) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new ArticleNotFoundException("Article with ID " + id + " does not exist."));
        if(principal.getName().equalsIgnoreCase(article.getAuthorUsername())) {
            articleRepository.delete(article);
            return true;
        }
        else
            throw new ArticleOwnerException("You are not the article owner.");
    }

    @Override
    public ArticleDto increaseLikes(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + articleId + " does not exist."));

        article.setNumberOfLikes(article.getNumberOfLikes() + 1);
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public ArticleDto increaseDislikes(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + articleId + " does not exist."));

        article.setNumberOfDislikes(article.getNumberOfDislikes() + 1);
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public ArticleDto disableArticle(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + articleId + " does not exist."));

        if(article.isDisabled()==false)
            article.setDisabled(true);

        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public ArticleDto enableArticle(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + articleId + " does not exist."));

        if(article.isDisabled()==true)
            article.setDisabled(false);

        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

}