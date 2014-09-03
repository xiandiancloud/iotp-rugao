/**
 * 
 */
package com.verywell.iotp.client.facade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.service.SysLoginService;
import com.verywell.iotp.client.facade.result.LoginResult;
import com.verywell.iotp.client.facade.result.ServiceResult;

/**
 * 
 * @title:登录操作控制器
 * @description:
 * 
 * @author: Yao
 * 
 */
@Controller
@RequestMapping("/clientService/login")
public class LoginFacade extends BaseController
{
	@Autowired
	private SysLoginService sysLoginService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param loginName
	 * @param loginPwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public LoginResult login(HttpServletRequest request, String loginName, String loginPwd) throws Exception
	{
		LoginResult result = new LoginResult();
		SysLogin sysLogin = sysLoginService.login(loginName, loginPwd, request);
		if (sysLogin != null)
		{
			result.setLoginId(sysLogin.getLoginId());
			result.setLoginName(sysLogin.getLoginName());
		}
		else
		{
			result.setError(ServiceResult.LOGIN_FAILED, ServiceResult.LOGIN_FAILED_MESSAGE);
		}
		return result;
	}
}
