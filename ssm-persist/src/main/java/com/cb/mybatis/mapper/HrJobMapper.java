package com.cb.mybatis.mapper;

import com.cb.model.HRJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrJobMapper {
	List<HRJob> getHrJob();
}
