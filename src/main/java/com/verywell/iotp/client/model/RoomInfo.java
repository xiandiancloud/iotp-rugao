package com.verywell.iotp.client.model;

import java.io.Serializable;

/**
 * 房间Model对象
 * 
 * @author yao
 * 
 */
public class RoomInfo implements Serializable
{
	private Long roomId;

	private String roomImage;

	private String roomName;

	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	public String getRoomImage()
	{
		return roomImage;
	}

	public void setRoomImage(String roomImage)
	{
		this.roomImage = roomImage;
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
