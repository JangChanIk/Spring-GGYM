package com.kh.ggym.classes;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/class/*")
public class ClassController {
	Logger logger = LogManager.getLogger(ClassController.class);

	@Autowired
	private ClassLogic classLogic = null;
	
	@GetMapping("scheduleList") //스케줄 리스트
	public String scheduleList(@RequestParam Map<String, Object> pMap) {
		List<Map<String, Object>> scheduleList = null;
		logger.info("scheduleList 호출");
		scheduleList = classLogic.scheduleList(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(scheduleList);
		return result;
	}
	
	@PostMapping("scheduleInsert")
	public int scheduleInsert(@RequestParam Map<String, Object> pMap) {

		logger.info("scheduleInsert 호출");
		int result = 0;
		result = classLogic.scheduleInsert(pMap);

		return result;
	}
	
	@PostMapping("scheduleUpdate")
	public int scheduleUpdate(@RequestParam Map<String, Object> pMap) {
		
		logger.info("scheduleUpdate 호출");
		int result = 0;
		result = classLogic.scheduleUpdate(pMap);
		
		return result;
	}
	
	@PostMapping("scheduleDelete")
	public int scheduleDelete(@RequestParam Map<String, Object> pMap) {
		
		logger.info("scheduleDelete 호출");
		int result = 0;
		result = classLogic.scheduleDelete(pMap);
		
		return result;
	}
	
//	=========================== [schedule 끝] ===========================
	@PostMapping("classList")
	public String classList(@RequestParam Map<String, Object> pMap) {
		List<Map<String, Object>> classList = null;
		logger.info("classList 호출");
		classList = classLogic.classList(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(classList);
		return result;
	}
	
	
	@GetMapping("classListTDetail")
	public String classListTDetail(@RequestParam Map<String, Object> pMap) {
		List<Map<String, Object>> classListTDetail = null;
		logger.info("classListTDetail 호출");
		classListTDetail = classLogic.classListTDetail(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(classListTDetail);
		return result;
	}
	
	@PostMapping("reservationMem")
	public String reservationMem(@RequestParam Map<String, Object> pMap) {
		List<Map<String, Object>> reservationMem = null;
		logger.info("reservationMem 호출");
		reservationMem = classLogic.reservationMem(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(reservationMem);
		return result;
	}
	
	@PostMapping("classInsert")
	public int classInsert(@RequestParam Map<String, Object> pMap) {
		
		logger.info("ordersInsert 호출");
		int result = 0;
		result = classLogic.classInsert(pMap);
		
		return result;
	}
	
	@PostMapping("classUpdate")
	public int classUpdate(@RequestParam Map<String, Object> pMap) {
		
		logger.info("classUpdate 호출");
		int result = 0;
		result = classLogic.classUpdate(pMap);
		
		return result;
	}
	
	@PostMapping("classDelete")
	public int classDelete(@RequestParam Map<String, Object> pMap) {
		
		logger.info("classDelete 호출");
		int result = 0;
		result = classLogic.classDelete(pMap);
		
		return result;
	}	
	
	@GetMapping("teacherSchedule")
	public String teacherSchedule(@RequestParam Map<String, Object> pMap) {
		List<Map<String, Object>> teacherSchedule = null;
		logger.info("teacherSchedule 호출");
		teacherSchedule = classLogic.teacherSchedule(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(teacherSchedule);
		return result;
	}
	
	@GetMapping("gymSchedule")
	public String gymSchedule(@RequestParam Map<String, Object> pMap) {
		List<Map<String, Object>> gymSchedule = null;
		logger.info("gymSchedule 호출");
		gymSchedule = classLogic.gymSchedule(pMap);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(gymSchedule);
		return result;
	}

}
