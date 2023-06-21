package com.thismsmemukul.blogappapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private UUID categoryId;
    @Column(name = "category_title", nullable = false, length = 40)
    private String categoryTitle;
    @Column(name = "category_description", nullable = false, length = 100)
    private String categoryDescription;
}
