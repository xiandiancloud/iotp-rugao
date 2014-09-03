package com.verywell.iotp.admin.constants;

public class DevAttributeConstants
{
	/** 空调温度 */
	public static String KEY_AIR_TEMP = "AIR_TEMP";
	/** 空调模式 */
	public static String KEY_AIR_MODE = "AIR_MODE";
	/** 空调风量 */
	public static String KEY_AIR_WIND = "AIR_WIND";
	/** 温度 */
	public static String KEY_TEMPERATURE = "TEMPERATURE";
	/** 湿度 */
	public static String KEY_HUMIDITY = "HUMIDITY";
	/** 光感 */
	public static String KEY_LIGHT = "LIGHT";

	public static String getAirModeDesc(String mode)
	{
		if ("1".equals(mode))
			return "制冷";
		else if ("2".equals(mode))
			return "制热";
		else if ("3".equals(mode))
			return "送风";
		else
			return "";
	}

	public static String getAirWindDesc(String wind)
	{
		if ("1".equals(wind))
			return "小风";
		else if ("2".equals(wind))
			return "大风";
		else
			return "";
	}
}
