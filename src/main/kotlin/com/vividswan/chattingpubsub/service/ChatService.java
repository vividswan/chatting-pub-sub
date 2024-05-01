package com.vividswan.chattingpubsub.service;

import java.util.Scanner;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService implements MessageListener {

	private final RedisMessageListenerContainer container;

	public void enterChatRoom(String roomName) {
		container.addMessageListener(this, new ChannelTopic(roomName));

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
