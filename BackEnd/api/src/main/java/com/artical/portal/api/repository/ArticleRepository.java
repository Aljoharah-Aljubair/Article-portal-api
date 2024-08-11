package com.artical.portal.api.repository;

import com.artical.portal.api.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200")
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    @Query("SELECT a FROM Article a WHERE a.Disabled = false")
    Page<Article> findAllByDisabledFalse(Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.Disabled = true")
    Page<Article> findAllByDisabledTrue(Pageable pageable);
}
