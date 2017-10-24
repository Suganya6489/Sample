package com.hubciti.common.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.Share;
import com.hubciti.common.utility.Utility;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XstreamParserHelper {

	/**
	 * For getting Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(XstreamParserHelper.class);

	/**
	 * For XStream initilization with null.
	 */
	private static XStream xstream;
	private static XStream xstreamJson;

	/**
	 * This method for alias names for response xmls.
	 * 
	 * @return XStream
	 * @throws HubCitiException.
	 */
	private static XStream getXstreamParser() throws HubCitiException {
		try {
			if (xstream == null) {
				xstream = new XStream();

				// xstream.alias("Temp", com.hubciti.common.pojos.Temp.class);
				xstream.alias("UserDetails", com.hubciti.common.pojos.UserDetails.class);
				xstream.alias("LoginFlowDetails", com.hubciti.common.pojos.LoginFlowDetails.class);
				xstream.alias("CategoryDetails", com.hubciti.common.pojos.CategoryDetails.class);
				xstream.alias("MainCategory", com.hubciti.common.pojos.MainCategory.class);
				xstream.alias("SubCategory", com.hubciti.common.pojos.SubCategory.class);
				xstream.alias("Menu", com.hubciti.common.pojos.Menu.class);
				xstream.alias("MenuItem", com.hubciti.common.pojos.MenuItem.class);
				xstream.addImplicitCollection(com.hubciti.common.pojos.CitiExperience.class, "partnerList");
				xstream.alias("CitiExperience", com.hubciti.common.pojos.CitiExperience.class);
				xstream.alias("AuthenticateUser", com.hubciti.common.pojos.AuthenticateUser.class);
				xstream.alias("ThisLocationRequest", com.hubciti.common.pojos.ThisLocationRequest.class);
				xstream.alias("RetailersDetails", com.hubciti.common.pojos.RetailersDetails.class);
				xstream.alias("RetailerDetail", com.hubciti.common.pojos.RetailerDetail.class);
				xstream.alias("Retailer", com.hubciti.common.pojos.Retailer.class);
				xstream.alias("UserRegistrationInfo", com.hubciti.common.pojos.UserRegistrationInfo.class);
				xstream.alias("TutorialMedia", com.hubciti.common.pojos.TutorialMedia.class);
				xstream.alias("TutorialMediaResultSet", com.hubciti.common.pojos.TutorialMediaResultSet.class);
				xstream.alias("BottomButton", com.hubciti.common.pojos.BottomButton.class);
				xstream.alias("CategoryInfo", com.hubciti.common.pojos.CategoryInfo.class);
				xstream.alias("RetailerCreatedPages", com.hubciti.common.pojos.RetailerCreatedPages.class);
				xstream.alias("ProductDetailsRequest", com.hubciti.common.pojos.ProductDetailsRequest.class);
				xstream.alias("ProductDetails", com.hubciti.common.pojos.ProductDetails.class);
				xstream.alias("HotDealsDetails", com.hubciti.common.pojos.HotDealsDetails.class);
				xstream.alias("CouponDetails", com.hubciti.common.pojos.CouponDetails.class);
				xstream.alias("ProductDetail", com.hubciti.common.pojos.ProductDetail.class);
				xstream.alias("HotDealsDetails", com.hubciti.common.pojos.HotDealsDetails.class);
				xstream.alias("HotDealsCategoryInfo", com.hubciti.common.pojos.HotDealsCategoryInfo.class);
				xstream.alias("UserTrackingData", com.hubciti.common.pojos.UserTrackingData.class);
				xstream.alias("Categories", com.hubciti.common.pojos.Categories.class);
				xstream.alias("GoogleCategory", com.hubciti.common.pojos.GoogleCategory.class);
				xstream.alias("ServiceSerachRequest", com.scansee.externalapi.google.pojos.ServiceSerachRequest.class);
				xstream.alias("UserRatingInfo", com.hubciti.common.pojos.UserRatingInfo.class);
				xstream.alias("CLRDetails", com.hubciti.common.pojos.CLRDetails.class);
				xstream.alias("CouponsDetails", com.hubciti.common.pojos.CouponsDetails.class);
				xstream.alias("ServiceSearchResponse", com.scansee.externalapi.google.pojos.ServiceSearchResponse.class);
				xstream.alias("ServiceResult", com.scansee.externalapi.google.pojos.ServiceResult.class);
				xstream.alias("OnlineStores", com.scansee.externalapi.shopzilla.pojos.OnlineStores.class);
				xstream.alias("OnlineStoresRequest", com.scansee.externalapi.shopzilla.pojos.OnlineStoresRequest.class);
				xstream.alias("Offer", com.scansee.externalapi.shopzilla.pojos.Offer.class);
				xstream.alias("FindNearByDetailsResultSet", com.hubciti.common.pojos.FindNearByIntactResponse.class);
				xstream.alias("FindNearByDetails", com.hubciti.common.pojos.FindNearByResponse.class);
				xstream.alias("OnlineStores", com.scansee.externalapi.shopzilla.pojos.OnlineStores.class);

				xstream.alias("ProductRetailerInfo", com.hubciti.common.pojos.ShareProductRetailerInfo.class);

				xstream.alias("HotDealAPIResultSet", com.hubciti.common.pojos.HotDealAPIResultSet.class);
				xstream.alias("HotDealsListRequest", com.hubciti.common.pojos.HotDealsListRequest.class);
				xstream.alias("HotDealsListResultSet", com.hubciti.common.pojos.HotDealsListResultSet.class);
				xstream.alias("HotDealsResultSet", com.hubciti.common.pojos.HotDealsResultSet.class);
				xstream.alias("Category", com.hubciti.common.pojos.Category.class);
				xstream.alias("ShareProductInfo", com.hubciti.common.pojos.ShareProductInfo.class);
				xstream.alias("WishListHistoryDetails", com.hubciti.common.pojos.WishListHistoryDetails.class);
				xstream.alias("WishListResultSet", com.hubciti.common.pojos.WishListResultSet.class);
				xstream.alias("AlertDetails", com.hubciti.common.pojos.AlertDetails.class);
				xstream.alias("ProductAttributes", com.hubciti.common.pojos.ProductAttributes.class);
				xstream.alias("EventDetails", com.hubciti.common.pojos.EventDetails.class);
				xstream.alias("UserSettings", com.hubciti.common.pojos.UserSettings.class);
				xstream.alias("ProductRatingReview", com.hubciti.common.pojos.ProductRatingReview.class);
				xstream.alias("UserRatingInfo", com.hubciti.common.pojos.UserRatingInfo.class);
				xstream.alias("ProductReview", com.hubciti.common.pojos.ProductReview.class);
				xstream.alias("CountryCode", com.hubciti.common.pojos.CountryCode.class);
				xstream.alias("CountryCodes", com.hubciti.common.pojos.CountryCodes.class);
				xstream.alias("EducationLevel", com.hubciti.common.pojos.EducationLevel.class);
				xstream.alias("MaritalStatus", com.hubciti.common.pojos.MaritalStatus.class);
				xstream.alias("IncomeRange", com.hubciti.common.pojos.IncomeRange.class);
				xstream.alias("FAQDetails", com.hubciti.common.pojos.FAQDetails.class);
				xstream.alias("City", com.hubciti.common.pojos.City.class);
				xstream.alias("Fundraiser", com.hubciti.common.pojos.Fundraiser.class);
				xstream.alias("Filter", com.hubciti.common.pojos.Filter.class);
				
				xstream.alias("Data", com.hubciti.common.pojos.Data.class);
				xstream.alias("Deal", com.hubciti.common.pojos.Deal.class);
				xstream.alias("RSSFeedMessage", com.hubciti.common.pojos.RSSFeedMessage.class);
				// share by email 
				xstream.alias("share", Share.class);
				xstream.alias("News", com.hubciti.common.pojos.News.class);
				xstream.alias("item",com.hubciti.common.pojos.Item.class);
				
				/*
				 * To remove nodes in xml
				 */
				xstream.addImplicitCollection(com.hubciti.common.pojos.MainCategory.class, "listSubCat");
				xstream.addImplicitCollection(com.hubciti.common.pojos.CategoryDetails.class, "listMainCat");
				xstream.addImplicitCollection(com.hubciti.common.pojos.Menu.class, "arMItemList");
				xstream.addImplicitCollection(com.hubciti.common.pojos.TutorialMediaResultSet.class, "tutorialMedia");
				xstream.addImplicitCollection(com.hubciti.common.pojos.Menu.class, "arBottomBtnList");
				xstream.addImplicitCollection(com.hubciti.common.pojos.UserRatingInfo.class, "arUserRatingInfo");
				xstream.addImplicitCollection(com.hubciti.common.pojos.UserRatingInfo.class, "userRatingInfo");
				// xstream.addImplicitCollection(com.hubciti.common.pojos.RetailersDetails.class,
				// "retailerDetail");
				xstream.addImplicitCollection(com.hubciti.common.pojos.ProductDetails.class, "productDetail");
				xstream.addImplicitCollection(com.hubciti.common.pojos.FindNearByIntactResponse.class, "findNearByDetails");
				xstream.addImplicitCollection(com.hubciti.common.pojos.FAQDetails.class, "faqDetail");
				xstream.addImplicitCollection(com.hubciti.common.pojos.Item.class, "item");
				//xstream.addImplicitCollection(com.hubciti.common.pojos.Menu.class, "arBottomBtnList");
				//xstream.addImplicitCollection(com.hubciti.common.pojos.RetailersDetails.class, "arBottomBtnList");
			}
		} catch (Exception exception) {
			throw new HubCitiException(exception);
		}
		return xstream;
	}
	
	/**
	 * This method for alias names for response xmls.
	 * 
	 * @return XStream
	 * @throws HubCitiException.
	 */
	private static XStream getXstreamJsonParser() throws HubCitiException {
		try {
			
			if (xstreamJson == null) {
				xstreamJson = new XStream(new JettisonMappedXmlDriver());
			}			
			
			xstreamJson.alias("customer", net.webqa.CreateCustomer.class);
			xstreamJson.alias("response", com.hubciti.common.pojos.Response.class);
			xstreamJson.alias("customfield",com.net.webqa.WebQACustomField.class);
			xstreamJson.alias("govqa",com.hubciti.common.pojos.GovQA.class);
			xstreamJson.alias("customerInfo",com.hubciti.common.pojos.CustomerInfo.class);
			xstreamJson.alias("faq",com.hubciti.common.pojos.Faq.class);
			xstreamJson.alias("categories",com.net.webqa.WebQAFilterItem.class);
			xstreamJson.useAttributeFor(com.hubciti.common.pojos.Response.class,"categories");
			
		} catch (Exception exception) {
			
			throw new HubCitiException(exception);
			
		}
		return xstreamJson;
	}

	/**
	 * This method produces the response xml.
	 * 
	 * @param object
	 * @return outPutXml as String
	 * @throws HubCitiException
	 */
	public static String produceXmlFromObject(Object object) throws HubCitiException {
		String outputXml = null;
		xstream = getXstreamParser();
		outputXml = xstream.toXML(object);
		return Utility.removeSpecialChars(outputXml);
	}
	
	/**
	 * This method produces the response xml.
	 * 
	 * @param object
	 * @return outPutXml as String
	 * @throws HubCitiException
	 */
	public static String produceJsonFromObject(Object object) throws HubCitiException {
		String outputXml = null;
		xstreamJson = getXstreamJsonParser();
		outputXml = xstreamJson.toXML(object);
		return Utility.removeSpecialChars(outputXml);
	}

	/**
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public Object parseXmlToObject(String xml) throws HubCitiException {
		Object obj = null;
		try {
			xstream = getXstreamParser();
			// xml = Utility.removeSpecialCharsReverse(xml);
			obj = xstream.fromXML(xml);
		} catch (ClassCastException classCastException) {
			LOG.error("ClassCastException:", classCastException);
			throw new HubCitiException(classCastException);
		} catch (Exception exception) {
			LOG.error("Exception:", exception);
			throw new HubCitiException(exception);
		}
		return obj;
	}
	
	/**
	 * 
	 * @param json
	 * @return Object Pojo
	 * @throws HubCitiException
	 */
	public Object parseJsonToObject(String json) throws HubCitiException {
		Object obj = null;
		
		try {
			xstreamJson = getXstreamJsonParser();
			// xml = Utility.removeSpecialCharsReverse(xml);
			obj = xstreamJson.fromXML(json);
		} catch (ClassCastException classCastException) {
			LOG.error("ClassCastException:", classCastException);
			throw new HubCitiException(classCastException);
		} catch (Exception exception) {
			LOG.error("Exception:", exception);
			throw new HubCitiException(exception);
		}
		
		return obj;
	}
}
