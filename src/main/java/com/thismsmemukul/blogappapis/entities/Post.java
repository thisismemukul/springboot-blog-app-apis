package com.thismsmemukul.blogappapis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "post_title", nullable = false, length = 100)
    @NotBlank(message = "*Please provide title of your post")
    private String title;

    @Column(name = "post_content", nullable = false)
    @NotBlank(message = "*Please provide some content")
    private String content;

    private String imageName;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
