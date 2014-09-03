package com.verywell.iotp.admin.entity.dev;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DEV_ATTR_HISTORY_INFO")
public class DevAttrHistoryInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String attrKey;
	private String attrValue;
	private DevInfo devInfo;
	private String updateTime;

	public DevAttrHistoryInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "DEV_ATTR_HISTORY_INFO_ID_GENERATOR", sequenceName = "SEQ_DEV_ATTR_HISTORY_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEV_ATTR_HISTORY_INFO_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "ATTR_KEY")
	public String getAttrKey()
	{
		return this.attrKey;
	}

	public void setAttrKey(String attrKey)
	{
		this.attrKey = attrKey;
	}

	@Column(name = "ATTR_VALUE")
	public String getAttrValue()
	{
		return this.attrValue;
	}

	public void setAttrValue(String attrValue)
	{
		this.attrValue = attrValue;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEV_ID")
	public DevInfo getDevInfo()
	{
		return devInfo;
	}

	public void setDevInfo(DevInfo devInfo)
	{
		this.devInfo = devInfo;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime()
	{
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}

}