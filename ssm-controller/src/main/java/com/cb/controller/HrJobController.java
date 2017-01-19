package com.cb.controller;

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

    @RequestMapping("getHrJob.do")
    public String getHrJob(){
        PageHelper.startPage(1,10);
        List<HRJob> list = hrjobService.getHrJob();
        PageInfo<HRJob> pageInfo = new PageInfo<HRJob>(list);
        System.out.print(list.size());
        return "index";
    }
}
