package com.verywell.iotp.admin.entity.dev;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verywell.iotp.admin.entity.AuditableEntity;

/**
 * The persistent class for the DEV_ATTRIBUTES database table.
 * 
 */
@Entity
@Table(name = "DEV_ATTRIBUTES")
public class DevAttributes extends AuditableEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String attrKey;
	private Integer allowEdit;
	private String attrDesc;
	private String attrMeasurement;
	private String attrName;
	private Integer attrStatus;
	private DevClassInfo devClassInfo;

	public DevAttributes()
	{
	}

	@Id
	@Column(name = "ATTR_KEY")
	public String getAttrKey()
	{
		return this.attrKey;
	}

	public void setAttrKey(String attrKey)
	{
		this.attrKey = attrKey;
	}

	@Column(name = "ALLOW_EDIT")
	public Integer getAllowEdit()
	{
		return this.allowEdit;
	}

	public void setAllowEdit(Integer allowEdit)
	{
		this.allowEdit = allowEdit;
	}

	@Column(name = "ATTR_DESC")
	public String getAttrDesc()
	{
		return this.attrDesc;
	}

	public void setAttrDesc(String attrDesc)
	{
		this.attrDesc = attrDesc;
	}

	@Column(name = "ATTR_MEASUREMENT")
	public String getAttrMeasurement()
	{
		return this.attrMeasurement;
	}

	public void setAttrMeasurement(String attrMeasurement)
	{
		this.attrMeasurement = attrMeasurement;
	}

	@Column(name = "ATTR_NAME")
	public String getAttrName()
	{
		return this.attrName;
	}

	public void setAttrName(String attrName)
	{
		this.attrName = attrName;
	}

	@Column(name = "ATTR_STATUS")
	public Integer getAttrStatus()
	{
		return this.attrStatus;
	}

	public void setAttrStatus(Integer attrStatus)
	{
		this.attrStatus = attrStatus;
	}

	@JsonIgnore
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

}