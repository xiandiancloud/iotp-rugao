package com.verywell.iotp.admin.dto;

import java.util.List;

import com.verywell.iotp.admin.constants.DevAttributeConstants;
import com.verywell.iotp.admin.entity.dev.DevAttrInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;

/**
 * 空调对象 -用于控制面板设备控制
 * 
 * @author yao
 * 
 */
public class AirConditioningDTO
{
	private Long devId;// 设备ID
	private Integer devStatus;// 设备状态
	private String windSpeed;// 风量
	private String mode;// 模式
	private String temperature;// 温度

	public AirConditioningDTO(DevInfo devInfo)
	{
		this.devId = devInfo.getDevId();
		this.devStatus = devInfo.getDevStatus();
		if (devStatus.equals(DevInfo.DEV_STATUS_OPEN))
		{
			List<DevAttrInfo> attrList = devInfo.getDevAttrInfos();
			if (attrList != null && attrList.size() > 0)
			{
				for (DevAttrInfo devAttr : attrList)
				{
					if (devAttr.getAttrKey().equals(DevAttributeConstants.KEY_AIR_MODE))
						this.mode = devAttr.getAttrValue();
					if (devAttr.getAttrKey().equals(DevAttributeConstants.KEY_AIR_TEMP))
						this.temperature = devAttr.getAttrValue();
					if (devAttr.getAttrKey().equals(DevAttributeConstants.KEY_AIR_WIND))
						this.windSpeed = devAttr.getAttrValue();
				}
			}
		}
	}

	public Long getDevId()
	{
		return devId;
	}

	public void setDevId(Long devId)
	{
		this.devId = devId;
	}

	public Integer getDevStatus()
	{
		return devStatus;
	}

	public void setDevStatus(Integer devStatus)
	{
		this.devStatus = devStatus;
	}

	public String getWindSpeed()
	{
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed)
	{
		this.windSpeed = windSpeed;
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getTemperature()
	{
		return temperature;
	}

	public void setTemperature(String temperature)
	{
		this.temperature = temperature;
	}

}
