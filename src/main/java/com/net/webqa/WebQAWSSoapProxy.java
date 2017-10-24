package com.net.webqa;

public class WebQAWSSoapProxy implements com.net.webqa.WebQAWSSoap {
  private String _endpoint = null;
  private com.net.webqa.WebQAWSSoap webQAWSSoap = null;
  
  public WebQAWSSoapProxy() {
    _initWebQAWSSoapProxy();
  }
  
  public WebQAWSSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebQAWSSoapProxy();
  }
  
  private void _initWebQAWSSoapProxy() {
    try {
      webQAWSSoap = (new com.net.webqa.WebQAWSLocator()).getWebQAWSSoap();
      if (webQAWSSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webQAWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webQAWSSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webQAWSSoap != null)
      ((javax.xml.rpc.Stub)webQAWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.net.webqa.WebQAWSSoap getWebQAWSSoap() {
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap;
  }
  
  public com.net.webqa.WebQAIssue createServiceRequestSimple(java.lang.String authKey, int typeNo, java.lang.String typeName, java.lang.String custEmail, com.net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.createServiceRequestSimple(authKey, typeNo, typeName, custEmail, customFieldData);
  }
  
  public com.net.webqa.WebQAIssue createIssueSimple(java.lang.String authKey, java.lang.String custEmail, java.lang.String custID, int filterA, int filterB, int filterC, java.lang.String subject, java.lang.String question, com.net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.createIssueSimple(authKey, custEmail, custID, filterA, filterB, filterC, subject, question, customFieldData);
  }
  
  public java.lang.String authenticateClientUser(java.lang.String authKey, java.lang.String loginEmail, java.lang.String uPassword, java.lang.String custFieldNameToValidate, java.lang.String custFieldValueToValidate) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.authenticateClientUser(authKey, loginEmail, uPassword, custFieldNameToValidate, custFieldValueToValidate);
  }
  
  public java.lang.String authenticateClientUserByID(java.lang.String authKey, java.lang.String userID, java.lang.String uPassword) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.authenticateClientUserByID(authKey, userID, uPassword);
  }
  
  public com.net.webqa.WebQAIssue[] getClientIssues(java.lang.String authKey, java.lang.String clientAuthSessionID, java.lang.String startDateRange, java.lang.String endDateRange) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getClientIssues(authKey, clientAuthSessionID, startDateRange, endDateRange);
  }
  
  public com.net.webqa.WebQAServiceRequestType[] getServiceRequestTypes(java.lang.String authKey, int categoryID, java.lang.String keywords) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getServiceRequestTypes(authKey, categoryID, keywords);
  }
  
  public com.net.webqa.WebQACategory[] getServiceRequestCategories(java.lang.String authKey) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getServiceRequestCategories(authKey);
  }
  
  public java.lang.String getWebQAEncryptedValue(java.lang.String valueToEncrypt) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getWebQAEncryptedValue(valueToEncrypt);
  }
  
  public com.net.webqa.WebQAStatusType[] getServiceRequestStatusTypes(java.lang.String authKey) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getServiceRequestStatusTypes(authKey);
  }
  
  public com.net.webqa.WebQACustomField[] getRequestTypeCustomFields(java.lang.String authKey, int typeNo) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getRequestTypeCustomFields(authKey, typeNo);
  }
  
  public com.net.webqa.WebQAIssue[] getIssueSearchSimple(java.lang.String authKey, java.lang.String keywords, java.lang.String filterA, java.lang.String filterB, java.lang.String filterC, int visibilityID, int totalToReturn) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getIssueSearchSimple(authKey, keywords, filterA, filterB, filterC, visibilityID, totalToReturn);
  }
  
  public com.net.webqa.WebQAIssue getIssueByRefNo(java.lang.String authKey, java.lang.String refNo) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getIssueByRefNo(authKey, refNo);
  }
  
  public com.net.webqa.WebQAIssue[] getTopXFAQs(int numQuestions) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getTopXFAQs(numQuestions);
  }
  
  public com.net.webqa.WebQAFilterItem[] getFilterAItems(java.lang.String authKey) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getFilterAItems(authKey);
  }
  
  public com.net.webqa.WebQAFilterItem[] getFilterBItems(java.lang.String authKey, int parentID) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getFilterBItems(authKey, parentID);
  }
  
  public com.net.webqa.WebQAFilterItem[] getFilterCItems(java.lang.String authKey, int parentID) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getFilterCItems(authKey, parentID);
  }
  
  public com.net.webqa.WebQAFilterConfig getFilterConfig(java.lang.String authKey, int filterTypeABC) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.getFilterConfig(authKey, filterTypeABC);
  }
  
  public java.lang.String stripHTML(java.lang.String stringToStrip) throws java.rmi.RemoteException{
    if (webQAWSSoap == null)
      _initWebQAWSSoapProxy();
    return webQAWSSoap.stripHTML(stringToStrip);
  }
  
  
}