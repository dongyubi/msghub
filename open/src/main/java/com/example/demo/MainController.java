package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.uplus.api.java_sdk.method.Message;


@Controller
public class MainController {

	// http://localhost:8080/ 으로 접속 시
    @RequestMapping("/msghub")
    public String welcome(ModelMap model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	
    	String apiPwd = SecurityUtil.encodeBase64(SecurityUtil.sha512("OpayTest0105494b508dd371e47ed3a912e877bd21"));
    	model.addAttribute("apiPwd", apiPwd);
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
