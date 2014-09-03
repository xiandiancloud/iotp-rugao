package com.verywell.framework.utils.socket;

public class PocDevService {

	public static int BT_Frame = 0;
	public static int BT_Addr = 1;
	public static int BT_Dev_ID = 3;
	public static int BT_Dev_CMD = 4;
	public static int BT_DataLen = 5;
	public static int BT_Light_Dev_ID = 6;
	public static int BT_Light_Dev_Value = 7;
	//fe  41 b4 03 03 02 00 01 00灯全开或者全关
	//fe  41 b4 03 03 02 01 01 00 开第一个灯
	//fe  41 b4 03 03 02 01 00 00 关第一个灯
	//fe  41 b4 03 03 02 02 01 00开第二个灯
	//fe  41 b4 03 03 02 03 01 00开第三个灯
	
	/**
	 * 控制一个灯的开关。
	 * 
	 * @param pocDevId
	 * @param value
	 * @return 状态("" 网络不通 "1" 成功 "0" 失败)
	 */
	public static String airConCtrlOne(String name, int lightId, int value) {   //fe      41          b4 03           03      02      00       01      00
		int[] request = {0xfe, 0x93, 0x9d, 0x08, 0x05, 0x01,  0x00, 0x00};

		request[6] = value;//7
		// call client and change model

		String response = SocketService.sendMessage(request);

		if (!response.endsWith(PoCConst.EMPTY)) {
			// change model
			PocDev dev = PoCModel.getPocModel().getDev(name);
			dev.value[lightId - 1] = value;
			return PoCConst.EMPTY_OK;
		}

		return PoCConst.EMPTY;
	}
	
	
	/**
	 * 控制一个灯的开关。
	 * 
	 * @param pocDevId
	 * @param value
	 * @return 状态("" 网络不通 "1" 成功 "0" 失败)
	 */
	public static String lightCtrlOne(String name, int lightId,
			int value) {   //fe      41          b4 03           03      02      00       01      00
		int[] request = {0xfe, 0x41, 0xb4, 0x03, 0x03, 0x02, 0x01, 0x01, 0x00};

//		request[BT_Dev_ID] = devId;//03
		request[BT_Dev_CMD] = 0x03;//04

		request[BT_Light_Dev_ID] = lightId;//6
		request[BT_Light_Dev_Value] = value;//7

		// call client and change model

		String response = SocketService.sendMessage(request);

		if (!response.endsWith(PoCConst.EMPTY)) {
			// change model
			PocDev dev = PoCModel.getPocModel().getDev(name);
			dev.value[lightId - 1] = value;
			return PoCConst.EMPTY_OK;
		}

		return PoCConst.EMPTY;
	}

	public static String lightCtrlAll(String name, int value) {

		int[] request = {0xfe, 0x41, 0xb4, 0x03, 0x03, 0x02, 0x00, 0x01, 0x00};
		
		request[BT_Dev_CMD] = 0x03;

		request[BT_Light_Dev_ID] = 0x00;
		request[BT_Light_Dev_Value] = value;
		// call client and change model

		String response = SocketService.sendMessage(request);
		if (!response.endsWith(PoCConst.EMPTY)) {
			// change model
			PocDev dev = PoCModel.getPocModel().getDev(name);
			for (int i = 0; i < 4; i++) {
				dev.value[i] = value;
			}
			return PoCConst.EMPTY_OK;
		}

		return PoCConst.EMPTY;

	}

	/**
	 * 灯光
	 * 
	 * @param devId
	 * @return
	 */
//	public static String lightQueryAll(String name, int devId) {
//
//		int[] request = { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x00 };// 没有长度
//
//		request[BT_Dev_ID] = devId;
//		request[BT_Dev_CMD] = 0x05;
//
//		String response = SocketService.sendMessage(request);
//		if (!response.endsWith(PoCConst.EMPTY)) {
//			// asynchronized data no return here
//			// update by a thread
//			return PoCConst.EMPTY_OK;
//		}
//
//		return PoCConst.EMPTY;
//	}

	/**
	 * 剂量开关查询所有。
	 * 
	 * @param devId
	 * @param value
	 * @return
	 *///ok
	public static String swithQueryAll(String name) {
		//fe ae 7a  07 07 00 00
		int[] request = { 0xfe, 0xae, 0x7a, 0x07, 0x07, 0x00, 0x00};

		request[BT_Dev_ID] = 0x07;
		request[BT_Dev_CMD] = 0x07;

		String response = SocketService.sendMessage(request);
		if (!response.endsWith(PoCConst.EMPTY)) {
			// asynchronized data no return here
			// update by a thread
			return PoCConst.EMPTY_OK;
		}

		return PoCConst.EMPTY;
	}
	
	
	/**
	 * 剂量开关查询所有。
	 * 
	 * @param devId
	 * @param value
	 * @return
	 */
	public static String updateModel(int[] values) {

		if (values == null || values.length ==0)
		{
			System.out.println("Return Response Empty!");
			return "";
		}
		
		if (values.length > 6)
		{
			//devId
			//cmdId
			//value
			//if light
			//if switch
			//light
			if (values[0] != 0xfe)
			{
				return "1";
			}
			if (values[BT_Dev_ID] == 0x03)
			{
				//单灯控制
				if (values[BT_Dev_CMD] == 0x04)
				{
					PocDev dev = PoCModel.getPocModel().getDev("0x01");
					int lightId = values[5];
					dev.value[lightId] = values[6];
				}
				//全部控制
				else if (values[BT_Dev_CMD] == 0x06)
				{
					PocDev dev = PoCModel.getPocModel().getDev("0x01");
					
					dev.value[0] = values[6];
					dev.value[1] = values[7];
					dev.value[2] = values[8];
					dev.value[3] = values[9];
				}
			}
			//开关
			else if (values[BT_Dev_ID] == 0x07)
			{
				PocDev dev = PoCModel.getPocModel().getDev("0x05");
				
				//dev.value[0] = values[5];
				
				if (values.length > 16)
				{
					int vhigh = values[6];
					int vlow = values[7];
					int voltage = vhigh << 8;
					voltage = voltage + vlow;//57CD
					dev.value[0] = voltage;//V伏特
					
					int chigh = values[8];
					int clow = values[9];
					int current = chigh<<8;
					current = current + clow;
					dev.value[1] = current;//A安培
					
					int whigh = values[10];
					int wlow = values[11];
					int watt = whigh<<8;
					watt = watt + wlow;
					dev.value[2] = watt;//W瓦
					
				}
			}
		}
		

		return PoCConst.EMPTY;
	}

}
