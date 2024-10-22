package com.artical.portal.api.entity;

import com.artical.portal.api.dto.ArticleDto;
import lombok.Data;

import java.util.List;
@Data
public class PaginatedArticleResponse {
    private List<ArticleDto> content;
    private long totalElements;
}