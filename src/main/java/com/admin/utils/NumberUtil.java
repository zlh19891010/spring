package com.admin.utils;

/**
 * 文件名称： com.admin.utils.NumberUtil.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月4日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者        日期       修改内容<br/>
 *
 *
 * ================================================<br/>
 *  Copyright (c) 2010-2011 .All rights reserved.<br/>
 */
public class NumberUtil {
	/**
	 * 将整型转换为字节数组~
	 * @param integer
	 * @return
	 */
	public static byte[] int2bytes(int integer) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (integer & 0xff); // 最低位
		bytes[1] = (byte) (integer >> 8 & 0xff); // 次低位
		bytes[2] = (byte) (integer >> 16 & 0xff); // 次高位
		bytes[3] = (byte) (integer >>> 24); // 最高位，无符号右移。
		return bytes;
	}

	/**
	 * 将字节数组转换为整型~
	 * @param bytes
	 * @return
	 */
	public static int bytes2int(byte[] bytes) {
		// 一个 byte 数据左移 24 位变成 0x??000000，再右移 8 位变成 0x00??0000（| 表示按位或）
		int integer = bytes[0] & 0xff
				| bytes[1] << 8 & 0xff00
				| bytes[2] << 24 >>> 8
				| bytes[3] << 24;
		return integer;
	}
}
