package com.verywell.iotp.admin.entity.dev;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verywell.iotp.admin.constants.DevAttributeConstants;
import com.verywell.iotp.admin.entity.AuditableEntity;

/**
 * The persistent class for the DEV_INFO database table.
 * 
 */
@Entity
@Table(name = "DEV_INFO")
public class DevInfo extends AuditableEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * 设备状态 - 关闭
	 */
	public static final Integer DEV_STATUS_CLOSE = 0;
	/**
	 * 设备状态 - 开启
	 */
	public static final Integer DEV_STATUS_OPEN = 1;
	/**
	 * 设备状态 - 故障
	 */
	public static final Integer DEV_STATUS_ERROR = 2;
	/**
	 * 设备状态 - 停用
	 */
	public static final Integer DEV_STATUS_DELETE = 3;
	/**
	 * 设备告警状态 - 正常
	 */
	public static final Integer ALARM_STATUS_NORMAL = 0;

	/**
	 * 设备告警状态 - 异常
	 */
	public static final Integer ALARM_STATUS_ABNORMAL = 1;

	/**
	 * 设备告警开关 - 开启
	 */
	public static final Integer ALARM_SWITCH_ON = 1;

	/**
	 * 设备告警开关 - 关闭
	 */
	public static final Integer ALARM_SWITCH_OFF = 0;

	private Long devId;

	private Integer alaramSwitch;

	private String alarmReceiver;

	private Integer alarmStatus;

	private String devDesc;

	private String devName;

	private Integer devRole;

	private Integer devStatus;

	private String macAddr;

	private String networkAddr;

	private Integer positionX;

	private Integer positionY;

	private Long roomId;

	private List<DevAlarmConfigInfo> devAlarmConfigInfos;

	private List<DevAttrInfo> devAttrInfos;

	private String devStatusDesc;

	private String alarmStatusDesc;

	private String lastestData;// 设备最新数据

	private DevClassInfo devClassInfo;

	public DevInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "DEV_ID_GENERATOR", sequenceName = "SEQ_DEV_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEV_ID_GENERATOR")
	@Column(name = "DEV_ID")
	public Long getDevId()
	{
		return devId;
	}

	public void setDevId(Long devId)
	{
		this.devId = devId;
	}

	@Column(name = "ALARAM_SWITCH")
	public Integer getAlaramSwitch()
	{
		return alaramSwitch;
	}

	public void setAlaramSwitch(Integer alaramSwitch)
	{
		this.alaramSwitch = alaramSwitch;
	}

	@Column(name = "ALARM_RECEIVER")
	public String getAlarmReceiver()
	{
		return alarmReceiver;
	}

	public void setAlarmReceiver(String alarmReceiver)
	{
		this.alarmReceiver = alarmReceiver;
	}

	@Column(name = "ALARM_STATUS")
	public Integer getAlarmStatus()
	{
		return alarmStatus;
	}

	public void setAlarmStatus(Integer alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}

	@Column(name = "DEV_DESC")
	public String getDevDesc()
	{
		return devDesc;
	}

	public void setDevDesc(String devDesc)
	{
		this.devDesc = devDesc;
	}

	@Column(name = "DEV_NAME")
	public String getDevName()
	{
		return devName;
	}

	public void setDevName(String devName)
	{
		this.devName = devName;
	}

	@Column(name = "DEV_ROLE")
	public Integer getDevRole()
	{
		return devRole;
	}

	public void setDevRole(Integer devRole)
	{
		this.devRole = devRole;
	}

	@Column(name = "DEV_STATUS")
	public Integer getDevStatus()
	{
		return devStatus;
	}

	public void setDevStatus(Integer devStatus)
	{
		this.devStatus = devStatus;
	}

	@Column(name = "MAC_ADDR")
	public String getMacAddr()
	{
		return macAddr;
	}

	public void setMacAddr(String macAddr)
	{
		this.macAddr = macAddr;
	}

	@Column(name = "NETWORK_ADDR")
	public String getNetworkAddr()
	{
		return networkAddr;
	}

	public void setNetworkAddr(String networkAddr)
	{
		this.networkAddr = networkAddr;
	}

	@Column(name = "POSITION_X")
	public Integer getPositionX()
	{
		return positionX;
	}

	public void setPositionX(Integer positionX)
	{
		this.positionX = positionX;
	}

	@Column(name = "POSITION_Y")
	public Integer getPositionY()
	{
		return positionY;
	}

	public void setPositionY(Integer positionY)
	{
		this.positionY = positionY;
	}

	@Column(name = "ROOM_ID")
	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devInfo")
	public List<DevAlarmConfigInfo> getDevAlarmConfigInfos()
	{
		return devAlarmConfigInfos;
	}

	public void setDevAlarmConfigInfos(List<DevAlarmConfigInfo> devAlarmConfigInfos)
	{
		this.devAlarmConfigInfos = devAlarmConfigInfos;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devInfo")
	public List<DevAttrInfo> getDevAttrInfos()
	{
		return devAttrInfos;
	}

	public void setDevAttrInfos(List<DevAttrInfo> devAttrInfos)
	{
		this.devAttrInfos = devAttrInfos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEV_CLASS_ID")
	public DevClassInfo getDevClassInfo()
	{
		return devClassInfo;
	}

	public void setDevClassInfo(DevClassInfo devClassInfo)
	{
		this.devClassInfo = devClassInfo;
	}

	@Transient
	public String getDevStatusDesc()
	{
		String devStatusDesc = "";
		if (DevInfo.DEV_STATUS_OPEN.equals(devStatus))
			devStatusDesc = "运行中";
		else if (DevInfo.DEV_STATUS_CLOSE.equals(devStatus))
			devStatusDesc = "停止";
		else if (DevInfo.DEV_STATUS_ERROR.equals(devStatus))
			devStatusDesc = "故障";
		else if (DevInfo.DEV_STATUS_DELETE.equals(devStatus))
			devStatusDesc = "无效";
		return devStatusDesc;
	}

	@Transient
	public String getAlarmStatusDesc()
	{
		String alarmStatusDesc = "";
		if (DevInfo.ALARM_STATUS_NORMAL.equals(alarmStatus))
			alarmStatusDesc = "正常";
		else if (DevInfo.ALARM_STATUS_ABNORMAL.equals(alarmStatus))
			alarmStatusDesc = "异常";
		return alarmStatusDesc;
	}

	@Transient
	public String getLastestData()
	{
		Map<String, String> attrMap = new HashMap<String, String>();
		List<DevAttrInfo> attrList = this.getDevAttrInfos();
		if (attrList != null && !attrList.isEmpty())
		{
			for (DevAttrInfo devAttrInfo : attrList)
			{
				attrMap.put(devAttrInfo.getAttrKey(), devAttrInfo.getAttrValue());
			}
		}
		// 空调最新数据
		if (this.getDevClassInfo().getDevClassGroupInfo().getGroupId().equals(DevClassGroupInfo.CLASS_GROUP_AIR))
		{
			StringBuffer data = new StringBuffer("");
			if (attrMap.get(DevAttributeConstants.KEY_AIR_MODE) != null)
			{
				data.append(DevAttributeConstants.getAirModeDesc(attrMap.get(DevAttributeConstants.KEY_AIR_MODE)));
				data.append(",");
			}

			if (attrMap.get(DevAttributeConstants.KEY_AIR_TEMP) != null)
			{
				data.append(attrMap.get(DevAttributeConstants.KEY_AIR_TEMP));
				data.append("度,");
			}
			if (attrMap.get(DevAttributeConstants.KEY_AIR_WIND) != null)
			{
				data.append(DevAttributeConstants.getAirWindDesc(attrMap.get(DevAttributeConstants.KEY_AIR_WIND)));
			}
			return data.toString();
		}
		if (this.getDevClassInfo().getDevClassGroupInfo().getGroupId().equals(DevClassGroupInfo.CLASS_GROUP_SENSOR))
		{
			StringBuffer data = new StringBuffer("");
			if (attrMap.get(DevAttributeConstants.KEY_TEMPERATURE) != null)
			{
				data.append("温度：");
				data.append(attrMap.get(DevAttributeConstants.KEY_TEMPERATURE) + ";");
				data.append("度;");
			}

			if (attrMap.get(DevAttributeConstants.KEY_HUMIDITY) != null)
			{
				data.append("湿度：");
				data.append(attrMap.get(DevAttributeConstants.KEY_HUMIDITY));
				data.append("%RH;");
			}
			if (attrMap.get(DevAttributeConstants.KEY_LIGHT) != null)
			{
				data.append("光感：");
				data.append(attrMap.get(DevAttributeConstants.KEY_LIGHT));
				data.append("%RH;");
			}
			return data.toString();
		}
		return "";
	}

	@Transient
	public Map<String, String> getAttrMap()
	{
		Map<String, String> attrMap = new HashMap<String, String>();
		List<DevAttrInfo> attrList = this.getDevAttrInfos();
		if (attrList != null && !attrList.isEmpty())
		{
			for (DevAttrInfo devAttrInfo : attrList)
			{
				attrMap.put(devAttrInfo.getAttrKey(), devAttrInfo.getAttrValue());
			}
		}
		return attrMap;
	}

	@Transient
	public String getCurrentClass()
	{
		if (this.getDevStatus().equals(DEV_STATUS_ERROR))
		{
			return this.getDevClassInfo().getInvalidClass();
		}
		else
		{
			if (ALARM_STATUS_ABNORMAL.equals(this.getAlarmStatus()))
			{
				return this.getDevClassInfo().getAlarmClass();
			}
			else
			{
				if (this.getDevStatus().equals(DEV_STATUS_OPEN))
				{
					return this.getDevClassInfo().getOpenClass();
				}
				else if (this.getDevStatus().equals(DEV_STATUS_CLOSE))
				{
					return this.getDevClassInfo().getCloseClass();
				}
			}
		}
		return "";
	}
	
	public String toString()
	{
		return "roomId:" + roomId + " devName:" + devName +   " devId:" + devId +  " devStatus:" + devStatus;
	}
}