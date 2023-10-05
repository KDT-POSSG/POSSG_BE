package possg.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import possg.com.a.dto.ProductDto;
import possg.com.a.service.ProductService;
import possg.com.a.service.TextService;
import possg.com.a.util.NaverCloudUtil;

@RestController
public class TextController {
	
	@Autowired
	TextService service;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	NaverCloudUtil naverUtil;
	
	private String responseMessage;
	
	@PostMapping("/stt")
	public String stt(@RequestParam("uploadFile") MultipartFile uploadFile
			,HttpServletRequest request) throws IOException {
		
	    // STT로 변환
	    String text = fileUpload(uploadFile, request);

	    return text;
	}
	
	// 음성인식 wav -> String
	public String fileUpload(MultipartFile uploadFile,
							HttpServletRequest request) throws IOException {
		System.out.println("NaverCloudController fileUpload" + new Date());
		
		// tomcat
		String uploadPath = request.getServletContext().getRealPath("/upload");
		
		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadPath + File.separator + filename;
		
		System.out.println(filepath);
		
		//fileupload
		try {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		os.write(uploadFile.getBytes());
		os.close();
		} catch (Exception e) {
			return "file load fail";
		}
		
		// Naver Cloud
		String response = naverUtil.processSTT(filepath);
		
		return response;
	}

	@PostMapping("/tts")
	public String tts(@RequestParam("message") String message,
			@RequestParam("speaker") String speaker,
	                  HttpServletRequest request) {
	    System.out.println("NaverCloudController tts " + new Date());
	    System.out.println(message);
	    // tomcat
	    String uploadPath = request.getServletContext().getRealPath("/upload");
	    Map<String,String> msg = naverUtil.processTTS(message, uploadPath, speaker);

	    // mp3 파일의 URL 생성
	    String audioURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + msg.get("tempname") + ".mp3";
	    System.out.println(audioURL);
	    return audioURL;
	}

}
