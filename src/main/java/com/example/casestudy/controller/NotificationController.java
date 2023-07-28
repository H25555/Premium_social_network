package com.example.casestudy.controller;

import com.example.casestudy.model.Notification;
import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import com.example.casestudy.model.enums.NotificationType;
import com.example.casestudy.repository.NotificationRepository;
import com.example.casestudy.repository.PostRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class NotificationController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final SimpMessagingTemplate messagingTemplate;

    private final NotificationRepository notificationRepository;






    @MessageMapping("/like/{postId}")
    public void handleLike(@DestinationVariable String postId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userInteract = userRepository.findByUserName(userDetails.getUsername());
        Long postIdCv = Long.valueOf(postId);
        Post post = postRepository.findById(postIdCv).get();
        User userReceive = post.getUser();
        System.out.println("handleLike ==============");
        // Xử lý sự kiện "like" ở đây
        // Ví dụ: Thêm thông báo "Người dùng đã thích bài viết của bạn" vào cơ sở dữ liệu

        // Sau đó, gửi thông báo tới người đăng bài viết
        String notificationMessage = "Người dùng đã thích bài viết của bạn";
        Notification notification = new Notification();
        notification.setContent(notificationMessage);
        notification.setNotificationType(NotificationType.LIKED);
        notification.setInteractUser(userInteract);
        messagingTemplate.convertAndSendToUser(userReceive.getUserName(), "/queue/notifications", notification, createHeaders(userReceive.getUserName()));
    }
    private MessageHeaders createHeaders(String username) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(username);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
