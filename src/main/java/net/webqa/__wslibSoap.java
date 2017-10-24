/**
 * __wslibSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public interface __wslibSoap extends java.rmi.Remote {

    /**
     * Returns session ID for admin to handle single signon. NOTE:
     * Include additional auth key. ProfileName currently unused.
     */
    public java.lang.String authenticateAdminUser(java.lang.String authKey, java.lang.String authKeyAdmin, java.lang.String userLogin, java.lang.String password, java.lang.String profileName) throws java.rmi.RemoteException;

    /**
     * Returns session ID for customer to handle single signon.
     */
    public java.lang.String authenticateClientUser(java.lang.String authKey, java.lang.String loginEmail, java.lang.String password, java.lang.String groupAccessName, java.lang.String firstName, java.lang.String lastName, java.lang.String phone) throws java.rmi.RemoteException;

    /**
     * Returns session ID for customer to handle single signon, updating
     * customer custom fields.
     */
    public java.lang.String authenticateClientUserCF(java.lang.String authKey, java.lang.String loginEmail, java.lang.String password, java.lang.String groupAccessName, java.lang.String firstName, java.lang.String lastName, java.lang.String phone, java.lang.String custCFName1, java.lang.String custCFValue1, java.lang.String custCFName2, java.lang.String custCFValue2, java.lang.String custCFName3, java.lang.String custCFValue3, java.lang.String custCFName4, java.lang.String custCFValue4, java.lang.String custCFName5, java.lang.String custCFValue5) throws java.rmi.RemoteException;

    /**
     * Returns session id if the customer email and password match
     * specific to passed in group
     */
    public java.lang.String authenticatePortalUserWithGroup(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password, java.lang.String groupAccessName) throws java.rmi.RemoteException;

    /**
     * Returns session id if the customer email and password match
     */
    public java.lang.String authenticatePortalUser(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Returns session id if the customer email and password match
     */
    public java.lang.String authenticatePortalUserV2(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Change the email address.
     */
    public java.lang.String updateClientUserEmail(java.lang.String authKey, java.lang.String oldLoginEmail, java.lang.String newLoginEmail) throws java.rmi.RemoteException;

    /**
     * Change the customer password.
     */
    public java.lang.String updateClientUserPassword(java.lang.String authKey, java.lang.String emailAddress, java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException;

    /**
     * Change the customer name.
     */
    public java.lang.String updateClientUserName(java.lang.String authKey, java.lang.String emailAddress, java.lang.String password, java.lang.String customerName) throws java.rmi.RemoteException;

    /**
     * Returns session ID for customer to handle single signon, updating
     * customer custom fields.
     */
    public java.lang.String updateClientUser(java.lang.String authKey, java.lang.String loginEmail, java.lang.String password, java.lang.String groupAccessName, java.lang.String firstName, java.lang.String lastName, java.lang.String phone, java.lang.String custCFName1, java.lang.String custCFValue1, java.lang.String custCFName2, java.lang.String custCFValue2, java.lang.String custCFName3, java.lang.String custCFValue3, java.lang.String custCFName4, java.lang.String custCFValue4, java.lang.String custCFName5, java.lang.String custCFValue5) throws java.rmi.RemoteException;

    /**
     * Returns group session ID to automatically log in to portal.
     */
    public java.lang.String getGroupSessionID(java.lang.String authKey, java.lang.String groupName) throws java.rmi.RemoteException;

    /**
     * Creates service request based on type and returns reference
     * no.
     */
    public java.lang.String createServiceRequest(java.lang.String authKey, java.lang.String srType, int customerId, java.lang.String customerEmail, net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException;

    /**
     * Creates service request based on type and returns reference
     * no.
     */
    public java.lang.String createServiceRequestWithTypeId(java.lang.String authKey, int srTypeId, int customerId, java.lang.String customerEmail, net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException;

    /**
     * Creates service request and returns reference no.
     */
    public java.lang.String createServiceRequestFull(java.lang.String authKey, net.webqa.WebQACustomer2 webQACustomer, net.webqa.WebQAServiceRequest2 webQAServiceRequest) throws java.rmi.RemoteException;

    /**
     * Creates service request based on type and returns reference
     * no with expanded criteria.
     */
    public java.lang.String createServiceRequestExtended(java.lang.String authKey, int srTypeId, int customerId, java.lang.String customerEmail, net.webqa.WebQACustomField[] customFieldData, java.lang.String customerFirstName, java.lang.String customerLastName, java.lang.String phone, java.util.Calendar createDate, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zip, java.lang.String modifyDate, int requestOriginId, java.lang.String locationAddress1) throws java.rmi.RemoteException;

    /**
     * Retrieve top X issues with optional group.
     */
    public net.webqa.WebQAIssue[] getTopXFAQs(int numQuestions, java.lang.String groupId) throws java.rmi.RemoteException;

    /**
     * Retrieves Service Request Types, matching by category and keywords
     * if passed in.
     */
    public net.webqa.WebQAServiceRequestType[] getServiceRequestTypesByCategory(java.lang.String authKey, int categoryID, java.lang.String keywords) throws java.rmi.RemoteException;

    /**
     * Returns all request categories.
     */
    public net.webqa.WebQACategory[] getServiceRequestCategories(java.lang.String authKey) throws java.rmi.RemoteException;

    /**
     * Retrieve requests with Reference Numbers as the parameter.
     * (NOTES: Limited to max 1000. Ref Nos format example = 'W01111-022013',
     * 'W01112-020212')
     */
    public net.webqa.GetServiceRequestsByReferenceNumbersResponseGetServiceRequestsByReferenceNumbersResult getServiceRequestsByReferenceNumbers(java.lang.String authKey, java.lang.String referenceNumbers) throws java.rmi.RemoteException;

    /**
     * Retrieve requests with Reference Numbers as the parameter.
     * (NOTES: Limited to max 1000. Ref Nos format example = 'W01111-022013',
     * 'W01112-020212')
     */
    public net.webqa.GetServiceRequestsByReferenceNumbersOpen311ResponseGetServiceRequestsByReferenceNumbersOpen311Result getServiceRequestsByReferenceNumbersOpen311(java.lang.String authKey, java.lang.String referenceNumbers) throws java.rmi.RemoteException;

    /**
     * Retrieve requests by various filters. (NOTES: Limited to max
     * 1000. IDsdollar format example = 1, 2, 3, 12. Date format - mm/dd/yyyy
     * hh:mm:ss - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetServiceRequestsByFiltersResponseGetServiceRequestsByFiltersResult getServiceRequestsByFilters(java.lang.String authKey, java.lang.String serviceRequestTypeIDs, java.lang.String statusIDs, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException;

    /**
     * Retrieve requests by various filters. (NOTES: Limited to max
     * 1000. IDsdollar format example = 1, 2, 3, 12. Date format - mm/dd/yyyy
     * hh:mm:ss - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetServiceRequestsByFiltersOpen311ResponseGetServiceRequestsByFiltersOpen311Result getServiceRequestsByFiltersOpen311(java.lang.String authKey, java.lang.String serviceRequestTypeIDs, java.lang.String statusIDs, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException;

    /**
     * Retrieve payment with custom field data by payment Id or reference
     * number.
     */
    public net.webqa.GetNewPaymentResponseGetNewPaymentResult getNewPayment(java.lang.String authKey, java.lang.String paymentId, java.lang.String referenceNumber) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter. (NOTES:
     * Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss - EX. 05/01/2007
     * 15:02:00)
     */
    public net.webqa.GetNewServiceRequestsResponseGetNewServiceRequestsResult getNewServiceRequests(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter. (NOTES:
     * Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss - EX. 05/01/2007
     * 15:02:00. Availability 1 - inactive types, 2 - admin only types, 3
     * - admin and portal )
     */
    public net.webqa.GetNewServiceRequestsWithAvailabilityResponseGetNewServiceRequestsWithAvailabilityResult getNewServiceRequestsWithAvailability(java.lang.String authKey, java.lang.String createDate, int availability) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created for a particular customer.  Use the
     * createDate field to filter by any requests greater than that create
     * date. (NOTES: Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss
     * - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult getServiceRequestsByCustomer(java.lang.String authKey, java.lang.String customerEmail, java.lang.String createDate) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter with cust
     * data. (NOTES: Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss
     * - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataResponseGetNewServiceRequestsWithCustomerDataResult getNewServiceRequestsWithCustomerData(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter with cust
     * data. (NOTES: Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss
     * - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataByRefResponseGetNewServiceRequestsWithCustomerDataByRefResult getNewServiceRequestsWithCustomerDataByRef(java.lang.String authKey, java.lang.String referenceNo) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter with cust
     * data. (NOTES: Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss
     * - EX. 05/01/2007 15:02:00. Availability 1 - inactive types, 2 - admin
     * only types, 3 - admin and portal )
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponseGetNewServiceRequestsWithCustomerDataAndAvailabilityResult getNewServiceRequestsWithCustomerDataAndAvailability(java.lang.String authKey, java.lang.String createDate, int availability) throws java.rmi.RemoteException;

    /**
     * Retrieve requests modified after modifiedDate parameter with
     * cust data. (NOTES: Date format - mm/dd/yyyy hh:mm:ss - EX. 05/01/2007
     * 15:02:00)
     */
    public net.webqa.GetUpdatedServiceRequestsWithCustomerDataResponseGetUpdatedServiceRequestsWithCustomerDataResult getUpdatedServiceRequestsWithCustomerData(java.lang.String authKey, java.lang.String modifiedDate) throws java.rmi.RemoteException;

    /**
     * Retrieve A List of Custom Fields.
     */
    public java.lang.String getCustomFields(java.lang.String authKey, java.lang.String srType) throws java.rmi.RemoteException;

    /**
     * Retrieve A List of Service Request Mappings.
     */
    public java.lang.String getServiceRequestMappings(java.lang.String authKey) throws java.rmi.RemoteException;

    /**
     * Retrieve A Custom View. (NOTES View size is limited to 2500
     * records)
     */
    public net.webqa.GetCustomViewResponseGetCustomViewResult getCustomView(java.lang.String authKey, int viewId) throws java.rmi.RemoteException;

    /**
     * Retrieve A Custom View by Id. (NOTES View size is limited to
     * 2500 records)
     */
    public net.webqa._webserviceDBReturn getCustomViewById(java.lang.String authKey, java.lang.String authKeyAdmin, int staffId, int viewId, int startPageIndex, int pageSize, java.lang.String sortColumn, java.lang.String sortDirection, boolean allRecords) throws java.rmi.RemoteException;

    /**
     * Retrieve requests closed after closeDate parameter. (NOTES:
     * Limited to max 1000. Date format - mm/dd/yyyy hh:mm:ss - EX. 05/01/2007
     * 15:02:00)
     */
    public net.webqa.GetClosedRequestsResponseGetClosedRequestsResult getClosedRequests(java.lang.String authKey, java.lang.String closeDate) throws java.rmi.RemoteException;

    /**
     * Update specific custom field for passed in reference number.
     */
    public boolean updateRequestCustomField(java.lang.String authKey, java.lang.String referenceNo, java.lang.String fieldName, java.lang.String newFieldValue) throws java.rmi.RemoteException;

    /**
     * Update specific custom field for passed in reference number.
     */
    public boolean updateRequestCustomFieldById(java.lang.String authKey, java.lang.String referenceNo, int fieldId, java.lang.String newFieldValue) throws java.rmi.RemoteException;

    /**
     * (LEGACY) Retrieve requests created after CreateDate parameter
     * and return matching property data. (NOTES: Limited to max 1000. Date
     * format - mm/dd/yyyy hh:mm:ss - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetNewServiceReqeustsWIthPropertyDataResponseGetNewServiceReqeustsWIthPropertyDataResult getNewServiceReqeustsWIthPropertyData(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter and return
     * matching property data. (NOTES: Limited to max 1000. Date format -
     * mm/dd/yyyy hh:mm:ss - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetNewServiceRequestsWithPropertyDataResponseGetNewServiceRequestsWithPropertyDataResult getNewServiceRequestsWithPropertyData(java.lang.String authKey, java.lang.String createDate) throws java.rmi.RemoteException;

    /**
     * Strips HTML tags from string.
     */
    public java.lang.String stripHTML(java.lang.String stringToStrip) throws java.rmi.RemoteException;

    /**
     * Update request status using reference number or custom field
     * value.
     */
    public boolean updateRequestStatus(java.lang.String authKey, java.lang.String referenceNo, java.lang.String customFieldName, java.lang.String customFieldValue, java.lang.String newStatusValue) throws java.rmi.RemoteException;

    /**
     * Adds a note to an existing Service Request. Notes are limited
     * in length.
     */
    public java.lang.String addServiceRequestNote(java.lang.String authKey, int serviceRequestId, java.lang.String note) throws java.rmi.RemoteException;

    /**
     * Adds a message to an existing Service Request. messages are
     * limited in length.
     */
    public java.lang.String addServiceRequestCommunication(java.lang.String authKey, int serviceRequestId, java.lang.String source, java.lang.String message) throws java.rmi.RemoteException;

    /**
     * Returns the request status using reference number.
     */
    public java.lang.String getRequestStatus(java.lang.String authKey, java.lang.String referenceNo) throws java.rmi.RemoteException;

    /**
     * Returns session ID for customer to handle single signon, updating
     * customer custom fields.
     */
    public net.webqa.RetrieveCustomFieldMappingResponseRetrieveCustomFieldMappingResult retrieveCustomFieldMapping(java.lang.String authKey, int module) throws java.rmi.RemoteException;

    /**
     * Returns customer information if they exist in string - ID|First|Middle|Last|Title|Phone|AddressOne|AddressTwo|City|State|Zip
     */
    public java.lang.String getCustomerInfoString(java.lang.String authKey, java.lang.String loginEmail) throws java.rmi.RemoteException;

    /**
     * Returns customer object if they exist
     */
    public net.webqa.WebQACustomer getCustomerInfoObject(java.lang.String authKey, java.lang.String loginEmail) throws java.rmi.RemoteException;

    /**
     * Retrieves a service request by reference number
     */
    public net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult getServiceRequestByReferenceNumber(java.lang.String authKey, java.lang.String referenceNumber) throws java.rmi.RemoteException;

    /**
     * Gets the Service Request Id from the custom field being passed
     * in
     */
    public int getServiceRequestId(java.lang.String authKey, java.lang.String customFieldName, java.lang.String customFieldValue) throws java.rmi.RemoteException;

    /**
     * Get the Service Request Reference Number from the custom field
     * being passed in
     */
    public java.lang.String getServiceRequestReferenceNumber(java.lang.String authKey, java.lang.String customFieldName, java.lang.String customFieldValue) throws java.rmi.RemoteException;

    /**
     * Get the Service Request Types.  (NOTES:  Availability 1 - admin
     * and portal, 2 - admin only, 3 - inactive )
     */
    public net.webqa.GetServiceRequestTypesResponseGetServiceRequestTypesResult getServiceRequestTypes(java.lang.String authKey, int availability) throws java.rmi.RemoteException;

    /**
     * Get the Service Request Type custom fields.
     */
    public net.webqa.GetServiceRequestTypeCustomFieldsResponseGetServiceRequestTypeCustomFieldsResult getServiceRequestTypeCustomFields(java.lang.String authKey, int serviceRequestTypeId) throws java.rmi.RemoteException;

    /**
     * Get the Service Request Type custom fields with all information.
     */
    public net.webqa.GetServiceRequestTypeCustomFieldsFullResponseGetServiceRequestTypeCustomFieldsFullResult getServiceRequestTypeCustomFieldsFull(java.lang.String authKey, int serviceRequestTypeId) throws java.rmi.RemoteException;

    /**
     * Get the Service Request Custom Field's list values.
     */
    public net.webqa.GetServiceRequestTypeCustomFieldListValuesResponseGetServiceRequestTypeCustomFieldListValuesResult getServiceRequestTypeCustomFieldListValues(java.lang.String authKey, int serviceRequestFieldId) throws java.rmi.RemoteException;

    /**
     * Get the Mobile Enabled Service Request Types.
     */
    public net.webqa.GetMobileServiceRequestTypesResponseGetMobileServiceRequestTypesResult getMobileServiceRequestTypes(java.lang.String authKey) throws java.rmi.RemoteException;

    /**
     * Get the Mobile Enabled Service Request Type custom fields.
     */
    public net.webqa.GetServiceRequestTypeMobileCustomFieldsResponseGetServiceRequestTypeMobileCustomFieldsResult getServiceRequestTypeMobileCustomFields(java.lang.String authKey, int serviceRequestTypeId) throws java.rmi.RemoteException;

    /**
     * Get the Service Request Type by default value of custom field
     */
    public java.lang.String getServiceRequestType(java.lang.String authKey, java.lang.String defaultValue) throws java.rmi.RemoteException;

    /**
     * Finds if an address exists in the system.
     */
    public boolean doesAddressExists(java.lang.String authKey, java.lang.String addressOne) throws java.rmi.RemoteException;

    /**
     * Retrieve requests created after CreateDate parameter with cust
     * data, including attachment count. (NOTES: Limited to max 1000. Date
     * format - mm/dd/yyyy hh:mm:ss - EX. 05/01/2007 15:02:00)
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataAttachCountResponseGetNewServiceRequestsWithCustomerDataAttachCountResult getNewServiceRequestsWithCustomerDataAttachCount(java.lang.String authKey, java.lang.String createDate, boolean nonPrivateAttachmentsOnly, java.lang.String filterExpression) throws java.rmi.RemoteException;

    /**
     * Retrieve attachments (name and id) for passed in reference
     * number.
     */
    public net.webqa.GetServiceRequestAttachmentsResponseGetServiceRequestAttachmentsResult getServiceRequestAttachments(java.lang.String authKey, java.lang.String refNo, int srID) throws java.rmi.RemoteException;

    /**
     * Attach file to service request
     */
    public boolean addServiceRequestAttachment(java.lang.String authKey, int requestId, java.lang.String fileName, byte[] fileStream) throws java.rmi.RemoteException;

    /**
     * Creates a customer record.
     */
    public java.lang.String createCustomer(java.lang.String authKey, java.lang.String customerEmail, java.lang.String customerFirstName, java.lang.String customerLastName, java.lang.String phone, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zip, java.lang.String comments, java.lang.String password, boolean isActive, java.lang.String groupId) throws java.rmi.RemoteException;

    /**
     * Updating customer's custom fields by customer's email address.
     */
    public java.lang.String updateCustomerCustomFieldbyEmail(java.lang.String authKey, java.lang.String customerEmail, java.lang.String customFieldName, java.lang.String customFieldValue) throws java.rmi.RemoteException;

    /**
     * Set payment status by Payment ID. If Payment ID is not provided,
     * it will use Reference Number.
     */
    public java.lang.String setPaymentStatus(java.lang.String authKey, java.lang.String paymentId, java.lang.String referenceNumber, java.lang.String status) throws java.rmi.RemoteException;
}
