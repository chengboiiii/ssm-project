package com.cb.framework.activemq.listener;

import com.cb.framework.email.Email;
import com.cb.framework.email.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


/**
 * 发送邮件队列
 */
public class SendEmailListener implements MessageListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void onMessage(Message message) {
		try {
			Email email = (Email) ((ObjectMessage) message).getObject();
			logger.info("将发送邮件至：" + email.getSendTo());
			EmailUtil.sendEmail(email);
		} catch (JMSException e) {
			logger.info(e.getMessage());
		}
	}
}
