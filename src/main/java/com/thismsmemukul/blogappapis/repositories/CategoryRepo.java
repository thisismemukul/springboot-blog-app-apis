package com.thismsmemukul.blogappapis.repositories;

import com.thismsmemukul.blogappapis.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {

}
