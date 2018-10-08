package com.admin.server.factory;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;




public class RSAUtils {

	/**
	 * 密钥算法
	 * java支持56位密钥，bouncycastle支持64位
	 * */
	public static final String KEY_ALGORITHM="DES";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * */
	public static final String CIPHER_ALGORITHM="DES/ECB/PKCS5Padding";
	/**
	 * 解密数据
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密后的数据
	 * */
	public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
		//欢迎密钥
		Key k =toKey(key);
		//实例化
		Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
		//初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		//执行操作
		return cipher.doFinal(data);
	}
	/**
	 * 转换密钥
	 * @param key 二进制密钥
	 * @return Key 密钥
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * */
	public static Key toKey(byte[] key) throws InvalidKeyException,  InvalidKeySpecException {
		//实例化Des密钥
		DESKeySpec dks=new DESKeySpec(key);
		//实例化密钥工厂
		SecretKeyFactory keyFactory = null;
		try {
			keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("  env not support , now program not catch it ,", e);
		}
		//生成密钥
		SecretKey secretKey=keyFactory.generateSecret(dks);
		return secretKey;
	}
}
