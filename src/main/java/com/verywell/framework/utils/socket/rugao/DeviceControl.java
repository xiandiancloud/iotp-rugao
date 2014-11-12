package com.verywell.framework.utils.socket.rugao;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.verywell.framework.utils.socket.SocketClientThread;
import com.verywell.framework.utils.socket.SocketService;



/**
 * 如皋中学项目的常量定义。
 * 
 * 设备类型：dev_class：。 
 * 	窗帘：55 AA 03H（5614083）
 * 	门禁：02 05 00H （132352） 
 * 	灯光：01 05 00H（66816）
 * 
 * 控制参数： 0 关 1 开 2 停。
 * 
 * 
 * !!!以后设备的状态控制位数、控制状态、通过后台界面进行定义!不需要后台逻辑处理！!!
 * !!!不能不同的设备、不同厂家、不同的数据格式而写程序。！！！
 * 
 * @author soong xueyong
 * 
 */
public class DeviceControl {
	
	private static Log log = LogFactory.getLog(DeviceControl.class);
	///////////////////以下变量是Web请求的数据/////////////////>
	private static String RequestURL_Light = "/lightService";
	private static String RequestURL_Door = "/doorService";
	private static String RequestURL_Curtain = "/curtainService";
	//如皋的光敏
	private static String RequestURL_Guang = "/guangService";
	//如皋临时增加
	public static int[] lightvalue = new int[]{0x00,0x00,0x00,0x00,0x00,0x00};
	public static int[] oldlightvalue = new int[]{0x00,0x00,0x00,0x00,0x00,0x00};
	//Web 系统
	//窗帘类型，类型存在数据库中
	private static int Dev_Class_Curtain = 0x55AA03;
	//门禁类型，类型存在数据库中
	private static int Dev_Class_Door = 0x020500;
	//灯逛类型，类型存在数据库中
	private static int Dev_Class_Light = 0x010500;
	//光敏，没有设备，不在数据库中
	private static int Dev_Class_Guang = 0x010501;
	
	//Web系统(数据库) 开设备
	private static int Dev_Open = 0x01;
	//Web系统(数据库) 关设备
	private static int Dev_Close =  0x00;
	//Web系统(数据库) 停止工作
	private static int Dev_Stop =   0x02;
	
	///////////////////以下上位机命令的数据/////////////////
	
	//窗帘类型，数据类型封装成byte[]传递给上位机
	private static int[] Dev_Class_Curtain_CMD = {0x55,0xAA,0x03};
	//窗帘类型，数据类型封装成byte[]传递给上位机
	private static int[] Dev_Class_Door_CMD = {0x02,0x05,0x00};
	//窗帘类型，数据类型封装成byte[]传递给上位机
	private static int[] Dev_Class_Light_CMD = {0x01,0x05,0x00};	
	//所有设备
	private static int Dev_Class_All_CMD =   0x00;	
	
	//以后设备的状态控制位数、控制状态、通过后台界面进行定义

	//窗帘设备打开
	private static int Dev_Curtain_Open_CMD =   0x01;
	//窗帘设备关闭
	private static int Dev_Curtain_Close_CMD =   0x03;	
	//窗帘停电
	private static int Dev_Curtain_Stop_CMD =   0x02;
	
	//灯开
	private static final int Dev_Light_Open_CMD = 0xFF;
	//灯关
	private static final int Dev_Light_Close_CMD = 0x00;
	
	//门开
	private static final int Dev_Door_Open_CMD = 0xFF;
	//门重置
	private static final int Dev_Door_Reset_CMD = 0x00;
	

	

	/**
	 * 把URL请求变成下发上位机的命令。
	 * @param type
	 * @param params
	 * @return
	 */
	public static String control(String url, Map<String, String> params) {
		
		try
		{
		int devClass = getDevClassCMD(url);
		if (devClass == -1)
		{
			return "";
		}		

		String cmd = params.get("cmd");//switch,  sence, all ?
		String deviceId = params.get("macaddr");
		String value = params.get("value");
		//debug
		log.info("cmd:"  + cmd +  "deviceId" +deviceId + "value:" + value);
		
		int valueCmd = Integer.valueOf(value).intValue();		
		int valueDevId = Integer.valueOf(deviceId).intValue();
		int[] request = null;
		if (devClass == Dev_Class_Light)
		{
			//int statusCmd = getLigthStatus(valueCmd);
			String status = params.get("statusCmd");
			int statusCmd = Integer.valueOf(status).intValue();	
			request = getLightCmds2(valueCmd,statusCmd, valueDevId);
		}
		else if (devClass == Dev_Class_Curtain)
		{
			int statusCmd = getCurtainStatus(valueCmd);
			request = getCurtainCmds(statusCmd, valueDevId);
		}
		else if (devClass == Dev_Class_Door)
		{
			//int statusCmd = getDoorStatus(valueCmd);
			String status = params.get("statusCmd");
			int statusCmd = Integer.valueOf(status).intValue();	
			request = getDoorCmds2(statusCmd, valueDevId);
		}
		else if (devClass == Dev_Class_Guang)
		{
			request = getGuangCmds2(valueCmd);
		}
		//debug
	
		
		log.info("request:" + SocketClientThread.ints2Hex(request));
		String res = SocketService.sendMessage(request);
		if ("1".equals(res))
		{
			
		}
		return res;	
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return "";
	}
	
	

	/**
	 * class(2) leh(1) cmd(1) id(2) verify(1)
	 * all curtain
	 * 55 AA 03 01 00 00 01  (Open)
	 * 55 AA 03 03 00 00 03  (close)
	 * 55 AA 03 02 00 00 02  (stop control)
	 * curtain 1#
	 * 55 AA 03 01 00 01 02 
	 * 55 AA 03 03 00 01 04 
	 * 55 AA 03 02 00 01 03 
	 * curtain 2#
	 * 55 AA 03 01 00 02 03 
	 * 55 AA 03 03 00 02 05 
	 * 55 AA 03 02 00 02 04
	 * ......
	 * 
	 * @param statusCmd
	 *            命令 Dev_Open_CMD/Dev_Close_CMD/Dev_Stop_CMD。
	 * @param devId
	 *            设备编号（如果设备编号为00，全部的电机）。
	 * @return
	 */
	public static int[] getCurtainCmds(int statusCmd, int devId)
	{

		//0,1 表示设备类型；第2 标书数据长度，
		int[] request = {0x55, 0xaa, 0x03, 0x00, 0x00, 0x00, 0x00};
		//第2位表示命令
		request[3] = statusCmd;
		//第4,5为表示设备ID
		request[4] = 0x00;
		request[5] = devId;
		//第6位表示检验，校验算法（命令[3]+设备ID[4,5]）
		request[6] =  request[3] + request[5];
		
		return request;
		
	}
	
	
	/**
	 * class(2) leh(1) cmd(1) id(2) verify(1)
	 * all ligth
	 * 01 0F 00 10 00 04 01 0F  (open 1)
	 * 01 0F 00 10 00 04 01 00  (close 2)
	 * light 1#
	 * 01 05 00 13 FF 00 
	 * 01 05 00 13 00 00 
	 * light 2#
	 * 01 05 00 12 FF 00 
	 * 01 05 00 12 00 00
	 * ......
	 * 
	 * @param statusCmd
	 *            命令 Dev_Open_CMD/Dev_Close_CMD/Dev_Stop_CMD。
	 * @param devId
	 *            设备编号（如果设备编号为00，全部的电机）。
	 * @return
	 */
	public static int[] getLightCmds(int statusCmd, int devId)
	{
		//所有灯控制，开和关，字节不清楚规格，只好硬编码.
		//以后后台不处理这个逻辑，全部定义决定：设备类型ID、设备ID、设备控制状态、设备的命令、设备的校验
		if (devId == 00)
		{			
			if (statusCmd == Dev_Light_Open_CMD)//硬编码
			{				       
				//01 0F 00 10 00 04 01 0F
				int[] request =  {0x01, 0x0F, 0x00, 0x10, 0x00, 0x04, 0x01, 0x0F};
				return request;
						
			}
			if (statusCmd == Dev_Light_Close_CMD)//硬编码
			{
				////01 0F 00 10 00 04 01 00
				int[] request =  {0x01, 0x0F, 0x00, 0x10, 0x00, 0x04, 0x01, 0x00};
				return request;
			}			
		}
		//01 05 00 13 FF 00 
		//0,1 表示设备类型
		int[] request = {0x01, 0x05, 0x00, 0x00, 0x00, 0x00};
		//第2,3为表示设备ID
		request[2] = 0x00;
		request[3] = devId;
		//第4位命令
		request[4] = statusCmd;
		//第5位命令
		request[5] = 0x00;
		
		return request;
		
	}
	
	public static int[] getLightCmds2(int cmd,int statusCmd, int devId)
	{
		if (cmd == 1)
		{
			if (devId == 9)
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x08, 0xFF, 0x00,0x0C,0x04};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x09, 0xFF, 0x00,0x5d,0xc4};
					return request;
				}
			}
			else if (devId == 10)
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x0a, 0xFF, 0x00,0xad,0xc4};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x0b, 0xff, 0x00,0xfc,0x04};
					return request;
				}
			}
			else
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x0C, 0xFF, 0x00,0x4D,0xC5};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x0D, 0xFF, 0x00,0x1C,0x05};
					return request;
				}
			}
		}
		else if (cmd == 0)
		{
			if (devId == 9)
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x08, 0x00, 0x00,0x4d,0xf4};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x09, 0x00, 0x00,0x1C,0x34};
					return request;
				}
			}
			else if (devId == 10)
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x0b, 0x00, 0x00,0xbd,0xf4};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x0a, 0x00, 0x00,0xec,0x34};
					return request;
				}
			}
			else
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x0D, 0x00, 0x00,0x5D,0xF5};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x0C, 0x00, 0x00,0x0C,0x35};
					return request;
				}
			}
		}
		else
		{
			if (devId == 9)
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x08, 0xFF, 0x00,0x0C,0x04};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x09, 0x00, 0x00,0x1C,0x34};
					return request;
				}
			}
			else if (devId == 10)
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x0a, 0xFF, 0x00,0xad,0xc4};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x0b, 0x00, 0x00,0xbd,0xf4};
					return request;
				}
			}
			else
			{
				if (statusCmd == 1)
				{
					int[] request = {0x01, 0x05, 0x01, 0x0C, 0xFF, 0x00,0x4D,0xC5};
					return request;
				}
				else
				{
					int[] request = {0x01, 0x05, 0x01, 0x0D, 0x00, 0x00,0x5D,0xF5};
					return request;
				}
			}
		}
	}
	
	/**
	 * door1#
	 * 02 05 00 10 FF 00 开门
	 * 02 05 00 10 00 00 复位
	 * door2#
	 * 02 05 00 11 FF 00开门
	 * 02 05 00 11 00 00开门
	 * ......
	 * 
	 * @param statusCmd
	 *            命令 Dev_Open_CMD/Dev_Close_CMD/Dev_Stop_CMD。
	 * @param devId
	 *            设备编号（如果设备编号为00，全部的电机）。
	 * @return
	 */
	public static int[] getDoorCmds(int statusCmd, int devId)
	{
		//0,1 表示设备类型
		int[] request = {0x02, 0x05, 0x00, 0x00, 0x00, 0x00};		
		//第2,3为表示设备ID
		request[2] = 0x00;
		request[3] = devId;
		//第4,5为表示设备ID
		request[4] = statusCmd;
		request[5] = 00;		
		return request;
		
	}
	
	public static int[] getDoorCmds2(int statusCmd, int devId)
	{
		if (statusCmd == 1)
		{
			//教室1
			if (devId == 11)
			{
				int[] request = {0x01, 0x05, 0x01, 0x01, 0xFF, 0x00,0xDC,0x06};
				return request;
			}
			else//教室2
			{
				int[] request = {0x01, 0x05, 0x01, 0x04, 0xFF, 0x00,0xCC,0x07};
				return request;
			}
		}
		else
		{
			//教室1
			if (devId == 11)
			{
				int[] request = {0x01, 0x05, 0x01, 0x01, 0x00, 0x00,0x9D,0xF6};
				return request;
			}
			else//教室2
			{
				int[] request = {0x01, 0x05, 0x01, 0x04, 0x00, 0x00,0x8D,0xF7};		
				return request;
			}
		}
	}
	
	public static int[] getGuangCmds2(int statusCmd)
	{
		if (statusCmd == 0)//打开
		{
			int[] request = {0x55, 0xAA, 0x03, 0x01, 0x00, 0x00, 0x01};
			return request;
		}
		else if (statusCmd == 1)//关闭
		{
			int[] request = {0x55, 0xAA, 0x03, 0x03, 0x00, 0x00, 0x03};
			return request;
		}
		else// if (statusCmd == 2)
		{
			int[] request = {0x55, 0xAA, 0x03, 0x02, 0x00, 0x00, 0x02};
			return request;
		}
	}
	//////////////////////////////////////////////////////////////
	
	
	/**
	 * 根据URL请求获得类型的字节。
	 * @return
	 */
	private static int getDevClassCMD(String strurl)
	{	
		if (strurl.contains(RequestURL_Light))
		{
			return Dev_Class_Light;
		}
		if (strurl.contains(RequestURL_Door))
		{
			return Dev_Class_Door;
		}
		if (strurl.contains(RequestURL_Curtain))
		{
			return Dev_Class_Curtain;
		}
		if (strurl.contains(RequestURL_Guang))
		{
			return Dev_Class_Guang;
		}
		return -1;
	}
	
	
	/**
	 * 根源URL param的的控制变量(0,1,2)转化成上位机控制的控制字节，由于
	 * 每个设备不同，因此分开定义，这里是窗帘。
	 * @return
	 */
	private static int getCurtainStatus(int value)	{	
		if (value == Dev_Open)
		{
			return Dev_Curtain_Open_CMD;
		}
		if (value == Dev_Close)
		{
			return Dev_Curtain_Close_CMD;
		}
		if (value == Dev_Stop)
		{
			return Dev_Curtain_Stop_CMD;
		}
		return -1;
	}
	
	/**
	 * 根源URL param的的控制变量(0,1,2)转化成上位机控制的控制字节，由于
	 * 每个设备不同，因此分开定义，这里是灯光窗帘。
	 * @return
	 */
	private static int getLigthStatus(int value)	{	
		if (value == Dev_Open)
		{
			return Dev_Light_Open_CMD;
		}
		if (value == Dev_Close)
		{
			return Dev_Light_Close_CMD;
		}		
		return -1;
	}
	
	/**
	 * 根源URL param的的控制变量(0,1,2)转化成上位机控制的控制字节，由于
	 * 每个设备不同，因此分开定义，这里是门禁窗帘。
	 * @return
	 */
	private static int getDoorStatus(int value)	{	
		if (value == Dev_Open)
		{
			return Dev_Door_Open_CMD;
		}
		if (value == Dev_Close)
		{
			return Dev_Door_Reset_CMD;
		}	
		return -1;
	}
	
	
	
	
	

}
