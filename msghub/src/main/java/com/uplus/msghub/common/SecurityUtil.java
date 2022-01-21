package com.uplus.msghub.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {

	public static String sha512(String apiPwd, String randomStr) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = MessageDigest.getInstance("SHA-512");

		md.update(apiPwd.getBytes("utf-8"));

		String sha512 = Base64.encodeBase64String(md.digest())+"." + randomStr;
		
		md.update(sha512.getBytes("utf-8"));
		
		return Base64.encodeBase64String(md.digest());
	}
}
