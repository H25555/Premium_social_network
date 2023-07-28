package com.example.casestudy.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Phương thức này được gọi khi một kết nối WebSocket được thiết lập
        super.afterConnectionEstablished(session);
        System.out.println("Kết nối WebSocket đã được thiết lập.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Phương thức này được gọi khi nhận được một tin nhắn từ client
        String payload = message.getPayload();
        System.out.println("Nhận được tin nhắn từ client: " + payload);

        // Xử lý thông điệp từ client và gửi phản hồi nếu cần
        // Ví dụ: Gửi thông báo trả lời về cho client
        session.sendMessage(new TextMessage("Hello from server!"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Phương thức này được gọi khi một kết nối WebSocket bị đóng
        super.afterConnectionClosed(session, status);
        System.out.println("Kết nối WebSocket đã bị đóng.");
    }
}
