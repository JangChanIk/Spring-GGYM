package com.kh.ggym.orders;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/orders/*")
public class OrdersController {
	Logger logger = LogManager.getLogger(OrdersController.class);
	@Autowired
	private OrdersLogic ordersLogic = null;
	
	
	@GetMapping("productList")
	public String productList(@RequestParam Map<String, Object> pMap) {
		logger.info("productList 호출");
		List<Map<String, Object>> productList = null;
		productList = ordersLogic.productList(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(productList);
		return result;
	}
	
	
	
	@GetMapping("payList")
	public String payList(@RequestParam Map<String, Object> pMap) {
		logger.info("payList 호출");
		List<Map<String, Object>> payList = null;
		payList = ordersLogic.payList(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(payList);
		return result;
	}
	
	@GetMapping("passList")
	public String passList(@RequestParam Map<String, Object> pMap) {
		logger.info("passList 호출");
		List<Map<String, Object>> passList = null;
		passList = ordersLogic.passList(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(passList);
		return result;
	}
	
	
	
	
	@PostMapping("payReady") // 카카오 페이 결제 버튼 클릭 시
	public String payReady(@RequestBody List<Map<String, Object>> list) {
		logger.info("payReady 호출");
		logger.info(list); // 파라미터로 넘어온 list를 보여줌(mem_no, prod_no)

		Map<String, Object> orMap = new HashMap<String, Object>();
		
		orMap = ordersLogic.payReady(list);

		logger.info(orMap);

		Gson g = new Gson();
		String result = g.toJson(orMap);
		return result;
	}

	
	

	@PostMapping("paySuccess") // 결제 승인을 기다리는 상태
	public String paySuccess(@RequestBody Map<String, Object> pMap) {
		logger.info("paySuccess 호출");
		logger.info(pMap); 
		try {
			ordersLogic.paySuccess(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson g = new Gson();
		String result = g.toJson(pMap.get("PAY_NO"));
		return result;
	}
	
	

	
	
	
	@PostMapping("transferSuccess") // 결제 승인을 기다리는 상태
	public String transferSuccess(@RequestBody Map<String, Object> pMap) {
		logger.info("paySuccess 호출");
		logger.info(pMap); 
		try {
			ordersLogic.transferSuccess(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson g = new Gson();
		String result = g.toJson(pMap.get("PAY_NO"));
		return result;
	}
	
	
	
	
	
	


}
