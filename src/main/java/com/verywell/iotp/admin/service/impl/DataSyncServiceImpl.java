package com.verywell.iotp.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.SphinxWSLocator;
import org.tempuri.SphinxWSSoap;

import com.verywell.framework.utils.DateTimeUtil;
import com.verywell.framework.utils.HttpUtil;
import com.verywell.framework.utils.MD5Utils;
import com.verywell.framework.utils.io.XMLUtil;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.dao.SysDeptDAO;
import com.verywell.iotp.admin.dao.SysLoginDAO;
import com.verywell.iotp.admin.dao.SysLoginRoleDAO;
import com.verywell.iotp.admin.entity.sys.SysCorp;
import com.verywell.iotp.admin.entity.sys.SysDept;
import com.verywell.iotp.admin.entity.sys.SysLog;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.entity.sys.SysLoginRole;
import com.verywell.iotp.admin.entity.sys.SysRole;
import com.verywell.iotp.admin.service.DataSyncService;
import com.verywell.iotp.admin.service.SysLogService;

/**
 * 部门用户数据同步
 * 
 * @author Yao
 * 
 */
@Service
public class DataSyncServiceImpl implements DataSyncService
{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysDeptDAO departmentDAO;

	@Autowired
	private SysLoginDAO sysLoginDAO;

	@Autowired
	private SysLoginRoleDAO sysLoginRoleDAO;

	@Autowired
	private SysLogService sysLogService;

	@Override
	@Transactional(readOnly = false)
	public void startSync()
	{

		deptDataSync();
		departmentDAO.flush();
		// userDataSync();

	}

	/**
	 * 部门信息同步
	 */
	private void deptDataSync()
	{

		try
		{
			CommonConstants.DATA_SYNC_SERVICE = "http://172.16.0.120:7001/WebService/SphinxWS.asmx";
			CommonConstants.DATA_SYNC_SERVICE_PWD = "system";
			CommonConstants.DATA_SYNC_SERVICE_USER = "administrator";

			SphinxWSSoap service = new SphinxWSLocator().getSphinxWSSoap(new URL(CommonConstants.DATA_SYNC_SERVICE));
			String timestamp = DateTimeUtil.getChar14();
			String validCode = MD5Utils.toMD5(timestamp + CommonConstants.DATA_SYNC_SERVICE_PWD);
			String reponseXml = service.getDepartments(CommonConstants.DATA_SYNC_SERVICE_USER, timestamp, 0, validCode);
			logger.info("reponseXml==" + reponseXml);
			XMLUtil xmlParse = XMLUtil.getInsance(new ByteArrayInputStream(reponseXml.getBytes("utf-8")));
			// 返回状态码
			String resultCode = xmlParse.getSingleElementText("/KeyKing/GetDepartments/Head/ErrCode");
			logger.info("resultCode==" + resultCode);
			if (resultCode.equals("0"))
			{
				List<Element> elements = xmlParse.getAllElements("/KeyKing/GetDepartments/Body/Item");
				if (elements != null && elements.size() > 0)
				{
					for (Element element : elements)
					{

						try
						{
							Long deptId = Long.valueOf(XMLUtil.getSingleElementText(element, "DeptID"));
							String deptName = XMLUtil.getSingleElementText(element, "DeptName");
							String parentDeptId = XMLUtil.getSingleElementText(element, "ParentDeptID");
							SysDept sysDept = departmentDAO.findById(deptId);
							// 新增部门
							if (sysDept == null)
							{
								sysDept = new SysDept();
								sysDept.setDeptId(deptId);
								sysDept.setDeptName(deptName);
								sysDept.setCorpId(0l);
								sysDept.setStatus(CommonConstants.STATUS_VALID);
								if (parentDeptId != null && !parentDeptId.equals(""))
									sysDept.setParentDeptId(Long.valueOf(parentDeptId));
								departmentDAO.save(sysDept);
								sysLogService.save(SysLog.OPERATE_TYPE_ADD, "【数据同步-新增部门】部门编号：" + deptId + " 部门名称：" + deptName);
							}
							// 修改部门
							else
							{
								sysDept.setDeptName(deptName);
								if (parentDeptId != null && !parentDeptId.equals(""))
									sysDept.setParentDeptId(Long.valueOf(parentDeptId));
								departmentDAO.update(sysDept);
								sysLogService.save(SysLog.OPERATE_TYPE_UPDATE, "【数据同步-修改部门】部门编号：" + deptId + " 部门名称：" + deptName);
							}
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 用户信息同步
	 */
	private void userDataSync()
	{
		List<SysDept> deptList = departmentDAO.findAll();
		for (SysDept sysDept : deptList)
		{
			try
			{
				CommonConstants.DATA_SYNC_SERVICE = "http://172.16.0.120:7001/WebService/SphinxWS.asmx";
				CommonConstants.DATA_SYNC_SERVICE_PWD = "system";
				CommonConstants.DATA_SYNC_SERVICE_USER = "administrator";
				SphinxWSSoap service = new SphinxWSLocator().getSphinxWSSoap(new URL(CommonConstants.DATA_SYNC_SERVICE));
				String timestamp = DateTimeUtil.getChar14();
				String validCode = MD5Utils.toMD5(timestamp + CommonConstants.DATA_SYNC_SERVICE_PWD);
				String reponseXml = service.getPersonInfos(CommonConstants.DATA_SYNC_SERVICE_USER, timestamp, -1, sysDept.getDeptId().intValue(), false,
						validCode);
				logger.info("reponseXml==" + reponseXml);
				XMLUtil xmlParse = XMLUtil.getInsance(new ByteArrayInputStream(reponseXml.getBytes("utf-8")));
				// 返回状态码
				String resultCode = xmlParse.getSingleElementText("/KeyKing/GetPersonInfos/Head/ErrCode");
				logger.info("resultCode==" + resultCode);
				if ("0".equals(resultCode))
				{
					List<Element> elements = xmlParse.getAllElements("/KeyKing/GetPersonInfos/Body/Item");
					if (elements != null && elements.size() > 0)
					{
						for (Element element : elements)
						{

							try
							{
								String userId = XMLUtil.getSingleElementText(element, "PersonnalID");
								String userName = XMLUtil.getSingleElementText(element, "Name");
								Long deptId = Long.valueOf(XMLUtil.getSingleElementText(element, "DeptID"));
								String phone = XMLUtil.getSingleElementText(element, "Phone");
								List<SysLogin> list = sysLoginDAO.findByProperty("userCode", userId);
								// 新增用户
								if (list == null || list.size() == 0)
								{
									SysLogin sysLogin = new SysLogin();
									sysLogin.setUserCode(userId);
									sysLogin.setLoginName(userName);
									sysLogin.setUserName(userName);
									sysLogin.setSysCorp(new SysCorp(0l));
									sysLogin.setLoginPwd(MD5Utils.toMD5("123456"));
									sysLogin.setStatus(CommonConstants.STATUS_VALID);
									sysLogin.setUserType(1);
									sysLogin.setRecordCorpId(0l);
									sysLogin.setSysDept(new SysDept(deptId));
									sysLogin.setSystemId(1);
									sysLogin.setTel(phone);
									sysLogin.setValidTag("1");
									sysLoginDAO.save(sysLogin);
									SysLoginRole sysLoginRole = new SysLoginRole();
									sysLoginRole.setSysLogin(sysLogin);
									sysLoginRole.setSysRole(new SysRole(1l));
									sysLoginRoleDAO.save(sysLoginRole);
									sysLogService.save(SysLog.OPERATE_TYPE_ADD, "【数据同步-新增用户】用户姓名：" + userName);
								}
								// 修改用户
								else
								{
									// sysLogin.setTel(phone);
									// sysLoginDAO.update(sysLogin);
								}
							}
							catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
