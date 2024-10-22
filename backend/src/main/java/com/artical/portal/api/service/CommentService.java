package com.artical.portal.api.service;

import com.artical.portal.api.dto.CommentDto;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto,int id, Principal principal);
    List<CommentDto> findCommentByArticleId(int id);
}
