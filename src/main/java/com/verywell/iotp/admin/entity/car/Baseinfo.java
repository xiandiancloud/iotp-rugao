package com.verywell.iotp.admin.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Baseinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASEINFO")
public class Baseinfo implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private Integer sort;

	// Constructors

	/** default constructor */
	public Baseinfo() {
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "BASEINFO_ID_GENERATOR", sequenceName = "SEQ_BASEINFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BASEINFO_ID_GENERATOR")
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SORT", nullable = false, precision = 4, scale = 0)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
