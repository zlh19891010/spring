package com.admin.server.factory;

public class MessageType {
	public static String QrCode="B1"; //二维码类型
	public static String Infrared="B2";//红外类型
	public static String Status="B3";//状态灯

	public static int Qualified=1;  //实际产能
	public static int UnQualified=2; //不良产能


	public static String DeviceType_3="3"; //设备第三种特殊
	public static int RedLight=5; //红灯
	public static int YellowLight=4;//黄灯
	public static int GreenLight=3;//绿灯
}
