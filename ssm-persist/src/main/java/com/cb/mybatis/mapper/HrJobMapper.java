package com.cb.mybatis.mapper;

import com.cb.model.HRJob;
import org.springframework.stereotype.Repository;

@Repository
public interface HrJobMapper {
	HRJob getHrJob(int sn);
}
