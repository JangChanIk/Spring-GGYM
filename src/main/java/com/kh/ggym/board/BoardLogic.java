package com.kh.ggym.board;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class BoardLogic {
	
	Logger logger = LogManager.getLogger(BoardLogic.class);
	@Autowired
	private BoardDao boardDao = null;
	

	
	public String imageUpload(MultipartFile image) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		logger.info("image:"+image);
		String savePath =  "C:\\JANG\\CODE\\Coding\\Workspace\\Final-Project\\Spring-GGYM\\src\\main\\webapp\\file";
		String filename =  null;
		String fullPath = null;

		//첨부파일이 존재하나?
		if(image!=null && !image.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar time = Calendar.getInstance();
			filename = sdf.format(time.getTime())+'-'+image.getOriginalFilename().replaceAll(" ", "-");
			fullPath = savePath+"\\"+filename;
			try {
				logger.info("fullPath : "+fullPath);
				File file = new File(fullPath);//파일명만 존재하고 내용은 없는
				byte[] bytes = image.getBytes();
				BufferedOutputStream bos = 
						new BufferedOutputStream(new FileOutputStream(file));
				//52번에서 생성된 File객체에 내용쓰기
				bos.write(bytes);
				bos.close();
				//파일크기
				double size = Math.floor(file.length()/(1024.0*1024.0)*10)/10;
				logger.info("size : "+size);
				pMap.put("FILE_NAME",filename);
				pMap.put("FILE_SIZE",size);
				pMap.put("FILE_PATH",fullPath);
				int result = boardDao.fileInsert(pMap);
				if(result!=1) {
					filename = null;
				}
			}catch(Exception e) {		
				e.printStackTrace();
			}
		}		
		return filename;
	}


	public byte[] imageDownload(String imageName) {
		String fname = null;
		try {
			fname = URLDecoder.decode(imageName, "UTF-8");
			logger.info(fname);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//out.print("b_file: 8->euc"+b_file);		
		//out.print("<br>");		
		String filePath = "C:\\JANG\\CODE\\Coding\\Workspace\\Final-Project\\Spring-GGYM\\src\\main\\webapp\\file"; // 절대경로.	
		//가져온 파일이름을 객체화 시켜줌. - 파일이 있는 물리적인 경로가 필요함.
		File file = new File(filePath, fname.trim());
	   	
	 	//해당 파일을 읽어오는 객체 생성해 줌. - 이 때 파일명을 객체화 한 주소번지가 필요함. 
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            fis = new FileInputStream(file);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
	}
	
	
	
	
	public List<Map<String, Object>> fileNames(Map<String, Object> pMap) {
		logger.info(pMap);
		//tablename, bno, filename
		List<Map<String, Object>> pList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> fMap = null;
		String[] fileNames = pMap.get("fileNames").toString().substring(1,pMap.get("fileNames").toString().length()-1).split(", "); 
		for(int i=0; i<fileNames.length; i++) {
			logger.info(fileNames[i]);
			fMap = new HashMap<String, Object>();
			fMap.put("FILE_NAME", fileNames[i]);
			fMap.put("BNO", pMap.get("BNO"));
			fMap.put("id", pMap.get("id"));
			pList.add(fMap);
		}
		return pList;
	}
	
	//=================================================================
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		List<Map<String, Object>> list = null;
		String id = pMap.get("id").toString();
		try {
			if(pMap.containsKey("content")){
				pMap.put("condition",URLDecoder.decode(pMap.get("condition").toString(), "UTF-8"));
				pMap.put("content",URLDecoder.decode(pMap.get("content").toString(), "UTF-8"));				
				logger.info(pMap);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(id.equals("notice")||id.equals("faq")) {
			list = boardDao.masterList(pMap);			
		} else if(id.equals("qna")) {
			list = boardDao.qnaList(pMap);						
		} else if(id.equals("review")) {
			list = boardDao.reviewList(pMap);									
		} else if(id.equals("trans")) {
			list = boardDao.transList(pMap);						
		}
		list.add(0, boardDao.getRowNum(pMap));
		return list;
	}
	
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public int boardInsert(Map<String, Object> pMap) {
		int result = 0;
		String id = pMap.get("id").toString();
		if(id.equals("notice")||id.equals("faq")) {
			result = boardDao.masterInsert(pMap);			
		} else if(id.equals("qna")) {
			result = boardDao.qnaInsert(pMap);						
		} else if(id.equals("review")) {
			result = boardDao.reviewInsert(pMap);									
		} else if(id.equals("trans")) {
			result = boardDao.transInsert(pMap);						
		}
		if(pMap.get("fileNames")!=null) {
			//첨부파일이 존재하나?
			boardDao.fileUpdate(fileNames(pMap));
		}
		return result;
	}

	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<Map<String, Object>> boardDetail(Map<String, Object> pMap) {
		String id = pMap.get("id").toString();
		List<Map<String,Object>> boardDetail = null;
		boardDao.boardHit(pMap);
		if(id.equals("notice")||id.equals("faq")) {
			boardDetail = boardDao.masterDetail(pMap);
		} else if(id.equals("qna")) {
			boardDetail = boardDao.qnaDetail(pMap);
		} else if(id.equals("review")) {
			boardDetail = boardDao.reviewDetail(pMap);
		} else if(id.equals("trans")) {
			boardDetail = boardDao.transDetail(pMap);		
		}
		if(!(id.equals("notice")||id.equals("faq"))) {			
			List<Map<String, Object>> commentList = boardDao.commentList(pMap);
			Map<String, Object> cMap = new HashMap<String, Object>();
			cMap.put("COMMENT", commentList);
			boardDetail.add(1, cMap);
		}
		if(!id.equals("trans")) {
			List<Map<String, Object>> fileList = boardDao.fileList(pMap);
			boardDetail.addAll(fileList);			
		}
		return boardDetail;
	}
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public int boardUpdate(Map<String, Object> pMap) {
		int result = 0;		
		String id = pMap.get("id").toString();
		if(id.equals("notice")||id.equals("faq")) {
			result = boardDao.masterUpdate(pMap);
		} else if(id.equals("qna")) {
			result = boardDao.qnaUpdate(pMap);			
		} else if(id.equals("review")) {
			result = boardDao.reviewUpdate(pMap);						
		} else if(id.equals("trans")) {
			result = boardDao.transUpdate(pMap);			
		}
		//첨부파일이 존재하나?
		if(pMap.get("fileNames")!=null&&result!=0) {
			//tablename, bno, filename
			List<Map<String, Object>> fList = new ArrayList<Map<String,Object>>();
			Map<String, Object> fMap = null;
			String[] fileNames = pMap.get("fileNames").toString().substring(1,pMap.get("fileNames").toString().length()-1).split(", "); 
			for(String name : fileNames) {
				logger.info(name);
				fMap = new HashMap<String, Object>();
				fMap.put("FILE_NAME", name);
				fMap.put("BNO", pMap.get("BNO"));
				fMap.put("id", pMap.get("id"));
				fList.add(fMap);
			}			
			result = boardDao.fileDelete(pMap);
			result = boardDao.fileUpdate(fList);
		}
		return result;
	}
	
	
	public int boardDelete(Map<String, Object> pMap) {
		return boardDao.boardDelete(pMap);
	}

	
	
	public int commentInsert(Map<String, Object> pMap) {
		return boardDao.commentInsert(pMap);
	}


	public int commentUpdate(Map<String, Object> pMap) {
		return boardDao.commentUpdate(pMap);
	}


	public int commentDelete(Map<String, Object> pMap) {
		return boardDao.commentDelete(pMap);
	}


	public int transferPass(Map<String, Object> pMap) {
		return boardDao.transferPass(pMap);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
}
