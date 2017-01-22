package com.cb.controller;

import com.alibaba.fastjson.JSON;
import com.cb.framework.activemq.QueueSender;
import com.cb.framework.email.Email;
import com.cb.model.HRJob;
import com.cb.mybatis.service.HrJobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping(value = "/hrjob")
public class HrJobController {

    @Resource
    private HrJobService hrjobService;
    @Resource
    private QueueSender queueSender;

    @RequestMapping("getHrJob.do")
    public String getHrJob(){
        PageHelper.startPage(1,10);
        List<HRJob> list = hrjobService.getHrJob();
        PageInfo<HRJob> pageInfo = new PageInfo<HRJob>(list);
        System.out.print(JSON.toJSON(pageInfo));
        Email email = new Email();
        email.setSendTo("2582949813@qq.com");
        email.setBody("<p>哈哈哈</P>");
        email.setTopic("测试");
        queueSender.send("emailSender",email);
        return "index";
    }
}
