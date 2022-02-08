package com.uplus.msghub;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uplus.msghub.common.HttpClientUtil;
import com.uplus.msghub.common.SecurityUtil;



@Controller
public class DashboardController {
	
	// application.yml 설정값 가져오기
	@Value("${server.url.host:}")
	private String reqUrl;

    @RequestMapping("/msghub")
    public String msghub(ModelMap model) throws Exception {

    	String apiKey = "APIq4eUYqw";
    	
    	Date today = new java.util.Date(); 
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyMMddhhmm");

		String randomStr = sdf.format(today);
		
		String apiPwd = SecurityUtil.sha512("1q2w3e!!", randomStr); 
    	
    	String url = reqUrl + "auth/v1/" +randomStr;
    	String data = "{\"apiPwd\":\""+apiPwd+"\",\"apiKey\":\""+apiKey+"\"}";
    	
    	HttpResponse<String> response = HttpClientUtil.post(url, data);
    	if (response.statusCode() == 200) {
			System.out.println("성공");
		} else {
			System.out.println("response is error : " + response.statusCode());
		}
		
    	model.addAttribute("response", response.body());
    	
        return "msghub";
    }
    
    @ResponseBody
	@RequestMapping("/ajaxRequest")
	public String ajaxRequest(@RequestParam Map<String, Object> paramMap) throws Exception {
    	
    	JSONObject data =  new JSONObject(paramMap);
    	
    	String request = (String) data.get("request");
    	String token = (String) data.get("token");
    	String recvInfoLst = (String) data.get("recvInfoLst");
    	
    	JSONParser parser = new JSONParser();
    	JSONObject json = (JSONObject) parser.parse(recvInfoLst);
    	JSONArray json2 = new JSONArray();
    	json2.add(json);
    	data.put("recvInfoLst", json2);
    	
    	System.out.println("data==>"+data);
    	
    	
    	HttpResponse<String> response;
    	
    	switch (request) {
    	case "mms": 
    		response = HttpClientUtil.post(reqUrl+"file/v1/mms", data.toString(), token);
			break;
    	case "mms2": 
    		response = HttpClientUtil.post(reqUrl+"msg/v1/mms", data.toString(), token);
			break;
    	case "mms3":
    		response = HttpClientUtil.post(reqUrl+"msg/v1/mms", data.toString(), token);
			break;
    	case "rpt":
    		response = HttpClientUtil.post(reqUrl+"msg/v1/"+request, data.toString(), token);
			break;
    	case "refresh":
			response = HttpClientUtil.put(reqUrl+"auth/v1/"+request, data.toString(), token);	
			break;
		default:
			response = HttpClientUtil.post(reqUrl+"msg/v1/"+request, data.toString(), token);
			break;
		}
    	
    	if (response.statusCode() == 200) {
			System.out.println("성공");
		} else {
			System.out.println("response is error : " + response.statusCode());
		}

		return response.body();
	}
}
