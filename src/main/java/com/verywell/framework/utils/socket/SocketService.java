package com.verywell.framework.utils.socket;

import com.verywell.framework.utils.socket.rugao.DeviceControl;


public class SocketService{

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String sendMessage(int[] request)
	{
		try
		{
			SocketClientThread sct = SocketClientThread.handle();
			if (sct == null)
			{
				return "";
			}
			return sct.sendMessage(request);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return "";
		}
	}
	
	/**
	 * call back by 
	 * @param request
	 * @return
	 */
	public static String receiveMessage(int[] request)
	{
		//修改如皋的光敏回传值
		if (request != null && request.length > 4) {
			int a = request[0];
			int b = request[1];
			int c = request[2];
			int d = request[3];
			if ((a == 0x55&& b == 0x66 && c == 0x01 && d == 0x01)||(a == 0x55&& b == 0x66 && c == 0x01 && d == 0x00))
			{
				DeviceControl.lightvalue = request;
				System.out.println("--------接收光敏数据----------------- ");
			}
		}
		//change Model and 
		if (request.length > 0)
		{
//			PocDevService.updateModel(request);
			return "1";
		}
		
		return "";
	}
	
	/**
	 * Test
	 * @param as
	 * @throws InterruptedException 
	 */
	public static void main(String[] as) throws InterruptedException
	{
//		FE AE 7A 07 08 0A 58 AC 00 00 00 00 00 00 00 36 44 C2
//
//		55所周工 2014/5/11 11:51:55
//		这个是插座的查询返回值。。
//
//		55所周工 2014/5/11 11:52:02
//		58 AC 00 00 00 00 00 00 00 36 

		
		//灯1开，灯1关
		int[] buf111 = { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x01, 0x01, 0x00 };		
		sendMessage(buf111);
		Thread.sleep(100);
		int[] buf110= { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x01, 0x00, 0x00 };		
		sendMessage(buf110);
		Thread.sleep(100);
		
		//灯2开，灯2关
		
		
		//灯3开，灯3关
		
		
		//灯4开，灯4关
		
		
		
		//灯全开，灯全关
		int[] bufall1 = { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x00, 0x01, 0x00 };		
		sendMessage(bufall1);
		Thread.sleep(100);
		int[] bufall0= { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x00, 0x00, 0x00 };		
		sendMessage(bufall1);
		Thread.sleep(100);
		
//		
//		//流量查询
//		int[] buf11 = { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x01, 0x00, 0x00 };
//		
//		int[] buf10= { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x01, 0x00, 0x00 };
//		
//		
//		// int[] buf = { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x01, 0x00, 0x00 };
		
		while (true)
		{
			Thread.sleep(1000);
		}
	}
	
	
}
