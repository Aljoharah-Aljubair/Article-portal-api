package com.artical.portal.api.service;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface ArticleService {
    ArticleDto creatArticle(ArticleDto articleDto,Principal principal);
    List<ArticleDto> getAllArticle(int pageNo , int pageSize);
    ArticleDto getArticleById(int id);
    boolean deleteArticleId(int id , Principal principal);
    ArticleDto increaseLikes(int articleId);
    ArticleDto increaseDislikes(int articleId);
    public ArticleDto disableArticle(int articleId);
    public ArticleDto enableArticle(int articleId);

}
