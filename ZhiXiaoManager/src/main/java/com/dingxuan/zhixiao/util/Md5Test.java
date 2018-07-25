package com.dingxuan.zhixiao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;
 
	public static void main(String[] args) {
		String a = hash("7339ADAD19A287EFC9356BCED73E50F280107CB01530187966DEVICENUM=1451237979LO=126.643638LA=45.582917POSDESC=黑龙江省哈尔滨市平房区联盟街道建安五道街3号;花蕾ABC幼儿园在东边271.087米处LOCTIME=2018-06-24 19:04:16BAT=40GSM=0GPS=0TYPE=0");
		System.out.println(a);
	}
	
	
	
	/**
	 * 利用MD5算法散列字符串作为一个十六进制数的字符串返回结果。
	 * @param data
	 * @return
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. "
						+ "Jive will be unable to function normally.");
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}
 
	/**
	 * 返回一个字符串的加密形式。 MD5 算法
	 */
	public static String encrypt(String originalStr) {
		if (originalStr == null) {
			originalStr = "";
		}
		return hash(originalStr);
	}
 
	/**
	 * 将字节数组变成一个字符串，表示每个字节为一个无符号十六进制数。
	 * @param bytes
	 * @return
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;
 
		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
}
