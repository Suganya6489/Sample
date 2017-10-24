/**
 * WebQAWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.net.webqa;

public interface WebQAWSSoap extends java.rmi.Remote {

    /**
     * Creates service request based on type and returns reference
     * no.
     */
    public com.net.webqa.WebQAIssue createServiceRequestSimple(java.lang.String authKey, int typeNo, java.lang.String typeName, java.lang.String custEmail, com.net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException;

    /**
     * Create customer issue using custEmail or custID.
     */
    public com.net.webqa.WebQAIssue createIssueSimple(java.lang.String authKey, java.lang.String custEmail, java.lang.String custID, int filterA, int filterB, int filterC, java.lang.String subject, java.lang.String question, com.net.webqa.WebQACustomField[] customFieldData) throws java.rmi.RemoteException;

    /**
     * Validates customer/user returning session ID for customer by
     * email.
     */
    public java.lang.String authenticateClientUser(java.lang.String authKey, java.lang.String loginEmail, java.lang.String uPassword, java.lang.String custFieldNameToValidate, java.lang.String custFieldValueToValidate) throws java.rmi.RemoteException;

    /**
     * Validates customer/user returning session ID for customer.
     */
    public java.lang.String authenticateClientUserByID(java.lang.String authKey, java.lang.String userID, java.lang.String uPassword) throws java.rmi.RemoteException;

    /**
     * Retrieves issues for customer, based on passed in cust session
     * ID.
     */
    public com.net.webqa.WebQAIssue[] getClientIssues(java.lang.String authKey, java.lang.String clientAuthSessionID, java.lang.String startDateRange, java.lang.String endDateRange) throws java.rmi.RemoteException;

    /**
     * Retrieves Service Request Types, matching by category and keywords
     * if passed in.
     */
    public com.net.webqa.WebQAServiceRequestType[] getServiceRequestTypes(java.lang.String authKey, int categoryID, java.lang.String keywords) throws java.rmi.RemoteException;

    /**
     * Returns all request categories.
     */
    public com.net.webqa.WebQACategory[] getServiceRequestCategories(java.lang.String authKey) throws java.rmi.RemoteException;

    /**
     * Encrypts Value Using WebQA Key.
     */
    public java.lang.String getWebQAEncryptedValue(java.lang.String valueToEncrypt) throws java.rmi.RemoteException;

    /**
     * Returns all request status types.
     */
    public com.net.webqa.WebQAStatusType[] getServiceRequestStatusTypes(java.lang.String authKey) throws java.rmi.RemoteException;

    /**
     * Returns all custom fields for passed in request type.
     */
    public com.net.webqa.WebQACustomField[] getRequestTypeCustomFields(java.lang.String authKey, int typeNo) throws java.rmi.RemoteException;

    /**
     * Retrieves issues based on passed in criteria.
     */
    public com.net.webqa.WebQAIssue[] getIssueSearchSimple(java.lang.String authKey, java.lang.String keywords, java.lang.String filterA, java.lang.String filterB, java.lang.String filterC, int visibilityID, int totalToReturn) throws java.rmi.RemoteException;

    /**
     * Look up issue by reference number.
     */
    public com.net.webqa.WebQAIssue getIssueByRefNo(java.lang.String authKey, java.lang.String refNo) throws java.rmi.RemoteException;

    /**
     * Retrieve top X issues.
     */
    public com.net.webqa.WebQAIssue[] getTopXFAQs(int numQuestions) throws java.rmi.RemoteException;

    /**
     * Gets all filter A items.
     */
    public com.net.webqa.WebQAFilterItem[] getFilterAItems(java.lang.String authKey) throws java.rmi.RemoteException;

    /**
     * Gets all filter B items, or children of passed in parent.
     */
    public com.net.webqa.WebQAFilterItem[] getFilterBItems(java.lang.String authKey, int parentID) throws java.rmi.RemoteException;

    /**
     * Gets all filter C items, or children of passed in parent.
     */
    public com.net.webqa.WebQAFilterItem[] getFilterCItems(java.lang.String authKey, int parentID) throws java.rmi.RemoteException;

    /**
     * Gets filter setting (title and enabled/disabled) for passed
     * in item.
     */
    public com.net.webqa.WebQAFilterConfig getFilterConfig(java.lang.String authKey, int filterTypeABC) throws java.rmi.RemoteException;

    /**
     * Strips HTML tags from string.
     */
    public java.lang.String stripHTML(java.lang.String stringToStrip) throws java.rmi.RemoteException;
}
