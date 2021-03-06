package com.cb.framework.interceptor;

import com.cb.framework.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 恶意请求拦截器
 */
public class MaliciousRequestInterceptor extends BaseInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Boolean allRequest = false; // 拦截所有请求,否则拦截相同请求
    private Long minRequestIntervalTime = 1000L; // 允许的最小请求间隔
    private Integer maxMaliciousTimes = 2; // 允许的最大恶意请求次数

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers",
            "x-requested-with,Access-Control-Allow-Origin,EX-SysAuthToken,EX-JSESSIONID");

        HttpSession session = request.getSession();
        String preRequest = (String)session.getAttribute(Constants.PREREQUEST);
        Long preRequestTime = (Long)session.getAttribute(Constants.PREREQUEST_TIME);
        String url = request.getServletPath();
        if (preRequestTime != null && preRequest != null) { // 过滤频繁操作
            if ((url.equals(preRequest) || allRequest)
                && System.currentTimeMillis() - preRequestTime < minRequestIntervalTime) {
                Integer maliciousRequestTimes = (Integer)session.getAttribute(Constants.MALICIOUS_REQUEST_TIMES);
                if (maliciousRequestTimes == null) {
                    maliciousRequestTimes = 1;
                } else {
                    maliciousRequestTimes++;
                }
                session.setAttribute(Constants.MALICIOUS_REQUEST_TIMES, maliciousRequestTimes);
                if (maliciousRequestTimes > maxMaliciousTimes) {
                    response.setStatus(207);
                    logger.warn("拦截了一个恶意请求 : {}", url);
                    return false;
                }
            } else {
                session.setAttribute(Constants.MALICIOUS_REQUEST_TIMES, 0);
            }
        }
        session.setAttribute(Constants.PREREQUEST, url);
        session.setAttribute(Constants.PREREQUEST_TIME, System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    public void setAllRequest(Boolean allRequest) {
        this.allRequest = allRequest;
    }

    public void setMinRequestIntervalTime(Long minRequestIntervalTime) {
        this.minRequestIntervalTime = minRequestIntervalTime;
    }

    public void setMaxMaliciousTimes(Integer maxMaliciousTimes) {
        this.maxMaliciousTimes = maxMaliciousTimes;
    }
}
