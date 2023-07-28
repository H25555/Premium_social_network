package com.example.casestudy.model;

import com.example.casestudy.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "interact_user")
    private User interactUser;
    @ManyToOne
    @JoinColumn(name = "receive_user")
    private User receiveUser;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private String content;

}
