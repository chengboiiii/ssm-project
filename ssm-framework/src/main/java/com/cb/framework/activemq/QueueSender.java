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
 * 队列消息发送类
 */
@Service
public class QueueSender {

	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的队列（目标）.
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(String queueName, final Serializable message) {
		jmsTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
}
