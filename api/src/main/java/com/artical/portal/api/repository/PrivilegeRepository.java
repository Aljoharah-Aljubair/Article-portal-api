package com.artical.portal.api.repository;

import com.artical.portal.api.models.Comment;
import com.artical.portal.api.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    List<Privilege> findByType(String name);
}
