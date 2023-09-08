package possg.com.a.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.ProductDto;
import possg.com.a.service.ProductService;
import possg.com.a.service.TranslationService;
import util.TranslationUtil;

@RestController
public class TranslationController {

	@Autowired
	TranslationService service;
	
	// country: 0(한국어), 1(영어), 2(중국어), 3(일본어)
	public static String translationProductName(String text, int country) {
		Map<String, String> translationCache = new HashMap<>();
		if(country == 0) {//country == 0
			return text;
		}else {
			// Check if the translation is already in the cache
			if (translationCache.containsKey(text)) {
				return translationCache.get(text);
			}
			try {
		        // Papago API에 필요한 정보를 설정
				String clientId = "NiFtuZ11rBQSEvLhORYw";
		        String clientSecret = "X80LHHz6oN";
		        String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
	
		        // HTTP 클라이언트를 생성
		        CloseableHttpClient httpClient = HttpClients.createDefault();
	
		        // POST 메소드를 생성하고 헤더를 설정
		        HttpPost httpPost = new HttpPost(apiUrl);
		        httpPost.addHeader("X-Naver-Client-Id", clientId);
		        httpPost.addHeader("X-Naver-Client-Secret", clientSecret);
		        // 번역할 텍스트를 List 형태로 요청 본문에 추가
		        List<NameValuePair> params = new ArrayList<>();
		        params.add(new BasicNameValuePair("source", "ko"));
		        if(country == 1) {
		        	params.add(new BasicNameValuePair("target", "en"));
		        }else if(country == 2) {
		        	params.add(new BasicNameValuePair("target", "zh-CN"));
		        }else if(country == 3) {
		        	params.add(new BasicNameValuePair("target", "ja"));
		        }
		        params.add(new BasicNameValuePair("text", text));
		        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	
		        // 요청을 실행하고 응답을 받아옴
		        HttpResponse response = httpClient.execute(httpPost);
		        String responseJson = EntityUtils.toString(response.getEntity(), "UTF-8");
		        
		        JSONObject jsonResponse = new JSONObject(responseJson);
		        //System.out.println("jsonResponse: " + jsonResponse);
		        // 응답 JSON에서 번역된 텍스트를 가져옴
		        String translatedText = jsonResponse.getJSONObject("message").getJSONObject("result").getString("translatedText");
		        
		    	// Store the translation in the cache for future use
				translationCache.put(text, translatedText);
				
		        return translatedText;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	}

}
