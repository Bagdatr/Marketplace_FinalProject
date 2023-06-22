package kz.rakhimov.marketplace_java_final_project.controllers;

import kz.rakhimov.marketplace_java_final_project.entities.CommentDto;
import kz.rakhimov.marketplace_java_final_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value="/{id}")
    public List<CommentDto> commentList(@PathVariable("id") Long id){
        return commentService.getAllCommentsByItemId(id);
    }

    @PostMapping
    public CommentDto addCommentPost(@RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto);
    }

    @DeleteMapping(value="/{id}")
    void deleteComment(@PathVariable("id") Long itemId){
        commentService.deleteComment(itemId);
    }

    @DeleteMapping(value="/exact/{id}")
    void deleteOneComment(@PathVariable("id") Long commentId){
        commentService.deleteOneComment(commentId);
    }
}
