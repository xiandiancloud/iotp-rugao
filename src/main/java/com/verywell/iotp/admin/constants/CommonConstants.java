package com.verywell.iotp.admin.constants;

import com.verywell.framework.module.config.Config;
import com.verywell.framework.module.config.PropertiesConfigLoader;
import com.verywell.framework.module.config.Property;

/**
 * 
 * @title:通用常量类
 * @description: 定义了系统常量信息
 * @author: Yao
 */
@Config
@PropertiesConfigLoader(file = "config/config.properties", refreshTime = 60 * 60)
public class CommonConstants
{
	@Property(key = "iotp_port")
	public static String IOTP_PORT;
	@Property(key = "iotp_addr")
	public static String IOTP_ADDR;
	
	/** 每页显示记录数量 */
	public static final int MAX_PAGE_ITEMS = 10;

	/** 显示索引数量 */
	public static final int MAX_INDEX_PAGES = 3;

	/** 逗号分割符 */
	public static final String SPLIT_SYMBOL_COMMA = ",";

	/** 竖杆分割符 */
	public static final String SPLIT_SYMBOL_VERTICAL_LINE = "|";

	/** 横线分割符 */
	public static final String SPLIT_SYMBOL_TRANSVERSE_LINE = "-";

	/** 冒号分割符 */
	public static final String SPLIT_SYMBOL_COLON = ":";

	/** 下划线分割符 */
	public static final String SPLIT_SYMBOL_UNDERLINE = "_";

	/** 状态 1-有效 */
	public static final Integer STATUS_VALID = 1;

	/** 状态 0-无效 */
	public static final Integer STATUS_INVALID = 0;

	/** 设备控制命令- 设备开关 */
	public static final String CMD_SWITCH = "switch";

	/** 灯光服务地址 */
	@Property(key = "light_service")
	public static String LIGHT_SERVICE;

	/** 灯光服务地址 */
	@Property(key = "door_service")
	public static String DOOR_SERVICE;
	/** 光敏服务地址 */
	@Property(key = "guang_service")
	public static String GUANG_SERVICE;
	
	/** 空调服务地址 */
	@Property(key = "air_conditioner_service")
	public static String AIR_CONDITIONER_SERVICE;

	/** 传感器服务地址 */
	@Property(key = "sensor_service")
	public static String SENSOR_SERVICE;

	/** 窗帘服务地址 */
	@Property(key = "curtain_service")
	public static String CURTAIN_SERVICE;

	/** 会议中控服务地址 */
	@Property(key = "mutimedia_service")
	public static String MUTIMEDIA_SERVICE;

	/** 场景服务地址 */
	@Property(key = "scene_service")
	public static String SCENE_SERVICE;

	/** 显示屏配置文件目录 */
	@Property(key = "screen_file_dir")
	public static String SCREEN_FILE_DIR = "D:\\排队机服务器\\info";

	/** 会议显示屏配置文件目录 */
	@Property(key = "meeting_screen_file_dir")
	public static String MEETING_SCREEN_FILE_DIR = "D:\\排队机服务器\\info";

	/** 用户部门数据同步地址 */
	@Property(key = "data_sync_service")
	public static String DATA_SYNC_SERVICE;

	/** 用户部门数据同步用户名 */
	@Property(key = "data_sync_service_user")
	public static String DATA_SYNC_SERVICE_USER;

	/** 用户部门数据同步密码 */
	@Property(key = "data_sync_service_pwd")
	public static String DATA_SYNC_SERVICE_PWD;

}
