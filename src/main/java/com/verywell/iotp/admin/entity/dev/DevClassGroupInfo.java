package com.verywell.iotp.admin.entity.dev;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the DEV_CLASS_GROUP_INFO database table.
 * 
 */
@Entity
@Table(name = "DEV_CLASS_GROUP_INFO")
public class DevClassGroupInfo implements Serializable
{
	/** 灯光 */
	public static Long CLASS_GROUP_LIGHT = Long.valueOf(1);
	/** 空调 */
	public static Long CLASS_GROUP_AIR = Long.valueOf(2);
	/** 传感器 */
	public static Long CLASS_GROUP_SENSOR = Long.valueOf(3);
	/** 传感器 */
	public static Long CLASS_GROUP_CURTAIN = Long.valueOf(4);
	/** 会议中控 */
	public static Long CLASS_GROUP_MUTIMEDIA = Long.valueOf(5);
	/** 显示屏 */
	public static Long CLASS_GROUP_SCREEN = Long.valueOf(6);
	/** 门禁 */
	public static Long CLASS_GROUP_DOOR = Long.valueOf(7);
	
	private static final long serialVersionUID = 1L;

	private Long groupId;

	private String groupName;

	public DevClassGroupInfo()
	{
	}

	@Id
	@Column(name = "GROUP_ID")
	public Long getGroupId()
	{
		return this.groupId;
	}

	public void setGroupId(Long groupId)
	{
		this.groupId = groupId;
	}

	@Column(name = "GROUP_NAME")
	public String getGroupName()
	{
		return this.groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

}