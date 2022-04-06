package com.kh.ggym.orders;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDao {
	Logger logger = LogManager.getLogger(OrdersDao.class);	
	private static final String NAMESPACE = "com.kh.phoenix.orders.";
	@Autowired
	private SqlSession sqlSession = null;

	
	
	public List<Map<String, Object>> ordersList(Map<String, Object> pMap) {
		logger.info("ordersList 호출성공");
		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"ordersList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> payList(Map<String, Object> pMap) {
		logger.info("payList 호출성공");
		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"payList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}




	public List<Map<String, Object>> prodList(List<Map<String, Object>> list) {
		logger.info("prodList 호출성공");
		List<Map<String,Object>> prlist = null;
		try {
			prlist = sqlSession.selectList(NAMESPACE+"prodList", list);
			
			logger.info(prlist);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return prlist;
	}

	
	
	
	public List<Map<String, Object>> productList(Map<String, Object> pMap) {
		logger.info("productList 호출성공");
		List<Map<String,Object>> prlist = null;
		try {
			prlist = sqlSession.selectList(NAMESPACE+"productList", pMap);
			
			logger.info(prlist);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return prlist;
	}

	


	public int payNo() {
		logger.info("payNo 호출성공");
		int pay_no = -99;
		try {
			pay_no = sqlSession.selectOne(NAMESPACE+"payNo");
			logger.info(pay_no);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return pay_no;
	}


	
	public int ordersInsert(List<Map<String, Object>> list) {
		logger.info("ordersInsert 호출성공");
		return sqlSession.insert(NAMESPACE+"ordersInsert", list);
	}



	public int payInsert(List<Map<String, Object>> ordersProdList) {
		logger.info("payInsert 호출성공");
		return sqlSession.insert(NAMESPACE+"payInsert",ordersProdList);
	}




	public List<Map<String, Object>> ordersProdList(Map<String, Object> map) {
		logger.info("ordersProdList 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"ordersProdList",map);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}

	
	

	public int ordersUpdate(Map<String, Object> kaMap) {
		logger.info("ordersUpdate 호출성공");
		return sqlSession.update(NAMESPACE+"ordersUpdate",kaMap);
	}




	public int passInsert(List<Map<String, Object>> ordersProdList) {
		logger.info("passInsert 호출성공");
		return sqlSession.insert(NAMESPACE+"passInsert", ordersProdList);
	}



	public int passTransferFrozen(Map<String, Object> pMap) {
		logger.info("passTransferFrozen 호출성공");
		return sqlSession.update(NAMESPACE+"passTransferFrozen", pMap);
	}



	public List<Map<String, Object>> passList(Map<String, Object> pMap) {
		logger.info("passList 호출성공");

		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"passList",pMap);
			logger.info(list);
		} catch (Exception e) {
			logger.info("Exection => "+ e.toString());
		}
		return list;
	}



	public int passTransferInsert(Map<String, Object> pMap) {
		logger.info("passTransferInsert 호출성공");
		return sqlSession.insert(NAMESPACE+"passTransferInsert", pMap);
	}



	public int transferInsert(Map<String, Object> pMap) {
		logger.info("transferInsert 호출성공");
		return sqlSession.insert(NAMESPACE+"transferInsert", pMap);
	}



	
	
}
