package com.kh.ggym.pass;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassLogic {	
	Logger logger = LogManager.getLogger(PassLogic.class);
	@Autowired
	private PassDao passDao = null;



	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String attendInsert(Map<String, Object> pMap) {
		int result = -1;
		List<Map<String, Object>> list = passDao.myPassList(pMap);
		if(list.get(0) == null) return "-1";
		result = passDao.attendDetail(list.get(0));
		if(result != 0) return "-2";
		result = passDao.attendInsert(list.get(0));
		if(result != 1) return null;
		return list.get(0).get("MEM_NAME").toString();
	}
	
	
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul") 
	public void schedulePassFrozen() { 
		logger.info("이용권 기간 만료"); 
		logger.info(passDao.passFrozen());
	}




}
