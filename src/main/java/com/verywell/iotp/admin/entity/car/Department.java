package com.verywell.iotp.admin.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department implements java.io.Serializable {

	// Fields

	private Integer id;

	private String depid;

	private String depname;

	private Integer upid;

	private String upname;

	private String deptype;

	private String DTelphone;

	private String DFax;

	private String DAddress;

	private String DPostcode;

	private String DMemo;

	// Constructors

	/** default constructor */
	public Department() {
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "DEPARTMENT_ID_GENERATOR", sequenceName = "SEQ_DEPARTMENT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_ID_GENERATOR")
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "DEPID", length = 20)
	public String getDepid() {
		return this.depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	@Column(name = "DEPNAME", length = 100)
	public String getDepname() {
		return this.depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	@Column(name = "UPID", precision = 22, scale = 0)
	public Integer getUpid() {
		return this.upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	@Column(name = "UPNAME", length = 100)
	public String getUpname() {
		return this.upname;
	}

	public void setUpname(String upname) {
		this.upname = upname;
	}

	@Column(name = "DEPTYPE")
	public String getDeptype() {
		return this.deptype;
	}

	public void setDeptype(String deptype) {
		this.deptype = deptype;
	}

	@Column(name = "D_TELPHONE", length = 30)
	public String getDTelphone() {
		return this.DTelphone;
	}

	public void setDTelphone(String DTelphone) {
		this.DTelphone = DTelphone;
	}

	@Column(name = "D_FAX", length = 30)
	public String getDFax() {
		return this.DFax;
	}

	public void setDFax(String DFax) {
		this.DFax = DFax;
	}

	@Column(name = "D_ADDRESS")
	public String getDAddress() {
		return this.DAddress;
	}

	public void setDAddress(String DAddress) {
		this.DAddress = DAddress;
	}

	@Column(name = "D_POSTCODE", length = 20)
	public String getDPostcode() {
		return this.DPostcode;
	}

	public void setDPostcode(String DPostcode) {
		this.DPostcode = DPostcode;
	}

	@Column(name = "D_MEMO")
	public String getDMemo() {
		return this.DMemo;
	}

	public void setDMemo(String DMemo) {
		this.DMemo = DMemo;
	}

}
