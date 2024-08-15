package com.artical.portal.api.service.impl;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.entity.Image;
import com.artical.portal.api.exceptions.ArticleOwnerException;
import com.artical.portal.api.exceptions.NotFoundException;
import com.artical.portal.api.mapper.ArticleMapper;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.PaginatedArticleResponse;
import com.artical.portal.api.entity.UserEntity;
import com.artical.portal.api.repository.ArticleRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public ArticleDto creatArticle(ArticleDto articleDto,Principal principal,MultipartFile[] file) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = now.format(formatter);

       UserEntity user= userRepository.findByUsername(principal.getName());
       Article article = articleMapper.toEntity(articleDto, formattedDate,user);
       Article savedArticle = new Article();
        try{
            Set<Image> images = uploadImage(file);
            article.setArticleImages(images);
            savedArticle = articleRepository.save(article);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return articleMapper.toDto(savedArticle);
    }
    public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<Image> images=new HashSet<>();
        for(MultipartFile file: multipartFiles){
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }

    @Override
    public ArticleDto getArticleById(int id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("cant find article id"));
        return articleMapper.toDto(article);
    }
    @Override
    public PaginatedArticleResponse getAllArticle(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Article> articles = articleRepository.findAllByDisabledFalse(pageable);
        List<Article> listOfArticles = articles.getContent();
        List<ArticleDto> articleDtos = listOfArticles.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
        PaginatedArticleResponse response = new PaginatedArticleResponse();
        response.setContent(articleDtos);
        response.setTotalElements(articles.getTotalElements());
        return response;
    }

    @Override
    public List<ArticleDto> getDisabeledArticle(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Article> articles = articleRepository.findAllByDisabledTrue(pageable);
        List<Article> ListOfArticle = articles.getContent();
        return ListOfArticle.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteArticleId(int id, Principal principal) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() ->new NotFoundException("Article with ID " + id + " does not exist."));

        if (principal.getName().equalsIgnoreCase(article.getAuthorUsername())) {
            articleRepository.delete(article);
            return "Article deleted successfully";
        } else {
            throw new ArticleOwnerException("You are not the article owner.");
        }
    }

    @Override
    public ArticleDto increaseLikes(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article with ID " + articleId + " does not exist."));

        article.setNumberOfLikes(article.getNumberOfLikes() + 1);
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public ArticleDto increaseDislikes(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article with ID " + articleId + " does not exist."));

        article.setNumberOfDislikes(article.getNumberOfDislikes() + 1);
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public ArticleDto disableArticle(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article with ID " + articleId + " does not exist."));
        article.setDisabled(!article.isDisabled());
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public ArticleDto enableArticle(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article with ID " + articleId + " does not exist."));

        if(article.isDisabled()==true)
            article.setDisabled(false);

        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }
}