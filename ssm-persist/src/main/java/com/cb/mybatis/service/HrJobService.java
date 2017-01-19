package com.cb.mybatis.service;

import com.cb.model.HRJob;
import com.cb.mybatis.mapper.HrJobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HrJobService {

	@Resource
	private HrJobMapper hrJobMapper;

	public HRJob getHrJob(int sn){
		return hrJobMapper.getHrJob(sn);
	}
}
