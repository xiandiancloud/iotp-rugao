package com.verywell.iotp.client.facade.result;

/**
 * GameService返回结果基类,定义所有返回码.
 * 
 * @author Yao
 */
public class ServiceResult
{

	// -- 返回代码定义 --//
	// 统一错误码，其他模块错误码统一4位
	public static final int SUCCESS = 0;
	public static final int SYSTEM_ERROR = 500;
	public static final String SYSTEM_ERROR_MESSAGE = "系统忙，请稍后再试！";

	public static final int LOGIN_FAILED = 1001;
	public static final String LOGIN_FAILED_MESSAGE = "登录信息错误！请检查用户和密码！";

	public static final int MEETING_SAVE_FAILED = 2001;
	public static final String MEETING_SAVE_FAILED_MESSAGE = "会议预定失败！该时间段已经被预定，请更换其他时间！";
	public static final int MEETING_CANCEL_FAILED = 2001;
	public static final String MEETING_CANCEL_FAILED_MESSAGE = "会议取消失败！";

	// -- ServiceResult基本属性 --//
	private int resultCode = SUCCESS;
	private String resultMessage = "SUCCESS";

	/**
	 * 创建结果.
	 */
	public <T extends ServiceResult> T setError(int resultCode, String resultMessage)
	{
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		return (T) this;
	}

	/**
	 * 创建默认异常结果.
	 */
	public <T extends ServiceResult> T setDefaultError()
	{
		return (T) setError(SYSTEM_ERROR, SYSTEM_ERROR_MESSAGE);
	}

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(int resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getResultMessage()
	{
		return resultMessage;
	}

	public void setResultMessage(String resultMessage)
	{
		this.resultMessage = resultMessage;
	}

	public <T extends ServiceResult> T setSuccessMessage(String successMessage)
	{
		resultCode = SUCCESS;
		resultMessage = successMessage;
		return (T) this;
	}

}
