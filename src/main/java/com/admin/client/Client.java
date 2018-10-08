package com.admin.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

	public static void main(String[] args) {


		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast( "logger", new LoggingFilter() );
		connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
		connector.setHandler(new IoClientHandle());
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress("192.168.1.3",9090));
		//等待建立连接
		connectFuture.awaitUninterruptibly();
		System.out.println("连接成功");

		/*IoSession session = connectFuture.getSession();

		//String str="ABCDE12345";

		int a = Integer.parseInt("AA", 16);
		        int b = Integer.parseInt("55", 16);
		        int c = Integer.parseInt("B", 16);
		        int d = Integer.parseInt("02", 16);
		        int e = Integer.parseInt("B1", 16);
		        int g = Integer.parseInt("41", 16);
		        int k = Integer.parseInt("42", 16);
		        int l = Integer.parseInt("31", 16);
		        int h = Integer.parseInt("71", 16);
		        int i = Integer.parseInt("FF", 16);
		        int j = Integer.parseInt("A0", 16);

		System.err.println("=============================================");
		//AA 55 09 04 B1 31 EE FF A0    49 44 3D 30 34 4F 4B

		int a4 = Integer.parseInt("49", 16);
		        int b4 = Integer.parseInt("44", 16);
		        int c4 = Integer.parseInt("3D", 16);
		        int d4 = Integer.parseInt("30", 16);
		        int e4 = Integer.parseInt("34", 16);
		        int g4 = Integer.parseInt("4F", 16);
		        int h4 = Integer.parseInt("4B", 16);

		int a1 = Integer.parseInt("AA", 16);
		int b1 = Integer.parseInt("55", 16);
		int c1 = Integer.parseInt("09", 16);
		int d1 = Integer.parseInt("04", 16);
		int e1 = Integer.parseInt("B3", 16);
		int g1 = Integer.parseInt("05", 16);
		int h1 = Integer.parseInt("C4", 16);
		int i1 = Integer.parseInt("FF", 16);
		int j1 = Integer.parseInt("A0", 16);
		  System.err.println("=============================================");

	            int a2 = Integer.parseInt("AA", 16);
		        int b2 = Integer.parseInt("55", 16);
		        int c2 = Integer.parseInt("09", 16);
		        int d2 = Integer.parseInt("04", 16);
		        int e2 = Integer.parseInt("B1", 16);
		        int g2 = Integer.parseInt("57", 16);
		        int h2 = Integer.parseInt("14", 16);
		        int i2 = Integer.parseInt("FF", 16);
		        int j2 = Integer.parseInt("A0", 16);

		System.err.println("=============================================");

		int a3 = Integer.parseInt("AA", 16);
		int b3 = Integer.parseInt("55", 16);
		int c3 = Integer.parseInt("09", 16);
		int d3 = Integer.parseInt("04", 16);
		int e3 = Integer.parseInt("B2", 16);
		int g3 = Integer.parseInt("44", 16);
		int g5 = Integer.parseInt("30", 16);
		int g6 = Integer.parseInt("30", 16);
		int g7 = Integer.parseInt("31", 16);
		int h3 = Integer.parseInt("93", 16);
		int i3 = Integer.parseInt("FF", 16);
		int j3 = Integer.parseInt("A0", 16);





		  byte[]  array = new byte[11];
		        array[0] = (byte)a;
		        array[1] = (byte)b;
		        array[2] = (byte)c;
		        array[3] = (byte)d;
		        array[4] = (byte)e;

		        array[5] = (byte)g;
		        array[6] = (byte)k;
		        array[7] = (byte)l;

		        array[8] = (byte)h;
		        array[9] = (byte)i;
		        array[10] = (byte)j;
		        ByteBuffer byteBuff = ByteBuffer.allocate(array.length);
		    	byteBuff.put(array);
	            session.write(IoBuffer.wrap(byteBuff.array()));

		System.err.println("=============================================");
		byte[]  array1 = new byte[16];

		 array1[0] = (byte)a4;
		        array1[1] = (byte)b4;
		        array1[2] = (byte)c4;
		        array1[3] = (byte)d4;
		        array1[4] = (byte)e4;
		        array1[5] = (byte)g4;
		        array1[6] = (byte)h4;


		array1[7] = (byte)a1;
		array1[8] = (byte)b1;
		array1[9] = (byte)c1;
		array1[10] = (byte)d1;
		array1[11] = (byte)e1;

		array1[12] = (byte)g1;

		array1[13] = (byte)h1;
		array1[14] = (byte)i1;
		array1[15] = (byte)j1;


		ByteBuffer  byteBuff = ByteBuffer.allocate(array1.length);
		byteBuff.put(array1);
		session.write(IoBuffer.wrap(byteBuff.array()));

		 System.err.println("=============================================");

		        byte[]  array2 = new byte[9];
		        array2[0] = (byte)a2;
		        array2[1] = (byte)b2;
		        array2[2] = (byte)c2;
		        array2[3] = (byte)d2;
		        array2[4] = (byte)e2;

		        array2[5] = (byte)g2;

		        array2[6] = (byte)h2;
		        array2[7] = (byte)i2;
		        array2[8] = (byte)j2;
		         byteBuff = ByteBuffer.allocate(array2.length);
		    	byteBuff.put(array2);
	            session.write(IoBuffer.wrap(byteBuff.array()));

		        System.err.println("=============================================");
		byte[]  array3 = new byte[12];
		array3[0] = (byte)a3;
		array3[1] = (byte)b3;
		array3[2] = (byte)c3;
		array3[3] = (byte)d3;
		array3[4] = (byte)e3;

		array3[5] = (byte)g3;
		array3[6] = (byte)g5;
		array3[7] = (byte)g6;
		array3[8] = (byte)g7;

		array3[9] = (byte)h3;
		array3[10] = (byte)i3;
		array3[11] = (byte)j3;


		byteBuff = ByteBuffer.allocate(array3.length);
		byteBuff.put(array3);
		session.write(IoBuffer.wrap(byteBuff.array()));









		//关闭
		if(session!=null){
			if(session.isConnected()){
				session.getCloseFuture().awaitUninterruptibly();
			}
			connector.dispose(true);
		}*/


		/*String srcStr = "qwer";
		    for (int i = 0; i < srcStr.getBytes().length; i++) {
		    	  System.out.println(Integer.toHexString(srcStr.getBytes()[i]));
			}

	        String encodeStr = encodeHexStr(srcStr.getBytes());
	        String decodeStr = new String(decodeHex(encodeStr.toCharArray()));
	        System.out.println("before encode:" + srcStr);
	        System.out.println("after encode:" + encodeStr);
	        System.out.println("convert:" + decodeStr);*/


	}

}
