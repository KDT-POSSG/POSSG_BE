package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import possg.com.a.dto.ProductDto;

public class NaverCloudUtil {

	public static String processSTT(String filepath) {
		String clientId = "qhv14zruwc";             // Application Client ID";
        String clientSecret = "";     // Application Client Secret";

        StringBuffer response = null;
        
        try {
//            String imgFile = filepath;
            File voiceFile = new File(filepath);

            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if(br != null) {
                response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                // 결과 출력
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return response.toString();
    }

	
	public static Map<String, String> processTTS(String speech, String uploadPath, String speaker) {
		
		String clientId = "qhv14zruwc";             // Application Client ID";
	     String clientSecret = "";     // Application Client Secret";
	     
	     String message = "success";
	     String tempname = null;
	     
	     try {
	         String text = URLEncoder.encode(speech, "UTF-8"); // 13자
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("POST");
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         // post request
	         String postParams = "speaker="+ speaker +"&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
	         con.setDoOutput(true);
	         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	         wr.writeBytes(postParams);
	         wr.flush();
	         wr.close();
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // 정상 호출
	             InputStream is = con.getInputStream();
	             int read = 0;
	             byte[] bytes = new byte[1024];
	             // 랜덤한 이름으로 mp3 파일 생성
	             tempname = Long.valueOf(new Date().getTime()).toString();
	             File f = new File(uploadPath + File.separator + tempname + ".mp3");
	             f.createNewFile();
	             OutputStream outputStream = new FileOutputStream(f);
	             while ((read =is.read(bytes)) != -1) {
	                 outputStream.write(bytes, 0, read);
	             }
	             is.close();
	         } else {  // 오류 발생
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	             String inputLine;
	             StringBuffer response = new StringBuffer();
	             while ((inputLine = br.readLine()) != null) {
	                 response.append(inputLine);
	             }
	             br.close();
	             System.out.println(response.toString());
	             message = "fail";
	         }
	     } catch (Exception e) {
	         System.out.println(e);
	     }
	     Map<String, String> result = new HashMap<>();
	     result.put("message", message);
	     result.put("tempname", tempname);
	     return result;
	}

	
	public static void makeTextfile(List<ProductDto> list) {
        // Assume getProductNamesFromDB() fetches the product names from your database
        System.out.println("makeTextfile: " + list);
        // File path where you want to save the TXT file
        String filePath = "../file.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        	System.out.println("makeTextfile 2: " + list);
            for (ProductDto productName : list) {
            	System.out.println("makeTextfile 3: " + productName.getProductName());
                writer.write(productName.getProductName() + ",");
            }
            //writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Dummy method, replace this with your actual DB fetching logic
    public static List<String> getProductNamesFromDB() {
        // Fetch product names from your database and return as a List
        return null;
    }

}