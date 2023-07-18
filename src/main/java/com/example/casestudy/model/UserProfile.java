package com.example.casestudy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor

public class UserProfile {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fullName;

    @OneToMany
    @JoinColumn(name = "id_post")
    private List<Post> posts;

    private String introduce;

    private String avatar;



}
