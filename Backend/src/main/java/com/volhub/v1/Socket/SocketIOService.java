package com.volhub.v1.Socket;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;

@Service
public class SocketIOService {
    private final SocketIOServer server;

    private final ConcurrentHashMap<String, String> userSocketMap; // Maps socket ID to user ID
    private final ConcurrentHashMap<String, String> socketUserMap;

    // @Autowired
    public SocketIOService(SocketIOServer server) {
        this.server = server;
        this.userSocketMap = new ConcurrentHashMap<>();
        this.socketUserMap = new ConcurrentHashMap<>();
    }

    public SocketIOServer getServer() {
        return server;
    }

    public void tes() {
        System.out.println("test");
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("Client connected" + client.getSessionId());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("Client disconnected" + client.getSessionId() + "kl "
                + getUserIdBySocketId(client.getSessionId().toString()));
        userSocketMap.remove(client.getSessionId().toString());
    }

    @OnEvent("user_online")
    public void onUserOnline(SocketIOClient client, String data) {
        System.out.println("User online: " + data);
        String userId = data;
        String socketId = client.getSessionId().toString();
        if (userId != null && !userId.isEmpty()) {
            // Associate the client's socket ID with the user ID
            userSocketMap.put(socketId, userId);

            socketUserMap.put(userId, socketId);

            System.out.println("Associated socket ID " + client.getSessionId() + " with user ID " + userId);
        } else {
            System.out.println("User ID not found in the data");
        }

    }

    @OnEvent("msg-send")
    public void sendmsg(SocketIOClient client, String userId) {
        System.out.println("Received message for user ID: " + userId);
        String socketId = getSocketIdByUserId(userId);
        System.out.println("Socket ID for user ID " + userId + ": " + socketId);
        if (socketId != null && !socketId.isEmpty()) {
            SocketIOClient recipientClient = server.getClient(java.util.UUID.fromString(socketId));
            
            if (recipientClient != null) {
                recipientClient.sendEvent("msg-receive", "Your message content here");
                System.out.println("Message sent to user ID " + userId);
            } else {
                System.out.println("No active client found for socket ID " + socketId);
            }
        } else {
            System.out.println("Socket ID not found for user ID " + userId);
        }
    }

    // Method to get socket ID using user ID
    public String getSocketIdByUserId(String userId) {
        return socketUserMap.get(userId);
    }

    // method to get useerid using socket id
    public String getUserIdBySocketId(String userId) {
        return userSocketMap.get(userId);
    }

}
