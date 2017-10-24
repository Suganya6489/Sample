package net.webqa;

public class __wslibSoapProxy implements net.webqa.__wslibSoap {
  private String _endpoint = null;
  private net.webqa.__wslibSoap __wslibSoap = null;
  
  public __wslibSoapProxy() {
    _init__wslibSoapProxy();
  }
  
  public __wslibSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _init__wslibSoapProxy();
  }
  
  private void _init__wslibSoapProxy() {
    try {
      __wslibSoap = (new net.webqa.__wslibLocator()).get__wslibSoap();
      if (__wslibSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)__wslibSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)__wslibSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__wslibSoap != null)
      ((javax.xml.rpc.Stub)__wslibSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.webqa.__wslibSoap get__wslibSoap() {
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap;
  }
  
  public java.lang.String authenticateAdminUser(java.lang.String authKey, java.lang.String authKeyAdmin, java.lang.String userLogin, java.lang.String password, java.lang.String profileName) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.authenticateAdminUser(authKey, authKeyAdmin, userLogin, password, profileName);
  }
  
  public java.lang.String authenticateClientUser(java.lang.String authKey, java.lang.String loginEmail, java.lang.String password, java.lang.String groupAccessName, java.lang.String firstName, java.lang.String lastName, java.lang.String phone) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.authenticateClientUser(authKey, loginEmail, password, groupAccessName, firstName, lastName, phone);
  }
  
  public java.lang.String authenticateClientUserCF(java.lang.String authKey, java.lang.String loginEmail, java.lang.String password, java.lang.String groupAccessName, java.lang.String firstName, java.lang.String lastName, java.lang.String phone, java.lang.String custCFName1, java.lang.String custCFValue1, java.lang.String custCFName2, java.lang.String custCFValue2, java.lang.String custCFName3, java.lang.String custCFValue3, java.lang.String custCFName4, java.lang.String custCFValue4, java.lang.String custCFName5, java.lang.String custCFValue5) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.authenticateClientUserCF(authKey, loginEmail, password, groupAccessName, firstName, lastName, phone, custCFName1, custCFValue1, custCFName2, custCFValue2, custCFName3, custCFValue3, custCFName4, custCFValue4, custCFName5, custCFValue5);
  }
  
  public java.lang.String authenticatePortalUserWithGroup(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password, java.lang.String groupAccessName) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.authenticatePortalUserWithGroup(authKey, emailAddress, password, groupAccessName);
  }
  
  public java.lang.String authenticatePortalUser(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.authenticatePortalUser(authKey, emailAddress, password);
  }
  
  public java.lang.String authenticatePortalUserV2(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.authenticatePortalUserV2(authKey, emailAddress, password);
  }
  
  public java.lang.String updateClientUserEmail(java.lang.String authKey, java.lang.String oldLoginEmail, java.lang.String newLoginEmail) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateClientUserEmail(authKey, oldLoginEmail, newLoginEmail);
  }
  
  public java.lang.String updateClientUserPassword(java.lang.String authKey, java.lang.String emailAddress, java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateClientUserPassword(authKey, emailAddress, oldPassword, newPassword);
  }
  
  public java.lang.String updateClientUserName(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password, java.lang.String customerName) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateClientUserName(authKey, emailAddress, password, customerName);
  }
  
  public java.lang.String updateClientUser(java.lang.String authKey, java.lang.String loginEmail, java.lang.String password, java.lang.String groupAccessName, java.lang.String firstName, java.lang.String lastName, java.lang.String phone, java.lang.String custCFName1, java.lang.String custCFValue1, java.lang.String custCFName2, java.lang.String custCFValue2, java.lang.String custCFName3, java.lang.String custCFValue3, java.lang.String custCFName4, java.lang.String custCFValue4, java.lang.String custCFName5, java.lang.String custCFValue5) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateClientUser(authKey, loginEmail, password, groupAccessName, firstName, lastName, phone, custCFName1, custCFValue1, custCFName2, custCFValue2, custCFName3, custCFValue3, custCFName4, custCFValue4, custCFName5, custCFValue5);
  }
  
  public java.lang.String getGroupSessionID(java.lang.String authKey, java.lang.String groupName) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getGroupSessionID(authKey, groupName);
  }
  
  public java.lang.String createServiceRequest(java.lang.String authKey, java.lang.String srType, int customerId, java.lang.String customerEmail, net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.createServiceRequest(authKey, srType, customerId, customerEmail, customFieldData);
  }
  
  public java.lang.String createServiceRequestWithTypeId(java.lang.String authKey, int srTypeId, int customerId, java.lang.String customerEmail, net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.createServiceRequestWithTypeId(authKey, srTypeId, customerId, customerEmail, customFieldData);
  }
  
  public java.lang.String createServiceRequestFull(java.lang.String authKey, net.webqa.WebQACustomer2 webQACustomer, net.webqa.WebQAServiceRequest2 webQAServiceRequest) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.createServiceRequestFull(authKey, webQACustomer, webQAServiceRequest);
  }
  
  public java.lang.String createServiceRequestExtended(java.lang.String authKey, int srTypeId, int customerId, java.lang.String customerEmail, net.webqa.WebQACustomField[] customFieldData, java.lang.String customerFirstName, java.lang.String customerLastName, java.lang.String phone, java.util.Calendar createDate, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zip, java.lang.String modifyDate, int requestOriginId, java.lang.String locationAddress1) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.createServiceRequestExtended(authKey, srTypeId, customerId, customerEmail, customFieldData, customerFirstName, customerLastName, phone, createDate, address1, address2, city, state, zip, modifyDate, requestOriginId, locationAddress1);
  }
  
  public net.webqa.WebQAIssue[] getTopXFAQs(int numQuestions, java.lang.String groupId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getTopXFAQs(numQuestions, groupId);
  }
  
  public net.webqa.WebQAServiceRequestType[] getServiceRequestTypesByCategory(java.lang.String authKey, int categoryID, java.lang.String keywords) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestTypesByCategory(authKey, categoryID, keywords);
  }
  
  public net.webqa.WebQACategory[] getServiceRequestCategories(java.lang.String authKey) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestCategories(authKey);
  }
  
  public net.webqa.GetServiceRequestsByReferenceNumbersResponseGetServiceRequestsByReferenceNumbersResult getServiceRequestsByReferenceNumbers(java.lang.String authKey, java.lang.String referenceNumbers) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestsByReferenceNumbers(authKey, referenceNumbers);
  }
  
  public net.webqa.GetServiceRequestsByReferenceNumbersOpen311ResponseGetServiceRequestsByReferenceNumbersOpen311Result getServiceRequestsByReferenceNumbersOpen311(java.lang.String authKey, java.lang.String referenceNumbers) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestsByReferenceNumbersOpen311(authKey, referenceNumbers);
  }
  
  public net.webqa.GetServiceRequestsByFiltersResponseGetServiceRequestsByFiltersResult getServiceRequestsByFilters(java.lang.String authKey, java.lang.String serviceRequestTypeIDs, java.lang.String statusIDs, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestsByFilters(authKey, serviceRequestTypeIDs, statusIDs, startDate, endDate);
  }
  
  public net.webqa.GetServiceRequestsByFiltersOpen311ResponseGetServiceRequestsByFiltersOpen311Result getServiceRequestsByFiltersOpen311(java.lang.String authKey, java.lang.String serviceRequestTypeIDs, java.lang.String statusIDs, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestsByFiltersOpen311(authKey, serviceRequestTypeIDs, statusIDs, startDate, endDate);
  }
  
  public net.webqa.GetNewPaymentResponseGetNewPaymentResult getNewPayment(java.lang.String authKey, java.lang.String paymentId, java.lang.String referenceNumber) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewPayment(authKey, paymentId, referenceNumber);
  }
  
  public net.webqa.GetNewServiceRequestsResponseGetNewServiceRequestsResult getNewServiceRequests(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequests(authKey, createDate);
  }
  
  public net.webqa.GetNewServiceRequestsWithAvailabilityResponseGetNewServiceRequestsWithAvailabilityResult getNewServiceRequestsWithAvailability(java.lang.String authKey, java.lang.String createDate, int availability) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequestsWithAvailability(authKey, createDate, availability);
  }
  
  public net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult getServiceRequestsByCustomer(java.lang.String authKey, java.lang.String customerEmail, java.lang.String createDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestsByCustomer(authKey, customerEmail, createDate);
  }
  
  public net.webqa.GetNewServiceRequestsWithCustomerDataResponseGetNewServiceRequestsWithCustomerDataResult getNewServiceRequestsWithCustomerData(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequestsWithCustomerData(authKey, createDate);
  }
  
  public net.webqa.GetNewServiceRequestsWithCustomerDataByRefResponseGetNewServiceRequestsWithCustomerDataByRefResult getNewServiceRequestsWithCustomerDataByRef(java.lang.String authKey, java.lang.String referenceNo) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequestsWithCustomerDataByRef(authKey, referenceNo);
  }
  
  public net.webqa.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponseGetNewServiceRequestsWithCustomerDataAndAvailabilityResult getNewServiceRequestsWithCustomerDataAndAvailability(java.lang.String authKey, java.lang.String createDate, int availability) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequestsWithCustomerDataAndAvailability(authKey, createDate, availability);
  }
  
  public net.webqa.GetUpdatedServiceRequestsWithCustomerDataResponseGetUpdatedServiceRequestsWithCustomerDataResult getUpdatedServiceRequestsWithCustomerData(java.lang.String authKey, java.lang.String modifiedDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getUpdatedServiceRequestsWithCustomerData(authKey, modifiedDate);
  }
  
  public java.lang.String getCustomFields(java.lang.String authKey, java.lang.String srType) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getCustomFields(authKey, srType);
  }
  
  public java.lang.String getServiceRequestMappings(java.lang.String authKey) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestMappings(authKey);
  }
  
  public net.webqa.GetCustomViewResponseGetCustomViewResult getCustomView(java.lang.String authKey, int viewId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getCustomView(authKey, viewId);
  }
  
  public net.webqa._webserviceDBReturn getCustomViewById(java.lang.String authKey, java.lang.String authKeyAdmin, int staffId, int viewId, int startPageIndex, int pageSize, java.lang.String sortColumn, java.lang.String sortDirection, boolean allRecords) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getCustomViewById(authKey, authKeyAdmin, staffId, viewId, startPageIndex, pageSize, sortColumn, sortDirection, allRecords);
  }
  
  public net.webqa.GetClosedRequestsResponseGetClosedRequestsResult getClosedRequests(java.lang.String authKey, java.lang.String closeDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getClosedRequests(authKey, closeDate);
  }
  
  public boolean updateRequestCustomField(java.lang.String authKey, java.lang.String referenceNo, java.lang.String fieldName, java.lang.String newFieldValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateRequestCustomField(authKey, referenceNo, fieldName, newFieldValue);
  }
  
  public boolean updateRequestCustomFieldById(java.lang.String authKey, java.lang.String referenceNo, int fieldId, java.lang.String newFieldValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateRequestCustomFieldById(authKey, referenceNo, fieldId, newFieldValue);
  }
  
  public net.webqa.GetNewServiceReqeustsWIthPropertyDataResponseGetNewServiceReqeustsWIthPropertyDataResult getNewServiceReqeustsWIthPropertyData(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceReqeustsWIthPropertyData(authKey, createDate);
  }
  
  public net.webqa.GetNewServiceRequestsWithPropertyDataResponseGetNewServiceRequestsWithPropertyDataResult getNewServiceRequestsWithPropertyData(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequestsWithPropertyData(authKey, createDate);
  }
  
  public java.lang.String stripHTML(java.lang.String stringToStrip) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.stripHTML(stringToStrip);
  }
  
  public boolean updateRequestStatus(java.lang.String authKey, java.lang.String referenceNo, java.lang.String customFieldName, java.lang.String customFieldValue, java.lang.String newStatusValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateRequestStatus(authKey, referenceNo, customFieldName, customFieldValue, newStatusValue);
  }
  
  public java.lang.String addServiceRequestNote(java.lang.String authKey, int serviceRequestId, java.lang.String note) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.addServiceRequestNote(authKey, serviceRequestId, note);
  }
  
  public java.lang.String addServiceRequestCommunication(java.lang.String authKey, int serviceRequestId, java.lang.String source, java.lang.String message) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.addServiceRequestCommunication(authKey, serviceRequestId, source, message);
  }
  
  public java.lang.String getRequestStatus(java.lang.String authKey, java.lang.String referenceNo) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getRequestStatus(authKey, referenceNo);
  }
  
  public net.webqa.RetrieveCustomFieldMappingResponseRetrieveCustomFieldMappingResult retrieveCustomFieldMapping(java.lang.String authKey, int module) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.retrieveCustomFieldMapping(authKey, module);
  }
  
  public java.lang.String getCustomerInfoString(java.lang.String authKey, java.lang.String loginEmail) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getCustomerInfoString(authKey, loginEmail);
  }
  
  public net.webqa.WebQACustomer getCustomerInfoObject(java.lang.String authKey, java.lang.String loginEmail) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getCustomerInfoObject(authKey, loginEmail);
  }
  
  public net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult getServiceRequestByReferenceNumber(java.lang.String authKey, java.lang.String referenceNumber) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestByReferenceNumber(authKey, referenceNumber);
  }
  
  public int getServiceRequestId(java.lang.String authKey, java.lang.String customFieldName, java.lang.String customFieldValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestId(authKey, customFieldName, customFieldValue);
  }
  
  public java.lang.String getServiceRequestReferenceNumber(java.lang.String authKey, java.lang.String customFieldName, java.lang.String customFieldValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestReferenceNumber(authKey, customFieldName, customFieldValue);
  }
  
  public net.webqa.GetServiceRequestTypesResponseGetServiceRequestTypesResult getServiceRequestTypes(java.lang.String authKey, int availability) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestTypes(authKey, availability);
  }
  
  public net.webqa.GetServiceRequestTypeCustomFieldsResponseGetServiceRequestTypeCustomFieldsResult getServiceRequestTypeCustomFields(java.lang.String authKey, int serviceRequestTypeId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestTypeCustomFields(authKey, serviceRequestTypeId);
  }
  
  public net.webqa.GetServiceRequestTypeCustomFieldsFullResponseGetServiceRequestTypeCustomFieldsFullResult getServiceRequestTypeCustomFieldsFull(java.lang.String authKey, int serviceRequestTypeId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestTypeCustomFieldsFull(authKey, serviceRequestTypeId);
  }
  
  public net.webqa.GetServiceRequestTypeCustomFieldListValuesResponseGetServiceRequestTypeCustomFieldListValuesResult getServiceRequestTypeCustomFieldListValues(java.lang.String authKey, int serviceRequestFieldId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestTypeCustomFieldListValues(authKey, serviceRequestFieldId);
  }
  
  public net.webqa.GetMobileServiceRequestTypesResponseGetMobileServiceRequestTypesResult getMobileServiceRequestTypes(java.lang.String authKey) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getMobileServiceRequestTypes(authKey);
  }
  
  public net.webqa.GetServiceRequestTypeMobileCustomFieldsResponseGetServiceRequestTypeMobileCustomFieldsResult getServiceRequestTypeMobileCustomFields(java.lang.String authKey, int serviceRequestTypeId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestTypeMobileCustomFields(authKey, serviceRequestTypeId);
  }
  
  public java.lang.String getServiceRequestType(java.lang.String authKey, java.lang.String defaultValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestType(authKey, defaultValue);
  }
  
  public boolean doesAddressExists(java.lang.String authKey, java.lang.String addressOne) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.doesAddressExists(authKey, addressOne);
  }
  
  public net.webqa.GetNewServiceRequestsWithCustomerDataAttachCountResponseGetNewServiceRequestsWithCustomerDataAttachCountResult getNewServiceRequestsWithCustomerDataAttachCount(java.lang.String authKey, java.lang.String createDate, boolean nonPrivateAttachmentsOnly, java.lang.String filterExpression) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getNewServiceRequestsWithCustomerDataAttachCount(authKey, createDate, nonPrivateAttachmentsOnly, filterExpression);
  }
  
  public net.webqa.GetServiceRequestAttachmentsResponseGetServiceRequestAttachmentsResult getServiceRequestAttachments(java.lang.String authKey, java.lang.String refNo, int srID) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.getServiceRequestAttachments(authKey, refNo, srID);
  }
  
  public boolean addServiceRequestAttachment(java.lang.String authKey, int requestId, java.lang.String fileName, byte[] fileStream) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.addServiceRequestAttachment(authKey, requestId, fileName, fileStream);
  }
  
  public java.lang.String createCustomer(java.lang.String authKey, java.lang.String customerEmail, java.lang.String customerFirstName, java.lang.String customerLastName, java.lang.String phone, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zip, java.lang.String comments, java.lang.String password, boolean isActive, java.lang.String groupId) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.createCustomer(authKey, customerEmail, customerFirstName, customerLastName, phone, address1, address2, city, state, zip, comments, password, isActive, groupId);
  }
  
  public java.lang.String updateCustomerCustomFieldbyEmail(java.lang.String authKey, java.lang.String customerEmail, java.lang.String customFieldName, java.lang.String customFieldValue) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.updateCustomerCustomFieldbyEmail(authKey, customerEmail, customFieldName, customFieldValue);
  }
  
  public java.lang.String setPaymentStatus(java.lang.String authKey, java.lang.String paymentId, java.lang.String referenceNumber, java.lang.String status) throws java.rmi.RemoteException{
    if (__wslibSoap == null)
      _init__wslibSoapProxy();
    return __wslibSoap.setPaymentStatus(authKey, paymentId, referenceNumber, status);
  }
  
  
}