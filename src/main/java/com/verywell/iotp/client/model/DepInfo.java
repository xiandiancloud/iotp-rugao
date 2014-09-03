package com.verywell.iotp.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * 参会人员部门Model对象
 * 
 * @author yao
 * 
 */
public class DepInfo implements Serializable
{
	private Long depId;
	private String depName;
	private List<UserInfo> users;

	public Long getDepId()
	{
		return depId;
	}

	public void setDepId(Long depId)
	{
		this.depId = depId;
	}

	public String getDepName()
	{
		return depName;
	}

	public void setDepName(String depName)
	{
		this.depName = depName;
	}

	public List<UserInfo> getUsers()
	{
		return users;
	}

	public void setUsers(List<UserInfo> users)
	{
		this.users = users;
	}

}
