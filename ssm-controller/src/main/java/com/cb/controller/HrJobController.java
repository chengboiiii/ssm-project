package com.cb.controller;

import com.cb.model.HRJob;
import com.cb.mybatis.service.HrJobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/hrjob")
public class HrJobController {

    @Resource
    private HrJobService hrjobService;

    @RequestMapping("getHrJob.do")
    public String getHrJob(){
        int sn = 10;
        HRJob hrJob = hrjobService.getHrJob(sn);
        System.out.print(hrJob.getCity());
        return "index";
    }
}
