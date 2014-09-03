package com.verywell.iotp.admin.entity.scene;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the SCENE_INFO database table.
 * 
 */
@Entity
@Table(name = "SCENE_INFO")
public class SceneInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final Integer SCENETYPE_MANUAL = 1;
	public static final Integer SCENETYPE_AUTO = 2;

	private Long sceneId;
	private Long roomId;
	private String sceneDesc;
	private String sceneEndTime;
	private String sceneName;
	private String sceneStartTime;
	private Integer sceneStatus;
	private Integer sceneType;
	private Integer triggerCondition;
	private Integer triggerType;
	private Integer triggerValue;
	private String validEndDate;
	private String validStartDate;
	private List<SceneConfigInfo> sceneConfigInfos;

	public SceneInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "SCENE_ID_GENERATOR", sequenceName = "SEQ_SCENE_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCENE_ID_GENERATOR")
	@Column(name = "SCENE_ID")
	public Long getSceneId()
	{
		return sceneId;
	}

	public void setSceneId(Long sceneId)
	{
		this.sceneId = sceneId;
	}

	@Column(name = "ROOM_ID")
	public Long getRoomId()
	{
		return this.roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	@Column(name = "SCENE_DESC")
	public String getSceneDesc()
	{
		return this.sceneDesc;
	}

	public void setSceneDesc(String sceneDesc)
	{
		this.sceneDesc = sceneDesc;
	}

	@Column(name = "SCENE_END_TIME")
	public String getSceneEndTime()
	{
		return this.sceneEndTime;
	}

	public void setSceneEndTime(String sceneEndTime)
	{
		this.sceneEndTime = sceneEndTime;
	}

	@Column(name = "SCENE_NAME")
	public String getSceneName()
	{
		return this.sceneName;
	}

	public void setSceneName(String sceneName)
	{
		this.sceneName = sceneName;
	}

	@Column(name = "SCENE_START_TIME")
	public String getSceneStartTime()
	{
		return this.sceneStartTime;
	}

	public void setSceneStartTime(String sceneStartTime)
	{
		this.sceneStartTime = sceneStartTime;
	}

	@Column(name = "SCENE_STATUS")
	public Integer getSceneStatus()
	{
		return this.sceneStatus;
	}

	public void setSceneStatus(Integer sceneStatus)
	{
		this.sceneStatus = sceneStatus;
	}

	@Column(name = "SCENE_TYPE")
	public Integer getSceneType()
	{
		return this.sceneType;
	}

	public void setSceneType(Integer sceneType)
	{
		this.sceneType = sceneType;
	}

	@Column(name = "TRIGGER_CONDITION")
	public Integer getTriggerCondition()
	{
		return this.triggerCondition;
	}

	public void setTriggerCondition(Integer triggerCondition)
	{
		this.triggerCondition = triggerCondition;
	}

	@Column(name = "TRIGGER_TYPE")
	public Integer getTriggerType()
	{
		return this.triggerType;
	}

	public void setTriggerType(Integer triggerType)
	{
		this.triggerType = triggerType;
	}

	@Column(name = "TRIGGER_VALUE")
	public Integer getTriggerValue()
	{
		return this.triggerValue;
	}

	public void setTriggerValue(Integer triggerValue)
	{
		this.triggerValue = triggerValue;
	}

	@Column(name = "VALID_END_DATE")
	public String getValidEndDate()
	{
		return this.validEndDate;
	}

	public void setValidEndDate(String validEndDate)
	{
		this.validEndDate = validEndDate;
	}

	@Column(name = "VALID_START_DATE")
	public String getValidStartDate()
	{
		return this.validStartDate;
	}

	public void setValidStartDate(String validStartDate)
	{
		this.validStartDate = validStartDate;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sceneInfo")
	public List<SceneConfigInfo> getSceneConfigInfos()
	{
		return sceneConfigInfos;
	}

	public void setSceneConfigInfos(List<SceneConfigInfo> sceneConfigInfos)
	{
		this.sceneConfigInfos = sceneConfigInfos;
	}

}