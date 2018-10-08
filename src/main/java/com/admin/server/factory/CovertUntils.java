package com.admin.server.factory;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

public class CovertUntils {
	private static Logger logger =Logger.getLogger(CovertUntils.class);
	static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String hexStr =  "0123456789ABCDEF";
	/**
	 * 数组转成十六进制字符串
	 * @param byte[]
	 * @return HexString
	 */
	public static String toHexString1(byte[] b){
		StringBuffer buffer = new StringBuffer();
		for (byte element : b) {
			buffer.append(toHexString1(element));
		}
		return buffer.toString();
	}
	public static String toHexString1(byte b){
		String s = Integer.toHexString(b & 0xFF);
		if (s.length() == 1){
			return "0" + s;
		}else{
			return s;
		}
	}

	/**
	 *
	 * @param hexString
	 * @return 将十六进制转换为字节数组
	 */
	public static byte[] HexStringToBinary(String hexString){
		//hexString的长度对2取整，作为bytes的长度
		int len = hexString.length()/2;
		byte[] bytes = new byte[len];
		byte high = 0;//字节高四位
		byte low = 0;//字节低四位

		for(int i=0;i<len;i++){
			//右移四位得到高位
			high = (byte)(hexStr.indexOf(hexString.charAt(2*i))<<4);
			low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
			bytes[i] = (byte) (high|low);//高地位做或运算
		}
		return bytes;
	}



	/**
	 * 生成16进制累加和校验码
	 *
	 * @param data 除去校验位的数据
	 * @return
	 */
	public static String makeChecksum(String data) {
		if (data==null||"".equals(data)) {
			return "";
		}
		int total = 0;
		int len = data.length();
		int num = 0;
		while (num < len) {
			String s = data.substring(num, num + 2);
			total += Integer.parseInt(s, 16);
			num = num + 2;
		}
		/**
		 * 用256求余最大是255，即16进制的FF
		 */
		int mod = total % 256;
		String hex = Integer.toHexString(mod);
		len = hex.length();
		//如果不够校验位的长度，补0,这里用的是两位校验
		if (len < 2) {
			hex = "0" + hex;
		}
		return hex.toUpperCase();
	}



	/**
	 * 16进制累加和校验
	 *
	 * @param data 除去校验位的数据
	 * @param sign 校验位的数据
	 * @return
	 */
	public static boolean checkChecksum(String data, String sign) {
		// String sourceData="0100150aa303b101b201b301b404b504b601b904ba03be0140";
		//data="0100150aa303b101b201b301b404b504b601b904ba03be01";
		//sign="40";
		if (data==null||"".equals(data) || sign==null||"".equals(sign)) {
			return false;
		}
		String checksum = makeChecksum(data);
		if (checksum.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 *
	 * 十六进制转换字符串
	 */

	public static String hexStr2Str(String hexStr) {

		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = hexStr.indexOf(hexs[2 * i]) * 16;
			n += hexStr.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}


	public static String decode(String bytes)
	{
		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
		//将每2位16进制整数组装成一个字节
		for(int i=0;i<bytes.length();i+=2) {
			baos.write(hexStr.indexOf(bytes.charAt(i))<<4 |hexStr.indexOf(bytes.charAt(i+1)));
		}
		return new String(baos.toByteArray());
	}


	/**
	 * 处理多个消息分割
	 * @param buf
	 * @return
	 */
	public static List<byte[]> praseIoBuffer(IoBuffer buf ){

		List<byte[]> list = new ArrayList<byte[]>();
		byte[] start= new byte[1];
		byte[] end = new byte[2];
		byte[] message=null;
		String str="";
		String str_="";
		int splitStart = 0;
		int splitLength=0;
		for (int i = 0; i <buf.limit(); i++) {
			if(i+1>buf.limit()){
				break;
			}
			start[0]=buf.get(i);
			str_ =CovertUntils.toHexString1(start).toUpperCase();
			if(str_.equals("AA")){
				splitStart=i;
			}
			if(!str.equals("FFA0")){
				end[0] =buf.get(i);
				end[1] =buf.get(i+1);
				str =CovertUntils.toHexString1(end).toUpperCase();
				continue;
			}
			str="";
			splitLength=i+1-splitStart;
			message=new byte[splitLength];
			buf.position(splitStart);
			buf.get(message,0,splitLength);
			splitStart=i+1;
			logger.info("收到的16进制消息"+CovertUntils.toHexString1(message).toUpperCase());
			list.add(message);
		}

		return list;
	}


	public static void main(String[] args) {
		System.out.println(CovertUntils.decode("4A"));
	}


	//静态方法，便于作为工具类
	public static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (byte element : b) {
				i = element;
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			//32位加密
			return buf.toString();
			// 16位的加密
			//return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static String getMacByIp(String IP){
		try{
			InetAddress[] addarray = InetAddress.getAllByName("WIN-QHQ2PQUJOHC.LeraDC.com");
			String sMAC = "";
			for(InetAddress address:addarray){
				System.out.println("mechina IP:"+address.getHostAddress());
				NetworkInterface ni = NetworkInterface.getByInetAddress(address);
				if(!IP.equals(address.getHostAddress())){
					continue;
				}
				System.out.println(" real mechina IP:"+address.getHostAddress());
				byte[] mac = ni.getHardwareAddress();
				Formatter formatter = new Formatter();
				for (int i = 0; i < mac.length; i++) {
					sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
							i < mac.length - 1 ? "-" : "").toString();
				}
				break;
			}
			System.out.println("mac:"+sMAC+"IP:"+IP);
			return sMAC;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	public static void validate(String[] args) throws UnsupportedEncodingException, Exception{
		String[] validateToken =new String(RSAUtils.decrypt(Base64.decodeBase64(args[0]), args[1].getBytes("UTF-8"))).split("&");
		System.out.println(validateToken[0]+" MD5:"+validateToken[1]+"  "+validateToken[2]);
		if(!validateToken[1].equals(CovertUntils.getMd5(CovertUntils.getMacByIp(validateToken[0])+args[1]))){
			System.exit(0);
			return;
		}
		if(sd.parse(validateToken[2]).before(new Date())){
			System.exit(0);
			return;
		}
	}




}
