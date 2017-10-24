package com.hubciti.common.pojos;

import java.text.DecimalFormat;
import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

public class EventDetails {

	private String responseCode;

	private String responseText;

	private Integer bottomBtn;

	private String bandName;

	private String bandImgPth;

	private Integer bandID;
	
	private String bandIds;

	private Integer eventId;

	private String eventName;

	private String retailName;

	private String shortDes;

	private String price;

	private String longDes;

	private Integer hubCitiId;
	
	private String listingImgPath;

	private String imgPath;

	private Integer busEvent;

	private Integer pkgEvent;

	private String startDate;

	private String endDate;

	private String startTime;

	private String endTime;

	private Integer mItemExist;

	private Integer eventCatId;

	private String eventCatIds;

	private String eventCatName;

	private String groupContent;
	
	private String popUpMsg;
	/**
	 * The retailerName property.
	 */

	private String retailerName;
	/**
	 * The retailerId property.
	 */

	private Integer retailId;

	private String address;
	private String address1;
	private String address2;

	private String city;

	private String state;

	private String postalCode;

	private String latitude;

	private String longitude;

	private String pkgDes;

	private String description;

	private String pkgTicketURL;

	private Integer hotelFlag;

	private Integer mainMenuId;

	private Integer mItemId;

	private Integer bottomBtnId;

	private String platform;

	private Integer rowNum;

	private Boolean isDateFormated;

	private Boolean isTimeFormated;

	private Integer maxCount;

	private Integer nextPage;

	private Integer maxRowNum;

	private Integer eventListId;

	private String distance;

	private String sortColumn;

	private String sortOrder;

	private String evtLocTitle;

	/**
	 * The retailLocationID property.
	 */
	private Integer retailLocationId;
	/**
	 * for contact phone
	 */
	private String contactPhone;
	private Integer rating;
	/**
	 * for contact phone
	 */
	private String roomCheckUrl;
	/**
	 * for contact phone
	 */
	private String roomBookUrl;

	private String sortBy;

	private String groupBy;
	/**
	 */
	private String hotelPrice;
	/**
	 */
	private String packagePrice;
	/**
	 * The imagePath property.
	 */
	private String imagePath;

	private String moreInfoURL;

	private Integer isOnGoing;
	private Integer isUserOutOfRange;

	private String recurringDays;

	private Integer isAppSiteFlag;

	private Integer isRetailLocationFlag;

	private List<EventDetails> eventList;

	private List<CategoryInfo> categoryList;
	
	private Integer bandCntFlag;

	/**
	 * 
	 */
	private String cityName;
	/**
	 * 
	 */
	private List<BottomButton> bottomBtnList;
	/**
	 * 
	 */
	private String shareText;
	/**
	 * 
	 */
	private String qrUrl;

	/**
	 * 
	 */
	private String eventLgSSQRPath;

	/**
	 * Variable for isFormated
	 */
	private Boolean isFormatted;
	/**
	 * Variable for UserID
	 */
	private Integer userId;

	/**
	 * Variable for FundraisingId
	 */
	private Integer fundRaisId;

	/**
	 * Variable for event date
	 */
	private String eventDate;

	/**
	 * Variable for Search key.
	 */
	private String searchKey;

	private Boolean flag;

	private String isOngoing;
	private String isOngoingDaily;
	private String isOngoingMonthly;

	private String eventSDate;
	private String eventSTime;
	private String eventSTimeHrs;
	private String eventSTimeMins;

	private Integer recurrencePatternID;
	private String recurrencePatternName;

	private Integer everyWeekDay;
	private Integer everyWeek;
	private Integer dateOfMonth;
	private Integer everyMonth;
	private Integer everyDayMonth;
	private String[] everyWeekDayMonth;
	private String dayOfMonth;

	private Integer endAfter;
	private Integer dayNumber;
	private String occurenceType;
	private Integer recurrenceInterval;
	private Boolean isWeekDay;
	private Boolean byDayNumber;

	private String eventEDate;
	private String eventETime;
	private String eventETimeHrs;
	private String eventETimeMins;
	private String[] days;
	private EventDetails events;

	private List<EventDetails> eventGroupList;
	private String ticketUrl;

	public List<EventDetails> getEventGroupList() {
		return eventGroupList;
	}

	public void setEventGroupList(List<EventDetails> eventGroupList) {
		this.eventGroupList = eventGroupList;
	}

	public String getGroupContent() {
		return groupContent;
	}

	public void setGroupContent(String groupContent) {
		this.groupContent = groupContent;
	}

	public EventDetails getEvents() {
		return events;
	}

	public void setEvents(EventDetails events) {
		this.events = events;
	}

	/**
	 * 
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * 
	 * @return the search key
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * 
	 * @param searchKey
	 *            to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	/**
	 * 
	 * @param the
	 *            userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return the isFormatted
	 */
	public Boolean getIsFormatted() {
		return isFormatted;
	}

	/**
	 * 
	 * @param isFormatted
	 *            to set
	 */
	public void setIsFormatted(Boolean isFormatted) {
		this.isFormatted = isFormatted;
	}

	/**
	 * 
	 * @return the fundraiseingID
	 */
	public Integer getFundRaisId() {
		return fundRaisId;
	}

	/**
	 * 
	 * @param fundRaisId
	 *            to set
	 */
	public void setFundRaisId(Integer fundRaisId) {
		this.fundRaisId = fundRaisId;
	}

	/**
	 * 
	 * @return the eventDate
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * 
	 * @param eventDate
	 *            to set
	 */
	public void setEventDate(String eventDate) {
		// To convert db date to standart format, set isDateFormated to false
		// else set it to true is date already formated
		if (eventDate == null || "".equals(eventDate)) {
			this.eventDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.eventDate = Utility.convertDBdate(eventDate);
		}
	}

	/**
	 *
	 * @return eventLocationTitle value
	 */
	public String getEvtLocTitle() {
		return evtLocTitle;
	}

	/**
	 * if value is null or empty then set N/A else set value
	 * 
	 * @param eLocTitle
	 */
	public void setEvtLocTitle(String evtLocTitle) {

		if ("".equals(Utility.checkNull(evtLocTitle))) {
			this.evtLocTitle = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.evtLocTitle = evtLocTitle;
		}
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

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		if (null == shortDes) {
			this.shortDes = " ";
		} else if (shortDes.contains("<![CDATA[")) {
			this.shortDes = shortDes;
		} else {
			this.shortDes = "<![CDATA[" + shortDes + "]]>";
		}
	}

	public String getLongDes() {
		return longDes;
	}

	public void setLongDes(String longDes) {
		if (null == longDes) {
			this.longDes = " ";
		} else if (longDes.contains("<![CDATA[")) {
			this.longDes = longDes;
		} else if (null != this.getFlag() && this.getFlag() == true) {
			this.longDes = longDes;
		} else {
			this.longDes = "<![CDATA[" + longDes + "]]>";
		}
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getBusEvent() {
		return busEvent;
	}

	public void setBusEvent(Integer busEvent) {
		this.busEvent = busEvent;
	}

	public Integer getPkgEvent() {
		return pkgEvent;
	}

	public void setPkgEvent(Integer pkgEvent) {
		this.pkgEvent = pkgEvent;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		if (startDate == null || "".equals(startDate)) {
			this.startDate = HubCitiConstants.NOTAPPLICABLE;
		} else if (getIsDateFormated() != null && getIsDateFormated() == false) {
			this.startDate = Utility.convertDBdate(startDate);
		} else {
			this.startDate = startDate;
		}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if (endDate == null || "".equals(endDate)) {
			this.endDate = HubCitiConstants.NOTAPPLICABLE;
		} else if (getIsDateFormated() != null && getIsDateFormated() == false) {
			this.endDate = Utility.convertDBdate(endDate);
		} else {
			this.endDate = endDate;
		}
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (startTime == null || "".equals(startTime)) {
			this.startTime = HubCitiConstants.NOTAPPLICABLE;
		} else if (getIsTimeFormated() != null && getIsTimeFormated() == false) {
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
		} else if (getIsTimeFormated() != null && getIsTimeFormated() == false) {
			this.endTime = Utility.convertDBtime(endTime);
		} else {
			this.endTime = endTime;
		}
	}

	public Integer getmItemExist() {
		return mItemExist;
	}

	public void setmItemExist(Integer mItemExist) {
		this.mItemExist = mItemExist;
	}

	public Integer getEventCatId() {
		return eventCatId;
	}

	public void setEventCatId(Integer eventCatId) {
		this.eventCatId = eventCatId;
	}

	public String getEventCatName() {
		return eventCatName;
	}

	public void setEventCatName(String eventCatName) {
		this.eventCatName = eventCatName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPkgDes() {
		return pkgDes;
	}

	public void setPkgDes(String pkgDes) {
		if (null != pkgDes && !pkgDes.contains("<![CDATA[")) {
			this.pkgDes = "<![CDATA[" + pkgDes + "]]>";
		} else {
			this.pkgDes = pkgDes;
		}
	}

	public String getPkgTicketURL() {
		return pkgTicketURL;
	}

	public void setPkgTicketURL(String pkgTicketURL) {
		this.pkgTicketURL = pkgTicketURL;
	}

	public Integer getHotelFlag() {
		return hotelFlag;
	}

	public void setHotelFlag(Integer hotelFlag) {
		this.hotelFlag = hotelFlag;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public Integer getmItemId() {
		return mItemId;
	}

	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
	}

	public Integer getBottomBtnId() {
		return bottomBtnId;
	}

	public void setBottomBtnId(Integer bottomBtnId) {
		this.bottomBtnId = bottomBtnId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Boolean getIsDateFormated() {
		return isDateFormated;
	}

	public void setIsDateFormated(Boolean isDateFormated) {
		this.isDateFormated = isDateFormated;
	}

	public Boolean getIsTimeFormated() {
		return isTimeFormated;
	}

	public void setIsTimeFormated(Boolean isTimeFormated) {
		this.isTimeFormated = isTimeFormated;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	public List<EventDetails> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDetails> eventList) {
		this.eventList = eventList;
	}

	public Integer getEventListId() {
		return eventListId;
	}

	public void setEventListId(Integer eventListId) {
		this.eventListId = eventListId;
	}

	public List<CategoryInfo> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryInfo> categoryList) {
		this.categoryList = categoryList;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		if ("".equals(Utility.checkNull(distance))) {
			this.distance = HubCitiConstants.NOTAPPLICABLE;
		} else if (!distance.contains(HubCitiConstants.NOTAPPLICABLE) && !distance.contains("mi")) {
			DecimalFormat forTwo = new DecimalFormat("#.##");
			this.distance = (forTwo.format(Float.valueOf(distance))).toString() + " mi";
		} else {
			this.distance = distance;
		}
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	/**
	 * @return the roomCheckUrl
	 */
	public String getRoomCheckUrl() {
		return roomCheckUrl;
	}

	/**
	 * @param roomCheckUrl
	 *            the roomCheckUrl to set
	 */
	public void setRoomCheckUrl(String roomCheckUrl) {
		this.roomCheckUrl = roomCheckUrl;
	}

	/**
	 * @return the roomBookUrl
	 */
	public String getRoomBookUrl() {
		return roomBookUrl;
	}

	/**
	 * @param roomBookUrl
	 *            the roomBookUrl to set
	 */
	public void setRoomBookUrl(String roomBookUrl) {
		this.roomBookUrl = roomBookUrl;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Integer rating) {
		if (null == rating) {
			this.rating = 0;
		} else {
			this.rating = rating;
		}
	}

	/**
	 * @return the retailLocationId
	 */
	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 * @param retailLocationId
	 *            the retailLocationId to set
	 */
	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	/**
	 * @return the hotelPrice
	 */
	public String getHotelPrice() {
		return hotelPrice;
	}

	/**
	 * @param hotelPrice
	 *            the hotelPrice to set
	 */
	public void setHotelPrice(String hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	/**
	 * @return the packagePrice
	 */
	public String getPackagePrice() {
		return packagePrice;
	}

	/**
	 * @param packagePrice
	 *            the packagePrice to set
	 */
	public void setPackagePrice(String packagePrice) {
		if ("".equals(Utility.checkNull(packagePrice))) {
			this.packagePrice = "0";
		} else {
			if (!packagePrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(packagePrice)) {
				this.packagePrice = Utility.formatDecimalValue(packagePrice);
				this.packagePrice = "$" + this.packagePrice;
			} else {
				this.packagePrice = packagePrice;
			}
		}
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		if ("".equals(Utility.checkNull(imagePath))) {
			this.imagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.imagePath = imagePath;
		}
	}

	/**
	 * @return the retailerName
	 */
	public String getRetailerName() {
		return retailerName;
	}

	/**
	 * @param retailerName
	 *            the retailerName to set
	 */
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	/**
	 * @return the retailId
	 */
	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * @param retailId
	 *            the retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * @return the retailName
	 */
	public String getRetailName() {
		return retailName;
	}

	/**
	 * @param retailName
	 *            the retailName to set
	 */
	public void setRetailName(String retailName) {
		this.retailName = retailName;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		if (price == null) {
			this.price = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!price.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(price)) {
				this.price = Utility.formatDecimalValue(price);
				this.price = "$" + this.price;
			} else {
				this.price = price;
			}
		}
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
			this.description = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.description = description;
		}
	}

	/**
	 * @return the eventCatIds
	 */
	public String getEventCatIds() {
		return eventCatIds;
	}

	/**
	 * @param eventCatIds
	 *            the eventCatIds to set
	 */
	public void setEventCatIds(String eventCatIds) {
		this.eventCatIds = eventCatIds;
	}

	public String getMoreInfoURL() {
		return moreInfoURL;
	}

	public void setMoreInfoURL(String moreInfoURL) {
		this.moreInfoURL = moreInfoURL;
	}

	/**
	 * @return the isOnGoing
	 */
	public Integer getIsOnGoing() {
		return isOnGoing;
	}

	/**
	 * @param isOnGoing
	 *            the isOnGoing to set
	 */
	public void setIsOnGoing(Integer isOnGoing) {
		this.isOnGoing = isOnGoing;
	}

	/**
	 * @return the recurringDays
	 */
	public String getRecurringDays() {
		return recurringDays;
	}

	/**
	 * @param recurringDays
	 *            the recurringDays to set
	 */
	public void setRecurringDays(String recurringDays) {
		this.recurringDays = recurringDays;
	}

	public Integer getIsAppSiteFlag() {
		return isAppSiteFlag;
	}

	public void setIsAppSiteFlag(Integer isAppSiteFlag) {
		this.isAppSiteFlag = isAppSiteFlag;
	}

	/**
	 * @return the bottomBtnList
	 */
	public List<BottomButton> getBottomBtnList() {
		return bottomBtnList;
	}

	/**
	 * @param bottomBtnList
	 *            the bottomBtnList to set
	 */
	public void setBottomBtnList(List<BottomButton> bottomBtnList) {
		this.bottomBtnList = bottomBtnList;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the bottomBtn
	 */
	public Integer getBottomBtn() {
		return bottomBtn;
	}

	/**
	 * @param bottomBtn
	 *            the bottomBtn to set
	 */
	public void setBottomBtn(Integer bottomBtn) {
		this.bottomBtn = bottomBtn;
	}

	/**
	 * @return the isRetailLocationFlag
	 */
	public Integer getIsRetailLocationFlag() {
		return isRetailLocationFlag;
	}

	/**
	 * @param isRetailLocationFlag
	 *            the isRetailLocationFlag to set
	 */
	public void setIsRetailLocationFlag(Integer isRetailLocationFlag) {
		this.isRetailLocationFlag = isRetailLocationFlag;
	}

	/**
	 * @return the isUserOutOfRange
	 */
	public Integer getIsUserOutOfRange() {
		return isUserOutOfRange;
	}

	/**
	 * @param isUserOutOfRange
	 *            the isUserOutOfRange to set
	 */
	public void setIsUserOutOfRange(Integer isUserOutOfRange) {
		this.isUserOutOfRange = isUserOutOfRange;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the shareText
	 */
	public String getShareText() {
		return shareText;
	}

	/**
	 * @param shareText
	 *            the shareText to set
	 */
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}

	/**
	 * @return the qrUrl
	 */
	public String getQrUrl() {
		return qrUrl;
	}

	/**
	 * @param qrUrl
	 *            the qrUrl to set
	 */
	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

	/**
	 * @return the eventLgSSQRPath
	 */
	public String getEventLgSSQRPath() {
		return eventLgSSQRPath;
	}

	/**
	 * @param eventLgSSQRPath
	 *            the eventLgSSQRPath to set
	 */
	public void setEventLgSSQRPath(String eventLgSSQRPath) {
		if ("".equals(Utility.checkNull(eventLgSSQRPath))) {
			this.eventLgSSQRPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.eventLgSSQRPath = eventLgSSQRPath;
		}
	}

	public String getIsOngoing() {
		return isOngoing;
	}

	public void setIsOngoing(String isOngoing) {
		this.isOngoing = isOngoing;
	}

	public String getIsOngoingDaily() {
		return isOngoingDaily;
	}

	public void setIsOngoingDaily(String isOngoingDaily) {
		this.isOngoingDaily = isOngoingDaily;
	}

	public String getIsOngoingMonthly() {
		return isOngoingMonthly;
	}

	public void setIsOngoingMonthly(String isOngoingMonthly) {
		this.isOngoingMonthly = isOngoingMonthly;
	}

	public String getEventSDate() {
		return eventSDate;
	}

	public void setEventSDate(String eventSDate) {
		this.eventSDate = eventSDate;
	}

	public String getEventSTime() {
		return eventSTime;
	}

	public void setEventSTime(String eventSTime) {
		this.eventSTime = eventSTime;
	}

	public String getEventSTimeHrs() {
		return eventSTimeHrs;
	}

	public void setEventSTimeHrs(String eventSTimeHrs) {
		this.eventSTimeHrs = eventSTimeHrs;
	}

	public String getEventSTimeMins() {
		return eventSTimeMins;
	}

	public void setEventSTimeMins(String eventSTimeMins) {
		this.eventSTimeMins = eventSTimeMins;
	}

	public Integer getRecurrencePatternID() {
		return recurrencePatternID;
	}

	public void setRecurrencePatternID(Integer recurrencePatternID) {
		this.recurrencePatternID = recurrencePatternID;
	}

	public String getRecurrencePatternName() {
		return recurrencePatternName;
	}

	public void setRecurrencePatternName(String recurrencePatternName) {
		this.recurrencePatternName = recurrencePatternName;
	}

	public Integer getEveryWeekDay() {
		return everyWeekDay;
	}

	public void setEveryWeekDay(Integer everyWeekDay) {
		this.everyWeekDay = everyWeekDay;
	}

	public Integer getEveryWeek() {
		return everyWeek;
	}

	public void setEveryWeek(Integer everyWeek) {
		this.everyWeek = everyWeek;
	}

	public Integer getDateOfMonth() {
		return dateOfMonth;
	}

	public void setDateOfMonth(Integer dateOfMonth) {
		this.dateOfMonth = dateOfMonth;
	}

	public Integer getEveryMonth() {
		return everyMonth;
	}

	public void setEveryMonth(Integer everyMonth) {
		this.everyMonth = everyMonth;
	}

	public Integer getEveryDayMonth() {
		return everyDayMonth;
	}

	public void setEveryDayMonth(Integer everyDayMonth) {
		this.everyDayMonth = everyDayMonth;
	}

	public String[] getEveryWeekDayMonth() {
		return everyWeekDayMonth;
	}

	public void setEveryWeekDayMonth(String[] everyWeekDayMonth) {
		this.everyWeekDayMonth = everyWeekDayMonth;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String[] getDays() {
		return days;
	}

	public void setDays(String[] days) {
		this.days = days;
	}

	public Integer getEndAfter() {
		return endAfter;
	}

	public void setEndAfter(Integer endAfter) {
		this.endAfter = endAfter;
	}

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getOccurenceType() {
		return occurenceType;
	}

	public void setOccurenceType(String occurenceType) {
		this.occurenceType = occurenceType;
	}

	public Integer getRecurrenceInterval() {
		return recurrenceInterval;
	}

	public void setRecurrenceInterval(Integer recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}

	public Boolean getIsWeekDay() {
		return isWeekDay;
	}

	public void setIsWeekDay(Boolean isWeekDay) {
		this.isWeekDay = isWeekDay;
	}

	public Boolean getByDayNumber() {
		return byDayNumber;
	}

	public void setByDayNumber(Boolean byDayNumber) {
		this.byDayNumber = byDayNumber;
	}

	public String getEventEDate() {
		return eventEDate;
	}

	public void setEventEDate(String eventEDate) {
		this.eventEDate = eventEDate;
	}

	public String getEventETime() {
		return eventETime;
	}

	public void setEventETime(String eventETime) {
		this.eventETime = eventETime;
	}

	public String getEventETimeHrs() {
		return eventETimeHrs;
	}

	public void setEventETimeHrs(String eventETimeHrs) {
		this.eventETimeHrs = eventETimeHrs;
	}

	public String getEventETimeMins() {
		return eventETimeMins;
	}

	public void setEventETimeMins(String eventETimeMins) {
		this.eventETimeMins = eventETimeMins;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getBandImgPth() {
		return bandImgPth;
	}

	public void setBandImgPth(String bandImgPth) {
		this.bandImgPth = bandImgPth;
	}

	public Integer getBandID() {
		return bandID;
	}

	public void setBandID(Integer bandID) {
		this.bandID = bandID;
	}

	public String getListingImgPath() {
		return listingImgPath;
	}

	public void setListingImgPath(String listingImgPath) {
		this.listingImgPath = listingImgPath;
	}

	public String getTicketUrl() {
		return ticketUrl;
	}

	public void setTicketUrl(String ticketUrl) {
		this.ticketUrl = ticketUrl;
	}



	/**
	 * @return the bandCntFlag
	 */
	public Integer getBandCntFlag() {
		return bandCntFlag;
	}

	/**
	 * @param bandCntFlag the bandCntFlag to set
	 */
	public void setBandCntFlag(Integer bandCntFlag) {
		this.bandCntFlag = bandCntFlag;
	}

	/**
	 * @return the popUpMsg
	 */
	public String getPopUpMsg() {
		return popUpMsg;
	}

	/**
	 * @param popUpMsg the popUpMsg to set
	 */
	public void setPopUpMsg(String popUpMsg) {
		this.popUpMsg = popUpMsg;
	}

	/**
	 * @return the bandIds
	 */
	public String getBandIds() {
		return bandIds;
	}

	/**
	 * @param bandIds the bandIds to set
	 */
	public void setBandIds(String bandIds) {
		this.bandIds = bandIds;
	}

}
