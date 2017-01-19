package com.cb.framework.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by chengbo on 2017/1/19.
 */
public class SessionListener implements HttpSessionListener{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sessionCreated(HttpSessionEvent event){
        HttpSession session = event.getSession();
        System.out.print("创建了一个Session连接:[" + session.getId() + "]");
        logger.info("创建了一个Session连接:[" + session.getId() + "]");
        setAllUserNumber(1);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        System.out.print("销毁了一个Session连接:[" + session.getId() + "]");
        logger.info("销毁了一个Session连接:[" + session.getId() + "]");
        //if (getAllUserNumber() > 0) {
        //   logger.info("销毁了一个Session连接:[" + session.getId() + "]");
        //}
        //session.removeAttribute(Constants.CURRENT_USER);
        //sessionService.deleteBySessionId(session.getId());
        //setAllUserNumber(-1);
    }

    private void setAllUserNumber(int n) {
        Long number = getAllUserNumber() + n;
        if (number >= 0) {
            logger.info("用户数：" + number);
            //JedisUtil.set(Constants.ALLUSER_NUMBER, 60 * 60 * 24, number);
        }
    }

    /** 获取在线用户数量 */
    public static Long getAllUserNumber() {
        //String v = JedisUtil.get(Constants.ALLUSER_NUMBER);
        //if (v != null) {
        //    return Long.valueOf(v);
        //}
        return 0L;
    }
}
