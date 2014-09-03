package org.tempuri;

public class SphinxWSSoapProxy implements org.tempuri.SphinxWSSoap {
  private String _endpoint = null;
  private org.tempuri.SphinxWSSoap sphinxWSSoap = null;
  
  public SphinxWSSoapProxy() {
    _initSphinxWSSoapProxy();
  }
  
  public SphinxWSSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSphinxWSSoapProxy();
  }
  
  private void _initSphinxWSSoapProxy() {
    try {
      sphinxWSSoap = (new org.tempuri.SphinxWSLocator()).getSphinxWSSoap();
      if (sphinxWSSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sphinxWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sphinxWSSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sphinxWSSoap != null)
      ((javax.xml.rpc.Stub)sphinxWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.SphinxWSSoap getSphinxWSSoap() {
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap;
  }
  
  public java.lang.String getDepartments(java.lang.String appKey, java.lang.String timeStamp, int deptID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getDepartments(appKey, timeStamp, deptID, validCode);
  }
  
  public java.lang.String getDeptAccessSecurityGroups(java.lang.String appKey, java.lang.String timeStamp, int deptID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getDeptAccessSecurityGroups(appKey, timeStamp, deptID, validCode);
  }
  
  public java.lang.String getGroups(java.lang.String appKey, java.lang.String timeStamp, int groupID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getGroups(appKey, timeStamp, groupID, validCode);
  }
  
  public java.lang.String getAccessSecurityGroups(java.lang.String appKey, java.lang.String timeStamp, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getAccessSecurityGroups(appKey, timeStamp, securityGroupID, validCode);
  }
  
  public java.lang.String getPersonInfos(java.lang.String appKey, java.lang.String timeStamp, int personnalID, int deptID, boolean includeSubDept, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getPersonInfos(appKey, timeStamp, personnalID, deptID, includeSubDept, validCode);
  }
  
  public java.lang.String getCardInfos(java.lang.String appKey, java.lang.String timeStamp, int personnalID, java.lang.String cardNo, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getCardInfos(appKey, timeStamp, personnalID, cardNo, validCode);
  }
  
  public java.lang.String getPersonCardInfos(java.lang.String appKey, java.lang.String timeStamp, int startPersonalID, int endPersonalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getPersonCardInfos(appKey, timeStamp, startPersonalID, endPersonalID, validCode);
  }
  
  public java.lang.String getPersonCardInfosByDeptID(java.lang.String appKey, java.lang.String timeStamp, int deptID, boolean includeSubDept, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getPersonCardInfosByDeptID(appKey, timeStamp, deptID, includeSubDept, validCode);
  }
  
  public java.lang.String getPersonAccessSecurityGroups(java.lang.String appKey, java.lang.String timeStamp, int personalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getPersonAccessSecurityGroups(appKey, timeStamp, personalID, validCode);
  }
  
  public java.lang.String getControllers(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getControllers(appKey, timeStamp, lineID, dpuID, validCode);
  }
  
  public java.lang.String getControllerDots(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, int dotID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getControllerDots(appKey, timeStamp, lineID, dpuID, dotID, validCode);
  }
  
  public java.lang.String changeOneDotStatus(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, int dotID, int status, boolean backtoNormal, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.changeOneDotStatus(appKey, timeStamp, lineID, dpuID, dotID, status, backtoNormal, validCode);
  }
  
  public java.lang.String isUsedCard(java.lang.String appKey, java.lang.String timeStamp, java.lang.String cardNo, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.isUsedCard(appKey, timeStamp, cardNo, validCode);
  }
  
  public java.lang.String downloadControllerAllParameters(java.lang.String appKey, java.lang.String timeStamp, int lineID, int dpuID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.downloadControllerAllParameters(appKey, timeStamp, lineID, dpuID, validCode);
  }
  
  public java.lang.String deptOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int deptID, java.lang.String deptName, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.deptOperations(appKey, timeStamp, type, deptID, deptName, validCode);
  }
  
  public java.lang.String personOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, int deptID, int groupID, java.lang.String lastName, java.lang.String firstName, java.lang.String personnalCode, java.lang.String gender, java.lang.String IDCard, java.lang.String birthday, boolean marriage, java.lang.String nativePlace, java.lang.String nation, java.lang.String accessionDate, java.lang.String dimissionDate, java.lang.String duty, java.lang.String diploma, java.lang.String school, java.lang.String speciality, java.lang.String address, java.lang.String phone, java.lang.String email, java.lang.String remark, int floorNumber, boolean hasPhoto, boolean isLose, boolean isStop, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.personOperations(appKey, timeStamp, type, personnalID, deptID, groupID, lastName, firstName, personnalCode, gender, IDCard, birthday, marriage, nativePlace, nation, accessionDate, dimissionDate, duty, diploma, school, speciality, address, phone, email, remark, floorNumber, hasPhoto, isLose, isStop, validCode);
  }
  
  public java.lang.String deletePersonInfo(java.lang.String appKey, java.lang.String timeStamp, int personnalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.deletePersonInfo(appKey, timeStamp, personnalID, validCode);
  }
  
  public java.lang.String editPersonnalCardOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, java.lang.String cardNo, java.util.Calendar endTime, int areaID, int cardPINID, boolean isTemp, boolean isSpecial, boolean isFirst, boolean isArea, boolean deactivate, int deactivateDays, java.lang.String lastCardEVTime, java.lang.String cardPIN, java.lang.String tempCardStartTime, java.lang.String tempCardEndTime, int customID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.editPersonnalCardOperations(appKey, timeStamp, type, personnalID, cardNo, endTime, areaID, cardPINID, isTemp, isSpecial, isFirst, isArea, deactivate, deactivateDays, lastCardEVTime, cardPIN, tempCardStartTime, tempCardEndTime, customID, validCode);
  }
  
  public java.lang.String personnalCardOperations(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.personnalCardOperations(appKey, timeStamp, type, personnalID, validCode);
  }
  
  public java.lang.String pastePersonATG(java.lang.String appKey, java.lang.String timeStamp, int sourcePersonnalID, int destPersonnalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.pastePersonATG(appKey, timeStamp, sourcePersonnalID, destPersonnalID, validCode);
  }
  
  public java.lang.String pastePersonATGFromDept(java.lang.String appKey, java.lang.String timeStamp, int deptID, int personnalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.pastePersonATGFromDept(appKey, timeStamp, deptID, personnalID, validCode);
  }
  
  public java.lang.String applyDeptATG(java.lang.String appKey, java.lang.String timeStamp, int deptID, boolean includeSubDept, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.applyDeptATG(appKey, timeStamp, deptID, includeSubDept, validCode);
  }
  
  public java.lang.String operateDeptSecurityGroupID(java.lang.String appKey, java.lang.String timeStamp, int type, int deptID, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.operateDeptSecurityGroupID(appKey, timeStamp, type, deptID, securityGroupID, validCode);
  }
  
  public java.lang.String operatePersonSecurityGroupID(java.lang.String appKey, java.lang.String timeStamp, int type, int personnalID, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.operatePersonSecurityGroupID(appKey, timeStamp, type, personnalID, securityGroupID, validCode);
  }
  
  public java.lang.String downloadDPUByPersonnel(java.lang.String appKey, java.lang.String timeStamp, int personalID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.downloadDPUByPersonnel(appKey, timeStamp, personalID, validCode);
  }
  
  public java.lang.String downloadDPUBySecurityGroup(java.lang.String appKey, java.lang.String timeStamp, int securityGroupID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.downloadDPUBySecurityGroup(appKey, timeStamp, securityGroupID, validCode);
  }
  
  public java.lang.String getTransCardLog(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int personnelID, int lineID, int dpuID, int dotID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getTransCardLog(appKey, timeStamp, startTime, endTime, personnelID, lineID, dpuID, dotID, validCode);
  }
  
  public java.lang.String getTransEventLog(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int lineID, int dpuID, int dotID, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getTransEventLog(appKey, timeStamp, startTime, endTime, lineID, dpuID, dotID, validCode);
  }
  
  public java.lang.String userLogin(java.lang.String appKey, java.lang.String timeStamp, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.userLogin(appKey, timeStamp, validCode);
  }
  
  public java.lang.String getTransCardLogPagination(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int personnelID, int lineID, int dpuID, int dotID, int pageIdx, int numPerPage, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getTransCardLogPagination(appKey, timeStamp, startTime, endTime, personnelID, lineID, dpuID, dotID, pageIdx, numPerPage, validCode);
  }
  
  public java.lang.String getTransEventLogPagination(java.lang.String appKey, java.lang.String timeStamp, java.util.Calendar startTime, java.util.Calendar endTime, int lineID, int dpuID, int dotID, int pageIdx, int numPerPage, java.lang.String validCode) throws java.rmi.RemoteException{
    if (sphinxWSSoap == null)
      _initSphinxWSSoapProxy();
    return sphinxWSSoap.getTransEventLogPagination(appKey, timeStamp, startTime, endTime, lineID, dpuID, dotID, pageIdx, numPerPage, validCode);
  }
  
  
}