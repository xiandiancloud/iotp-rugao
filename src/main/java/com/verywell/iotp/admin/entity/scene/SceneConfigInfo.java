package com.verywell.iotp.admin.entity.scene;

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
 * The persistent class for the SCENE_CONFIG_INFO database table.
 * 
 */
@Entity
@Table(name = "SCENE_CONFIG_INFO")
public class SceneConfigInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String attrKey;

	private String attrValue;

	private Long devId;

	// bi-directional many-to-one association to SceneInfo

	private SceneInfo sceneInfo;

	public SceneConfigInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "SCENE_CONFIG_INFO_ID_GENERATOR", sequenceName = "SEQ_SCENE_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCENE_CONFIG_INFO_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "ATTR_KEY")
	public String getAttrKey()
	{
		return attrKey;
	}

	public void setAttrKey(String attrKey)
	{
		this.attrKey = attrKey;
	}

	@Column(name = "ATTR_VALUE")
	public String getAttrValue()
	{
		return attrValue;
	}

	public void setAttrValue(String attrValue)
	{
		this.attrValue = attrValue;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCENE_ID")
	public SceneInfo getSceneInfo()
	{
		return sceneInfo;
	}

	public void setSceneInfo(SceneInfo sceneInfo)
	{
		this.sceneInfo = sceneInfo;
	}

}