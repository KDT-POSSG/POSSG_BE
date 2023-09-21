package possg.com.a.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TokenUtil {

	public static String getToken() {
		// 응답 토큰
		String token = "";
		
		try {
            // IAMPORT API URL
            String apiUrl = "https://api.iamport.kr/users/getToken";
            
            // REST API Key와 Secret 설정
            String impKey = "2163432028322800";
            String impSecret = "EYlCweVjWensJKmrEXVqZlw37fV7qdIGRIQDCnoyLiEvdVkv4OMpKwEx0WDlGLIVw2T6tPyOgkkmNrTm";
            
            // HttpClient 생성
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(apiUrl);
            
            // 요청 헤더 설정
            httpPost.setHeader("Content-Type", "application/json");
            
            // 요청 데이터 설정
            String json = String.format("{\"imp_key\":\"%s\", \"imp_secret\":\"%s\"}", impKey, impSecret);
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            
            // 요청 보내기
            HttpResponse response = httpClient.execute(httpPost);
            
            // 응답 데이터 읽기
            String responseBody = EntityUtils.toString(response.getEntity());
            
            // JSON 타입으로 파싱
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
            JSONObject responseObj = (JSONObject) jsonObject.get("response");
            token = (String) responseObj.get("access_token");
            
            // HttpClient 종료
            httpClient.getConnectionManager().shutdown();

        } 
		
		catch (Exception e) {
            e.printStackTrace();
        }	
		
		// 토큰 확인
		System.out.println(token);
		return token;

	}
}
