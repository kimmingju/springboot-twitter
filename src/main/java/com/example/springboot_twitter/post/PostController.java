package com.example.springboot_twitter.post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor //생성자 생성 역할
@RestController
public class PostController {

    private final PostRepository postRepository;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/posts") //글쓰기
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post newPost = Post.builder()
                .content(post.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        postRepository.save(newPost);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newPost);
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/api/posts/{id}") //게시글 가져오기
    public Post getPost(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow();
    }

    @PutMapping("/api/posts/{id}") //게시글 생성
    public Post updatePost(@PathVariable Long id, @RequestBody Post postRequest){
        return postRepository.findById(id).map(post->{
            post.updateContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow();
    }

    @DeleteMapping("/api/posts/{id}") //게시글 삭제
    public void deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
    }

    // /api/posts/search?page=1&size=3
    @GetMapping("/api/posts/search") //게시글 검색
    public Slice<Post> searchPosts(
            @RequestParam(required = false) Long lastPostId,
            @RequestParam(defaultValue = "3") int size
    ) {
        int page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        if (lastPostId == null) {
            return postRepository.findSliceBy(pageable);
        } else {
            return postRepository.findSliceByIdLessThan(lastPostId,pageable);
        }
    }
}
