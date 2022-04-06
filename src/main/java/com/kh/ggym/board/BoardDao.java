package com.kh.ggym.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	Logger logger = LogManager.getLogger(BoardDao.class);
	private static final String NAMESPACE = "com.kh.phoenix.board.";
	@Autowired
	private SqlSession sqlSession = null;
	
	
	//=====================================================================

	public int fileInsert(Map<String, Object> pMap) {
		logger.info("fileInsert 호출성공");
		int result = 0;
		result = sqlSession.insert(NAMESPACE + "fileInsert", pMap);
		logger.info(result);
		return result;
	}

	public int fileUpdate(List<Map<String, Object>> pList) {
		logger.info("fileUpdate 호출성공");
		logger.info(pList);
		int result = 0;
		result = sqlSession.update(NAMESPACE + "fileUpdate", pList);
		logger.info(result);
		return result;
	}

	
	public List<Map<String, Object>> fileList(Map<String, Object> pMap) {
		logger.info("fileList 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "fileList", pMap);
		logger.info(list);
		return list;
	}


	public int fileDelete(Map<String, Object> map) {
		logger.info("fileDelete 호출성공");
		int result = 0;
		result = sqlSession.update(NAMESPACE + "fileDelete", map);
		logger.info(result);
		return result;
	}

	//=================================================================================
	
	

	public int commentInsert(Map<String, Object> pMap) {
		logger.info("commentInsert 호출성공");
		int result = -99;
		result = sqlSession.insert(NAMESPACE+"commentInsert", pMap);
		logger.info(result);
		return result;
	}

	public int commentUpdate(Map<String, Object> pMap) {
		logger.info("commentUpdate 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"commentUpdate", pMap);
		logger.info(result);
		return result;
	}

	public int commentDelete(Map<String, Object> pMap) {
		logger.info("commentDelete 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"commentDelete", pMap);
		logger.info(result);
		return result;
	}

	public List<Map<String, Object>> commentList(Map<String, Object> pMap) {
		logger.info("commentList 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE +"commentList", pMap);			
		logger.info(list);	
		return list;
	}
	
	


	//=================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void boardHit(Map<String, Object> pMap) {
		logger.info("boardHit 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE + "boardHit", pMap);
		logger.info(result);

	}
	
	public Map<String, Object> getRowNum(Map<String, Object> pMap) {
		logger.info("getRowNum 호출성공");
		Map<String, Object> row = null;
		row = sqlSession.selectOne(NAMESPACE + "getRowNum", pMap);
		logger.info(row);
		return row;
	}

	public int boardDelete(Map<String, Object> map) {
		logger.info("boardDelete 호출성공");
		int result = 0;
		result = sqlSession.update(NAMESPACE + "boardDelete", map);
		logger.info(result);
		return result;
	}
	
	
	public List<Map<String, Object>> masterList(Map<String, Object> pMap) {
		logger.info("masterList 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "masterList", pMap);
		logger.info(list);
		return list;
	}

	
	public int masterInsert(Map<String, Object> pMap) {
		logger.info("masterInsert 호출성공");
		int result = -99;
		result = sqlSession.insert(NAMESPACE + "masterInsert", pMap);
		logger.info(result);
		return result;
	}
	
	
	public List<Map<String, Object>> masterDetail(Map<String, Object> pMap) {
		logger.info("masterDetail 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "masterDetail", pMap);			
		logger.info(list);	
		return list;
	}
	

	public int masterUpdate(Map<String, Object> pMap) {
		logger.info("masterUpdate 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"masterUpdate", pMap);
		logger.info(result);
		return result;
	}



	public List<Map<String, Object>> qnaList(Map<String, Object> pMap) {
		logger.info("qnaList 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "qnaList", pMap);
		logger.info(list);
		return list;
	}

	public int qnaInsert(Map<String, Object> pMap) {
		logger.info("qnaInsert 호출성공");
		int result = -99;
		result = sqlSession.insert(NAMESPACE + "qnaInsert", pMap);
		logger.info(result);
		return result;
	}

	public List<Map<String, Object>> qnaDetail(Map<String, Object> pMap) {
		logger.info("qnaDetail 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "qnaDetail", pMap);			
		logger.info(list);	
		return list;
	}

	public int qnaUpdate(Map<String, Object> pMap) {
		logger.info("qnaUpdate 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"qnaUpdate", pMap);
		logger.info(result);
		return result;
	}

	
	
	public List<Map<String, Object>> transList(Map<String, Object> pMap) {
		logger.info("transList 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "transList", pMap);
		logger.info(list);
		return list;
	}

	public int transInsert(Map<String, Object> pMap) {
		logger.info("transInsert 호출성공");
		int result = -99;
		result = sqlSession.insert(NAMESPACE + "transInsert", pMap);
		logger.info(result);
		return result;
	}

	public List<Map<String, Object>> transDetail(Map<String, Object> pMap) {
		logger.info("transDetail 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "transDetail", pMap);			
		logger.info(list);	
		return list;
	}

	public int transUpdate(Map<String, Object> pMap) {
		logger.info("transUpdate 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"transUpdate", pMap);
		logger.info(result);
		return result;
	}

	public int transferPass(Map<String, Object> pMap) {
		logger.info("transferPass 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"transferPass", pMap);
		logger.info(result);
		return result;
	}

	public List<Map<String, Object>> reviewList(Map<String, Object> pMap) {
		logger.info("reviewList 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "reviewList", pMap);
		logger.info(list);
		return list;
	}

	public int reviewInsert(Map<String, Object> pMap) {
		logger.info("reviewInsert 호출성공");
		int result = -99;
		result = sqlSession.insert(NAMESPACE + "reviewInsert", pMap);
		logger.info(result);
		return result;
	}

	public List<Map<String, Object>> reviewDetail(Map<String, Object> pMap) {
		logger.info("reviewDetail 호출성공");
		List<Map<String, Object>> list = null;
		list = sqlSession.selectList(NAMESPACE + "reviewDetail", pMap);			
		logger.info(list);	
		return list;
	}

	public int reviewUpdate(Map<String, Object> pMap) {
		logger.info("reviewUpdate 호출성공");
		int result = -99;
		result = sqlSession.update(NAMESPACE+"reviewUpdate", pMap);
		logger.info(result);
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
}
