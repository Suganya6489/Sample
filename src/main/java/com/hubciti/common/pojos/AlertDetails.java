package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * POJO class for alert details.
 * 
 * @author dhruvanath_mm
 */
public class AlertDetails {

	private String responseCode;

	private String responseText;

	private Integer alertId;

	private String alertName;

	private String grey;
	private String yellow;
	private String green;
	private String red;

	private String shortDes;

	private String description;

	private Integer hubCitiId;

	private Integer severityId;

	private String startDate;

	private String endDate;

	private Boolean isDateFormated;

	private Integer alertListId;

	private Integer mainMenuId;

	private Integer alertCatId;

	private String alertCatName;

	private Integer nextPage;

	private Integer maxCount;

	private Integer userId;

	private Integer rowNum;

	private String startTime;

	private String endTime;

	private Boolean isTimeFormated;

	private Integer maxRowNum;

	private String sevImgPath;

	private List<CategoryInfo> categoryList;

	private List<AlertDetails> alertList;
	
	private String btnName;

	private String btnLink;
	
	private String evtLogMapUrl;

	private String evtLogImg;
	
	private Integer overLayFlag;

	public Integer getAlertId() {
		return alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		if (null == shortDes) {
			this.shortDes = " ";
		} else
			if (shortDes.contains("<![CDATA[")) {
				this.shortDes = shortDes;
			} else {
				this.shortDes = "<![CDATA[" + shortDes + "]]>";
			}
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public Integer getSeverityId() {
		return severityId;
	}

	public void setSeverityId(Integer severityId) {
		this.severityId = severityId;
	}

	public String getStartDate() {
		return startDate;
	}

	/**
	 * To set start date
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		// To convert db date to standart format, set isDateFormated to false
		// else set it to true is date already formated
		if (startDate == null || "".equals(startDate)) {
			this.startDate = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (getIsDateFormated() != null && getIsDateFormated() == false) {
				this.startDate = Utility.convertDBdate(startDate);
			} else {
				this.startDate = startDate;
			}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		// To convert db date to standart format, set isDateFormated to false
		// else set it to true if date already formated.
		if (endDate == null || "".equals(endDate)) {
			this.endDate = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (getIsDateFormated() != null && getIsDateFormated() == false) {
				this.endDate = Utility.convertDBdate(endDate);
			} else {
				this.endDate = endDate;
			}
	}

	public Integer getAlertListId() {
		return alertListId;
	}

	public void setAlertListId(Integer alertListId) {
		this.alertListId = alertListId;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public Boolean getIsDateFormated() {
		return isDateFormated;
	}

	public void setIsDateFormated(Boolean isDateFormated) {
		this.isDateFormated = isDateFormated;
	}

	public Integer getAlertCatId() {
		return alertCatId;
	}

	public void setAlertCatId(Integer alertCatId) {
		this.alertCatId = alertCatId;
	}

	public String getAlertCatName() {
		return alertCatName;
	}

	public void setAlertCatName(String alertCatName) {
		this.alertCatName = alertCatName;
	}

	public List<CategoryInfo> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryInfo> categoryList) {
		this.categoryList = categoryList;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public List<AlertDetails> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<AlertDetails> alertList) {
		this.alertList = alertList;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (startTime == null || "".equals(startTime)) {
			this.startTime = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (getIsTimeFormated() != null && getIsTimeFormated() == false) {
				this.startTime = Utility.convertDBtime(startTime);
			} else {
				this.startTime = startTime;
			}
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (endTime == null || "".equals(endTime)) {
			this.endTime = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (getIsTimeFormated() != null && getIsTimeFormated() == false) {
				this.endTime = Utility.convertDBtime(endTime);
			} else {
				this.endTime = endTime;
			}
	}

	public Boolean getIsTimeFormated() {
		return isTimeFormated;
	}

	public void setIsTimeFormated(Boolean isTimeFormated) {
		this.isTimeFormated = isTimeFormated;
	}

	public String getSevImgPath() {
		return sevImgPath;
	}

	public void setSevImgPath(String sevImgPath) {
		this.sevImgPath = sevImgPath;
	}

	/**
	 * @return the grey
	 */
	public String getGrey() {
		return grey;
	}

	/**
	 * @param grey
	 *            the grey to set
	 */
	public void setGrey(String grey) {
		this.grey = grey;
	}

	/**
	 * @return the yellow
	 */
	public String getYellow() {
		return yellow;
	}

	/**
	 * @param yellow
	 *            the yellow to set
	 */
	public void setYellow(String yellow) {
		this.yellow = yellow;
	}

	/**
	 * @return the green
	 */
	public String getGreen() {
		return green;
	}

	/**
	 * @param green
	 *            the green to set
	 */
	public void setGreen(String green) {
		this.green = green;
	}

	/**
	 * @return the red
	 */
	public String getRed() {
		return red;
	}

	/**
	 * @param red
	 *            the red to set
	 */
	public void setRed(String red) {
		this.red = red;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		if ("".equals(Utility.checkNull(description))) {
			this.description = description;
		} else {
			this.description = "<![CDATA[" + description + "]]>";

		}

	}

	/**
	 * @return the btnName
	 */
	public String getBtnName() {
		return btnName;
	}

	/**
	 * @return the btnLink
	 */
	public String getBtnLink() {
		return btnLink;
	}

	/**
	 * @return the evtLogMapUrl
	 */
	public String getEvtLogMapUrl() {
		return evtLogMapUrl;
	}

	/**
	 * @return the evtLogImg
	 */
	public String getEvtLogImg() {
		return evtLogImg;
	}

	/**
	 * @return the overLayFlag
	 */
	public Integer getOverLayFlag() {
		return overLayFlag;
	}

	/**
	 * @param btnName the btnName to set
	 */
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	/**
	 * @param btnLink the btnLink to set
	 */
	public void setBtnLink(String btnLink) {
		this.btnLink = btnLink;
	}

	/**
	 * @param evtLogMapUrl the evtLogMapUrl to set
	 */
	public void setEvtLogMapUrl(String evtLogMapUrl) {
		this.evtLogMapUrl = evtLogMapUrl;
	}

	/**
	 * @param evtLogImg the evtLogImg to set
	 */
	public void setEvtLogImg(String evtLogImg) {
		this.evtLogImg = evtLogImg;
	}

	/**
	 * @param overLayFlag the overLayFlag to set
	 */
	public void setOverLayFlag(Integer overLayFlag) {
		this.overLayFlag = overLayFlag;
	}
}
