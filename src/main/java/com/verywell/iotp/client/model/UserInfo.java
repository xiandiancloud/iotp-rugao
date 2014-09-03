package com.verywell.iotp.client.model;

import java.io.Serializable;

/**
 * 参会人员Model对象
 * 
 * @author yao
 * 
 */
public class UserInfo implements Serializable
{
	private Long userId;
	private String userName;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
