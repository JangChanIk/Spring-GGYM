package com.kh.ggym.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLogic {	
	Logger logger = LogManager.getLogger(MemberLogic.class);	
	@Autowired
	private MemberDao memberDao = null;
	
	public Object memberList(Map<String, Object> pMap) {		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		list = memberDao.memberList(pMap);
		Object obj = null;
		if(pMap.containsKey("type")){
			if(pMap.get("type").equals("overlap")) {
				obj = list.size();
			} else if(pMap.get("type").equals("auth")&&list.size()!=0) {
				map.put("MEM_AUTH",list.get(0).get("MEM_AUTH"));
				map.put("MEM_STATUS",list.get(0).get("MEM_STATUS"));
				map.put("MEM_NICKNAME",list.get(0).get("MEM_NICKNAME"));
				map.put("MEM_NO",list.get(0).get("MEM_NO"));
				obj = map;
			} else if(pMap.get("type").equals("email")&&list.size()!=0) {
				for(int i=0; i < list.size(); i++) {
					map.put("MEM_EMAIL-"+i,list.get(i).get("MEM_EMAIL"));					
				}
				obj = map;
			} else {
				obj = list;
			}
		} else {
			obj = list;
		}
		logger.info(obj);
		return obj;		
	}
	


	public int memberInsert(Map<String, Object> pMap) {		
		int result = 0;
		result = memberDao.memberInsert(pMap);
		return result;		
	}

	public int memberUpdate(Map<String, Object> pMap) {
		int result = 0;
		result = memberDao.memberUpdate(pMap);
		return result;
	}

	public int memberDelete(Map<String, Object> pMap) {
		int result = 0;
		result = memberDao.memberDelete(pMap);
		return result;
	}
	
}
