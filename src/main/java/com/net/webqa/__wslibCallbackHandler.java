/**
 * __wslibCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package com.net.webqa;


/**
 *  __wslibCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class __wslibCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public __wslibCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public __wslibCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for createServiceRequestWithTypeId method
     * override this method for handling normal response from createServiceRequestWithTypeId operation
     */
    public void receiveResultcreateServiceRequestWithTypeId(
        com.net.webqa.__wslibStub.CreateServiceRequestWithTypeIdResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createServiceRequestWithTypeId operation
     */
    public void receiveErrorcreateServiceRequestWithTypeId(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createServiceRequest method
     * override this method for handling normal response from createServiceRequest operation
     */
    public void receiveResultcreateServiceRequest(
        com.net.webqa.__wslibStub.CreateServiceRequestResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createServiceRequest operation
     */
    public void receiveErrorcreateServiceRequest(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequestsWithCustomerDataByRef method
     * override this method for handling normal response from getNewServiceRequestsWithCustomerDataByRef operation
     */
    public void receiveResultgetNewServiceRequestsWithCustomerDataByRef(
        com.net.webqa.__wslibStub.GetNewServiceRequestsWithCustomerDataByRefResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequestsWithCustomerDataByRef operation
     */
    public void receiveErrorgetNewServiceRequestsWithCustomerDataByRef(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getCustomViewById method
     * override this method for handling normal response from getCustomViewById operation
     */
    public void receiveResultgetCustomViewById(
        com.net.webqa.__wslibStub.GetCustomViewByIdResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getCustomViewById operation
     */
    public void receiveErrorgetCustomViewById(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for authenticatePortalUserWithGroup method
     * override this method for handling normal response from authenticatePortalUserWithGroup operation
     */
    public void receiveResultauthenticatePortalUserWithGroup(
        com.net.webqa.__wslibStub.AuthenticatePortalUserWithGroupResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from authenticatePortalUserWithGroup operation
     */
    public void receiveErrorauthenticatePortalUserWithGroup(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for authenticateClientUserCF method
     * override this method for handling normal response from authenticateClientUserCF operation
     */
    public void receiveResultauthenticateClientUserCF(
        com.net.webqa.__wslibStub.AuthenticateClientUserCFResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from authenticateClientUserCF operation
     */
    public void receiveErrorauthenticateClientUserCF(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getCustomFields method
     * override this method for handling normal response from getCustomFields operation
     */
    public void receiveResultgetCustomFields(
        com.net.webqa.__wslibStub.GetCustomFieldsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getCustomFields operation
     */
    public void receiveErrorgetCustomFields(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestsByCustomer method
     * override this method for handling normal response from getServiceRequestsByCustomer operation
     */
    public void receiveResultgetServiceRequestsByCustomer(
        com.net.webqa.__wslibStub.GetServiceRequestsByCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestsByCustomer operation
     */
    public void receiveErrorgetServiceRequestsByCustomer(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestByReferenceNumber method
     * override this method for handling normal response from getServiceRequestByReferenceNumber operation
     */
    public void receiveResultgetServiceRequestByReferenceNumber(
        com.net.webqa.__wslibStub.GetServiceRequestByReferenceNumberResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestByReferenceNumber operation
     */
    public void receiveErrorgetServiceRequestByReferenceNumber(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getRequestStatus method
     * override this method for handling normal response from getRequestStatus operation
     */
    public void receiveResultgetRequestStatus(
        com.net.webqa.__wslibStub.GetRequestStatusResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getRequestStatus operation
     */
    public void receiveErrorgetRequestStatus(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestTypeCustomFields method
     * override this method for handling normal response from getServiceRequestTypeCustomFields operation
     */
    public void receiveResultgetServiceRequestTypeCustomFields(
        com.net.webqa.__wslibStub.GetServiceRequestTypeCustomFieldsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestTypeCustomFields operation
     */
    public void receiveErrorgetServiceRequestTypeCustomFields(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestId method
     * override this method for handling normal response from getServiceRequestId operation
     */
    public void receiveResultgetServiceRequestId(
        com.net.webqa.__wslibStub.GetServiceRequestIdResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestId operation
     */
    public void receiveErrorgetServiceRequestId(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createCustomer method
     * override this method for handling normal response from createCustomer operation
     */
    public void receiveResultcreateCustomer(
        com.net.webqa.__wslibStub.CreateCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createCustomer operation
     */
    public void receiveErrorcreateCustomer(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestsByReferenceNumbersOpen311 method
     * override this method for handling normal response from getServiceRequestsByReferenceNumbersOpen311 operation
     */
    public void receiveResultgetServiceRequestsByReferenceNumbersOpen311(
        com.net.webqa.__wslibStub.GetServiceRequestsByReferenceNumbersOpen311Response result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestsByReferenceNumbersOpen311 operation
     */
    public void receiveErrorgetServiceRequestsByReferenceNumbersOpen311(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for authenticatePortalUser method
     * override this method for handling normal response from authenticatePortalUser operation
     */
    public void receiveResultauthenticatePortalUser(
        com.net.webqa.__wslibStub.AuthenticatePortalUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from authenticatePortalUser operation
     */
    public void receiveErrorauthenticatePortalUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getUpdatedServiceRequestsWithCustomerData method
     * override this method for handling normal response from getUpdatedServiceRequestsWithCustomerData operation
     */
    public void receiveResultgetUpdatedServiceRequestsWithCustomerData(
        com.net.webqa.__wslibStub.GetUpdatedServiceRequestsWithCustomerDataResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getUpdatedServiceRequestsWithCustomerData operation
     */
    public void receiveErrorgetUpdatedServiceRequestsWithCustomerData(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequestsWithCustomerDataAttachCount method
     * override this method for handling normal response from getNewServiceRequestsWithCustomerDataAttachCount operation
     */
    public void receiveResultgetNewServiceRequestsWithCustomerDataAttachCount(
        com.net.webqa.__wslibStub.GetNewServiceRequestsWithCustomerDataAttachCountResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequestsWithCustomerDataAttachCount operation
     */
    public void receiveErrorgetNewServiceRequestsWithCustomerDataAttachCount(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestsByReferenceNumbers method
     * override this method for handling normal response from getServiceRequestsByReferenceNumbers operation
     */
    public void receiveResultgetServiceRequestsByReferenceNumbers(
        com.net.webqa.__wslibStub.GetServiceRequestsByReferenceNumbersResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestsByReferenceNumbers operation
     */
    public void receiveErrorgetServiceRequestsByReferenceNumbers(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for doesAddressExists method
     * override this method for handling normal response from doesAddressExists operation
     */
    public void receiveResultdoesAddressExists(
        com.net.webqa.__wslibStub.DoesAddressExistsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from doesAddressExists operation
     */
    public void receiveErrordoesAddressExists(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTopXFAQs method
     * override this method for handling normal response from getTopXFAQs operation
     */
    public void receiveResultgetTopXFAQs(
        com.net.webqa.__wslibStub.GetTopXFAQsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTopXFAQs operation
     */
    public void receiveErrorgetTopXFAQs(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateRequestStatus method
     * override this method for handling normal response from updateRequestStatus operation
     */
    public void receiveResultupdateRequestStatus(
        com.net.webqa.__wslibStub.UpdateRequestStatusResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateRequestStatus operation
     */
    public void receiveErrorupdateRequestStatus(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateRequestCustomFieldById method
     * override this method for handling normal response from updateRequestCustomFieldById operation
     */
    public void receiveResultupdateRequestCustomFieldById(
        com.net.webqa.__wslibStub.UpdateRequestCustomFieldByIdResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateRequestCustomFieldById operation
     */
    public void receiveErrorupdateRequestCustomFieldById(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestMappings method
     * override this method for handling normal response from getServiceRequestMappings operation
     */
    public void receiveResultgetServiceRequestMappings(
        com.net.webqa.__wslibStub.GetServiceRequestMappingsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestMappings operation
     */
    public void receiveErrorgetServiceRequestMappings(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for addServiceRequestNote method
     * override this method for handling normal response from addServiceRequestNote operation
     */
    public void receiveResultaddServiceRequestNote(
        com.net.webqa.__wslibStub.AddServiceRequestNoteResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from addServiceRequestNote operation
     */
    public void receiveErroraddServiceRequestNote(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setPaymentStatus method
     * override this method for handling normal response from setPaymentStatus operation
     */
    public void receiveResultsetPaymentStatus(
        com.net.webqa.__wslibStub.SetPaymentStatusResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setPaymentStatus operation
     */
    public void receiveErrorsetPaymentStatus(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequestsWithCustomerDataAndAvailability method
     * override this method for handling normal response from getNewServiceRequestsWithCustomerDataAndAvailability operation
     */
    public void receiveResultgetNewServiceRequestsWithCustomerDataAndAvailability(
        com.net.webqa.__wslibStub.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequestsWithCustomerDataAndAvailability operation
     */
    public void receiveErrorgetNewServiceRequestsWithCustomerDataAndAvailability(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestReferenceNumber method
     * override this method for handling normal response from getServiceRequestReferenceNumber operation
     */
    public void receiveResultgetServiceRequestReferenceNumber(
        com.net.webqa.__wslibStub.GetServiceRequestReferenceNumberResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestReferenceNumber operation
     */
    public void receiveErrorgetServiceRequestReferenceNumber(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestTypeMobileCustomFields method
     * override this method for handling normal response from getServiceRequestTypeMobileCustomFields operation
     */
    public void receiveResultgetServiceRequestTypeMobileCustomFields(
        com.net.webqa.__wslibStub.GetServiceRequestTypeMobileCustomFieldsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestTypeMobileCustomFields operation
     */
    public void receiveErrorgetServiceRequestTypeMobileCustomFields(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createServiceRequestExtended method
     * override this method for handling normal response from createServiceRequestExtended operation
     */
    public void receiveResultcreateServiceRequestExtended(
        com.net.webqa.__wslibStub.CreateServiceRequestExtendedResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createServiceRequestExtended operation
     */
    public void receiveErrorcreateServiceRequestExtended(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getCustomerInfoString method
     * override this method for handling normal response from getCustomerInfoString operation
     */
    public void receiveResultgetCustomerInfoString(
        com.net.webqa.__wslibStub.GetCustomerInfoStringResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getCustomerInfoString operation
     */
    public void receiveErrorgetCustomerInfoString(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequestsWithPropertyData method
     * override this method for handling normal response from getNewServiceRequestsWithPropertyData operation
     */
    public void receiveResultgetNewServiceRequestsWithPropertyData(
        com.net.webqa.__wslibStub.GetNewServiceRequestsWithPropertyDataResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequestsWithPropertyData operation
     */
    public void receiveErrorgetNewServiceRequestsWithPropertyData(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestAttachments method
     * override this method for handling normal response from getServiceRequestAttachments operation
     */
    public void receiveResultgetServiceRequestAttachments(
        com.net.webqa.__wslibStub.GetServiceRequestAttachmentsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestAttachments operation
     */
    public void receiveErrorgetServiceRequestAttachments(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateClientUserPassword method
     * override this method for handling normal response from updateClientUserPassword operation
     */
    public void receiveResultupdateClientUserPassword(
        com.net.webqa.__wslibStub.UpdateClientUserPasswordResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateClientUserPassword operation
     */
    public void receiveErrorupdateClientUserPassword(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestTypeCustomFieldsFull method
     * override this method for handling normal response from getServiceRequestTypeCustomFieldsFull operation
     */
    public void receiveResultgetServiceRequestTypeCustomFieldsFull(
        com.net.webqa.__wslibStub.GetServiceRequestTypeCustomFieldsFullResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestTypeCustomFieldsFull operation
     */
    public void receiveErrorgetServiceRequestTypeCustomFieldsFull(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for addServiceRequestAttachment method
     * override this method for handling normal response from addServiceRequestAttachment operation
     */
    public void receiveResultaddServiceRequestAttachment(
        com.net.webqa.__wslibStub.AddServiceRequestAttachmentResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from addServiceRequestAttachment operation
     */
    public void receiveErroraddServiceRequestAttachment(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestTypes method
     * override this method for handling normal response from getServiceRequestTypes operation
     */
    public void receiveResultgetServiceRequestTypes(
        com.net.webqa.__wslibStub.GetServiceRequestTypesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestTypes operation
     */
    public void receiveErrorgetServiceRequestTypes(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateRequestCustomField method
     * override this method for handling normal response from updateRequestCustomField operation
     */
    public void receiveResultupdateRequestCustomField(
        com.net.webqa.__wslibStub.UpdateRequestCustomFieldResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateRequestCustomField operation
     */
    public void receiveErrorupdateRequestCustomField(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateClientUserEmail method
     * override this method for handling normal response from updateClientUserEmail operation
     */
    public void receiveResultupdateClientUserEmail(
        com.net.webqa.__wslibStub.UpdateClientUserEmailResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateClientUserEmail operation
     */
    public void receiveErrorupdateClientUserEmail(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateClientUser method
     * override this method for handling normal response from updateClientUser operation
     */
    public void receiveResultupdateClientUser(
        com.net.webqa.__wslibStub.UpdateClientUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateClientUser operation
     */
    public void receiveErrorupdateClientUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getClosedRequests method
     * override this method for handling normal response from getClosedRequests operation
     */
    public void receiveResultgetClosedRequests(
        com.net.webqa.__wslibStub.GetClosedRequestsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getClosedRequests operation
     */
    public void receiveErrorgetClosedRequests(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestType method
     * override this method for handling normal response from getServiceRequestType operation
     */
    public void receiveResultgetServiceRequestType(
        com.net.webqa.__wslibStub.GetServiceRequestTypeResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestType operation
     */
    public void receiveErrorgetServiceRequestType(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for authenticateAdminUser method
     * override this method for handling normal response from authenticateAdminUser operation
     */
    public void receiveResultauthenticateAdminUser(
        com.net.webqa.__wslibStub.AuthenticateAdminUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from authenticateAdminUser operation
     */
    public void receiveErrorauthenticateAdminUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequests method
     * override this method for handling normal response from getNewServiceRequests operation
     */
    public void receiveResultgetNewServiceRequests(
        com.net.webqa.__wslibStub.GetNewServiceRequestsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequests operation
     */
    public void receiveErrorgetNewServiceRequests(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestsByFilters method
     * override this method for handling normal response from getServiceRequestsByFilters operation
     */
    public void receiveResultgetServiceRequestsByFilters(
        com.net.webqa.__wslibStub.GetServiceRequestsByFiltersResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestsByFilters operation
     */
    public void receiveErrorgetServiceRequestsByFilters(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateClientUserName method
     * override this method for handling normal response from updateClientUserName operation
     */
    public void receiveResultupdateClientUserName(
        com.net.webqa.__wslibStub.UpdateClientUserNameResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateClientUserName operation
     */
    public void receiveErrorupdateClientUserName(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewPayment method
     * override this method for handling normal response from getNewPayment operation
     */
    public void receiveResultgetNewPayment(
        com.net.webqa.__wslibStub.GetNewPaymentResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewPayment operation
     */
    public void receiveErrorgetNewPayment(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createServiceRequestFull method
     * override this method for handling normal response from createServiceRequestFull operation
     */
    public void receiveResultcreateServiceRequestFull(
        com.net.webqa.__wslibStub.CreateServiceRequestFullResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createServiceRequestFull operation
     */
    public void receiveErrorcreateServiceRequestFull(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateCustomerCustomFieldbyEmail method
     * override this method for handling normal response from updateCustomerCustomFieldbyEmail operation
     */
    public void receiveResultupdateCustomerCustomFieldbyEmail(
        com.net.webqa.__wslibStub.UpdateCustomerCustomFieldbyEmailResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateCustomerCustomFieldbyEmail operation
     */
    public void receiveErrorupdateCustomerCustomFieldbyEmail(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceReqeustsWIthPropertyData method
     * override this method for handling normal response from getNewServiceReqeustsWIthPropertyData operation
     */
    public void receiveResultgetNewServiceReqeustsWIthPropertyData(
        com.net.webqa.__wslibStub.GetNewServiceReqeustsWIthPropertyDataResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceReqeustsWIthPropertyData operation
     */
    public void receiveErrorgetNewServiceReqeustsWIthPropertyData(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for authenticatePortalUserV2 method
     * override this method for handling normal response from authenticatePortalUserV2 operation
     */
    public void receiveResultauthenticatePortalUserV2(
        com.net.webqa.__wslibStub.AuthenticatePortalUserV2Response result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from authenticatePortalUserV2 operation
     */
    public void receiveErrorauthenticatePortalUserV2(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for addServiceRequestCommunication method
     * override this method for handling normal response from addServiceRequestCommunication operation
     */
    public void receiveResultaddServiceRequestCommunication(
        com.net.webqa.__wslibStub.AddServiceRequestCommunicationResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from addServiceRequestCommunication operation
     */
    public void receiveErroraddServiceRequestCommunication(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequestsWithAvailability method
     * override this method for handling normal response from getNewServiceRequestsWithAvailability operation
     */
    public void receiveResultgetNewServiceRequestsWithAvailability(
        com.net.webqa.__wslibStub.GetNewServiceRequestsWithAvailabilityResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequestsWithAvailability operation
     */
    public void receiveErrorgetNewServiceRequestsWithAvailability(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getNewServiceRequestsWithCustomerData method
     * override this method for handling normal response from getNewServiceRequestsWithCustomerData operation
     */
    public void receiveResultgetNewServiceRequestsWithCustomerData(
        com.net.webqa.__wslibStub.GetNewServiceRequestsWithCustomerDataResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getNewServiceRequestsWithCustomerData operation
     */
    public void receiveErrorgetNewServiceRequestsWithCustomerData(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMobileServiceRequestTypes method
     * override this method for handling normal response from getMobileServiceRequestTypes operation
     */
    public void receiveResultgetMobileServiceRequestTypes(
        com.net.webqa.__wslibStub.GetMobileServiceRequestTypesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMobileServiceRequestTypes operation
     */
    public void receiveErrorgetMobileServiceRequestTypes(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for retrieveCustomFieldMapping method
     * override this method for handling normal response from retrieveCustomFieldMapping operation
     */
    public void receiveResultretrieveCustomFieldMapping(
        com.net.webqa.__wslibStub.RetrieveCustomFieldMappingResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from retrieveCustomFieldMapping operation
     */
    public void receiveErrorretrieveCustomFieldMapping(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestsByFiltersOpen311 method
     * override this method for handling normal response from getServiceRequestsByFiltersOpen311 operation
     */
    public void receiveResultgetServiceRequestsByFiltersOpen311(
        com.net.webqa.__wslibStub.GetServiceRequestsByFiltersOpen311Response result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestsByFiltersOpen311 operation
     */
    public void receiveErrorgetServiceRequestsByFiltersOpen311(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for authenticateClientUser method
     * override this method for handling normal response from authenticateClientUser operation
     */
    public void receiveResultauthenticateClientUser(
        com.net.webqa.__wslibStub.AuthenticateClientUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from authenticateClientUser operation
     */
    public void receiveErrorauthenticateClientUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for stripHTML method
     * override this method for handling normal response from stripHTML operation
     */
    public void receiveResultstripHTML(
        com.net.webqa.__wslibStub.StripHTMLResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from stripHTML operation
     */
    public void receiveErrorstripHTML(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getCustomView method
     * override this method for handling normal response from getCustomView operation
     */
    public void receiveResultgetCustomView(
        com.net.webqa.__wslibStub.GetCustomViewResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getCustomView operation
     */
    public void receiveErrorgetCustomView(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getGroupSessionID method
     * override this method for handling normal response from getGroupSessionID operation
     */
    public void receiveResultgetGroupSessionID(
        com.net.webqa.__wslibStub.GetGroupSessionIDResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getGroupSessionID operation
     */
    public void receiveErrorgetGroupSessionID(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getServiceRequestTypeCustomFieldListValues method
     * override this method for handling normal response from getServiceRequestTypeCustomFieldListValues operation
     */
    public void receiveResultgetServiceRequestTypeCustomFieldListValues(
        com.net.webqa.__wslibStub.GetServiceRequestTypeCustomFieldListValuesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getServiceRequestTypeCustomFieldListValues operation
     */
    public void receiveErrorgetServiceRequestTypeCustomFieldListValues(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getCustomerInfoObject method
     * override this method for handling normal response from getCustomerInfoObject operation
     */
    public void receiveResultgetCustomerInfoObject(
        com.net.webqa.__wslibStub.GetCustomerInfoObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getCustomerInfoObject operation
     */
    public void receiveErrorgetCustomerInfoObject(java.lang.Exception e) {
    }
}
