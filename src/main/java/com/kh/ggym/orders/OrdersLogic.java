package com.kh.ggym.orders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kh.ggym.board.BoardDao;
@PropertySource("classpath:/application.properties")
@Service
public class OrdersLogic {
	Logger logger = LogManager.getLogger(OrdersLogic.class);	
	@Autowired
	private OrdersDao ordersDao = null;	
	@Autowired
	private BoardDao boardDao = null;	
	
	@Value("${spring.kakao.adminkey}")
	private String adminkey;


	

	public String addDate(String dt, int m) { 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar cal = Calendar.getInstance(); 
		Date date;
		try {
			date = format.parse(dt);
			cal.setTime(date); 
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		cal.add(Calendar.YEAR, 0); 
		cal.add(Calendar.MONTH, m); 
		cal.add(Calendar.DATE, 0); 

		return format.format(cal.getTime()); 
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> kakaoPayReady(List<Map<String, Object>> list, List<Map<String, Object>> prlist, int pay_no) {

		RestTemplate restTemplate = new RestTemplate(); //통신 템플릿 만들기
		Map<String, Object> kaMap = new HashMap<String, Object>(); //값을 받을 MAP
		StringBuffer totalName = new StringBuffer();
		String mem_no = list.get(0).get("MEM_NO").toString();
		int totalPrice = 0; //상품의 총 값
		int totalCnt = prlist.size(); //상품의 총 갯수

		for (int i = 0; i < prlist.size(); i++) {
			if (totalName.length() != 0) { 
				totalName.append(", ");
			}
			totalName.append(prlist.get(i).get("PROD_NAME").toString()); // 상품 이름들 합쳐주기
			totalPrice += (Integer.parseInt(prlist.get(i).get("PROD_PRICE").toString())); // 상품 가격들 합쳐주기
		}

		logger.info(pay_no + "/" + mem_no + "/" + totalName + "/" + totalCnt + "/" + totalPrice);

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + adminkey);
		// headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 서버로 요청할 Body
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		params.add("cid", "TC0ONETIME"); //가맹점 번호
		params.add("partner_order_id", pay_no);
		params.add("partner_user_id", mem_no);
		params.add("item_name", totalName);
		params.add("quantity", totalCnt);
		params.add("total_amount", totalPrice);
		params.add("tax_free_amount", 0); //부가세
		if(prlist.get(0).get("PROD_TYPE").toString().equals("2")) {
			params.add("approval_url", "http://ggym.asuscomm.com:8000/pay/transferSuccess"); // 결제 성공시						
		}else {
			params.add("approval_url", "http://ggym.asuscomm.com:8000/pay/passSuccess"); // 결제 성공시			
		}
		params.add("fail_url", "http://ggym.asuscomm.com:8000/pay/fail"); // 결제 실패 시
		params.add("cancel_url", "http://ggym.asuscomm.com:8000/cancel"); //결제 취소 시
		
		try {
			HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
			// 담은 정보를 보내기
			kaMap = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/ready", body, Map.class);
			logger.info(kaMap);	
			return kaMap;
		} catch (RestClientException e) {
			logger.info(e);
        } catch (Exception e) {
			logger.info(e);
		} 
		
		return null; //실패시 null 반환
	}
	
	
    @SuppressWarnings("unchecked")
	public Map<String, Object> kakaoPayInfo(Map<String, Object> map) {
    	
    	Map<String, Object> kaMap = new HashMap<String, Object>();
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + adminkey);
		// headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", map.get("TID"));
        params.add("partner_order_id", map.get("PAY_NO"));
        params.add("partner_user_id", map.get("MEM_NO"));
        params.add("pg_token", map.get("PG_TOKEN"));
        
        HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        
        try {
        	kaMap = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/approve", body, Map.class);
            logger.info(kaMap);
            
    		String type = kaMap.get("payment_method_type").toString();
    		if(type.equals("MONEY")) {
    			kaMap.put("PAY_TYPE", 0);
    		}else if(type.equals("CARD")) {
    			kaMap.put("PAY_TYPE", 1);
    		}
          
            return kaMap;
        
        } catch (RestClientException e) {
            logger.info(e);
        } catch (Exception e) {
        	logger.info(e);
        }
        
        return null;
    }
    
    
    @SuppressWarnings("unchecked")
	private Map<String, Object> kakaoPayCancel(Map<String, Object> pMap) {
	   	Map<String, Object> kaMap = new HashMap<String, Object>();
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + adminkey);
		// headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", pMap.get("TID"));
        params.add("cancel_amount", pMap.get("amount"));
        params.add("cancel_tax_free_amount", 0);
        
        HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        
        try {
        	kaMap = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/cancel", body, Map.class);
            logger.info(kaMap);
            
            return kaMap;
        
        } catch (RestClientException e) {
            logger.info(e);
        } catch (Exception e) {
        	logger.info(e);
        }
        
        return null;

		
	}
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Map<String, Object> payReady(List<Map<String, Object>> list) {
		
		Map<String, Object> orMap = new HashMap<String, Object>();
		Map<String, Object> kaMap = null;
		List<Map<String, Object>> ordersList = null;
		List<Map<String, Object>> prlist = null;
		int pay_no = 0;
		
		prlist = ordersDao.prodList(list); // 상품 조회
		pay_no = ordersDao.payNo(); // 결제 번호를 미리 받아오기

		
		kaMap = kakaoPayReady(list, prlist, pay_no); // 카카오 쪽에 정보 넘겨주기
		
		if(kaMap==null) return null;

		for (int i = 0; i < list.size(); i++) { // 결제 후 승인 시 tid가 필요함
			list.get(i).put("TID", kaMap.get("tid"));
		}

		ordersDao.ordersInsert(list); // orders에 기록 남기기
		ordersList = ordersDao.ordersList(list.get(0)); // orderList 한 번 더 불러오기 -> 주문 번호를 가져오기 위해서
		
		orMap.put("ORDER_NO", ordersList.get(0).get("ORDER_NO"));
		orMap.put("PAY_NO", pay_no);
		orMap.put("NEXT_REDIRECT_PC_URL", kaMap.get("next_redirect_pc_url"));
		return orMap;
	}

	
	
	

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void paySuccess(Map<String, Object> pMap) throws Exception {
		List<Map<String, Object>> ordersProdList = null;
		Map<String, Object> kaMap = null;
			
			ordersProdList = ordersDao.ordersProdList(pMap);
			
			pMap.put("TID", ordersProdList.get(0).get("ORDER_TID")); // tid를 추가
			
			kaMap = kakaoPayInfo(pMap);
			if(kaMap==null) return;
		
		try {
				
			for (Map<String, Object> tMap : ordersProdList) {
				tMap.put("PAY_NO", pMap.get("PAY_NO"));
				tMap.put("PAY_TYPE", kaMap.get("PAY_TYPE"));
				tMap.put("PASS_STATUS", 0);
				
				if ((tMap.get("PROD_TYPE").toString()).equals("0")) {
					tMap.put("PASS_SDAY", pMap.get("PASS_SDAY"));
					tMap.put("PASS_EDAY", addDate(pMap.get("PASS_SDAY").toString(), 
							Integer.parseInt(tMap.get("PROD_AMOUNT").toString())));
					
				} else if ((tMap.get("PROD_TYPE").toString()).equals("1")) {
					tMap.put("PASS_SDAY", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					tMap.put("PASS_EDAY", addDate(tMap.get("PASS_SDAY").toString(),
							2 + (Integer.parseInt(tMap.get("PROD_AMOUNT").toString()) / 10)));
				} 
			}
			logger.info(ordersProdList);
			
			ordersDao.payInsert(ordersProdList);
			ordersDao.ordersUpdate(kaMap);
			ordersDao.passInsert(ordersProdList);
		} catch (Exception e) {
			logger.info("오류로 인한 결제취소 : "+e);
			pMap.put("amount", kaMap.get("amount").toString().split(",")[0].split("=")[1]);
			kakaoPayCancel(pMap);
			throw new Exception();
		}

	}


	
	
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void transferSuccess(Map<String, Object> pMap) throws Exception {
		List<Map<String, Object>> ordersProdList = null;
		Map<String, Object> kaMap = null;
		
		ordersProdList = ordersDao.ordersProdList(pMap);
		
		pMap.put("TID", ordersProdList.get(0).get("ORDER_TID")); // tid를 추가
		
		kaMap = kakaoPayInfo(pMap);
		if(kaMap==null) return;
		
		try {
			ordersProdList.get(0).put("PAY_NO", pMap.get("PAY_NO"));
			ordersProdList.get(0).put("PAY_TYPE", kaMap.get("PAY_TYPE"));
			pMap.put("TRANSB_STATUS", true);
			boardDao.transUpdate(pMap);
			ordersDao.payInsert(ordersProdList);
			ordersDao.ordersUpdate(kaMap);
			
			List<Map<String,Object>> passList = ordersDao.passList(pMap);
			ordersDao.passTransferFrozen(pMap);
			pMap.put("PASS_NO", passList.get(0).get("PASS_NO"));
			pMap.put("PASS_NO", passList.get(0).get("PASS_NO"));
			pMap.put("PASS_SDAY", passList.get(0).get("PASS_SDAY"));
			pMap.put("PASS_EDAY", passList.get(0).get("PASS_EDAY"));
			pMap.put("PASS_CNT", passList.get(0).get("PASS_CNT"));
			pMap.put("PASS_TYPE", passList.get(0).get("PASS_TYPE"));
			
			ordersDao.passTransferInsert(pMap);
			ordersDao.transferInsert(pMap);
		

			logger.info(pMap);
			
		} catch (Exception e) {
			logger.info("오류로 인한 결제취소 : "+e);
			pMap.put("amount", kaMap.get("amount").toString().split(",")[0].split("=")[1]);
			logger.info(pMap);
			kakaoPayCancel(pMap);
			throw new Exception();
		}
		
	}
	
	


	public List<Map<String, Object>> productList(Map<String, Object> pMap) {
		return ordersDao.productList(pMap);
	}


	public List<Map<String, Object>> payList(Map<String, Object> pMap) {
		return ordersDao.payList(pMap);
	}
	
	public List<Map<String, Object>> passList(Map<String, Object> pMap) {
		return ordersDao.passList(pMap);
	}
	
	
	
	
	

}
