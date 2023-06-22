package kz.rakhimov.marketplace_java_final_project.mapper;

import kz.rakhimov.marketplace_java_final_project.entities.Comment;
import kz.rakhimov.marketplace_java_final_project.entities.CommentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto mapToDto(Comment comment);
    List<CommentDto> mapToDtoList(List<Comment> comments);
}
