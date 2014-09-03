package com.verywell.iotp.admin.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.verywell.iotp.admin.entity.dev.DevAttrInfo;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;

public class DevInfoDTO implements Serializable
{
	private Long devId;

	private Integer alaramSwitch;

	private String alarmReceiver;

	private Integer alarmStatus;

	private String devDesc;

	private String devName;

	private Integer devRole;

	private Integer devStatus;

	private String macAddr;

	private String networkAddr;

	private Integer positionX;

	private Integer positionY;

	private Long roomId;

	private String devStatusDesc;

	private String alarmStatusDesc;

	private String lastestData;

	private Long devClassId;

	private String devClassName;

	private Long devClassGroupId;

	private String currentClass;

	private String roomName;

	private Map<String, String> attrMap = new HashMap<String, String>();

	public DevInfoDTO()
	{

	}

	public DevInfoDTO(DevInfo devInfo)
	{
		this.alaramSwitch = devInfo.getAlaramSwitch();
		this.alarmReceiver = devInfo.getAlarmReceiver();
		this.alarmStatus = devInfo.getAlarmStatus();
		this.alarmStatusDesc = devInfo.getAlarmStatusDesc();
		DevClassInfo classInfo = devInfo.getDevClassInfo();
		this.devClassId = classInfo.getDevClassId();
		this.devClassName = classInfo.getClassName();
		this.devClassGroupId = classInfo.getDevClassGroupInfo().getGroupId();
		this.devDesc = devInfo.getDevDesc();
		this.devId = devInfo.getDevId();
		this.devName = devInfo.getDevName();
		this.devRole = devInfo.getDevRole();
		this.devStatus = devInfo.getDevStatus();
		this.devStatusDesc = devInfo.getDevStatusDesc();
		this.lastestData = devInfo.getLastestData();
		this.macAddr = devInfo.getMacAddr();
		this.networkAddr = devInfo.getNetworkAddr();
		this.positionX = devInfo.getPositionX();
		this.positionY = devInfo.getPositionY();
		this.roomId = devInfo.getRoomId();
		List<DevAttrInfo> attrList = devInfo.getDevAttrInfos();
		if (attrList != null && !attrList.isEmpty())
		{
			for (DevAttrInfo devAttrInfo : attrList)
			{
				attrMap.put(devAttrInfo.getAttrKey(), devAttrInfo.getAttrValue());
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

	public Integer getAlaramSwitch()
	{
		return alaramSwitch;
	}

	public void setAlaramSwitch(Integer alaramSwitch)
	{
		this.alaramSwitch = alaramSwitch;
	}

	public String getAlarmReceiver()
	{
		return alarmReceiver;
	}

	public void setAlarmReceiver(String alarmReceiver)
	{
		this.alarmReceiver = alarmReceiver;
	}

	public Integer getAlarmStatus()
	{
		return alarmStatus;
	}

	public void setAlarmStatus(Integer alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}

	public String getDevDesc()
	{
		return devDesc;
	}

	public void setDevDesc(String devDesc)
	{
		this.devDesc = devDesc;
	}

	public String getDevName()
	{
		return devName;
	}

	public void setDevName(String devName)
	{
		this.devName = devName;
	}

	public Integer getDevRole()
	{
		return devRole;
	}

	public void setDevRole(Integer devRole)
	{
		this.devRole = devRole;
	}

	public Integer getDevStatus()
	{
		return devStatus;
	}

	public void setDevStatus(Integer devStatus)
	{
		this.devStatus = devStatus;
	}

	public String getMacAddr()
	{
		return macAddr;
	}

	public void setMacAddr(String macAddr)
	{
		this.macAddr = macAddr;
	}

	public String getNetworkAddr()
	{
		return networkAddr;
	}

	public void setNetworkAddr(String networkAddr)
	{
		this.networkAddr = networkAddr;
	}

	public Integer getPositionX()
	{
		return positionX;
	}

	public void setPositionX(Integer positionX)
	{
		this.positionX = positionX;
	}

	public Integer getPositionY()
	{
		return positionY;
	}

	public void setPositionY(Integer positionY)
	{
		this.positionY = positionY;
	}

	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	public String getDevStatusDesc()
	{
		return devStatusDesc;
	}

	public void setDevStatusDesc(String devStatusDesc)
	{
		this.devStatusDesc = devStatusDesc;
	}

	public String getAlarmStatusDesc()
	{
		return alarmStatusDesc;
	}

	public void setAlarmStatusDesc(String alarmStatusDesc)
	{
		this.alarmStatusDesc = alarmStatusDesc;
	}

	public String getLastestData()
	{
		return lastestData;
	}

	public void setLastestData(String lastestData)
	{
		this.lastestData = lastestData;
	}

	public Long getDevClassId()
	{
		return devClassId;
	}

	public void setDevClassId(Long devClassId)
	{
		this.devClassId = devClassId;
	}

	public String getDevClassName()
	{
		return devClassName;
	}

	public void setDevClassName(String devClassName)
	{
		this.devClassName = devClassName;
	}

	public Long getDevClassGroupId()
	{
		return devClassGroupId;
	}

	public void setDevClassGroupId(Long devClassGroupId)
	{
		this.devClassGroupId = devClassGroupId;
	}

	public Map<String, String> getAttrMap()
	{
		return attrMap;
	}

	public void setAttrMap(Map<String, String> attrMap)
	{
		this.attrMap = attrMap;
	}

	public String getCurrentClass()
	{
		return currentClass;
	}

	public void setCurrentClass(String currentClass)
	{
		this.currentClass = currentClass;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
}
