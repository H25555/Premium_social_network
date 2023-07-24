package com.example.casestudy.repository;

import com.example.casestudy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
