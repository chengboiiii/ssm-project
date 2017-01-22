package com.cb.framework.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;
/**
 * 订阅消息发送类
 */
@Service
public class TopicSender {

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的订阅者（目标）
	 * @param topicName 订阅者名称
	 * @param message 消息内容
	 */
	public void send(String topicName, final Serializable message) {
		jmsTemplate.send(topicName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
}
