package com.verywell.iotp.admin.service;

import com.verywell.iotp.admin.dto.ScreenInfo;

/**
 * 显示屏相关服务
 * 
 * @author Yao
 * 
 */
public interface ScreenService
{
	public ScreenInfo getScreenInfo(String fileDir, String screenFileName);

	public void saveScreenInfo(ScreenInfo screenInfo, String fileDir, String screenFileName) throws Exception;

}
