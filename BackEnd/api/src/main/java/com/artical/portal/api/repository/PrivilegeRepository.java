package com.artical.portal.api.repository;

import com.artical.portal.api.models.Comment;
import com.artical.portal.api.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    List<Privilege> findByType(String name);
}
