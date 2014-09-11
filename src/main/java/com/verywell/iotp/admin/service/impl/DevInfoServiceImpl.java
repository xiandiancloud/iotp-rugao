package com.verywell.iotp.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.framework.utils.HttpUtil;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dao.DevAttrInfoDAO;
import com.verywell.iotp.admin.dao.SceneConfigInfoDAO;
import com.verywell.iotp.admin.entity.dev.DevAttrInfo;
import com.verywell.iotp.admin.entity.dev.DevAttributes;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.scene.SceneConfigInfo;
import com.verywell.iotp.admin.service.DevAttributesService;
import com.verywell.iotp.admin.service.DevInfoService;

/**
 * 设备相关服务实现类
 * 
 * @author yao
 * 
 */
@Service
@Transactional(readOnly = true)
public class DevInfoServiceImpl extends BaseCrudServiceImpl<DevInfo, Long> implements DevInfoService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DevAttributesService devAttributesService;

	@Autowired
	private DevAttrInfoDAO devAttrInfoDAO;

	@Autowired
	private SceneConfigInfoDAO sceneConfigInfoDAO;
	
	@Autowired
	@Override
	@Qualifier(value = "devInfoDAO")
	public void setBaseDao(BaseHibernateDAO<DevInfo, Long> baseDao)
	{
		this.baseDao = baseDao;
	}

	@Override
	public long getAlarmDevCountByRoomId(Long roomId) throws Exception
	{
		String hql = "select count(*) from DevInfo dev where dev.roomId=? and dev.alarmStatus=" + DevInfo.ALARM_STATUS_ABNORMAL;
		return baseDao.findLong(hql, roomId);
	}

	@Override
	public List<DevInfo> findByRoomId(Long roomId) throws Exception
	{
		String hql = "from DevInfo dev where dev.roomId=? and dev.devStatus!=" + DevInfo.DEV_STATUS_DELETE;
		List<DevInfo> list = baseDao.findByHql(hql, roomId);
		return list;
	}

	@Override
	public DevInfo findById(Long id) throws Exception
	{
		DevInfo devInfo = baseDao.findById(id);
		return devInfo;
	}

	@Override
	public List<DevInfo> findByClassGroupId(Long roomId, Long classGroupId) throws Exception
	{
		List<DevInfo> resultList = new ArrayList<DevInfo>();
		String hql = "from DevInfo dev where dev.devClassInfo.devClassGroupInfo.groupId=? and dev.roomId=? and dev.devStatus!=" + DevInfo.DEV_STATUS_DELETE
				+ " order by dev.devName";
		return baseDao.findByHql(hql, classGroupId, roomId);
	}

	@Transactional(readOnly = false)
	@Override
	public int updatePosition(Long devId, Integer posX, Integer posY) throws Exception
	{
		DevInfo devInfo = baseDao.findById(devId);
		if (devInfo != null)
		{
			devInfo.setPositionX(posX);
			devInfo.setPositionY(posY);
			baseDao.update(devInfo);
			return ResultConstants.UPDATE_SUCCEED;
		}
		else
		{
			return ResultConstants.UPDATE_FAILED;
		}
	}

	@Transactional(readOnly = false)
	@Override
	public int delete(Long devId) throws Exception
	{
		DevInfo devInfo = baseDao.findById(devId);
		if (devInfo != null)
		{
			devInfo.setDevStatus(DevInfo.DEV_STATUS_DELETE);
			baseDao.update(devInfo);
			return ResultConstants.DELETE_SUCCEED;
		}
		else
		{
			return ResultConstants.DELETE_ERR0R;
		}
	}

	/**
	 * 根据设备类型分组获得相应的控制服务地址
	 * 
	 * @param devClassGroupId
	 * @return
	 * @throws Exception
	 */
	private String getServiceUrl(Long devClassGroupId) throws Exception
	{
		if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_LIGHT))
			return CommonConstants.LIGHT_SERVICE;
		else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_DOOR))
			return CommonConstants.DOOR_SERVICE;
		else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_AIR))
			return CommonConstants.AIR_CONDITIONER_SERVICE;
		else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_SENSOR))
			return CommonConstants.SENSOR_SERVICE;
		else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_CURTAIN))
			return CommonConstants.CURTAIN_SERVICE;
		else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_MUTIMEDIA))
			return CommonConstants.MUTIMEDIA_SERVICE;
		else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_GUANG))
			return CommonConstants.GUANG_SERVICE;
		return null;

	}

	@Override
	public String controllDev(String devIds, String cmd, String value) throws Exception
	{
		Long devId = null;
		int index = devIds.indexOf(",");
//		if (devIds.indexOf(",") >= 0)
//		{
//			devId = Long.valueOf(devIds.split(",")[0]);
//		}
//		else
//			devId = Long.valueOf(devIds);
		if (index != -1)
		{
			String result = "1";
			String[] strs = devIds.split(",");
			for (String str:strs)
			{
				devId = Long.valueOf(str);
				String re = realcontrollDev(devId,devIds,cmd,value);
				if (!result.equals(re))
				{
					result = re;
				}
			}
			return result;
		}
		else
		{
			devId = Long.valueOf(devIds);
			return realcontrollDev(devId,devIds,cmd,value);
		}
		
	}
	
	//光敏传感器，直接写死调用
	@Override
	public String lightcontrollDev(String devIds, String cmd, String value)  throws Exception
	{
		String resultCode = "0";
//		DevInfo devInfo = baseDao.findById(devId);
		Long devClassGroupId = 8L;
		String serviceUrl = getServiceUrl(devClassGroupId);

		if (serviceUrl != null)
		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("deviceId", String.valueOf(devIds));
			params.put("cmd", cmd);
			params.put("statusCmd", "1");
			params.put("macaddr", "1");
			if (value.indexOf(",") >= 0)
			{
				params.put("value", value.split(",")[0]);
				params.put("index", value.split(",")[1]);
			}
			else
			{
				params.put("value", value);
				params.put("index", "0");
			}
			resultCode = HttpUtil.URLGet(serviceUrl, params);
			
		}
//		devInfo.setDevStatus(new Integer(value));
//		baseDao.update(devInfo);//Update Data 
//		devInfo = baseDao.findById(devId);
		return resultCode;
	}
	
	private String realcontrollDev(Long devId,String devIds, String cmd, String value)  throws Exception
	{
		String resultCode = "0";
		DevInfo devInfo = baseDao.findById(devId);
		Long devClassGroupId = devInfo.getDevClassInfo().getDevClassGroupInfo().getGroupId();
		String serviceUrl = getServiceUrl(devClassGroupId);
		if (serviceUrl != null)
		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("deviceId", String.valueOf(devIds));
			params.put("cmd", cmd);
			params.put("statusCmd", "1");
			params.put("macaddr", devInfo.getMacAddr());
			if (value.indexOf(",") >= 0)
			{
				params.put("value", value.split(",")[0]);
				params.put("index", value.split(",")[1]);
			}
			else
			{
				params.put("value", value);
				params.put("index", "0");
			}
			resultCode = HttpUtil.URLGet(serviceUrl, params);
			if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_DOOR))
			{
				Thread.sleep(1000);
				Map<String, String> paramss = new HashMap<String, String>();
				paramss.put("deviceId", String.valueOf(devIds));
				paramss.put("cmd", cmd);
				paramss.put("statusCmd", "0");
				paramss.put("macaddr", devInfo.getMacAddr());
				if (value.indexOf(",") >= 0)
				{
					paramss.put("value", "0");
					paramss.put("index", value.split(",")[1]);
				}
				else
				{
					paramss.put("value", "0");
					paramss.put("index", "0");
				}
				HttpUtil.URLGet(serviceUrl, paramss);
			}
			else if (devClassGroupId.equals(DevClassGroupInfo.CLASS_GROUP_LIGHT))
			{
				Thread.sleep(1000);				
				Map<String, String> paramss = new HashMap<String, String>();
				paramss.put("deviceId", String.valueOf(devIds));
				paramss.put("cmd", cmd);
				paramss.put("statusCmd", "0");
				paramss.put("macaddr", devInfo.getMacAddr());
				if (value.indexOf(",") >= 0)
				{
					paramss.put("value", "0");
					paramss.put("index", value.split(",")[1]);
				}
				else
				{
					paramss.put("value", value);
					paramss.put("index", "0");
				}
				HttpUtil.URLGet(serviceUrl, paramss);
			}
		}
		devInfo.setDevStatus(new Integer(value));
		baseDao.update(devInfo);//Update Data 
//		devInfo = baseDao.findById(devId);
		return resultCode;
	}
	
	@Override
	public String setScene(Long sceneId) throws Exception
	{
		String result = "1";
		List<SceneConfigInfo> list = sceneConfigInfoDAO.getSceneConfigInfo(sceneId);
		if (list != null)
		{
			int len = list.size();
			if (len > 0)
			{
				for (int i=0;i<len;i++)
				{
					SceneConfigInfo info = list.get(i);
					Long devId = info.getDevId();
					
					String re = realcontrollDev(devId,devId+"",info.getAttrKey(),info.getAttrValue());
					if (!result.equals(re))
					{
						result = re;
					}
				}
			}
		}
		return result;
//		String resultCode = "0";
//		String serviceUrl = CommonConstants.SCENE_SERVICE;
//		if (serviceUrl != null)
//		{
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("sceneId", String.valueOf(sceneId));
//			resultCode = HttpUtil.URLGet(serviceUrl, params);
//		}
//		return resultCode;
	}

	@Override
	public List<DevInfo> findByClassGroupId(Long classGroupId) throws Exception
	{
		List<DevInfo> resultList = new ArrayList<DevInfo>();
		String hql = "from DevInfo dev where dev.devClassInfo.devClassGroupInfo.groupId=? and dev.devStatus!=" + DevInfo.DEV_STATUS_DELETE
				+ " order by dev.devName";
		return baseDao.findByHql(hql, classGroupId);
	}

	@Override
	public List<DevInfo> findByRoomIdAndExceptClassGroupId(Long roomId, Long... classGroupId) throws Exception
	{

		String hql = "from DevInfo dev where dev.roomId=? and dev.devClassInfo.devClassGroupInfo.groupId not in(" + StringUtils.join(classGroupId, ",")
				+ ") and dev.devStatus!=" + DevInfo.DEV_STATUS_DELETE;
		List<DevInfo> list = baseDao.findByHql(hql, roomId);
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public int save(DevInfo entity) throws Exception
	{
		baseDao.save(entity);
		Long devClassId = entity.getDevClassInfo().getDevClassId();
		List<DevAttributes> attrList = devAttributesService.findByDevClassId(devClassId);
		if (attrList != null && attrList.size() > 0)
		{
			for (DevAttributes devAttributes : attrList)
			{
				DevAttrInfo devAttrInfo = new DevAttrInfo();
				devAttrInfo.setDevInfo(entity);
				devAttrInfo.setAttrKey(devAttributes.getAttrKey());
				devAttrInfo.setAttrValue("");
				devAttrInfoDAO.save(devAttrInfo);
			}
		}
		return ResultConstants.SAVE_SUCCEED;
	}

	@Override
	@Transactional(readOnly = false)
	public int update(DevInfo entity) throws Exception
	{
		baseDao.update(entity);
		baseDao.executeHQL("delete from DevAttrInfo d where d.devInfo.devId=?", entity.getDevId());
		Long devClassId = entity.getDevClassInfo().getDevClassId();
		List<DevAttributes> attrList = devAttributesService.findByDevClassId(devClassId);
		if (attrList != null && attrList.size() > 0)
		{
			for (DevAttributes devAttributes : attrList)
			{
				DevAttrInfo devAttrInfo = new DevAttrInfo();
				devAttrInfo.setDevInfo(entity);
				devAttrInfo.setAttrKey(devAttributes.getAttrKey());
				devAttrInfo.setAttrValue("");
				devAttrInfoDAO.save(devAttrInfo);
			}
		}
		return ResultConstants.UPDATE_SUCCEED;
	}
}
