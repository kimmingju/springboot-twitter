package com.example.springboot_twitter.post;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor //JPA는 기본생성자가 필요함
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void updateContent(String content){
        this.content=content;
    }
    public void increaseCommentCount(){
        this.commentCount++;
    }
    public void decreaseCommentCount(){
        this.commentCount--;
    }
}