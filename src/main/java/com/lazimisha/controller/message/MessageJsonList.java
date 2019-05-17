package com.lazimisha.controller.message;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MessageJsonList {

	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
