package com.verywell.iotp.admin.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CarPark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAR_PARK")
public class CarPark implements java.io.Serializable {

	// Fields

	private Integer id;

	private String cardno;

	private String cardid;

	private String cardtype;

	private String cardstate;

	private String empno;

	private String empname;

	private String carlicense;

	private String intime;

	private String inmachnum;

	private String inposition;

	private String outtime;

	private String outmachnum;

	private String outposition;

	private String parkname;

	private String memo;

	// Constructors

	/** default constructor */
	public CarPark() {
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "CAR_PARK_ID_GENERATOR", sequenceName = "SEQ_CAR_PARK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_PARK_ID_GENERATOR")
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CARDNO", length = 15)
	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	@Column(name = "CARDID", length = 20)
	public String getCardid() {
		return this.cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	@Column(name = "CARDTYPE", length = 20)
	public String getCardtype() {
		return this.cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	@Column(name = "CARDSTATE", length = 20)
	public String getCardstate() {
		return this.cardstate;
	}

	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}

	@Column(name = "EMPNO", length = 20)
	public String getEmpno() {
		return this.empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	@Column(name = "EMPNAME", length = 50)
	public String getEmpname() {
		return this.empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	@Column(name = "CARLICENSE", length = 50)
	public String getCarlicense() {
		return this.carlicense;
	}

	public void setCarlicense(String carlicense) {
		this.carlicense = carlicense;
	}

	@Column(name = "INTIME", length = 20)
	public String getIntime() {
		return this.intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	@Column(name = "INMACHNUM", length = 6)
	public String getInmachnum() {
		return this.inmachnum;
	}

	public void setInmachnum(String inmachnum) {
		this.inmachnum = inmachnum;
	}

	@Column(name = "INPOSITION", length = 200)
	public String getInposition() {
		return this.inposition;
	}

	public void setInposition(String inposition) {
		this.inposition = inposition;
	}

	@Column(name = "OUTTIME", length = 20)
	public String getOuttime() {
		return this.outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	@Column(name = "OUTMACHNUM", length = 6)
	public String getOutmachnum() {
		return this.outmachnum;
	}

	public void setOutmachnum(String outmachnum) {
		this.outmachnum = outmachnum;
	}

	@Column(name = "OUTPOSITION", length = 200)
	public String getOutposition() {
		return this.outposition;
	}

	public void setOutposition(String outposition) {
		this.outposition = outposition;
	}

	@Column(name = "PARKNAME", length = 200)
	public String getParkname() {
		return this.parkname;
	}

	public void setParkname(String parkname) {
		this.parkname = parkname;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
