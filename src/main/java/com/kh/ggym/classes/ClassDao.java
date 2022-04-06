package com.kh.ggym.classes;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassDao {

	Logger logger = LogManager.getLogger(ClassDao.class);
	
	private static final String NAMESPACE = "com.kh.phoenix.class.";
	@Autowired
	private SqlSession sqlSession = null;
	
	
	public List<Map<String, Object>> scheduleList(Map<String, Object> pMap) {
		logger.info("scheduleList 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"scheduleList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}
	
	public int scheduleInsert(Map<String, Object> pMap) {
		logger.info("scheduleInsert 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.insert(NAMESPACE+"scheduleInsert", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}
	
	public int scheduleUpdate(Map<String, Object> pMap) {
		logger.info("scheduleUpdate 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.update(NAMESPACE+"scheduleUpdate", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}
	
	public int scheduleDelete(Map<String, Object> pMap) {
		logger.info("scheduleDelete 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.delete(NAMESPACE+"scheduleDelete", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}
	
	public List<Map<String, Object>> classList(Map<String, Object> pMap) {
		
		logger.info("classList 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"classList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}

	public List<Map<String, Object>> classListTDetail(Map<String, Object> pMap) {
		logger.info("classListTDetail 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"classListTDetail",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}
	
	public List<Map<String, Object>> reservationMem(Map<String, Object> pMap) {
		logger.info("reservationMem 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"reservationMem",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}
	
	public int classInsert(Map<String, Object> pMap) {
		
		logger.info("classInsert 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.insert(NAMESPACE+"classInsert", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}

	public int classUpdate(Map<String, Object> pMap) {
		logger.info("classUpdate 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.update(NAMESPACE+"classUpdate", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}

	public int classDelete(Map<String, Object> pMap) {
		logger.info("classDelete 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.delete(NAMESPACE+"classDelete", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}


	public List<Map<String, Object>> teacherSchedule(Map<String, Object> pMap) {
		logger.info("teacherSchedule 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"teacherSchedule",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}

	public List<Map<String, Object>> gymSchedule(Map<String, Object> pMap) {
		logger.info("gymSchedule 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"gymSchedule",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}
















	
}
