package com.vividswan.chattingpubsub.service;

import java.util.Scanner;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements MessageListener {

	public void enterChatRoom(String roomName) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.equals("q")) {
				System.out.println("Quit");
				break;
			}
		}
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("message: " + message.toString());
	}
}
