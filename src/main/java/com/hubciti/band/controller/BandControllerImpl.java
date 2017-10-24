package com.hubciti.band.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.band.service.BandService;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;

/**
 * Class for BandControllerImpl
 * 
 * @author Kumar D
 */
public class BandControllerImpl implements BandController {
	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BandControllerImpl.class);

	/**
	 * The FirstUseControllerImpl Constructor.
	 */
	public BandControllerImpl() {

	}

	/**
	 * This ControllerImpl method is for fetching EventType List information. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing EventType List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getAllEventTypes(String strJSON) {
		LOG.info("Inside BandControllerImpl : getAllEventTypes");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getAllEventTypes(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getAllEventTypes : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getAllEventTypes");
		return strJsonResponse;
	}



	/**
	 * This ControllerImpl method is for fetching Band List information. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing Band List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getBandList(String strJSON) {
		LOG.info("Inside BandControllerImpl : getBandList");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getBandList(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getBandList : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getBandList");
		return strJsonResponse;
	}



	/**
	 * This Service method fetches Band information.
	 * @param strJson
	 * @return String Json with Band details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getBandSummary(String strJson) {
		LOG.info("Inside BandControllerImpl : getBandSummary");

		String strResponseXML = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {
			strResponseXML = bandService.getBandSummary(strJson);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getBandSummary : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This controllerImpl method for Event List or Band Event List.
	 * 
	 * @param strJSON as request
	 * @return JSON containing Event List or Band Event array list response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getEventList(String strJSON) {
		LOG.info("Inside BandControllerImpl : getEventList ");
		String strJsonResponse = null;

		final BandService bandService = ServiceFactory.getBandService();
		try {
			strJsonResponse = bandService.getEventList(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getEventList : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getEventList ");
		return strJsonResponse;
	}


	/**
	 * This controllerImpl method for displaying News BookMark and Non BookMark catagories.
	 * 
	 * @param strJSON as request
	 * @return JSON containing BookMark and Non BookMark catagories array list response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getNewsBookMarkCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl : getNewsBookMarkCategories ");
		String strJsonResponse = null;

		final BandService bandService = ServiceFactory.getBandService();
		try {
			strJsonResponse = bandService.getNewsBookMarkCategories(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getNewsBookMarkCategories : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getNewsBookMarkCategories ");
		return strJsonResponse;
	}


	/**
	 * This controllerImpl method for Updating News BookMark and HubCiti Functionality.
	 * 
	 * @param strJSON as request
	 * @return JSON containing Success/Failure response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String updateNewsBookMarkCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl: updateNewsBookMarkCategories");

		String strJsonResponse=null;
		final BandService bandService=ServiceFactory.getBandService();
		try{
			strJsonResponse=bandService.updateNewsBookMarkCategories(strJSON);
		}catch(HubCitiException exception){
			LOG.error("Inside BandControllerImpl: updateNewsBookMarkCategories "+exception);
			strJsonResponse=Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl: updateNewsBookMarkCategories");

		return strJsonResponse;
	}



	/**
	 * This controller method for get News detail from news ID.
	 * 
	 * @param newsID as request
	 * @return JSON containing  News detail information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getNewsDetail(Integer newsID) {
		LOG.info("Inside BandControllerImpl : getNewsDetail ");
		String strJsonResponse = null;

		final BandService bandService = ServiceFactory.getBandService();
		try {
			strJsonResponse = bandService.getNewsDetail(newsID);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getNewsDetail : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getNewsDetail ");
		return strJsonResponse;
	}



	/**
	 * This controllerImpl method for displaying both HubCiti Functionality and News categories.
	 * 
	 * @param strJSON as request
	 * @return JSON containing both HubCiti Functionality and News categories array list response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getNewsSideNavigationMenu(String strJSON) {
		LOG.info("Inside BandControllerImpl : getNewsSideNavigationMenu ");
		String strJsonResponse = null;

		final BandService bandService = ServiceFactory.getBandService();
		try {
			strJsonResponse = bandService.getNewsSideNavigationMenu(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getNewsSideNavigationMenu : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getNewsSideNavigationMenu ");
		return strJsonResponse;
	}



	/**
	 * This ControllerImpl method is for fetching News List by Category's. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing Band List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getNewsListByCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl : getNewsListByCategories");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getNewsListByCategories(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getNewsListByCategories : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getNewsListByCategories");
		return strJsonResponse;
	}

	/**
	 * This ControllerImpl method is for fetching Combinational News List by Category's information. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getCombNewsListByCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl : getCombNewsListByCategories");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getCombNewsListByCategories(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getCombNewsListByCategories : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getCombNewsListByCategories");
		return strJsonResponse;
	}


	/**
	 * This ControllerImpl method is for fetching Scrolling pattern News List by Category's information. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getScrollingNewsListByCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl : getScrollingNewsListByCategories");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getScrollingNewsListByCategories(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getScrollingNewsListByCategories : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getScrollingNewsListByCategories");
		return strJsonResponse;
	}



	/**
	 * This ControllerImpl method is for displaying all News  Category's. 
	 * 
	 * @param strJSON as Request
	 * @return Json News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getAllNewsCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl : getAllNewsCategories");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getAllNewsCategories(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getAllNewsCategories : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getAllNewsCategories");
		return strJsonResponse;
	}


	/**
	 * This controllerImpl method for displaying News BookMark and HubCiti Functionality.
	 * 
	 * @param strJSON as request
	 * @return JSON containing News BookMark and HubCiti Functionality list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getNewsBookMarkAndHubCitiFunctn(String strJSON) {
		LOG.info("Inside BandControllerImpl : getNewsBookMarkAndHubCitiFunctn ");
		String strJsonResponse = null;

		final BandService bandService = ServiceFactory.getBandService();
		try {
			strJsonResponse = bandService.getNewsBookMarkAndHubCitiFunctn(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getNewsBookMarkAndHubCitiFunctn : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getNewsBookMarkAndHubCitiFunctn ");
		return strJsonResponse;
	}



	/**
	 * This controllerImpl method for displaying All News Sub categories.
	 * 
	 * @param strJSON as request
	 * @return JSON containing All News Sub categories arrayList response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getAllNewsSubCategoriesByCatID(String strJSON) {
		LOG.info("Inside BandControllerImpl : getAllNewsSubCategoriesByCatID ");
		String strJsonResponse = null;

		final BandService bandService = ServiceFactory.getBandService();
		try {
			strJsonResponse = bandService.getAllNewsSubCategoriesByCatID(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getAllNewsSubCategoriesByCatID : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getAllNewsSubCategoriesByCatID ");
		return strJsonResponse;
	}


	/**
	 * This ControllerImpl method is for fetching Block Pattern News List by Category's information. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getBlockNewsListByCategories(String strJSON) {
		LOG.info("Inside BandControllerImpl : getBlockNewsListByCategories");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getBlockNewsListByCategories(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getBlockNewsListByCategories : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getBlockNewsListByCategories");
		return strJsonResponse;
	}


	/**
	 * This ControllerImpl method is for fetching Event Band List Information. 
	 * 
	 * @param strJSON as Request
	 * @return strJSON containing Band List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getEventBandsList(String strJSON) {
		LOG.info("Inside BandControllerImpl : getEventBandsList");

		String strJsonResponse = null;
		final BandService bandService = ServiceFactory.getBandService();
		try {

			strJsonResponse = bandService.getEventBandsList(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside BandControllerImpl : getEventBandsList : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandControllerImpl : getEventBandsList");
		return strJsonResponse;
	}
	
}
