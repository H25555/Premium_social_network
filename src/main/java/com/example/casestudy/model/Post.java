package com.example.casestudy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Content content;
    @ColumnDefault("0")
    private Integer like_count;
    private LocalDateTime create_date;

}
