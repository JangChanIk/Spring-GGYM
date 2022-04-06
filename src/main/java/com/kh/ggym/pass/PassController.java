package com.kh.ggym.pass;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pass/*")
public class PassController {
	Logger logger = LogManager.getLogger(PassController.class);

	@Autowired
	private PassLogic passLogic = null;


	@PostMapping("attendInsert")
	public String attendInsert(@RequestBody Map<String, Object> pMap) {
		
		logger.info("attendInsert 호출");
		String result = null;
		result = passLogic.attendInsert(pMap);
		
		return result;
	}

	

}
