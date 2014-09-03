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
import com.verywell.iotp.admin.entity.AuditableEntity;

/**
 * The persistent class for the DEV_STATUS database table.
 * 
 */
@Entity
@Table(name = "DEV_STATUS")
public class DevStatus extends AuditableEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer devStatus;

	private String devStatusName;

	private String icon;

	// bi-directional many-to-one association to DevClassInfo

	private DevClassInfo devClassInfo;

	public DevStatus()
	{
	}

	@Id
	@SequenceGenerator(name = "DEV_STATUS_ID_GENERATOR", sequenceName = "SEQ_DEV_STATUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEV_STATUS_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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

	@Column(name = "DEV_STATUS_NAME")
	public String getDevStatusName()
	{
		return devStatusName;
	}

	public void setDevStatusName(String devStatusName)
	{
		this.devStatusName = devStatusName;
	}

	@Column(name = "ICON")
	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
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