package com.artical.portal.api.repository;

import com.artical.portal.api.models.Article;
import com.artical.portal.api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
public interface CommentRepository extends JpaRepository<Comment,Integer>{
    List<Comment> findAllByArticleId(int articleId);
}
