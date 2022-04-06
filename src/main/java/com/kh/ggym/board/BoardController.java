package com.kh.ggym.board;


import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;



@RestController
@RequestMapping("/board/*")
public class BoardController {
	Logger logger = LogManager.getLogger(BoardController.class);

	@Autowired
	private BoardLogic boardLogic = null;

	

	//=======================================================================================
	
	@PostMapping("imageUpload")
	public String imageUpload(@RequestParam(value="image") MultipartFile image) {
		logger.info("imageUpload 호출 성공");
		String filename = boardLogic.imageUpload(image);
		logger.info(filename);
		return filename;
	}// end of boardList
	
	
	@GetMapping("imageGet")
	public byte[] imageGet(@RequestParam(value="imageName") String imageName, HttpServletResponse res) {
		logger.info("imageGet 호출 성공");
		byte[] fileArray = boardLogic.imageDownload(imageName);
		return fileArray;
	}// end of boardList
	
	@GetMapping("imageDownload")
	public ResponseEntity<Resource> imageDownload(@RequestParam(value="imageName") String imageName) {
		logger.info("imageDownload 호출 성공");
		String filePath = "C:\\JANG\\CODE\\Coding\\Workspace\\eclipse-workspace_eGov\\spring-phoenix\\src\\main\\webapp\\file"; // 절대경로.	
		try {
		File file = new File(filePath,URLDecoder.decode(imageName, "UTF-8"));

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
			resource = new ByteArrayResource(Files.readAllBytes(path));
			
		return ResponseEntity.ok()
				.headers(header)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}// end of boardList
	
	
	//===================================================================================
	
	
	@GetMapping("boardList")
	public String boardList(@RequestParam Map<String, Object> pMap) {
		logger.info("boardList 호출");
		logger.info(pMap);
		List<Map<String, Object>> list = boardLogic.boardList(pMap);
		Gson g = new Gson();
		String result = g.toJson(list);
		return result;
	}
	
	
	
	@PostMapping("boardInsert")
	public String boardInsert(@RequestBody Map<String,Object> pMap) {
		logger.info("boardInsert 호출 성공");
		logger.info(pMap);	
		int result = boardLogic.boardInsert(pMap);
		return String.valueOf(result);
	}
	
	
	
	
	@GetMapping("boardDetail")
	public String boardDetail(@RequestParam Map<String,Object> pMap) {
		logger.info("boardDetail 호출 성공");
		logger.info(pMap);	
		List<Map<String,Object>> boardDetail  = boardLogic.boardDetail(pMap);
		logger.info("boardDetail : " + boardDetail);
		Gson g = new Gson();
		String result = g.toJson(boardDetail);
		return result;
	}// end of boardList


	
	
	@PostMapping("boardUpdate")
	public String boardUpdate(@RequestBody Map<String,Object> pMap) {
		logger.info("boardInsert 호출 성공");
		logger.info(pMap);
		int result = boardLogic.boardUpdate(pMap);	
		return String.valueOf(result);
	}
	
	
	
	
	@PostMapping("boardDelete")
	public String boardDelete(@RequestBody Map<String,Object> pMap) {
		logger.info("boardDelete 호출 성공");
		logger.info(pMap);
		
		int result = 0;		
		result = boardLogic.boardDelete(pMap);
		
		return String.valueOf(result);
	}
	
	
	//======================================================================================
	
	
	
	@PostMapping("commentInsert")
	public String commentInsert(@RequestBody Map<String,Object> pMap) {
		logger.info("commentInsert 호출 성공");
		logger.info(pMap);
		int result = 0;		
		result = boardLogic.commentInsert(pMap);
		return String.valueOf(result);
	}
	
	
	@PostMapping("commentUpdate")
	public String commentUpdate(@RequestBody Map<String,Object> pMap) {
		logger.info("commentUpdate 호출 성공");
		logger.info(pMap);
		
		int result = 0;		
		result = boardLogic.commentUpdate(pMap);
		
		return String.valueOf(result);
	}
	
	
	@PostMapping("commentDelete")
	public String commentDelete(@RequestBody Map<String,Object> pMap) {
		logger.info("commentDelete 호출 성공");
		logger.info(pMap);
		int result = 0;		
		result = boardLogic.commentDelete(pMap);
		return String.valueOf(result);
	}
	
	
	//==============================================================================
	

	
	
	
	

	
	
	
}
