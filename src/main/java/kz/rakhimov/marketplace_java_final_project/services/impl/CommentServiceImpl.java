package kz.rakhimov.marketplace_java_final_project.services.impl;

import kz.rakhimov.marketplace_java_final_project.entities.Comment;
import kz.rakhimov.marketplace_java_final_project.entities.CommentDto;
import kz.rakhimov.marketplace_java_final_project.mapper.CommentMapper;
import kz.rakhimov.marketplace_java_final_project.repositories.CommentRepository;
import kz.rakhimov.marketplace_java_final_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        LocalDate localDate = LocalDate.now();
        comment.setDate(localDate);
        comment.setText(commentDto.getText());
        comment.setItem(commentDto.getItem());
        return commentMapper.mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long itemId) {
        commentRepository.deleteByItemId(itemId);
    }

    @Override
    public List<CommentDto> getAllCommentsByItemId(Long id) {
        return commentMapper.mapToDtoList(commentRepository.findAllByItemId(id));
    }

    @Override
    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
