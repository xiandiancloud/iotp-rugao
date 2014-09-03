package com.verywell.iotp.client.facade.result;


public class LoginResult extends ServiceResult
{
	private Long loginId;
	private String loginName;

	public Long getLoginId()
	{
		return loginId;
	}

	public void setLoginId(Long loginId)
	{
		this.loginId = loginId;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

}
