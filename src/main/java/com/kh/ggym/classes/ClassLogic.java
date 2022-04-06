package com.kh.ggym.classes;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassLogic {
	
	Logger logger = LogManager.getLogger(ClassLogic.class);
	
	@Autowired
	private ClassDao classDao = null;
	
	public List<Map<String, Object>> scheduleList(Map<String, Object> pMap) {
		List<Map<String, Object>> list = null;
		list = classDao.scheduleList(pMap);
		return list;
	}
	
	public int scheduleInsert(Map<String, Object> pMap) {
		int result = 0;
		result = classDao.scheduleInsert(pMap);
		return result;
	}
	
	public int scheduleUpdate(Map<String, Object> pMap) {
		int result = 0;
		result = classDao.scheduleUpdate(pMap);
		return result;
	}
	
	public int scheduleDelete(Map<String, Object> pMap) {
		int result = 0;
		result = classDao.scheduleDelete(pMap);
		return result;
	}

	public List<Map<String, Object>> classList(Map<String, Object> pMap) {
		
		List<Map<String, Object>> list = null;
		list = classDao.classList(pMap);
		return list;
	}

	public List<Map<String, Object>> classListTDetail(Map<String, Object> pMap) {
		List<Map<String, Object>> list = null;
		list = classDao.classListTDetail(pMap);
		return list;
	}
	
	public List<Map<String, Object>> reservationMem(Map<String, Object> pMap) {
		List<Map<String, Object>> list = null;
		list = classDao.reservationMem(pMap);
		return list;
	}

	public int classInsert(Map<String, Object> pMap) {
		int result = 0;
		result = classDao.classInsert(pMap);
		return result;
	}

	public int classUpdate(Map<String, Object> pMap) {
		int result = 0;
		result = classDao.classUpdate(pMap);
		return result;
	}

	public int classDelete(Map<String, Object> pMap) {
		int result = 0;
		result = classDao.classDelete(pMap);
		return result;
	}

	public List<Map<String, Object>> teacherSchedule(Map<String, Object> pMap) {
		List<Map<String, Object>> list = null;
		list = classDao.teacherSchedule(pMap);
		return list;
	}

	public List<Map<String, Object>> gymSchedule(Map<String, Object> pMap) {
		List<Map<String, Object>> list = null;
		list = classDao.gymSchedule(pMap);
		return list;
	}

















	

}
