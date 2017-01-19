package com.cb.mybatis.service;

import com.cb.model.HRJob;
import com.cb.mybatis.mapper.HrJobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HrJobService {

	@Resource
	private HrJobMapper hrJobMapper;

	public List<HRJob> getHrJob(){
		return hrJobMapper.getHrJob();
	}
}
