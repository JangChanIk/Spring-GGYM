package com.kh.ggym.pass;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PassDao {

	Logger logger = LogManager.getLogger(PassDao.class);
	
	private static final String NAMESPACE = "com.kh.phoenix.pass.";
	@Autowired
	private SqlSession sqlSession = null;
	
	public List<Map<String, Object>> myPassList(Map<String, Object> pMap) {
		
		logger.info("myPassList 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"myPassList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}

	public int attendInsert(Map<String, Object> pMap) {
		logger.info("attendInsert 호출성공");
		int result = -99;
		
		try {
			result = sqlSession.insert(NAMESPACE+"attendInsert", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}

	public int attendDetail(Map<String, Object> pMap) {
		logger.info("attendDetail 호출성공");

		Map<String, Object> result = null;
		try {
			result = sqlSession.selectOne(NAMESPACE+"attendDetail",pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return Integer.parseInt(result.get("RESULT").toString());
	}

	public int passFrozen() {
		logger.info("passFrozen 호출성공");
		return sqlSession.update(NAMESPACE+"passFrozen");
	}

	


	
}
