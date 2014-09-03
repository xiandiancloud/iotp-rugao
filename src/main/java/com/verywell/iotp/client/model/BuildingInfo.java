package com.verywell.iotp.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * 大楼Model对象
 * 
 * @author yao
 * 
 */
public class BuildingInfo implements Serializable
{
	private Long buildingId;

	private String buildingName;

	private List<RoomInfo> rooms;

	public Long getBuildingId()
	{
		return buildingId;
	}

	public void setBuildingId(Long buildingId)
	{
		this.buildingId = buildingId;
	}

	public String getBuildingName()
	{
		return buildingName;
	}

	public void setBuildingName(String buildingName)
	{
		this.buildingName = buildingName;
	}

	public List<RoomInfo> getRooms()
	{
		return rooms;
	}

	public void setRooms(List<RoomInfo> rooms)
	{
		this.rooms = rooms;
	}

}
