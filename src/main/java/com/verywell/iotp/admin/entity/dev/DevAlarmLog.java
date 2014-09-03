package com.verywell.iotp.admin.entity.dev;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.verywell.iotp.admin.entity.map.MapRoomInfo;

/**
 * The persistent class for the DEV_ALARM_LOG database table.
 * 
 */
@Entity
@Table(name = "DEV_ALARM_LOG")
public class DevAlarmLog implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String alarmDesc;

	private String alarmTime;

	private Integer alarmType;

	private Long devId;

	private String devName;

	private Long devClassId;

	private String disposeTime;

	private String receiverName;

	private String receiverPhone;

	private Integer status;

	private MapRoomInfo mapRoomInfo;

	public DevAlarmLog()
	{
	}

	@Id
	@SequenceGenerator(name = "DEV_ALARM_LOG_ID_GENERATOR", sequenceName = "SEQ_DEV_ALARM_LOG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEV_ALARM_LOG_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "ALARM_DESC")
	public String getAlarmDesc()
	{
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc)
	{
		this.alarmDesc = alarmDesc;
	}

	@Column(name = "ALARM_TIME")
	public String getAlarmTime()
	{
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime)
	{
		this.alarmTime = alarmTime;
	}

	@Column(name = "ALARM_TYPE")
	public Integer getAlarmType()
	{
		return alarmType;
	}

	public void setAlarmType(Integer alarmType)
	{
		this.alarmType = alarmType;
	}

	@Column(name = "DISPOSE_TIME")
	public String getDisposeTime()
	{
		return disposeTime;
	}

	public void setDisposeTime(String disposeTime)
	{
		this.disposeTime = disposeTime;
	}

	@Column(name = "RECEIVER_NAME")
	public String getReceiverName()
	{
		return receiverName;
	}

	public void setReceiverName(String receiverName)
	{
		this.receiverName = receiverName;
	}

	@Column(name = "RECEIVER_PHONE")
	public String getReceiverPhone()
	{
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone)
	{
		this.receiverPhone = receiverPhone;
	}

	@Column(name = "STATUS")
	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	@Column(name = "DEV_ID")
	public Long getDevId()
	{
		return devId;
	}

	public void setDevId(Long devId)
	{
		this.devId = devId;
	}

	@Column(name = "DEV_CLASS_ID")
	public Long getDevClassId()
	{
		return devClassId;
	}

	public void setDevClassId(Long devClassId)
	{
		this.devClassId = devClassId;
	}

	@ManyToOne
	@JoinColumn(name = "ROOM_ID")
	public MapRoomInfo getMapRoomInfo()
	{
		return mapRoomInfo;
	}

	public void setMapRoomInfo(MapRoomInfo mapRoomInfo)
	{
		this.mapRoomInfo = mapRoomInfo;
	}

	@Transient
	public String getDevName()
	{
		return devName;
	}

	public void setDevName(String devName)
	{
		this.devName = devName;
	}

}