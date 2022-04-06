package com.kh.ggym.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	Logger logger = LogManager.getLogger(MemberDao.class);
	private static final String NAMESPACE = "com.kh.phoenix.member.";
	@Autowired
	private SqlSession sqlSession = null;
	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("memberList 호출성공");
		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"memberList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}

	
	public int memberInsert(Map<String, Object> pMap) {	
		logger.info("memberInsert 호출성공");
		int result = -99;
		try {
			result = sqlSession.insert(NAMESPACE+"memberInsert", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}

	public int memberUpdate(Map<String, Object> pMap) {
		logger.info("memberUpdate 호출성공");
		int result = -99;
		try {
			result = sqlSession.update(NAMESPACE+"memberUpdate", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}

	public int memberDelete(Map<String, Object> pMap) {
		logger.info("memberDelete 호출성공");
		int result = -99;
		try {
			result = sqlSession.update(NAMESPACE+"memberDelete", pMap);
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return result;
	}


	
}
