package com.uplus.msghub;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uplus.msghub.common.HttpClientUtil;
import com.uplus.msghub.common.SecurityUtil;



@Controller
public class DashboardController {

    @RequestMapping("/msghub")
    public String welcome(ModelMap model) throws Exception {
    	
    	Date today = new java.util.Date(); 
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyMMddhhmm");

		String randomStr = sdf.format(today);
		  
    	String apiPwd = SecurityUtil.encodeBase64(SecurityUtil.sha512("OpayTest0105494b508dd371e47ed3a912e877bd21"));
    	apiPwd = apiPwd + "." + randomStr;
    	
    	String apiKey = "test";
    	
    	String url = "https://api.msghub.uplus.co.kr/auth/v1/";
    	String data = "{\"apiPwd\":\""+apiPwd+"\",\"apiKey\":\""+apiKey+"\"}";
    	
    	url = url + randomStr;
    	
    	System.out.println("url==>"+url);
    	System.out.println("data==>"+data);
    	
    	HttpResponse<String> response = HttpClientUtil.post(url, data);

    	if (response.statusCode() == 200) {
			System.out.println("성공");
		} else {
			System.out.println("response is error : " + response.statusCode());

	        model.addAttribute("response", response.body());
		}
    	
    	/*   	
    	HttpRequest request = HttpRequest.newBuilder()
    		    .uri(URI.create("https://api.msghub.uplus.co.kr/auth/v1/test1234"))
    		    .header("accept", "*삭제/*")
    		    .header("Content-Type", "application/json")
    		    .method("POST", HttpRequest.BodyPublishers.ofString("{\"test\":\"1234\",\"test2\":\"22\"}"))
    		    .build();
    		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    	*/

    	
    	/*
    	HttpResponse<String> response = post(url, data);
    	
    	if (response.statusCode() == 200) {
			System.out.println();
		} else {
			System.out.println("response is error : " + response.statusCode());
			JSONParser jsonParser = new JSONParser();
	        Object obj = jsonParser.parse(response.body());
	        JSONObject jsonObj = (JSONObject) obj;
	        
	        //JsonElement test1 = jsonParser.parser(param.get("id").toString());
	        //String test2 = jsonParser.parser(param.get("pw").toString()); 
	        
	        System.out.println("jsonObj===>"+jsonObj);
	        System.out.println(jsonObj.get("code"));
	        System.out.println(jsonObj.get("message"));

	        model.addAttribute("jsonObj", jsonObj);
		}
		*/
		
        

    		
		// {"code":"29001","message":"must not be empty property=apiPwd"}

    			
		/*
		 * Date today = new java.util.Date(); java.text.SimpleDateFormat sdf = new
		 * java.text.SimpleDateFormat("yyyyMMddhhmmss");
		 * 
		 * StringBuilder sb = new StringBuilder(); sb.append( "01010102020202030" );
		 * sb.append( sdf.format(today) ); sb.append( "5067733303" ); // 10자리 랜덤숫자
		 * sb.append( "123456" ); // 사용자 API_KEY 비밀번호 String hmac =
		 * sha1(sb.toString().getBytes());
		 * 
		 * System.out.println("hmac==>"+hmac);
		 * 
		 * String api_key = "01010102020202030"; String api_secret = hmac; String
		 * algorithm = "1"; // 알고리즘 유형
		 * 
		 * Message sender = new Message(api_key, api_secret, algorithm);
		 * 
		 * HashMap<String, String> params = new HashMap<String, String>();
		 * 
		 * params.put("to", "01012341234"); // 수신번호 params.put("from", "01012341234");
		 * // 발신번호 params.put("msg_type", "S"); //메시지종류(S,M,L) params.put("msg",
		 * "메시지 내용"); params.put("send_type", "S"); //발송형태(예약R,즉시S)
		 * params.put("cmpg_id", ""); //발송형태 R일 경우 예약ID
		 * 
		 * params.put("subject", "테스트"); //제목 params.put("datetime", ""); //예약시간
		 * 
		 * params.put("image", ""); //이미지, MMS인 경우 사용
		 * 
		 * JSONObject obj = (JSONObject) sender.send(params);
		 * 
		 * // 결과는 사용자 시스템에 맞게 처리 System.out.println(obj.toString());
		 */
    	
        return "msghub";
    }
    
    @ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> ajaxReStatisticInfo(@RequestParam Map<String, Object> paramMap) throws Exception {
    	
    	String jsonText =  "{\"isMarried\":false,\"address\":null,\"name\":\"kim\",\"age\":19}";
        
        JSONParser parser = new JSONParser();
        
        JSONObject obj = null;
        
	     obj = (JSONObject)parser.parse(jsonText);
        
        System.out.println("obj=>"+obj);
        System.out.println("obj2=>"+obj.get("name"));
        System.out.println("obj3=>"+obj.get("isMarried"));

		return obj;
	}
    
    private static String sha1(byte[] bs) throws Exception
    {
      String hashtext = "";
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      md.update(bs);
      byte[] byteData = md.digest();

      StringBuffer sb2 = new StringBuffer();

      for (int i = 0; i < byteData.length; ++i) {
        sb2.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
      }

      hashtext = sb2.toString();

      return hashtext;
    }
}
