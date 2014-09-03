package com.verywell.iotp.admin.entity.map;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verywell.iotp.admin.entity.AuditableEntity;

/**
 * The persistent class for the MAP_ROOM_INFO database table.
 * 
 */
@Entity
@Table(name = "MAP_ROOM_INFO")
public class MapRoomInfo extends AuditableEntity implements Serializable
{
	/** 房间类型-会议室 */
	public static final Integer ROOM_TYPE_METTING = 2;

	/** 房间类型-教室 */
	public static final Integer ROOM_TYPE_CLASSROOM = 1;

	/** 房间类型-校史馆 */
	public static final Integer ROOM_TYPE_SHOWROOM = 3;

	private static final long serialVersionUID = 1L;

	private Long roomId;

	private Integer floorNo;

	private String position;

	private String roomDesc;

	private String roomImage;

	private String roomName;

	private Integer roomType;

	private Long sceneId;

	private Integer status;

	// bi-directional many-to-one association to MapBuildingInfo

	private MapBuildingInfo mapBuildingInfo;

	private Integer alarmStatus;

	public MapRoomInfo()
	{
	}

	public MapRoomInfo(Long roomId)
	{
		this.roomId = roomId;
	}

	@Id
	@SequenceGenerator(name = "ROOM_ID_GENERATOR", sequenceName = "SEQ_MAP_ROOM_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_ID_GENERATOR")
	@Column(name = "ROOM_ID")
	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	@Column(name = "FLOOR_NO")
	public Integer getFloorNo()
	{
		return floorNo;
	}

	public void setFloorNo(Integer floorNo)
	{
		this.floorNo = floorNo;
	}

	@Column(name = "POSITION")
	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	@Column(name = "ROOM_DESC")
	public String getRoomDesc()
	{
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc)
	{
		this.roomDesc = roomDesc;
	}

	@Column(name = "ROOM_IMAGE")
	public String getRoomImage()
	{
		return roomImage;
	}

	public void setRoomImage(String roomImage)
	{
		this.roomImage = roomImage;
	}

	@Column(name = "ROOM_NAME")
	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	@Column(name = "ROOM_TYPE")
	public Integer getRoomType()
	{
		return roomType;
	}

	public void setRoomType(Integer roomType)
	{
		this.roomType = roomType;
	}

	@Column(name = "SCENE_ID")
	public Long getSceneId()
	{
		return sceneId;
	}

	public void setSceneId(Long sceneId)
	{
		this.sceneId = sceneId;
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

	@ManyToOne
	@JoinColumn(name = "BULIDING_ID")
	@JsonIgnore
	public MapBuildingInfo getMapBuildingInfo()
	{
		return mapBuildingInfo;
	}

	public void setMapBuildingInfo(MapBuildingInfo mapBuildingInfo)
	{
		this.mapBuildingInfo = mapBuildingInfo;
	}

	@Transient
	public Integer getAlarmStatus()
	{
		return alarmStatus;
	}

	public void setAlarmStatus(Integer alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}

}