package com.example.springboot_twitter.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CommentController 사용자의 HTTP 요청을 받는 입구

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public CommentResponse createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest request
    ){
       return commentService.createComment(postId,request);
    }

    @GetMapping("/api/posts/{postId}/comments")
    public List<CommentResponse> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @PutMapping("/api/posts/{postId}/comments/{commentId}")
    public CommentResponse updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request
    ){
        return commentService.updateComment(postId,commentId,request);
    }

    @DeleteMapping("/api/posts/{postId}/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        commentService.deleteComment(postId,commentId);
    }

    @GetMapping("/api/comments")
    public List<CommentResponse> getAllComments(){
        return commentService.getAllComments();
    }
}
