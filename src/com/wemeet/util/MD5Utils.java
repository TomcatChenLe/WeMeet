package com.wemeet.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
   /**
    * ���û������������м��ܺ�������ݿ�
    * 
    * @param �û�������û�����
    * @return   ���ܺ���û�����
    */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("û��md5����㷨��");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}