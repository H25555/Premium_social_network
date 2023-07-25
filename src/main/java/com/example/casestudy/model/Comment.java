package com.example.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_content_conmment")

    private ContentComment contentComment;

    @ManyToOne
    @JoinColumn(name = "id_post")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_parent_comment")
    @JsonIgnore
    private Comment parentComment;

    private LocalDateTime comment_date;
}
