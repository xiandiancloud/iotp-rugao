package com.verywell.framework.utils.socket;

public class PoCConst {
	
	public static final String EMPTY = "";
	public static final String EMPTY_OK = "1";
	public static final String EMPTY_FAIL = "0";
	public static final int Frame_Header = 0xfe;
	
	//{0xfe, 0xe7, 0xe1, 0x03,  0x03,  0x02,  0x01,  0x00,  0x00};
	
	public static final int PoCDevID_Light_Addr_Header = 0xe7;
	public static final int PoCDevID_Light_Addr_Footer = 0xe1;
	//{0xfe, 0xe7, 0xe1, 0x03,  0x03,  0x02,  0x01,  0x00,  0x00};
	public static final int PoCDevID_Swith_Addr_Header = 0xe7;
	public static final int PoCDevID_Swith_Addr_Footer = 0xe2;
	
			
	public static final int PoCDevID_Light = 0x03;
	public static final int PoCDevID_Swith = 0x07;
	
	public static final int PoCDevID_Light_Ctrl=0x03;
	public static final int PoCDevID_Light_Ctrl_Return=0x04;
	
	public static final int PoCDevID_Light_Query=0x05;
	public static final int PoCDevID_Light_QUERY_RETURN=0x06;
	
	public static final int PoCDevID_Swith_QUERY=0x05;
	public static final int PoCDevID_Swith_QUERY_RETURN=0x06;
	
	

}
