package com.volhub.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.volhub.v1.Socket.SocketIOService;

@SpringBootApplication
public class V1Application implements CommandLineRunner {

	@Autowired
	private SocketIOServer socketIOServer;

	@Autowired
	private SocketIOService socketIOService;

	public static void main(String[] args) {
		SpringApplication.run(V1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		socketIOServer.start();

		socketIOServer.addListeners(socketIOService);

		// socketIOServer.addConnectListener(new ConnectListener() {
		// 	@Override
		// 	public void onConnect(SocketIOClient client) {

		// 		System.out.println("new user connected with socket " + client.getSessionId());
		// 	}
		// });

		// socketIOServer.addDisconnectListener(new DisconnectListener() {
		// 	@Override
		// 	public void onDisconnect(SocketIOClient client) {

		// 		System.out.println("user disconnected " + client.getSessionId());

		// 	}
		// });

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			socketIOServer.stop();
			System.out.println("Socket.IO Server stopped");
		}));
	}

}
