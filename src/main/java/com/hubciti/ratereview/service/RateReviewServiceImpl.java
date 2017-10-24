package com.hubciti.ratereview.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.Share;
import com.hubciti.common.pojos.ShareProductInfo;
import com.hubciti.common.pojos.ShareProductInfoEmailLink;
import com.hubciti.common.pojos.ShareProductRetailerInfo;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRatingInfo;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.utility.Utility;
import com.hubciti.ratereview.dao.RateReviewDao;

public class RateReviewServiceImpl implements RateReviewService {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(RateReviewServiceImpl.class);

	/**
	 * Instance variable for rateReviewDao.
	 */
	public RateReviewDao rateReviewDao;

	/**
	 * @param rateReviewDao
	 *            the rateReviewDao to set
	 */
	public void setRateReviewDao(RateReviewDao rateReviewDao) {
		this.rateReviewDao = rateReviewDao;
	}

	/**
	 * This ServiceImpl method for get the user product rating information.
	 * 
	 * @param ProductDetailsRequest
	 *            as productDetReq.
	 * @return XML containing user product rating list information in the
	 *         response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String fetchUserProductRating(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : fecthUserProductRating");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetailsRequest productDetReq = (ProductDetailsRequest) streamHelper.parseXmlToObject(xml);

		if (null == productDetReq.getUserId() || null == productDetReq.getProductId() || null == productDetReq.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final UserRatingInfo userRatingInfo = rateReviewDao.fetchUserProductRating(productDetReq);
			if (null != userRatingInfo) {
				response = XstreamParserHelper.produceXmlFromObject(userRatingInfo);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : fecthUserProductRating");
		return response;
	}

	/**
	 * This ServiceImpl method for for saving user product rating information.
	 * 
	 * @param xml
	 *            input request for which needed to save user product rating.
	 * @return XML containing success or failure in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String saveUserProductRating(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : userTrackingShareTypeUpdate");

		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final UserRatingInfo userRatingInfo = (UserRatingInfo) parser.parseXmlToObject(xml);

		if (null == userRatingInfo.getUserId() || null == userRatingInfo.getProductId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = rateReviewDao.saveUserProductRating(userRatingInfo);
			if (null != response && response.equals(HubCitiConstants.SUCCESS)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : saveUserProductRating");
		return response;
	}

	/**
	 * This ServiceImpl method for input parameters and returns validation error
	 * if validation fails. This Method fetches product and retailer
	 * information.
	 * 
	 * @param xml
	 *            contains information to fetch product and retailer details.
	 * @return String XML with Product and Retailer Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareProductRetailerInfo(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareProductRetailerInfo");

		String response = null;
		// RetailerDetail objRetDetail = null;
		ShareProductRetailerInfo shareProductRetailerInfo = null;
		ShareProductInfoEmailLink shareProductInfoEmailLink = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo shareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		if (null == shareProductInfo.getHubCitiId() || null == shareProductInfo.getProductId() || null == shareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			final ProductDetail productDetail = rateReviewDao.getProductInfo(shareProductInfo);

			/*
			 * if (1 == shareProductInfo.getIsFromThisLocation()) {
			 * 
			 * if (null == shareProductInfo.getRetailerId()) { return
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.INSUFFICENTREQUESTTEXT); }
			 * 
			 * if (null == shareProductInfo.getRetailerLocationId()) { return
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.INSUFFICENTREQUESTTEXT); }
			 * 
			 * objRetDetail =
			 * rateReviewDao.getRetailerInfoWithSalePrice(shareProductInfo); }
			 */

			if (null != productDetail) {

				shareProductRetailerInfo = new ShareProductRetailerInfo();
				shareProductInfoEmailLink = new ShareProductInfoEmailLink();

				shareProductRetailerInfo.setResponseCode(HubCitiConstants.SUCCESSCODE);
				shareProductRetailerInfo.setResponseText(HubCitiConstants.SUCCESSTEXT);

				// shareProductInfoEmailLink.setEmailTemplateURL(productDetail.getEmailTemplateURL());
				shareProductInfoEmailLink.setImagePath(productDetail.getImagePath());
				shareProductInfoEmailLink.setProductName(productDetail.getProductName());
				shareProductInfoEmailLink.setlDescription(productDetail.getShareProdLongDesc());

				if (null != productDetail.getQrUrl() && !productDetail.getQrUrl().equals("N/A")) {
					shareProductInfoEmailLink.setQrUrl(Utility.getTinyURL(productDetail.getQrUrl()));
				}
				/*
				 * shareProductRetailerInfo.setTitleText(HubCitiConstants.
				 * SHAREPRODUCTTEXT +
				 * HubCitiConstants.SHARE_PRODUCT_TEXT_FROM_HUBCITI);
				 * shareProductRetailerInfo
				 * .setTitleText2(HubCitiConstants.SHAREPRODUCTMEDIATITLETEXT);
				 */
				shareProductRetailerInfo.setProductDetail(shareProductInfoEmailLink);
			}

			/*
			 * if (null != objRetDetail) { final RetailerDetail retailerDetails
			 * = new RetailerDetail();
			 * retailerDetails.setCompleteAddress(objRetDetail
			 * .getRetaileraddress1() + "," + objRetDetail.getCity() + "," +
			 * objRetDetail.getState() + "," + objRetDetail.getPostalCode());
			 * retailerDetails.setRetailerName(objRetDetail.getRetailerName());
			 * retailerDetails.setSalePrice(objRetDetail.getSalePrice());
			 * shareProductRetailerInfo
			 * .setTitleText(HubCitiConstants.SHAREPRODUCTTEXT +
			 * objRetDetail.getRetailerName() +
			 * HubCitiConstants.SHARE_PRODUCT_TEXT_FROM_HUBCITI);
			 * shareProductRetailerInfo.setRetailerDetail(retailerDetails); }
			 */
			if (null != shareProductRetailerInfo) {
				response = XstreamParserHelper.produceXmlFromObject(shareProductRetailerInfo);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareProductRetailerInfo");
		return response;
	}

	/**
	 * This ServiceImpl method for the input parameters and returns validation
	 * error if validation fails. This Method fetches hot deal or coupon
	 * information.
	 * 
	 * @param xml
	 *            contains information to fetch product and retailer details.
	 * @return String XML with hot deal.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	/*
	 * public String shareHotdealInfo(Integer hotdealId, Integer hubCitiId)
	 * throws HubCitiException {
	 * LOG.info("Inside RateReviewServiceImpl : shareHotdealInfo");
	 * 
	 * String response = null; List<HotDealsDetails> hotDealsDetailslst = null;
	 * 
	 * if (null == hotdealId) { response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.INSUFFICENTREQUESTTEXT); } else { hotDealsDetailslst =
	 * rateReviewDao.getShareHotdealInfo(hotdealId, hubCitiId); if (null !=
	 * hotDealsDetailslst && !hotDealsDetailslst.isEmpty()) { for
	 * (HotDealsDetails hdDetails : hotDealsDetailslst) {
	 * hdDetails.setIsDateFormated(false);
	 * hdDetails.sethDStartDate(hdDetails.gethDStartDate());
	 * hdDetails.sethDEndDate(hdDetails.gethDEndDate());
	 * hdDetails.setIsDateFormated(null); }
	 * hotDealsDetailslst.get(0).setTitleText(HubCitiConstants.SHAREPRODUCTTEXT
	 * + HubCitiConstants.SHARE_PRODUCT_TEXT_FROM_HUBCITI);
	 * hotDealsDetailslst.get
	 * (0).setTitleText2(HubCitiConstants.SHAREPRODUCTMEDIATITLETEXT);
	 * hotDealsDetailslst.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
	 * hotDealsDetailslst.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
	 * response = XstreamParserHelper.produceXmlFromObject(hotDealsDetailslst);
	 * response = response.replaceAll("<list>", ""); response =
	 * response.replaceAll("</list>", ""); } else { response =
	 * Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,
	 * HubCitiConstants.NORECORDSFOUNDTEXT); } }
	 * 
	 * LOG.info("Exit RateReviewServiceImpl : shareHotdealInfo"); return
	 * response; }
	 */

	/**
	 * This ServiceImpl method for the input parameters and returns validation
	 * error if validation fails. This Method fetches coupon ,loyalty and rebate
	 * details information.
	 * 
	 * @param xml
	 *            contains information to fetch coupon details.
	 * @return String XML with coupon details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	/*
	 * public String shareCLRInfo(Integer couponId, Integer hubCitiId) throws
	 * HubCitiException {
	 * LOG.info("Inside RateReviewServiceImpl : shareCLRInfo");
	 * 
	 * String response = null; List<CouponDetails> couponDetaillst = null;
	 * 
	 * if (null == couponId) { response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.INSUFFICENTREQUESTTEXT); } else if (null != couponId) {
	 * couponDetaillst = rateReviewDao.getShareCouponInfo(couponId, hubCitiId);
	 * if (!couponDetaillst.isEmpty() && couponDetaillst != null) {
	 * couponDetaillst.get(0).setTitleText(HubCitiConstants.SHAREPRODUCTTEXT +
	 * HubCitiConstants.SHARE_PRODUCT_TEXT_FROM_HUBCITI);
	 * couponDetaillst.get(0).
	 * setTitleText2(HubCitiConstants.SHAREPRODUCTMEDIATITLETEXT);
	 * couponDetaillst.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
	 * couponDetaillst.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
	 * response = XstreamParserHelper.produceXmlFromObject(couponDetaillst);
	 * response = response.replaceAll("<list>", ""); response =
	 * response.replaceAll("</list>", ""); } else { response =
	 * Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,
	 * HubCitiConstants.NORECORDSFOUNDTEXT); } }
	 * 
	 * LOG.info("Exit RateReviewServiceImpl : shareCLRInfo"); return response; }
	 */

	/**
	 * This ServiceImpl method for the input parameters and returns validation
	 * error if validation fails. Sends email to recipients with product
	 * information.
	 * 
	 * @param xml
	 *            contains email id, product to be shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String shareProductInfo(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareProductInfo");

		String response = null;
		List<ProductDetail> productInfo = null;
		// String strUserEmail = null;
		// String toAddress = null;
		// String[] toMailArray = null;
		// String fromAddress = null;
		// String msgBody = null;
		RetailerDetail retailerDetail = null;
		String shareURL = null;
		// String smtpHost = null;
		// String smtpPort = null;

		// ArrayList<AppConfiguration> emailConf = null;
		// ArrayList<AppConfiguration> shareURLList = null;

		final String subject = HubCitiConstants.SHARE_PRODUCTINFO_SUBJECT;
		// final String delimiter = ";";
		Share share = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo shareProductInfo = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		boolean valFlag = true;
		if (null != shareProductInfo) {

			if (null == shareProductInfo.getHubCitiId() || null == shareProductInfo.getProductId() || null == shareProductInfo.getPlatform()) {
				valFlag = false;
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == shareProductInfo.getUserId()) {
				valFlag = false;
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
				/*
				 * } else if (null == shareProductInfo.getToEmail()) { valFlag =
				 * false; response =
				 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE
				 * , HubCitiConstants.INSUFFICENTREQUESTTEXT);
				 */
			} /*
			 * else if (1 == shareProductInfo.getIsFromThisLocation()) {
			 * 
			 * if (null == shareProductInfo.getRetailerId()) { valFlag = false;
			 * response =
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.NOUSERID); } else if (null ==
			 * shareProductInfo.getRetailerLocationId()) { valFlag = false;
			 * response =
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.INSUFFICENTREQUESTTEXT); }
			 * 
			 * retailerDetail =
			 * rateReviewDao.getRetailerInfoWithSalePrice(shareProductInfo); }
			 */
		}

		if (valFlag && null != shareProductInfo) {

			final int userId = shareProductInfo.getUserId();

			/*
			 * shareURLList =
			 * rateReviewDao.getAppConfig(HubCitiConstants.CONFIGURATIONTYPESHAREURL
			 * );
			 * 
			 * for (int i = 0; i < shareURLList.size(); i++) { if
			 * (shareURLList.get
			 * (i).getScreenName().equals(HubCitiConstants.EMAILSHAREURL)) {
			 * shareURL = shareURLList.get(i).getScreenContent(); } }
			 */

			productInfo = rateReviewDao.getProductInfo(shareProductInfo, retailerDetail, userId, shareURL);

			if (null != productInfo && !productInfo.isEmpty()) {

				share = new Share();

				try {

					share.setImagePath(productInfo.get(0).getImagePath());
					if (shareProductInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
						share.setSubject("<![CDATA[" + subject + "]]>");
						/*
						 * productInfo.get(0).setImagePath(HubCitiConstants.
						 * IOS_IMAGE_PATH_IDENTIFIER);
						 */
						share.setShareText(Utility.formProductInfoHTML(productInfo, retailerDetail, userId, shareURL));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}
					} else {
						share.setSubject(subject);
						share.setShareText(Utility.formProductInfoHTML_ANDROID(productInfo, retailerDetail, userId, shareURL));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}

					}
					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);

				} catch (InvalidKeyException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : InvalidKeyException : " + e.getMessage());
				} catch (NoSuchAlgorithmException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : NoSuchAlgorithmException : " + e.getMessage());
				} catch (NoSuchPaddingException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : NoSuchPaddingException : " + e.getMessage());
				} catch (InvalidAlgorithmParameterException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : InvalidAlgorithmParameterException : " + e.getMessage());
				} catch (InvalidKeySpecException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : InvalidKeySpecException : " + e.getMessage());
				} catch (IllegalBlockSizeException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : IllegalBlockSizeException : " + e.getMessage());
				} catch (BadPaddingException e) {
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : BadPaddingException : " + e.getMessage());
				}
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
			/*
			 * if (null == productInfo) { valFlag = false; response =
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.SHAREPRODUCTINFONOTEXIST); return response; }
			 */

			/*
			 * strUserEmail = rateReviewDao.getUserInfo(shareProductInfo);
			 * 
			 * if (null == strUserEmail) { valFlag = false; response =
			 * Utility.formResponseXml
			 * (HubCitiConstants.INSUFFICENTREQUESTCODE_EMAILID,
			 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return response; }
			 */

			/*
			 * toAddress = shareProductInfo.getToEmail();
			 * 
			 * try {
			 * 
			 * emailConf =
			 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG);
			 * 
			 * for (int j = 0; j < emailConf.size(); j++) { if
			 * (emailConf.get(j).
			 * getScreenName().equals(HubCitiConstants.SMTPHOST)) { smtpHost =
			 * emailConf.get(j).getScreenContent(); } if
			 * (emailConf.get(j).getScreenName
			 * ().equals(HubCitiConstants.SMTPPORT)) { smtpPort =
			 * emailConf.get(j).getScreenContent(); } }
			 * 
			 * msgBody = productInfo; fromAddress = strUserEmail;
			 * 
			 * toMailArray = toAddress.split(delimiter);
			 * 
			 * if (null != smtpHost || null != smtpPort) {
			 * EmailComponent.multipleUsersmailingComponent(fromAddress,
			 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
			 * 
			 * response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
			 * HubCitiConstants.SHAREPRODUCTINFO);
			 * 
			 * } catch (MessagingException exception) { LOG.error(
			 * "Inside RateReviewServiceImpl : shareProductInfo : MessagingException : "
			 * + exception); response =
			 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
			 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
			 */

		} else {
			if (shareProductInfo == null || response == null) {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareProductInfo");
		return response;
	}

	/**
	 * This ServiceImpl method for fetches coupon information via Email.
	 * 
	 * @param xml
	 *            contains information to fetch coupon details.
	 * @return String XML with coupon Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	/*
	 * public String shareCLRbyEmail(String xml) throws HubCitiException {
	 * LOG.info("Inside RateReviewServiceImpl : shareCLRbyEmail");
	 * 
	 * String response = null; String productInfo = null; String strUserEmail =
	 * null; String toAddress = null; String[] toMailArray = null; String
	 * fromAddress = null; String subject = null; String msgBody = null; String
	 * smtpHost = null; String smtpPort = null;
	 * 
	 * ArrayList<AppConfiguration> emailConf = null;
	 * 
	 * final XstreamParserHelper streamHelper = new XstreamParserHelper(); final
	 * ShareProductInfo shareProductInfoObj = (ShareProductInfo)
	 * streamHelper.parseXmlToObject(xml);
	 * 
	 * if (null != shareProductInfoObj) { if (null ==
	 * shareProductInfoObj.getUserId()) {
	 * LOG.info("Validation failed User Id is not available"); response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.NOUSERID); } else if (null ==
	 * shareProductInfoObj.getCouponId()) {
	 * LOG.info("Validation failed Product Id is not available"); response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.INSUFFICENTREQUESTTEXT); } else if (null ==
	 * shareProductInfoObj.getToEmail()) {
	 * LOG.info("Validation failed To Email is not available"); response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.INSUFFICENTREQUESTTEXT); }
	 * 
	 * if (null != shareProductInfoObj.getCouponId()) { productInfo =
	 * rateReviewDao.getCouponShareThruEmail(shareProductInfoObj); subject =
	 * HubCitiConstants.SHARECOUPONBYEMAILSUBJECT; } if (null == productInfo) {
	 * LOG.info("CLR details is not available"); response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.NORECORDSFOUNDTEXT); return response; } strUserEmail =
	 * rateReviewDao.getUserInfo(shareProductInfoObj); if (null == strUserEmail)
	 * { LOG.info("Validation failed To Email is not available"); response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE_EMAILID,
	 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return response; }
	 * toAddress = shareProductInfoObj.getToEmail(); try { emailConf =
	 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG); for (int j = 0;
	 * j < emailConf.size(); j++) { if
	 * (emailConf.get(j).getScreenName().equals(HubCitiConstants.SMTPHOST)) {
	 * smtpHost = emailConf.get(j).getScreenContent(); } if
	 * (emailConf.get(j).getScreenName().equals(HubCitiConstants.SMTPPORT)) {
	 * smtpPort = emailConf.get(j).getScreenContent(); } }
	 * 
	 * msgBody = productInfo; fromAddress = strUserEmail; final String delimiter
	 * = ";"; toMailArray = toAddress.split(delimiter); if (null != smtpHost ||
	 * null != smtpPort) {
	 * EmailComponent.multipleUsersmailingComponent(fromAddress, toMailArray,
	 * subject, msgBody, smtpHost, smtpPort); } LOG.info("Mail delivered to:" +
	 * toAddress); response =
	 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
	 * HubCitiConstants.SHAREPRODUCTINFO); } catch (MessagingException
	 * exception) { LOG.error(
	 * "Inside RateReviewServiceImpl : shareCLRbyEmail : MessagingException : "
	 * + exception); response =
	 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
	 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); } } else { if (null ==
	 * shareProductInfoObj) { response =
	 * Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE,
	 * HubCitiConstants.TECHNICALPROBLEMTEXT); } }
	 * 
	 * LOG.info("Exit RateReviewServiceImpl : shareCLRbyEmail"); return
	 * response; }
	 */

	/**
	 * This ServiceImpl method shares app site information via email. It also
	 * parse input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains recipient email ID, share type, user ID, retailer ID
	 *            and retailer location ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareAppSiteByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareAppSiteByEmail");

		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objAppShareInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		List<RetailersDetails> appsiteDetails = null;
		/*
		 * String strUserEmail = null; String toMail = null; String[]
		 * toMailArray = null; String msgBody = null; String smtpHost = null;
		 * String smtpPort = null;
		 */

		// final String subject = HubCitiConstants.SHARE_APPSITE_SUBJECT;
		Share share = null;

		/*
		 * final String delimiter = ";";
		 * 
		 * ArrayList<AppConfiguration> emailConf = null;
		 */

		if (null == objAppShareInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
		} /*
		 * else if (null == objAppShareInfo.getToEmail() ) { response =
		 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
		 * HubCitiConstants.NOEMAILID); }
		 */else if (null == objAppShareInfo.getRetailerId() || null == objAppShareInfo.getRetailerLocationId() || null == objAppShareInfo.getHubCitiId()
				|| null == objAppShareInfo.getPlatform()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			/*
			 * strUserEmail = rateReviewDao.getUserInfo(objAppShareInfo);
			 * 
			 * if ("".equals(Utility.checkNull(strUserEmail))) { response =
			 * Utility
			 * .formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE_EMAILID,
			 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return response; }
			 */

			appsiteDetails = rateReviewDao.shareAppSiteByEmail(objAppShareInfo);

			if (null != appsiteDetails && !appsiteDetails.isEmpty()) {

				try {

					share = new Share();
					share.setImagePath(appsiteDetails.get(0).getRetImage());

					if (null != objAppShareInfo.getPlatform() && objAppShareInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
						/*
						 * appsiteDetails.get(0).setRetImage(HubCitiConstants.
						 * IOS_IMAGE_PATH_IDENTIFIER);
						 */
						share.setSubject("<![CDATA[" + HubCitiConstants.SHARE_APPSITE_SUBJECT + "]]>");
						share.setShareText(Utility.formShareAppInfoHTML(appsiteDetails));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}
					} else {
						share.setSubject(HubCitiConstants.SHARE_APPSITE_SUBJECT);
						share.setShareText(Utility.formShareAppInfoHTML(appsiteDetails));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}

					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);

				} catch (InvalidKeyException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : InvalidKeyException : " + e.getMessage());
				} catch (NoSuchAlgorithmException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : NoSuchAlgorithmException : " + e.getMessage());
				} catch (NoSuchPaddingException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : NoSuchPaddingException : " + e.getMessage());
				} catch (InvalidAlgorithmParameterException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
				} catch (InvalidKeySpecException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : InvalidKeySpecException : " + e.getMessage());
				} catch (IllegalBlockSizeException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : IllegalBlockSizeException : " + e.getMessage());
				} catch (BadPaddingException e) {
					LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : BadPaddingException : " + e.getMessage());
				}

			}
			/*
			 * if ( null == msgBody) { return response =
			 * Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,
			 * HubCitiConstants.NORECORDSFOUNDTEXT); }
			 * 
			 * toMail = objAppShareInfo.getToEmail(); toMailArray =
			 * toMail.split(delimiter);
			 * 
			 * try { emailConf =
			 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG); for
			 * (int j = 0; j < emailConf.size(); j++) { if
			 * (emailConf.get(j).getScreenName
			 * ().equals(HubCitiConstants.SMTPHOST)) { smtpHost =
			 * emailConf.get(j).getScreenContent(); } if
			 * (emailConf.get(j).getScreenName
			 * ().equals(HubCitiConstants.SMTPPORT)) { smtpPort =
			 * emailConf.get(j).getScreenContent(); } }
			 * 
			 * if (null != smtpHost || null != smtpPort) {
			 * EmailComponent.multipleUsersmailingComponent(strUserEmail,
			 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
			 * 
			 * response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
			 * HubCitiConstants.SHAREPRODUCTINFO); } catch (MessagingException
			 * exception) { LOG.error(
			 * "Inside RateReviewServiceImpl : shareAppSiteByEmail : MessagingException : "
			 * + exception); response =
			 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
			 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
			 */
		}
		if (null == response) {

		}
		LOG.info("Exit RateReviewServiceImpl : shareAppSiteByEmail");
		return response;
	}

	/**
	 * This is a RestEasy WebService Method used when the user clicks Share,
	 * they will be prompted to share via different options
	 * Text/Twitter/Facebook will send wording, retailer name, and link to the
	 * AppSite information.
	 * 
	 * @param xml
	 *            contains shareType, userId, retailerId, retailerLocationId to
	 *            be shared.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareAppsite(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareAppsite");

		String response = null;
		List<RetailersDetails> arRetailerList = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo objRetailerDetail = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null != objRetailerDetail) {

			if (null == objRetailerDetail.getUserId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
			} else if (null == objRetailerDetail.getRetailerId() || null == objRetailerDetail.getRetailerLocationId() || null == objRetailerDetail.getHubCitiId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				arRetailerList = rateReviewDao.getShareAppsite(objRetailerDetail);

				if (!arRetailerList.isEmpty() && arRetailerList != null) {

					if (null != arRetailerList.get(0).getQrUrl() && !arRetailerList.get(0).getQrUrl().equals("N/A")) {
						arRetailerList.get(0).setQrUrl(Utility.getTinyURL(arRetailerList.get(0).getQrUrl()));
					}

					response = XstreamParserHelper.produceXmlFromObject(arRetailerList);
					response = response.replaceAll("<list>", "");
					response = response.replaceAll("</list>", "");
				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

		} else {
			if (null == objRetailerDetail) {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareAppsite");
		return response;
	}

	/**
	 * This ServiceImpl method used to share app download link
	 * 
	 * @param xml
	 *            .
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareAppDownloadLink(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareAppDownloadLink");

		String response = null;
		UserDetails objUserDetails = null;
		Share share = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo objShareDetails = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null != objShareDetails) {
			if (null == objShareDetails.getUserId() || null == objShareDetails.getShareType() || null == objShareDetails.getHubCitiId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				objUserDetails = rateReviewDao.shareAppDownloadLink(objShareDetails);
				if (objUserDetails != null) {
					if (!"email".equalsIgnoreCase(objShareDetails.getShareType())) {
						objUserDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objUserDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
						response = XstreamParserHelper.produceXmlFromObject(objUserDetails);
					} else if (null != objShareDetails.getPlatform()) {
						/*
						 * String fromAddress = objUserDetails.getUserName(); //
						 * String toAddress = objShareDetails.getToEmail();
						 * String[] toMailArray = null; if (null !=
						 * objShareDetails.getToEmail()) { toMailArray =
						 * objShareDetails.getToEmail().split(";"); }
						 */

						/*
						 * String subject =
						 * HubCitiConstants.SHAREAPPLINK_SUBJECT;
						 */
						/*
						 * String msgBody = null; String smtpHost = null; String
						 * smtpPort = null; ArrayList<AppConfiguration>
						 * emailConf = null;
						 * 
						 * if (null == fromAddress) {
						 * LOG.info("Validation failed To Email is not available"
						 * ); response =
						 * Utility.formResponseXml(HubCitiConstants
						 * .INSUFFICENTREQUESTCODE_EMAILID,
						 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return
						 * response; }
						 */

						/*
						 * try { emailConf =
						 * rateReviewDao.getAppConfig(HubCitiConstants
						 * .EMAILCONFIG); for (int j = 0; j < emailConf.size();
						 * j++) { if
						 * (emailConf.get(j).getScreenName().equals(HubCitiConstants
						 * .SMTPHOST)) { smtpHost =
						 * emailConf.get(j).getScreenContent(); } if
						 * (emailConf.get
						 * (j).getScreenName().equals(HubCitiConstants
						 * .SMTPPORT)) { smtpPort =
						 * emailConf.get(j).getScreenContent(); } } msgBody =
						 * Utility.formShareDownLoadLinkHTML(objUserDetails); if
						 * (null != smtpHost || null != smtpPort) {
						 * EmailComponent
						 * .multipleUsersmailingComponent(fromAddress,
						 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
						 * response =
						 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
						 * HubCitiConstants.SHAREPRODUCTINFO); } catch
						 * (MessagingException exception) { LOG.error(
						 * "Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : "
						 * + exception); response =
						 * Utility.formResponseXml(HubCitiConstants
						 * .INVALIDEMAILADDRESS,
						 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
						 */
						try {
							share = new Share();

							if (null != objUserDetails.getiTunesImg() || objUserDetails.getiTunesImg().trim().length() != 0) {
								share.setiTunesImg(objUserDetails.getiTunesImg());
							} else {
								share.setiTunesImg(HubCitiConstants.IMAGENOTFOUND);
							}
							if (null != objUserDetails.getAndroidImg() || objUserDetails.getAndroidImg().trim().length() != 0) {
								share.setAndroidImg(objUserDetails.getAndroidImg());
							} else {
								share.setAndroidImg(HubCitiConstants.IMAGENOTFOUND);
							}
							if (objShareDetails.getPlatform().equalsIgnoreCase("IOS")) {
								share.setSubject("<![CDATA[" + HubCitiConstants.SHAREAPPLINK_SUBJECT + "]]>");
								share.setShareText(Utility.formShareDownLoadLinkHTML(objUserDetails));
								if (null != share.getShareText()) {
									share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
								}
								/*
								 * objUserDetails.setiTunesImg(HubCitiConstants.
								 * IOS_IMAGE_PATH_IDENTIFIER);
								 * objUserDetails.setAndroidImg
								 * (HubCitiConstants.IOS_IMAGE_PATH_IDENTIFIER);
								 */} else {
								share.setSubject(HubCitiConstants.SHAREAPPLINK_SUBJECT);
								share.setShareText(Utility.formShareDownLoadLinkHTML_WithoutImTag(objUserDetails));
								if (null != share.getShareText()) {
									share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
								}

							}
							share.setResponseCode(HubCitiConstants.SUCCESSCODE);
							share.setResponseText(HubCitiConstants.SUCCESSTEXT);
							response = XstreamParserHelper.produceXmlFromObject(share);
						} catch (InvalidKeyException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						} catch (NoSuchAlgorithmException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						} catch (NoSuchPaddingException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						} catch (InvalidAlgorithmParameterException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						} catch (InvalidKeySpecException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						} catch (IllegalBlockSizeException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						} catch (BadPaddingException e) {
							LOG.error("Inside RateReviewServiceImpl : shareAppDownloadLink : MessagingException : " + e);
						}
					} else {
						response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
					}
				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} else {
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit RateReviewServiceImpl : shareAppDownloadLink");
		return response;
	}

	/**
	 * This ServiceImpl method used to share retailer special offer information
	 * via Email.Method
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareSpecialOffByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareSpecialOffByEmail");

		String response = null;
		List<RetailersDetails> productInfo = null;
		Share share = null;
		/*
		 * String strUserEmail = null; String toAddress = null; String[]
		 * toMailArray = null; String fromAddress = null; String subject = null;
		 * String msgBody = null; String smtpHost = null; String smtpPort =
		 * null;
		 * 
		 * ArrayList<AppConfiguration> emailConf = null;
		 */
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo retailerDetailObj = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null != retailerDetailObj) {

			if (null == retailerDetailObj.getUserId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
				return response;
			} else if (null == retailerDetailObj.getPlatform() || null == retailerDetailObj.getPageId() || null == retailerDetailObj.getHubCitiId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return response;
			}

			productInfo = rateReviewDao.shareSpecialOffByEmail(retailerDetailObj);

			if (null != productInfo && !productInfo.isEmpty()) {

				try {

					share = new Share();
					share.setImagePath(productInfo.get(0).getRetImage());

					if (null != retailerDetailObj.getPlatform() && retailerDetailObj.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
						/*
						 * productInfo.get(0).setRetImage(HubCitiConstants.
						 * IOS_IMAGE_PATH_IDENTIFIER);
						 */
						share.setSubject("<![CDATA[" + HubCitiConstants.SHARESPECIALOFFBYEMAILSUBJECT + "]]>");
						share.setShareText(Utility.formSpecailOffInfoHtml(productInfo));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}
					} else {
						share.setSubject(HubCitiConstants.SHARESPECIALOFFBYEMAILSUBJECT);
						share.setShareText(Utility.formSpecailOffInfoHtml(productInfo));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}
					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);

				} catch (InvalidKeyException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : InvalidKeyException : " + e.getMessage());
				} catch (NoSuchAlgorithmException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : NoSuchAlgorithmException : " + e.getMessage());
				} catch (NoSuchPaddingException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : NoSuchPaddingException : " + e.getMessage());
				} catch (InvalidAlgorithmParameterException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
				} catch (InvalidKeySpecException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : InvalidKeySpecException : " + e.getMessage());
				} catch (IllegalBlockSizeException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : IllegalBlockSizeException : " + e.getMessage());
				} catch (BadPaddingException e) {
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : BadPaddingException : " + e.getMessage());
				}

			} else {
				/* if (null == productInfo) { */
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				return response;
			}

			/*
			 * strUserEmail = rateReviewDao.getUserInfo(retailerDetailObj);
			 * 
			 * if (null == strUserEmail) { response =
			 * Utility.formResponseXml(HubCitiConstants
			 * .INSUFFICENTREQUESTCODE_EMAILID,
			 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return response; }
			 * 
			 * toAddress = retailerDetailObj.getToEmail();
			 * 
			 * try {
			 * 
			 * emailConf =
			 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG);
			 * 
			 * for (int j = 0; j < emailConf.size(); j++) { if
			 * (emailConf.get(j).
			 * getScreenName().equals(HubCitiConstants.SMTPHOST)) { smtpHost =
			 * emailConf.get(j).getScreenContent(); }
			 * 
			 * if
			 * (emailConf.get(j).getScreenName().equals(HubCitiConstants.SMTPPORT
			 * )) { smtpPort = emailConf.get(j).getScreenContent(); }
			 * 
			 * }
			 * 
			 * subject = HubCitiConstants.SHARESPECIALOFFBYEMAILSUBJECT;
			 * 
			 * msgBody = productInfo; fromAddress = strUserEmail; final String
			 * delimiter = ";"; toMailArray = toAddress.split(delimiter);
			 * 
			 * if (null != smtpHost || null != smtpPort) {
			 * EmailComponent.multipleUsersmailingComponent(fromAddress,
			 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
			 * 
			 * response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
			 * HubCitiConstants.SHAREPRODUCTINFO);
			 * 
			 * } catch (MessagingException exception) { LOG.error(
			 * "Inside RateReviewServiceImpl : shareSpecialOffByEmail : MessagingException : "
			 * + exception); response =
			 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
			 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
			 */

		} else {
			if (null == retailerDetailObj || null == response) {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareSpecialOffByEmail");
		return response;
	}

	/**
	 * This ServiceImpl method used to share retailer special offer information
	 * via Facebook, Text, Twitter.
	 * 
	 * @param xml
	 * 
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareSpecialOffer(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareSpecialOffer");

		String response = null;
		RetailersDetails objRetDetails = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo retailerDetailObj = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null == retailerDetailObj.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
			return response;
		} else if (null == retailerDetailObj.getPageId() || null == retailerDetailObj.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}

		objRetDetails = rateReviewDao.shareSpecialOffer(retailerDetailObj);

		if (null != objRetDetails) {

			if (null != objRetDetails.getQrUrl() && !objRetDetails.getQrUrl().equals("N/A")) {
				objRetDetails.setQrUrl(Utility.getTinyURL(objRetDetails.getQrUrl()));
			}

			objRetDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
			objRetDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			response = XstreamParserHelper.produceXmlFromObject(objRetDetails);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
		}

		LOG.info("Exit RateReviewServiceImpl : shareSpecialOffer");
		return response;
	}

	/**
	 * This ServiceImpl method shares event information via email. It also parse
	 * input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID and hubciti ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public String shareEventByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareEventByEmail");

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		Share share = null;

		String response = null;
		EventDetails eventDetails = null;
		/*
		 * String strUserEmail = null; String toMail = null; String[]
		 * toMailArray = null; String subject = null; String msgBody = null;
		 * String smtpHost = null; String smtpPort = null; String shortUrl =
		 * null; ArrayList<AppConfiguration> emailConf = null;
		 */

		if (objShareProductInfo.getUserId() == null || null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objShareProductInfo.getHubCitiId() == null || null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (objShareProductInfo.getEventId() == null || null == objShareProductInfo.getEventId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.EVENTIDREQUIRED);
		} else if (null == objShareProductInfo.getPlatform()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_REQUIREDTEXT);
		} else {
			try {
				/*
				 * strUserEmail =
				 * rateReviewDao.getUserInfo(objShareProductInfo); if
				 * ("".equals(Utility.checkNull(strUserEmail))) {
				 * LOG.info("Validation failed To Email is not available");
				 * response = Utility.formResponseXml(HubCitiConstants.
				 * INSUFFICENTREQUESTCODE_EMAILID,
				 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); } else {
				 */
				eventDetails = rateReviewDao.shareEventByEmail(objShareProductInfo);
				if (null != eventDetails) {

					if (null != eventDetails.getQrUrl()) {
						eventDetails.setQrUrl(Utility.getTinyURL(eventDetails.getQrUrl()));
					}
					share = new Share();

					if (null == eventDetails.getImgPath() || eventDetails.getImgPath().trim().length() == 0) {
						share.setImagePath(HubCitiConstants.IMAGENOTFOUND);
					} else {
						share.setImagePath(eventDetails.getImgPath());
					}
					if (null != objShareProductInfo.getPlatform() && objShareProductInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
						/*
						 * eventDetails.setImgPath(HubCitiConstants.
						 * IOS_IMAGE_PATH_IDENTIFIER);
						 */
						share.setSubject("<![CDATA[" + HubCitiConstants.SHARE_EVENT_SUBJECT + "]]>");
						share.setShareText(Utility.formShareEventInfoHTML(eventDetails));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}

					} else {
						share.setSubject(HubCitiConstants.SHARE_EVENT_SUBJECT);
						share.setShareText(Utility.formShareEventInfoHTML_ANDROID(eventDetails));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}
					/*
					 * eventDetails.setQrUrl(Utility.getTinyURL(eventDetails.
					 * getQrUrl()));
					 */

					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);
					if (response == null) {
						return response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
					}
				}

				/*
				 * toMail = objShareProductInfo.getToEmail(); toMailArray =
				 * toMail.split(";");
				 * 
				 * emailConf =
				 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG); for
				 * (int j = 0; j < emailConf.size(); j++) { if
				 * (emailConf.get(j).
				 * getScreenName().equals(HubCitiConstants.SMTPHOST)) { smtpHost
				 * = emailConf.get(j).getScreenContent(); } if
				 * (emailConf.get(j).
				 * getScreenName().equals(HubCitiConstants.SMTPPORT)) { smtpPort
				 * = emailConf.get(j).getScreenContent(); } } subject =
				 * HubCitiConstants.SHARE_EVENT_SUBJECT;
				 * 
				 * if (null != smtpHost || null != smtpPort) {
				 * EmailComponent.multipleUsersmailingComponent(strUserEmail,
				 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
				 * LOG.info("Mail delivered to:" + toMailArray); response =
				 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
				 * HubCitiConstants.SHAREPRODUCTINFO);
				 */
				/* } */
			} /*
			 * catch (MessagingException exception) { LOG.error(
			 * "Inside RateReviewServiceImpl : shareEventByEmail : MessagingException : "
			 * + exception); response =
			 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
			 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
			 */catch (InvalidKeyException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : InvalidKeyException : " + e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : NoSuchAlgorithmException : " + e.getMessage());
			} catch (NoSuchPaddingException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : NoSuchPaddingException : " + e.getMessage());
			} catch (InvalidAlgorithmParameterException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
			} catch (InvalidKeySpecException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : InvalidKeySpecException : " + e.getMessage());
			} catch (IllegalBlockSizeException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : IllegalBlockSizeException : " + e.getMessage());
			} catch (BadPaddingException e) {
				LOG.error("Inside RateReviewServiceImpl : shareEventByEmail : BadPaddingException : " + e.getMessage());
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareEventByEmail");
		return response;
	}

	/**
	 * This ServiceImpl method shares event information via facebook, twitter
	 * and sms. It also parse input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID and hubciti ID.
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * @throws HubCitiException
	 */
	public String shareEvent(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareEvent");

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		String response = null;
		EventDetails eventDetails = null;
		// EventDetails responseDetails = null;
		// String shortUrl = null;

		if (objShareProductInfo.getUserId() == null || null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objShareProductInfo.getHubCitiId() == null || null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (objShareProductInfo.getEventId() == null || null == objShareProductInfo.getEventId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.EVENTIDREQUIRED);
		} else {
			eventDetails = rateReviewDao.shareEventByEmail(objShareProductInfo);

			if (null != eventDetails) {

				if (null != eventDetails.getQrUrl() && !eventDetails.getQrUrl().equals("N/A")) {
					eventDetails.setQrUrl(Utility.getTinyURL(eventDetails.getQrUrl()));
				}

				eventDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				eventDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				eventDetails.setImagePath(eventDetails.getImgPath());
				response = XstreamParserHelper.produceXmlFromObject(eventDetails);

			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareEvent");
		return response;
	}

	/**
	 * This ServiceImpl method shares fundraiser information via email. It also
	 * parse input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID and hubciti ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public String shareFundraiserByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareFundraiserByEmail");

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		Share share = null;
		String response = null;
		Fundraiser fundraiser = null;
		/* String strUserEmail = null; */
		/*
		 * String toMail = null; String[] toMailArray = null; String subject =
		 * null; String msgBody = null; String smtpHost = null; String smtpPort
		 * = null; String shortUrl = null; ArrayList<AppConfiguration> emailConf
		 * = null;
		 */

		if (objShareProductInfo.getUserId() == null || null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objShareProductInfo.getHubCitiId() == null || null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (objShareProductInfo.getFundraiserId() == null || null == objShareProductInfo.getFundraiserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.FUNDRAISERIDREQUIRED);
		} else if (null == objShareProductInfo.getPlatform()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_REQUIREDTEXT);
		} else {
			try {
				/*
				 * strUserEmail =
				 * rateReviewDao.getUserInfo(objShareProductInfo); if
				 * ("".equals(Utility.checkNull(strUserEmail))) {
				 * LOG.info("Validation failed To Email is not available");
				 * response = Utility.formResponseXml(HubCitiConstants.
				 * INSUFFICENTREQUESTCODE_EMAILID,
				 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); } else {
				 */
				fundraiser = rateReviewDao.shareFundraiserByEmail(objShareProductInfo);
				if (null != fundraiser) {

					fundraiser.setQrUrl(Utility.getTinyURL(fundraiser.getQrUrl()));
					// msgBody =
					// Utility.formShareFundraiserInfoHTML(fundraiser);

					share = new Share();
					if (null == fundraiser.getImgPath() || fundraiser.getImgPath().trim().length() == 0) {
						share.setImagePath(HubCitiConstants.IMAGENOTFOUND);
					} else {
						share.setImagePath(fundraiser.getImgPath());
					}
					if (null != objShareProductInfo.getPlatform() && objShareProductInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {

						/*
						 * fundraiser.setImgPath(HubCitiConstants.
						 * IOS_IMAGE_PATH_IDENTIFIER);
						 */
						share.setSubject("<![CDATA[" + HubCitiConstants.SHARE_FUNDRAISER_SUBJECT + "]]>");
						share.setShareText(Utility.formShareFundraiserInfoHTML(fundraiser));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}
					} else {
						share.setSubject(HubCitiConstants.SHARE_FUNDRAISER_SUBJECT);
						share.setShareText(Utility.formShareFundraiserInfoHTML_ANDROID(fundraiser));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}
					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);
				} else {
					return response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
				/*
				 * toMail = objShareProductInfo.getToEmail(); toMailArray =
				 * toMail.split(";");
				 * 
				 * emailConf =
				 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG); for
				 * (int j = 0; j < emailConf.size(); j++) { if
				 * (emailConf.get(j).
				 * getScreenName().equals(HubCitiConstants.SMTPHOST)) { smtpHost
				 * = emailConf.get(j).getScreenContent(); } if
				 * (emailConf.get(j).
				 * getScreenName().equals(HubCitiConstants.SMTPPORT)) { smtpPort
				 * = emailConf.get(j).getScreenContent(); } } subject =
				 * HubCitiConstants.SHARE_FUNDRAISER_SUBJECT;
				 * 
				 * if (null != smtpHost || null != smtpPort) {
				 * EmailComponent.multipleUsersmailingComponent(strUserEmail,
				 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
				 * LOG.info("Mail delivered to:" + toMailArray); response =
				 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
				 * HubCitiConstants.SHAREPRODUCTINFO);
				 */
				/* } */
			} /*
			 * catch (MessagingException exception) { LOG.error(
			 * "Inside RateReviewServiceImpl : shareFundraiserByEmail : MessagingException : "
			 * + exception); response =
			 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
			 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
			 */catch (InvalidKeyException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : InvalidKeyException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} catch (NoSuchAlgorithmException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : NoSuchAlgorithmException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} catch (NoSuchPaddingException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : NoSuchPaddingException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} catch (InvalidAlgorithmParameterException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} catch (InvalidKeySpecException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : InvalidKeySpecException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} catch (IllegalBlockSizeException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : IllegalBlockSizeException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} catch (BadPaddingException e) {
				LOG.error("Inside RateReviewServiceImpl : shareFundraiserByEmail : BadPaddingException : " + e.getMessage());
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareFundraiserByEmail");
		return response;
	}

	/**
	 * This ServiceImpl method shares fundraiser information via facebook,
	 * twitter and sms. It also parse input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID and hubciti ID.
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * @throws HubCitiException
	 */
	public String shareFundraiser(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareFundraiser");

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		String response = null;
		Fundraiser fundraiser = null;
		// Fundraiser responseFundraiser = null;
		// String shortUrl = null;*/

		if (objShareProductInfo.getUserId() == null || null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objShareProductInfo.getHubCitiId() == null || null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (objShareProductInfo.getFundraiserId() == null || null == objShareProductInfo.getFundraiserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.FUNDRAISERIDREQUIRED);
		} else {
			fundraiser = rateReviewDao.shareFundraiserByEmail(objShareProductInfo);

			if (null != fundraiser) {

				if (null != fundraiser.getQrUrl() && !fundraiser.getQrUrl().equals("N/A")) {
					fundraiser.setQrUrl(Utility.getTinyURL(fundraiser.getQrUrl()));
				}

				fundraiser.setResponseCode(HubCitiConstants.SUCCESSCODE);
				fundraiser.setResponseText(HubCitiConstants.SUCCESSTEXT);
				fundraiser.setImagePath(fundraiser.getImgPath());
				response = XstreamParserHelper.produceXmlFromObject(fundraiser);

			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		}
		LOG.info("Exit RateReviewServiceImpl : shareFundraiser");
		return response;
	}

	/**
	 * This ServiceImpl method for hot deal information via Email.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareHotdealByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareHotdealbyEmail");

		String response = null;
		// String productInfo = null;
		List<HotDealsDetails> productInfo = null;
		/*
		 * String strUserEmail = null; String toAddress = null; String[]
		 * toMailArray = null; String fromAddress = null; String subject = null;
		 * String msgBody = null; String smtpHost = null; String smtpPort =
		 * null;
		 * 
		 * ArrayList<AppConfiguration> arEmailConfList = null;
		 */

		Share share = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null != objShareProductInfo) {

			if (null == objShareProductInfo.getUserId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
			} else if (null == objShareProductInfo.getHubCitiId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
			} else /*
					 * if (null == objShareProductInfo.getToEmail() || null ==
					 * objShareProductInfo.getHotdealId()) {
					 */
			if (null == objShareProductInfo.getHotdealId() || null == objShareProductInfo.getPlatform()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				productInfo = rateReviewDao.shareHotdealByEmail(objShareProductInfo);

				if (null != productInfo && !productInfo.isEmpty()) {

					try {
						share = new Share();
						share.setImagePath(productInfo.get(0).getHotDealImagePath());
						if (null != objShareProductInfo.getPlatform() && objShareProductInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
							/*
							 * productInfo.get(0).setHotDealImagePath(
							 * HubCitiConstants.IOS_IMAGE_PATH_IDENTIFIER);
							 */
							share.setSubject("<![CDATA[" + HubCitiConstants.SHAREHOTDEALBYEMAILSUBJECT + "]]>");
							share.setShareText(Utility.formHotdealInfoHTML(productInfo));
							if (null != share.getShareText()) {

								share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
							}

						} else {
							share.setSubject(HubCitiConstants.SHAREHOTDEALBYEMAILSUBJECT);
							share.setShareText(Utility.formHotdealInfoHTML_ANDROID(productInfo));
							if (null != share.getShareText()) {
								share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
								/*
								 * share.setShareText(share.getShareText().replace
								 * ("\"", "'"));
								 */
							}
						}
						share.setResponseCode(HubCitiConstants.SUCCESSCODE);
						share.setResponseText(HubCitiConstants.SUCCESSTEXT);
						response = XstreamParserHelper.produceXmlFromObject(share);
					} catch (InvalidKeyException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : InvalidKeyException : " + e.getMessage());
					} catch (NoSuchAlgorithmException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : NoSuchAlgorithmException : " + e.getMessage());
					} catch (NoSuchPaddingException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : NoSuchPaddingException : " + e.getMessage());
					} catch (InvalidAlgorithmParameterException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
					} catch (InvalidKeySpecException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : InvalidKeySpecException : " + e.getMessage());
					} catch (IllegalBlockSizeException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : IllegalBlockSizeException : " + e.getMessage());
					} catch (BadPaddingException e) {
						LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : BadPaddingException : " + e.getMessage());
					}

				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}

				/*
				 * if (null == productInfo) { response =
				 * Utility.formResponseXml(
				 * HubCitiConstants.INSUFFICENTREQUESTCODE,
				 * HubCitiConstants.SHAREPRODUCTINFONOTEXIST); return response;
				 * }
				 */

				/*
				 * strUserEmail =
				 * rateReviewDao.getUserInfo(objShareProductInfo);
				 * 
				 * if (null == strUserEmail) { response =
				 * Utility.formResponseXml
				 * (HubCitiConstants.INSUFFICENTREQUESTCODE_EMAILID,
				 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return
				 * response; }
				 * 
				 * toAddress = objShareProductInfo.getToEmail();
				 * 
				 * try { arEmailConfList =
				 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG); for
				 * (int j = 0; j < arEmailConfList.size(); j++) { if
				 * (arEmailConfList
				 * .get(j).getScreenName().equals(HubCitiConstants.SMTPHOST)) {
				 * smtpHost = arEmailConfList.get(j).getScreenContent(); } if
				 * (arEmailConfList
				 * .get(j).getScreenName().equals(HubCitiConstants.SMTPPORT)) {
				 * smtpPort = arEmailConfList.get(j).getScreenContent(); } }
				 * 
				 * subject = HubCitiConstants.SHAREHOTDEALBYEMAILSUBJECT;
				 * 
				 * msgBody = productInfo; fromAddress = strUserEmail; final
				 * String delimiter = ";"; toMailArray =
				 * toAddress.split(delimiter);
				 * 
				 * if (null != smtpHost || null != smtpPort) {
				 * EmailComponent.multipleUsersmailingComponent(fromAddress,
				 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
				 * 
				 * response =
				 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
				 * HubCitiConstants.SHAREPRODUCTINFO);
				 * 
				 * } catch (MessagingException exception) { LOG.error(
				 * "Inside RateReviewServiceImpl : shareHotdealbyEmail : MessagingException : "
				 * + exception); response =
				 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
				 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
				 */
			}
		} else {
			if (null == objShareProductInfo || null == response) {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareHotdealbyEmail");
		return response;
	}

	/**
	 * This ServiceImpl method for hot deal information information via
	 * Facebook, Text, Twitter.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareHotdeal(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareHotdeal");

		String response = null;
		HotDealsDetails hotDealsDetails = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
		} else if (null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (null == objShareProductInfo.getHotdealId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			hotDealsDetails = rateReviewDao.shareHotdeal(objShareProductInfo);

			if (null != hotDealsDetails) {

				if (null != hotDealsDetails.getQrUrl() && !hotDealsDetails.getQrUrl().equals("N/A")) {
					hotDealsDetails.setQrUrl(Utility.getTinyURL(hotDealsDetails.getQrUrl()));
				}

				hotDealsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				hotDealsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(hotDealsDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareHotdeal");
		return response;
	}

	/**
	 * This ServiceImpl method for share Coupon information via Email.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal
	 * @return String XML with coupon Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareCouponByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareCouponByEmail");

		String response = null;
		List<CouponDetails> productInfo = null;
		/*
		 * String strUserEmail = null; String toAddress = null; String[]
		 * toMailArray = null; String fromAddress = null; String msgBody = null;
		 * String smtpHost = null; String smtpPort = null;
		 * 
		 * ArrayList<AppConfiguration> arEmailConfList = null;
		 */

		Share share = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo shareProductInfo = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null != shareProductInfo) {

			if (null == shareProductInfo.getUserId() || null == shareProductInfo.getCouponId() || null == shareProductInfo.getHubCitiId() || null == shareProductInfo.getPlatform()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);

			}/*
			 * else if (null == shareProductInfo.getToEmail()) { else if (null
			 * == shareProductInfo.getCouponId()) { response =
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.INSUFFICENTREQUESTTEXT);
			 * 
			 * }
			 */else {

				productInfo = rateReviewDao.shareCouponByEmail(shareProductInfo);

				if (null != productInfo && !productInfo.isEmpty()) {

					try {

						share = new Share();
						share.setImagePath(productInfo.get(0).getCouponImagePath());
						if (null != shareProductInfo.getPlatform() && shareProductInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
							/*
							 * productInfo.get(0).setCouponImagePath(
							 * HubCitiConstants.IOS_IMAGE_PATH_IDENTIFIER);
							 */
							share.setSubject("<![CDATA[" + HubCitiConstants.SHARECOUPONBYEMAILSUBJECT + "]]>");
							share.setShareText(Utility.formCouponInfoHTML(productInfo));
							if (null != share.getShareText()) {
								share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
							}
						} else {
							share.setSubject(HubCitiConstants.SHARECOUPONBYEMAILSUBJECT);
							share.setShareText(Utility.formCouponInfoHTML_ANDROID(productInfo));

							if (null != share.getShareText()) {
								share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
							}
						}
						share.setResponseCode(HubCitiConstants.SUCCESSCODE);
						share.setResponseText(HubCitiConstants.SUCCESSTEXT);

						response = XstreamParserHelper.produceXmlFromObject(share);

					} catch (InvalidKeyException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : InvalidKeyException : " + e.getMessage());
					} catch (NoSuchAlgorithmException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : NoSuchAlgorithmException : " + e.getMessage());
					} catch (NoSuchPaddingException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : NoSuchPaddingException : " + e.getMessage());
					} catch (InvalidAlgorithmParameterException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
					} catch (InvalidKeySpecException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : InvalidKeySpecException : " + e.getMessage());
					} catch (IllegalBlockSizeException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : IllegalBlockSizeException : " + e.getMessage());
					} catch (BadPaddingException e) {
						LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : BadPaddingException : " + e.getMessage());
					}

				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
				/*
				 * if (null == productInfo) { response =
				 * Utility.formResponseXml(
				 * HubCitiConstants.INSUFFICENTREQUESTCODE,
				 * HubCitiConstants.NORECORDSFOUNDTEXT); return response; }
				 */

				/*
				 * strUserEmail = rateReviewDao.getUserInfo(shareProductInfo);
				 * 
				 * if (null == strUserEmail) { response =
				 * Utility.formResponseXml
				 * (HubCitiConstants.INSUFFICENTREQUESTCODE_EMAILID,
				 * HubCitiConstants.USEREMAILIDNOTEXISTTOSHARE); return
				 * response; }
				 * 
				 * toAddress = shareProductInfo.getToEmail();
				 * 
				 * try { arEmailConfList =
				 * rateReviewDao.getAppConfig(HubCitiConstants.EMAILCONFIG); for
				 * (int j = 0; j < arEmailConfList.size(); j++) { if
				 * (arEmailConfList
				 * .get(j).getScreenName().equals(HubCitiConstants.SMTPHOST)) {
				 * smtpHost = arEmailConfList.get(j).getScreenContent(); } if
				 * (arEmailConfList
				 * .get(j).getScreenName().equals(HubCitiConstants.SMTPPORT)) {
				 * smtpPort = arEmailConfList.get(j).getScreenContent(); } }
				 * 
				 * final String subject =
				 * HubCitiConstants.SHARECOUPONBYEMAILSUBJECT;
				 * 
				 * msgBody = productInfo; fromAddress = strUserEmail; final
				 * String delimiter = ";"; toMailArray =
				 * toAddress.split(delimiter);
				 * 
				 * if (null != smtpHost || null != smtpPort) {
				 * EmailComponent.multipleUsersmailingComponent(fromAddress,
				 * toMailArray, subject, msgBody, smtpHost, smtpPort); }
				 * 
				 * response =
				 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
				 * HubCitiConstants.SHAREPRODUCTINFO);
				 * 
				 * } catch (MessagingException exception) { LOG.error(
				 * "Inside RateReviewServiceImpl : shareHotdealbyEmail : MessagingException : "
				 * + exception); response =
				 * Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS,
				 * HubCitiConstants.INVALIDEMAILADDRESSTEXT); }
				 */
			}

		}
		if (null == shareProductInfo || null == response) {
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit RateReviewServiceImpl : shareCouponByEmail" + response);
		return response;
	}

	/**
	 * This ServiceImpl method for shareCoupon information information via
	 * Facebook, Text, Twitter.
	 * 
	 * @param xml
	 *            contains information to fetch share Coupon
	 * @return String XML with coupon Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareCoupon(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareCoupon");

		String response = null;
		CouponDetails couponDetails = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo shareProductInfo = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null == shareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
		} else if (null == shareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (null == shareProductInfo.getCouponId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			couponDetails = rateReviewDao.shareCoupon(shareProductInfo);

			if (null != couponDetails) {

				if (null != couponDetails.getQrUrl() && !couponDetails.getQrUrl().equals("N/A")) {
					couponDetails.setQrUrl(Utility.getTinyURL(couponDetails.getQrUrl()));
				}

				couponDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				couponDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(couponDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareCoupon");
		return response;
	}

	/**
	 * This ServiceImpl method for updating receiver share information details.
	 * 
	 * @param xml
	 *            contains information about the share tracking details.
	 * @return String xml containing SUCCESS or FAILURE
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateReceiverShare(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : updateReceiverShare");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ShareProductInfo shareProductInfo = (ShareProductInfo) streamHelper.parseXmlToObject(xml);

		if (null == shareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
			return response;
		} else if (null == shareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
			return response;
		} else if (null == shareProductInfo.getPageId() || null == shareProductInfo.getQrTypeCode()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}

		response = rateReviewDao.updateReceiverShare(shareProductInfo);

		if (null != response && HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(response)) {
			response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
		}

		LOG.info("Exit RateReviewServiceImpl : updateReceiverShare");
		return response;
	}

	/**
	 * This ServiceImpl method for updating sender share information details.
	 * 
	 * @param xml
	 *            as request parameter.
	 * @return SUCCESS or FAILURE string
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateSenderShare(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : updateSenderShare");

		String response = null;
		List<UserTrackingData> shareTypesList = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final UserTrackingData objUserTrackingData = (UserTrackingData) parser.parseXmlToObject(xml);

		if (null == objUserTrackingData.getMainMenuId() || null == objUserTrackingData.getShrTypNam()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			Integer shrTypID = null;
			shareTypesList = new ArrayList<UserTrackingData>();

			shareTypesList = rateReviewDao.getShareTypes();

			for (int i = 0; i < shareTypesList.size(); i++) {
				if (objUserTrackingData.getShrTypNam().equalsIgnoreCase(shareTypesList.get(i).getShrTypNam())) {
					shrTypID = shareTypesList.get(i).getShrTypId();
				}
			}

			objUserTrackingData.setShrTypId(shrTypID);

			response = rateReviewDao.updateSenderShare(objUserTrackingData);

			if (!HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(response)) {
				response = HubCitiConstants.FAILURE;
			}
		}

		LOG.info("Exit RateReviewServiceImpl : updateSenderShare");
		return response;
	}

	/**
	 * This ServiceImpl method shares event information via email. It also parse
	 * input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID and hubciti ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public String shareBandEventByEmail(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareEventByEmail");

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		Share share = null;

		String response = null;
		EventDetails eventDetails = null;

		if (objShareProductInfo.getUserId() == null || null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objShareProductInfo.getHubCitiId() == null || null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (objShareProductInfo.getEventId() == null || null == objShareProductInfo.getEventId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.EVENTIDREQUIRED);
		} else if (null == objShareProductInfo.getPlatform()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_REQUIREDTEXT);
		} else {
			try {

				eventDetails = rateReviewDao.shareBandEventByEmail(objShareProductInfo);
				if (null != eventDetails) {

					if (null != eventDetails.getQrUrl()) {
						eventDetails.setQrUrl(Utility.getTinyURL(eventDetails.getQrUrl()));
					}
					share = new Share();

					if (null == eventDetails.getImgPath() || eventDetails.getImgPath().trim().length() == 0) {
						share.setImagePath(HubCitiConstants.IMAGENOTFOUND);
					} else {
						share.setImagePath(eventDetails.getImgPath());
					}
					if (null != objShareProductInfo.getPlatform() && objShareProductInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {

						share.setSubject("<![CDATA[" + HubCitiConstants.BAND_SHARE_EVENT_SUBJECT + "]]>");
						share.setShareText(Utility.formShareEventInfoHTML(eventDetails));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}

					} else {
						share.setSubject(HubCitiConstants.BAND_SHARE_EVENT_SUBJECT);
						share.setShareText(Utility.formShareEventInfoHTML_ANDROID(eventDetails));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}

					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);
					if (response == null) {
						return response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
					}
				}

			} catch (InvalidKeyException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : InvalidKeyException : " + e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : NoSuchAlgorithmException : " + e.getMessage());
			} catch (NoSuchPaddingException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : NoSuchPaddingException : " + e.getMessage());
			} catch (InvalidAlgorithmParameterException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
			} catch (InvalidKeySpecException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : InvalidKeySpecException : " + e.getMessage());
			} catch (IllegalBlockSizeException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : IllegalBlockSizeException : " + e.getMessage());
			} catch (BadPaddingException e) {
				LOG.error("Inside RateReviewServiceImpl : shareBandEventByEmail : BadPaddingException : " + e.getMessage());
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareBandEventByEmail ");
		return response;
	}

	/**
	 * This ServiceImpl method shares event information via facebook, twitter
	 * and sms. It also parse input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID and hubciti ID.
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * @throws HubCitiException
	 */
	public String shareBandEvent(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareEvent");

		final XstreamParserHelper parser = new XstreamParserHelper();
		final ShareProductInfo objShareProductInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

		String response = null;
		EventDetails eventDetails = null;
		// EventDetails responseDetails = null;
		// String shortUrl = null;

		if (objShareProductInfo.getUserId() == null || null == objShareProductInfo.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objShareProductInfo.getHubCitiId() == null || null == objShareProductInfo.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (objShareProductInfo.getEventId() == null || null == objShareProductInfo.getEventId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.EVENTIDREQUIRED);
		} else {
			eventDetails = rateReviewDao.shareBandEventByEmail(objShareProductInfo);

			if (null != eventDetails) {

				if (null != eventDetails.getQrUrl() && !eventDetails.getQrUrl().equals("N/A")) {
					eventDetails.setQrUrl(Utility.getTinyURL(eventDetails.getQrUrl()));
				}

				eventDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				eventDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				eventDetails.setImagePath(eventDetails.getImgPath());
				response = XstreamParserHelper.produceXmlFromObject(eventDetails);

			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit RateReviewServiceImpl : shareEvent");
		return response;
	}

	/**
	 * This ServiceImpl method shares Band information via email. It also parse
	 * input xml to object using XStream parser.
	 * 
	 * @param xml
	 *            contains recipient email ID, share type, user ID, retailer ID
	 *            and retailer location ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBandByEmail(String xml) throws HubCitiException {

		LOG.info("Inside RateReviewServiceImpl : shareBandByEmail");
		String response = null;

		List<RetailersDetails> appsiteDetails = null;

		Share share = null;

		try {

			final XstreamParserHelper parser = new XstreamParserHelper();
			final ShareProductInfo objAppShareInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

			if (null == objAppShareInfo.getUserId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
			} else if (null == objAppShareInfo.getBandId() || null == objAppShareInfo.getHubCitiId() || null == objAppShareInfo.getPlatform()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				appsiteDetails = rateReviewDao.shareBandByEmail(objAppShareInfo);

				if (null != appsiteDetails && !appsiteDetails.isEmpty()) {

					share = new Share();
					
					if (null == appsiteDetails.get(0).getRetImage() || appsiteDetails.get(0).getRetImage().trim().length() == 0) {
						share.setImagePath(HubCitiConstants.IMAGENOTFOUND);
					} else {
						share.setImagePath(appsiteDetails.get(0).getRetImage());
					}
					
					if (null != objAppShareInfo.getPlatform() && objAppShareInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {

						share.setSubject("<![CDATA[" + HubCitiConstants.SHARE_BAND_SUBJECT + "]]>");
						share.setShareText(Utility.formShareAppInfoHTML(appsiteDetails));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}

					} else {

						share.setSubject(HubCitiConstants.SHARE_BAND_SUBJECT);
						share.setShareText(Utility.formShareAppInfoHTML(appsiteDetails));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}
					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);

				}
			}
		} catch (InvalidKeyException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : InvalidKeyException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : NoSuchAlgorithmException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : NoSuchPaddingException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : InvalidKeySpecException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : IllegalBlockSizeException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : BadPaddingException : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}catch (Exception e) {
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : Exception : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		
		LOG.info("Exit RateReviewServiceImpl : shareBandByEmail");
		return response;
	}
	
	/**
	 * This is a RestEasy WebService Method used when the user clicks Share,
	 * they will be prompted to share via different options
	 * Text/Twitter/Facebook will send wording, retailer name, and link to the
	 * AppSite information.
	 * 
	 * @param xml
	 *            contains shareType, userId, BandId
	 *            be shared.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBand(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareBand");

		String response = null;
		List<RetailersDetails> arRetailerList = null;

		try{
			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final ShareProductInfo objRetailerDetail = (ShareProductInfo) streamHelper.parseXmlToObject(xml);
	
			if (null != objRetailerDetail) {
	
				if (null == objRetailerDetail.getUserId()) {
					response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
				} else if (null == objRetailerDetail.getBandId() || null == objRetailerDetail.getHubCitiId()) {
					response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				} else {
	
					arRetailerList = rateReviewDao.shareBand(objRetailerDetail);
	
					if (!arRetailerList.isEmpty() && arRetailerList != null) {
	
						if (null != arRetailerList.get(0).getQrUrl() && !arRetailerList.get(0).getQrUrl().equals("N/A")) {
							arRetailerList.get(0).setQrUrl(Utility.getTinyURL(arRetailerList.get(0).getQrUrl()));
						}
	
						response = XstreamParserHelper.produceXmlFromObject(arRetailerList);
						response = response.replaceAll("<list>", "");
						response = response.replaceAll("</list>", "");
					} else {
						response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
					}
				}
	
			} else {
				if (null == objRetailerDetail) {
					response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
				}
			}
		}catch(Exception e){
			LOG.error("Inside RateReviewDaoImpl : shareBandByEmail : Exception : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		
		LOG.info("Exit RateReviewServiceImpl : shareBand");
		return response;
	}
	

	/**
	 * ServiceImpl method shares News information via email.
	 * 
	 * @param newsID
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareNewsByEmail(String xml) throws HubCitiException {

		LOG.info("Inside RateReviewServiceImpl : shareNewsByEmail");
		String response = null;

		List<Item> arNewsDetail = null;

		Share share = null;

		try {

			final XstreamParserHelper parser = new XstreamParserHelper();
			final ShareProductInfo shareNewsInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

			if (null == shareNewsInfo.getNewsId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
			} else {
				arNewsDetail = rateReviewDao.shareNewsByEmail(shareNewsInfo);
				if (null != arNewsDetail && !arNewsDetail.isEmpty()) {

					share = new Share();

					if (null == arNewsDetail.get(0).getImage() || arNewsDetail.get(0).getImage().trim().length() == 0) {
						share.setImagePath(HubCitiConstants.IMAGENOTFOUND);
					} else {
						share.setImagePath(arNewsDetail.get(0).getImage());
					}

					if (null != shareNewsInfo.getPlatform() && shareNewsInfo.getPlatform().equalsIgnoreCase(HubCitiConstants.PLATFORM_IOS)) {
						share.setSubject("<![CDATA[" + HubCitiConstants.SHARE_NEWS_SUBJECT + "]]>");
						share.setShareText(Utility.formShareAppNewsDetailHTML(arNewsDetail));
						if (null != share.getShareText()) {
							share.setShareText("<![CDATA[" + share.getShareText().replace("\"", "'") + "]]>");
						}

					} else {

						share.setSubject(HubCitiConstants.SHARE_NEWS_SUBJECT);
						share.setShareText(Utility.formShareAppNewsDetailAndriodHTML(arNewsDetail));
						if (null != share.getShareText()) {
							share.setShareText(share.getShareText().replaceAll("<", "&#60;").replaceAll(">", "&#62;"));
						}
					}
					share.setResponseCode(HubCitiConstants.SUCCESSCODE);
					share.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(share);

				}
			}
		} catch (Exception e) {
			LOG.error("Inside RateReviewDaoImpl : shareNewsByEmail : Exception : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}

		LOG.info("Exit RateReviewServiceImpl : shareNewsByEmail");
		return response;
	}

	/**
	 * ServiceImpl method shares News information,
	 * they will be prompted to share via different options
	 * Text/Twitter/Facebook will send wording.
	 * 
	 * @param newsID.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareNewsBySocailMedia(String xml) throws HubCitiException {
		LOG.info("Inside RateReviewServiceImpl : shareNewsBySocailMedia");

		String response = null;
		List<Item> arNewsList = null;

		try {

			final XstreamParserHelper parser = new XstreamParserHelper();
			final ShareProductInfo shareNewsInfo = (ShareProductInfo) parser.parseXmlToObject(xml);

			if (null == shareNewsInfo.getNewsId()) {
					response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOUSERID);
				} else {

					arNewsList = rateReviewDao.shareNewsBySocailMedia(shareNewsInfo);

					if (!arNewsList.isEmpty() && arNewsList != null) {

						if (null != arNewsList.get(0).getQrUrl() && !arNewsList.get(0).getQrUrl().equals("N/A")) {
							arNewsList.get(0).setQrUrl(Utility.getTinyURL(arNewsList.get(0).getQrUrl()));
						}

						response = XstreamParserHelper.produceXmlFromObject(arNewsList);
						response = response.replaceAll("<list>", "");
						response = response.replaceAll("</list>", "").trim();
					} else {
						response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
					}
				}
		} catch(Exception e) {
			LOG.error("Inside RateReviewDaoImpl : shareNewsBySocailMedia : Exception : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		LOG.info("Exit RateReviewServiceImpl : shareNewsBySocailMedia");
		return response;
	}
}





