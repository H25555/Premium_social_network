package com.example.casestudy.repository;

import com.example.casestudy.model.Like;
import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {
    public Like findByUserAndPost(User user,Post post);
    public int countLikeByPost(Post post);
}
