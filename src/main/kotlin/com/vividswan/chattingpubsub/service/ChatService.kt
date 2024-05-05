package com.vividswan.chattingpubsub.service

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service
import java.util.Scanner

@Service
class ChatService(
    private val container: RedisMessageListenerContainer,
    private val redisTemplate: RedisTemplate<String, String>
) : MessageListener {

    fun enterChatRoom(roomName: String) {
        container.addMessageListener(this, ChannelTopic(roomName))
        val scanner = Scanner(System.`in`)

        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            if (line == "q") {
                println("Quit")
                break
            }
            redisTemplate.convertAndSend(roomName, line)
        }
        container.removeMessageListener(this)
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        println("message: ${message.toString()}")
    }
}
