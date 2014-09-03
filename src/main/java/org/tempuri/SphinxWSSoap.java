/**
 * SphinxWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface SphinxWSSoap extends java.rmi.Remote {
    public java.lang.String getDepartments(java.lang.String appKey, java.lang.String timeStamp, int deptID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getDeptAccessSecurityGroups(java.lang.String appKey, java.lang.String timeStamp, int deptID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getGroups(java.lang.String appKey, java.lang.String timeStamp, int groupID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getAccessSecurityGroups(java.lang.String appKey, java.lang.String timeStamp, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getPersonInfos(java.lang.String appKey, java.lang.String timeStamp, int personnalID, int deptID, boolean includeSubDept, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getCardInfos(java.lang.String appKey, java.lang.String timeStamp, int personnalID, java.lang.String cardNo, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getPersonCardInfos(java.lang.String appKey, java.lang.String timeStamp, int startPersonalID, int endPersonalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getPersonCardInfosByDeptID(java.lang.String appKey, java.lang.String timeStamp, int deptID, boolean includeSubDept, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getPersonAccessSecurityGroups(java.lang.String appKey, java.lang.String timeStamp, int personalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getControllers(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getControllerDots(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, int dotID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String changeOneDotStatus(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, int dotID, int status, boolean backtoNormal, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String isUsedCard(java.lang.String appKey, java.lang.String timeStamp, java.lang.String cardNo, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String downloadControllerAllParameters(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String deptOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int deptID, java.lang.String deptName, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String personOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, int deptID, int groupID, java.lang.String lastName, java.lang.String firstName, java.lang.String personnalCode, java.lang.String gender, java.lang.String IDCard, java.lang.String birthday, boolean marriage, java.lang.String nativePlace, java.lang.String nation, java.lang.String accessionDate, java.lang.String dimissionDate, java.lang.String duty, java.lang.String diploma, java.lang.String school, java.lang.String speciality, java.lang.String address, java.lang.String phone, java.lang.String email, java.lang.String remark, int floorNumber, boolean hasPhoto, boolean isLose, boolean isStop, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String deletePersonInfo(java.lang.String appKey, java.lang.String timeStamp, int personnalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String editPersonnalCardOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, java.lang.String cardNo, java.util.Calendar endTime, int areaID, int cardPINID, boolean isTemp, boolean isSpecial, boolean isFirst, boolean isArea, boolean deactivate, int deactivateDays, java.lang.String lastCardEVTime, java.lang.String cardPIN, java.lang.String tempCardStartTime, java.lang.String tempCardEndTime, int customID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String personnalCardOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String pastePersonATG(java.lang.String appKey, java.lang.String timeStamp, int sourcePersonnalID, int destPersonnalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String pastePersonATGFromDept(java.lang.String appKey, java.lang.String timeStamp, int deptID, int personnalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String applyDeptATG(java.lang.String appKey, java.lang.String timeStamp, int deptID, boolean includeSubDept, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String operateDeptSecurityGroupID(java.lang.String appKey, java.lang.String timeStamp, int type, int deptID, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String operatePersonSecurityGroupID(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String downloadDPUByPersonnel(java.lang.String appKey, java.lang.String timeStamp, int personalID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String downloadDPUBySecurityGroup(java.lang.String appKey, java.lang.String timeStamp, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getTransCardLog(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int personnelID, int lineID, int dpuID, int dotID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getTransEventLog(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int lineID, int dpuID, int dotID, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String userLogin(java.lang.String appKey, java.lang.String timeStamp, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getTransCardLogPagination(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int personnelID, int lineID, int dpuID, int dotID, int pageIdx, int numPerPage, java.lang.String validCode) throws java.rmi.RemoteException;
    public java.lang.String getTransEventLogPagination(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int lineID, int dpuID, int dotID, int pageIdx, int numPerPage, java.lang.String validCode) throws java.rmi.RemoteException;
}
