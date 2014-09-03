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

/**
 * The persistent class for the DEV_ALARM_CONFIG_INFO database table.
 * 
 */
@Entity
@Table(name = "DEV_ALARM_CONFIG_INFO")
public class DevAlarmConfigInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String attrKey;
	private String attrMaxValue;
	private String attrMinValue;
	private DevInfo devInfo;

	public DevAlarmConfigInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "DEV_ALARM_CONFIG_INFO_ID_GENERATOR", sequenceName = "SEQ_DEV_ALARM_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEV_ALARM_CONFIG_INFO_ID_GENERATOR")
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

	@Column(name = "ATTR_MAX_VALUE")
	public String getAttrMaxValue()
	{
		return this.attrMaxValue;
	}

	public void setAttrMaxValue(String attrMaxValue)
	{
		this.attrMaxValue = attrMaxValue;
	}

	@Column(name = "ATTR_MIN_VALUE")
	public String getAttrMinValue()
	{
		return this.attrMinValue;
	}

	public void setAttrMinValue(String attrMinValue)
	{
		this.attrMinValue = attrMinValue;
	}

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
}