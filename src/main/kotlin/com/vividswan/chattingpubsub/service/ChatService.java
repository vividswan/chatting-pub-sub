package com.vividswan.chattingpubsub.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("message: " + message.toString());
	}
}
