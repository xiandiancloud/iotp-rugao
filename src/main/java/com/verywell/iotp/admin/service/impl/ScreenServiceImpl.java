package com.verywell.iotp.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.iotp.admin.dto.ScreenInfo;
import com.verywell.iotp.admin.service.ScreenService;

/**
 * 显示屏相关服务
 * 
 * @author Yao
 * 
 */
@Service
@Transactional(readOnly = true)
public class ScreenServiceImpl implements ScreenService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public ScreenInfo getScreenInfo(String fileDir, String screenFileName)
	{
		ScreenInfo screenInfo = new ScreenInfo();
		String filePath = fileDir + File.separator + screenFileName;
		File configFile = new File(filePath);
		if (!configFile.exists())
		{
			logger.info("未找到显示屏的配置文件!文件地址：" + filePath);
		}
		else
		{
			try
			{
				List<String> lines = FileUtils.readLines(configFile, "gbk");
				for (String line : lines)
				{
					try
					{
						if (line.indexOf("$config1") == 0)
						{
							String[] configArray = line.split(";");
							screenInfo.setTitleTextSize(configArray[0].substring(configArray[0].indexOf("(") + 1, configArray[0].indexOf(",")));
							screenInfo.setTitleTextColor(configArray[0].substring(configArray[0].indexOf(",") + 1, configArray[0].indexOf(")")));
							screenInfo.setContentTextSize(configArray[1].substring(configArray[1].indexOf("(") + 1, configArray[1].indexOf(",")));
							screenInfo.setContentTextColor(configArray[1].substring(configArray[1].indexOf(",") + 1, configArray[1].indexOf(")")));
							screenInfo.setBackgroundColor(configArray[2].substring(configArray[2].indexOf("(") + 1, configArray[2].indexOf(",")));
							screenInfo.setIntervalTime(configArray[2].substring(configArray[2].indexOf(",") + 1, configArray[2].indexOf(")")));
						}
						else if (line.indexOf("title：") == 0)
						{
							screenInfo.setTitle(line.substring(line.indexOf("：") + 1));
						}
						else if (line.indexOf("message") == 0)
						{
							screenInfo.addMessage(line.substring(line.indexOf("：") + 1));
						}
						else
							continue;
					}
					catch (Exception e)
					{
						continue;
					}
				}

			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return screenInfo;
	}

	@Override
	public void saveScreenInfo(ScreenInfo screenInfo, String fileDir, String screenFileName) throws Exception
	{
		List<String> lines = new ArrayList<String>();
		StringBuffer line1 = new StringBuffer("$config1(" + screenInfo.getTitleTextSize() + "," + screenInfo.getTitleTextColor() + ");");
		line1.append("$config2(" + screenInfo.getContentTextSize() + "," + screenInfo.getContentTextColor() + ");");
		line1.append("$backolor(" + screenInfo.getBackgroundColor() + "," + screenInfo.getIntervalTime() + ");");
		lines.add(line1.toString());
		lines.add("");
		lines.add("title：" + screenInfo.getTitle());
		for (int i = 0; i < screenInfo.getMessages().size(); i++)
		{
			lines.add("message" + (i + 1) + "：" + screenInfo.getMessages().get(i));
		}
		// 最后需要增加一行空行，否则最后一行文字无法显示
		// lines.add("");

		File configFile = new File(fileDir + File.separator + screenFileName);
		FileUtils.writeLines(configFile, "gbk", lines);

	}
}
