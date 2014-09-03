package com.verywell.iotp.admin.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	// Fields

	private Integer id;

	private Integer depid;

	private String empno;

	private String empname;

	private String EPhoto;

	private String ESex;

	private String idCard;

	private String EBirthday;

	private String ENationality;

	private String EPolitical;

	private String EEducation;

	private String ESchool;

	private String EMarital;

	private String familyPlace;

	private String badgeid;

	private String hukou;

	private String officePhone;

	private String EMobile;

	private String homeaddress;

	private String EPostcode;

	private String hiredate;

	private String separatedate;

	private String separatereason;

	private String zhiwu;

	private String ETitle;

	private String EContract;

	private String ESalary;

	private String EState;

	private String EProfession;

	private String carno;

	private String driverno;

	private String EMemo;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "EMPLOYEE_ID_GENERATOR", sequenceName = "SEQ_EMPLOYEE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_GENERATOR")
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "DEPID", precision = 22, scale = 0)
	public Integer getDepid() {
		return this.depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	@Column(name = "EMPNO", length = 20)
	public String getEmpno() {
		return this.empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	@Column(name = "EMPNAME", length = 20)
	public String getEmpname() {
		return this.empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	@Column(name = "E_PHOTO")
	public String getEPhoto() {
		return this.EPhoto;
	}

	public void setEPhoto(String EPhoto) {
		this.EPhoto = EPhoto;
	}

	@Column(name = "E_SEX", length = 4)
	public String getESex() {
		return this.ESex;
	}

	public void setESex(String ESex) {
		this.ESex = ESex;
	}

	@Column(name = "ID_CARD", length = 30)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "E_BIRTHDAY", length = 20)
	public String getEBirthday() {
		return this.EBirthday;
	}

	public void setEBirthday(String EBirthday) {
		this.EBirthday = EBirthday;
	}

	@Column(name = "E_NATIONALITY", length = 30)
	public String getENationality() {
		return this.ENationality;
	}

	public void setENationality(String ENationality) {
		this.ENationality = ENationality;
	}

	@Column(name = "E_POLITICAL", length = 30)
	public String getEPolitical() {
		return this.EPolitical;
	}

	public void setEPolitical(String EPolitical) {
		this.EPolitical = EPolitical;
	}

	@Column(name = "E_EDUCATION", length = 30)
	public String getEEducation() {
		return this.EEducation;
	}

	public void setEEducation(String EEducation) {
		this.EEducation = EEducation;
	}

	@Column(name = "E_SCHOOL")
	public String getESchool() {
		return this.ESchool;
	}

	public void setESchool(String ESchool) {
		this.ESchool = ESchool;
	}

	@Column(name = "E_MARITAL", length = 30)
	public String getEMarital() {
		return this.EMarital;
	}

	public void setEMarital(String EMarital) {
		this.EMarital = EMarital;
	}

	@Column(name = "FAMILY_PLACE")
	public String getFamilyPlace() {
		return this.familyPlace;
	}

	public void setFamilyPlace(String familyPlace) {
		this.familyPlace = familyPlace;
	}

	@Column(name = "BADGEID", length = 20)
	public String getBadgeid() {
		return this.badgeid;
	}

	public void setBadgeid(String badgeid) {
		this.badgeid = badgeid;
	}

	@Column(name = "HUKOU")
	public String getHukou() {
		return this.hukou;
	}

	public void setHukou(String hukou) {
		this.hukou = hukou;
	}

	@Column(name = "OFFICE_PHONE", length = 30)
	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "E_MOBILE", length = 30)
	public String getEMobile() {
		return this.EMobile;
	}

	public void setEMobile(String EMobile) {
		this.EMobile = EMobile;
	}

	@Column(name = "HOMEADDRESS")
	public String getHomeaddress() {
		return this.homeaddress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	@Column(name = "E_POSTCODE", length = 20)
	public String getEPostcode() {
		return this.EPostcode;
	}

	public void setEPostcode(String EPostcode) {
		this.EPostcode = EPostcode;
	}

	@Column(name = "HIREDATE", length = 20)
	public String getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	@Column(name = "SEPARATEDATE", length = 20)
	public String getSeparatedate() {
		return this.separatedate;
	}

	public void setSeparatedate(String separatedate) {
		this.separatedate = separatedate;
	}

	@Column(name = "SEPARATEREASON")
	public String getSeparatereason() {
		return this.separatereason;
	}

	public void setSeparatereason(String separatereason) {
		this.separatereason = separatereason;
	}

	@Column(name = "ZHIWU", length = 30)
	public String getZhiwu() {
		return this.zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	@Column(name = "E_TITLE", length = 30)
	public String getETitle() {
		return this.ETitle;
	}

	public void setETitle(String ETitle) {
		this.ETitle = ETitle;
	}

	@Column(name = "E_CONTRACT", length = 50)
	public String getEContract() {
		return this.EContract;
	}

	public void setEContract(String EContract) {
		this.EContract = EContract;
	}

	@Column(name = "E_SALARY", length = 50)
	public String getESalary() {
		return this.ESalary;
	}

	public void setESalary(String ESalary) {
		this.ESalary = ESalary;
	}

	@Column(name = "E_STATE", length = 20)
	public String getEState() {
		return this.EState;
	}

	public void setEState(String EState) {
		this.EState = EState;
	}

	@Column(name = "E_PROFESSION", length = 50)
	public String getEProfession() {
		return this.EProfession;
	}

	public void setEProfession(String EProfession) {
		this.EProfession = EProfession;
	}

	@Column(name = "CARNO", length = 20)
	public String getCarno() {
		return this.carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	@Column(name = "DRIVERNO", length = 50)
	public String getDriverno() {
		return this.driverno;
	}

	public void setDriverno(String driverno) {
		this.driverno = driverno;
	}

	@Column(name = "E_MEMO")
	public String getEMemo() {
		return this.EMemo;
	}

	public void setEMemo(String EMemo) {
		this.EMemo = EMemo;
	}

}
