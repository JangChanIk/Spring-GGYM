package com.kh.ggym.member;


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
@RequestMapping("/member/*")
public class MemberController {

	Logger logger = LogManager.getLogger(MemberController.class);

	@Autowired
	private MemberLogic memberLogic = null;

	@GetMapping("memberList")
	public String memberList(@RequestParam Map<String, Object> pMap) {
		logger.info("memberList 호출");
		logger.info(pMap);		
		Object obj = memberLogic.memberList(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(obj);			
		return result;
	}
	
	

	@PostMapping("memberInsert")
	public int memberInsert(@RequestBody Map<String, Object> pMap) {
		logger.info("memberInsert 호출");
		logger.info(pMap);		
		int result = 0;
		result = memberLogic.memberInsert(pMap);
		return result;
	}
	
	

	
	@PostMapping("memberUpdate")
	public int memberUpdate(@RequestBody Map<String, Object> pMap) {
		logger.info("memberUpdate 호출");
		logger.info(pMap);		
		int result = 0;
		result = memberLogic.memberUpdate(pMap);	
		return result;
	}
	
	@PostMapping("memberDelete")
	public int memberDelete(@RequestBody Map<String, Object> pMap) {	
		logger.info("memberDelete 호출");
		logger.info(pMap);		
		int result = 0;
		result = memberLogic.memberDelete(pMap);
		return result;
	}
}
