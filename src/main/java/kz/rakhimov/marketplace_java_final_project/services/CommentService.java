package kz.rakhimov.marketplace_java_final_project.services;

import kz.rakhimov.marketplace_java_final_project.entities.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto);
    void deleteComment(Long itemId);
    List<CommentDto> getAllCommentsByItemId(Long id);
    void deleteOneComment(Long commentId);
}
