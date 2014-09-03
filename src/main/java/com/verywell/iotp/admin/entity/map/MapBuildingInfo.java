package com.verywell.iotp.admin.entity.map;

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

import com.verywell.iotp.admin.entity.AuditableEntity;

/**
 * The persistent class for the MAP_BUILDING_INFO database table.
 * 
 */
@Entity
@Table(name = "MAP_BUILDING_INFO")
public class MapBuildingInfo extends AuditableEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long buildingId;

	private String buildingDesc;

	private String buildingName;

	private Integer buildingType;

	private Integer floorNum;

	private Long parentId;

	private String position;

	private Integer status;

	private List<MapRoomInfo> mapRoomInfos;

	public MapBuildingInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "BUILDING_ID_GENERATOR", sequenceName = "SEQ_MAP_BUILDING_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUILDING_ID_GENERATOR")
	@Column(name = "BUILDING_ID")
	public Long getBuildingId()
	{
		return buildingId;
	}

	public void setBuildingId(Long buildingId)
	{
		this.buildingId = buildingId;
	}

	@Column(name = "BUILDING_DESC")
	public String getBuildingDesc()
	{
		return buildingDesc;
	}

	public void setBuildingDesc(String buildingDesc)
	{
		this.buildingDesc = buildingDesc;
	}

	@Column(name = "BUILDING_NAME")
	public String getBuildingName()
	{
		return buildingName;
	}

	public void setBuildingName(String buildingName)
	{
		this.buildingName = buildingName;
	}

	@Column(name = "BUILDING_TYPE")
	public Integer getBuildingType()
	{
		return buildingType;
	}

	public void setBuildingType(Integer buildingType)
	{
		this.buildingType = buildingType;
	}

	@Column(name = "FLOOR_NUM")
	public Integer getFloorNum()
	{
		return floorNum;
	}

	public void setFloorNum(Integer floorNum)
	{
		this.floorNum = floorNum;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
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

	@Column(name = "STATUS")
	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mapBuildingInfo")
	public List<MapRoomInfo> getMapRoomInfos()
	{
		return mapRoomInfos;
	}

	public void setMapRoomInfos(List<MapRoomInfo> mapRoomInfos)
	{
		this.mapRoomInfos = mapRoomInfos;
	}

}