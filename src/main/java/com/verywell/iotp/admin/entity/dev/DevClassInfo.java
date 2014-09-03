package com.verywell.iotp.admin.entity.dev;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verywell.iotp.admin.entity.AuditableEntity;

/**
 * The persistent class for the DEV_CLASS_INFO database table.
 * 
 */
@Entity
@Table(name = "DEV_CLASS_INFO")
public class DevClassInfo extends AuditableEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Long devClassId;

	private String classDesc;

	private String className;
	private String openClass;
	private String closeClass;
	private String alarmClass;
	private String invalidClass;

	private List<DevAttributes> devAttributes;

	private DevClassGroupInfo devClassGroupInfo;

	private List<DevStatus> devStatuses;

	public DevClassInfo()
	{
	}

	@Id
	@Column(name = "DEV_CLASS_ID")
	public Long getDevClassId()
	{
		return devClassId;
	}

	public void setDevClassId(Long devClassId)
	{
		this.devClassId = devClassId;
	}

	@Column(name = "CLASS_DESC")
	public String getClassDesc()
	{
		return classDesc;
	}

	public void setClassDesc(String classDesc)
	{
		this.classDesc = classDesc;
	}

	@Column(name = "CLASS_NAME")
	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;

	}

	@Column(name = "OPEN_CLASS")
	public String getOpenClass()
	{
		return openClass;
	}

	public void setOpenClass(String openClass)
	{
		this.openClass = openClass;
	}

	@Column(name = "CLOSE_CLASS")
	public String getCloseClass()
	{
		return closeClass;
	}

	public void setCloseClass(String closeClass)
	{
		this.closeClass = closeClass;
	}

	@Column(name = "ALARM_CLASS")
	public String getAlarmClass()
	{
		return alarmClass;
	}

	public void setAlarmClass(String alarmClass)
	{
		this.alarmClass = alarmClass;
	}

	@Column(name = "INVALID_CLASS")
	public String getInvalidClass()
	{
		return invalidClass;
	}

	public void setInvalidClass(String invalidClass)
	{
		this.invalidClass = invalidClass;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devClassInfo")
	public List<DevAttributes> getDevAttributes()
	{
		return devAttributes;
	}

	public void setDevAttributes(List<DevAttributes> devAttributes)
	{
		this.devAttributes = devAttributes;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	public DevClassGroupInfo getDevClassGroupInfo()
	{
		return devClassGroupInfo;
	}

	public void setDevClassGroupInfo(DevClassGroupInfo devClassGroupInfo)
	{
		this.devClassGroupInfo = devClassGroupInfo;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devClassInfo")
	public List<DevStatus> getDevStatuses()
	{
		return devStatuses;
	}

	public void setDevStatuses(List<DevStatus> devStatuses)
	{
		this.devStatuses = devStatuses;
	}

}