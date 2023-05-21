package com.ssafy.travellego.encryption.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {


	@Override
	public String getEncryptedPw(String plainPw) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(plainPw.getBytes());
			byte[] encryptedPw = md.digest();
			
			
			StringBuffer sb = new StringBuffer();
			for (byte b: encryptedPw) {
				sb.append(String.format("%02x", b));
			}
			
			
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
