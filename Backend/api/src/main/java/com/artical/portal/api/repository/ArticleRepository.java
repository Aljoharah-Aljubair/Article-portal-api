package com.artical.portal.api.repository;

import com.artical.portal.api.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200")
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Page<Article> findAllByDisabledFalse(Pageable pageable);
    Page<Article> findAllByDisabledTrue(Pageable pageable);
}
