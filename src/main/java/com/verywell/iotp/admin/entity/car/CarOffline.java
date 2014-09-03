package com.verywell.iotp.admin.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CarOffline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAR_OFFLINE")
public class CarOffline implements java.io.Serializable {

	// Fields

	private Integer id;

	private String cardno;

	private String cardid;

	private String cardtype;

	private String cardstate;

	private String empno;

	private String empname;

	private String carlicense;

	private String machnum;

	private String inorout;

	private String parkname;

	private String inouttime;

	private String inoutposition;

	// Constructors

	/** default constructor */
	public CarOffline() {
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "CAR_OFFLINE_ID_GENERATOR", sequenceName = "SEQ_CAR_OFFLINE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_OFFLINE_ID_GENERATOR")
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

	@Column(name = "MACHNUM", length = 6)
	public String getMachnum() {
		return this.machnum;
	}

	public void setMachnum(String machnum) {
		this.machnum = machnum;
	}

	@Column(name = "INOROUT", length = 10)
	public String getInorout() {
		return this.inorout;
	}

	public void setInorout(String inorout) {
		this.inorout = inorout;
	}

	@Column(name = "PARKNAME", length = 100)
	public String getParkname() {
		return this.parkname;
	}

	public void setParkname(String parkname) {
		this.parkname = parkname;
	}

	@Column(name = "INOUTTIME", length = 20)
	public String getInouttime() {
		return this.inouttime;
	}

	public void setInouttime(String inouttime) {
		this.inouttime = inouttime;
	}

	@Column(name = "INOUTPOSITION", length = 200)
	public String getInoutposition() {
		return this.inoutposition;
	}

	public void setInoutposition(String inoutposition) {
		this.inoutposition = inoutposition;
	}

}
