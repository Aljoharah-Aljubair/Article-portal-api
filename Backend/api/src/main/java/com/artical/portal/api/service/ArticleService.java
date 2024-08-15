package com.artical.portal.api.service;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.entity.PaginatedArticleResponse;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface ArticleService {
    ArticleDto creatArticle(ArticleDto articleDto, Principal principal, MultipartFile[] file);
    PaginatedArticleResponse getAllArticle(int pageNo , int pageSize);
     List<ArticleDto> getDisabeledArticle(int pageNo,int pageSize);
    ArticleDto getArticleById(int id);
    String deleteArticleId(int id , Principal principal);
    ArticleDto increaseLikes(int articleId);
    ArticleDto increaseDislikes(int articleId);
    public ArticleDto disableArticle(int articleId);
    public ArticleDto enableArticle(int articleId);

}
