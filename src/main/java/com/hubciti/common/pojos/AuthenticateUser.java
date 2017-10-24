package com.hubciti.common.pojos;

/**
 * POJO class for AuthenticateUser.
 * 
 * @author Kumar D
 */

public class AuthenticateUser {

	/**
	 * Commented as there is change in User table in database.
	 */
	protected String userName;
	/**
	 * for email.
	 */
	protected String email;
	/**
	 * for password.
	 */
	protected String password;
	/**
	 * for userId.
	 */
	protected Integer userId;

	/**
	 * Login Status.
	 */

	protected String loginStatus;

	/**
	 * Login Status.
	 */

	protected Boolean LoginSuccess;

	/**
	 * Variable screenContent declared as String.
	 */
	protected String screenContent;

	// -------------------Email

	/**
	 * Variable toAddress declared as String.
	 */

	protected String toAddress;

	/**
	 * Variable fromAddress declared as String.
	 */

	protected String fromAddress;

	/**
	 * Variable subject declared as String.
	 */

	protected String subject;

	/**
	 * Variable msgBody declared as String.
	 */
	protected String msgBody;

	/**
	 * Variable authenticateUsers declared as String.
	 */

	/**
	 * Variable configurationType declared as String.
	 */

	protected String configurationType;

	/**
	 * Variable deviceID declared as String.
	 */
	protected String deviceID;

	/**
	 * for app version
	 */
	private String appVersion;

	/**
	 * update latest version text.
	 */
	private String updateVersionText;
	/**
	 * update version url
	 */
	private String latestAppVerUrl;

	/**
	 * to check latest version or not
	 */

	protected Boolean updateVersionFlag;

	/**
	 * Adding device id to user
	 */

	private Boolean addDeviceToUser;

	/**
	 * Checking user exist or logging for first time through facebook
	 */
	private boolean isExistingUser;

	/**
	 * for chkPassword check
	 */
	private Boolean chkPassword;

	/**
	 * for user isExistingEmail check
	 */
	private Boolean isExistingEmail;
	/**
	 * for user isSufficientQty check
	 */
	private boolean isSufficientQty;
	/**
	 * for qrRetCustPageID.
	 */
	private Integer qrRetCustPageID;

	/**
	 * for status.
	 */
	private Integer status;
	/**
	 * for status.
	 */
	private Integer success;

	private Integer hubCitiId;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistingUser() {
		return isExistingUser;
	}

	public void setExistingUser(boolean isExistingUser) {
		this.isExistingUser = isExistingUser;
	}

	/**
	 * For failure or success response
	 */
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * This method for getting userId.
	 * 
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method for setting userId.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * this method for setting email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * this method for setting email.
	 * 
	 * @param email
	 *            as request parameter.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * for getting loginStatus.
	 * 
	 * @return the loginStatus
	 */
	public String getLoginStatus() {
		return loginStatus;
	}

	/**
	 * for setting loginStatus.
	 * 
	 * @param loginStatus
	 *            the loginStatus to set.
	 */
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	/**
	 * for fetch loginSuccess.
	 * 
	 * @return the loginSuccess
	 */
	public Boolean isLoginSuccess() {
		return LoginSuccess;
	}

	/**
	 * for setting loginSuccess.
	 * 
	 * @param loginSuccess
	 *            the loginSuccess to set
	 */
	public void setLoginSuccess(Boolean loginSuccess) {
		LoginSuccess = loginSuccess;
	}

	// -------------------------------------------Emails---------------------

	/**
	 * to get toAddress.
	 * 
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * to set toAddress.
	 * 
	 * @param toAddress
	 *            the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * to get fromAddress.
	 * 
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * to set fromAddress.
	 * 
	 * @param fromAddress
	 *            the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * to get subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * to set subject.
	 * 
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * to get msgBody.
	 * 
	 * @return the msgBody
	 */
	public String getMsgBody() {
		return msgBody;
	}

	/**
	 * to set msgBody.
	 * 
	 * @param msgBody
	 *            the msgBody to set
	 */
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	/**
	 * to get configurationType.
	 * 
	 * @return the configurationType
	 */
	public String getConfigurationType() {
		return configurationType;
	}

	/**
	 * to set configurationType.
	 * 
	 * @param configurationType
	 *            the configurationType to set
	 */
	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}

	/**
	 * to get screenContent.
	 * 
	 * @return the screenContent
	 */
	public String getScreenContent() {
		return screenContent;
	}

	/**
	 * to set screenContent.
	 * 
	 * @param screenContent
	 *            the screenContent to set
	 */
	public void setScreenContent(String screenContent) {
		this.screenContent = screenContent;
	}

	/**
	 * @return the isSufficientQty
	 */
	public boolean isSufficientQty() {
		return isSufficientQty;
	}

	/**
	 * @param isSufficientQty
	 *            the isSufficientQty to set
	 */
	public void setSufficientQty(boolean isSufficientQty) {
		this.isSufficientQty = isSufficientQty;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the isExistingEmail
	 */
	public Boolean getIsExistingEmail() {
		return isExistingEmail;
	}

	/**
	 * @param isExistingEmail
	 *            the isExistingEmail to set
	 */
	public void setIsExistingEmail(Boolean isExistingEmail) {
		this.isExistingEmail = isExistingEmail;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the qrRetCustPageID
	 */
	public Integer getQrRetCustPageID() {
		return qrRetCustPageID;
	}

	/**
	 * @param qrRetCustPageID
	 *            the qrRetCustPageID to set
	 */
	public void setQrRetCustPageID(Integer qrRetCustPageID) {
		this.qrRetCustPageID = qrRetCustPageID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getUpdateVersionText() {
		return updateVersionText;
	}

	public void setUpdateVersionText(String updateVersionText) {
		this.updateVersionText = updateVersionText;
	}

	public String getLatestAppVerUrl() {
		return latestAppVerUrl;
	}

	public void setLatestAppVerUrl(String latestAppVerUrl) {
		this.latestAppVerUrl = latestAppVerUrl;
	}

	public Boolean isUpdateVersionFlag() {
		return updateVersionFlag;
	}

	public void setUpdateVersionFlag(Boolean updateVersionFlag) {
		this.updateVersionFlag = updateVersionFlag;
	}

	public Boolean getAddDeviceToUser() {
		return addDeviceToUser;
	}

	public void setAddDeviceToUser(Boolean addDeviceToUser) {
		this.addDeviceToUser = addDeviceToUser;
	}

	public Boolean getChkPassword() {
		return chkPassword;
	}

	public void setChkPassword(Boolean chkPassword) {
		this.chkPassword = chkPassword;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the loginSuccess
	 */
	public Boolean getLoginSuccess() {
		return LoginSuccess;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Boolean getUpdateVersionFlag() {
		return updateVersionFlag;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}
}
