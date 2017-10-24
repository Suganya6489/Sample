/**
 * 
 */
package com.hubciti.common.externalapi;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.find.dao.FindDao;
import com.scansee.externalapi.common.pojos.ExternalAPIInformation;
import com.scansee.externalapi.common.pojos.ExternalAPISearchParameters;
import com.scansee.externalapi.common.pojos.ExternalAPIVendor;

/**
 * This class contains methods related to external APIs.
 * 
 * @author dileepa_cc
 */
public class ExternalAPIManager {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ExternalAPIManager.class);

	/**
	 * moduleName as String.
	 */
	private String moduleName;

	/**
	 * variable as object.
	 */
	private FindDao findDao;

	/**
	 * Constructor with detail messages.
	 * 
	 * @param moduleName
	 *            -As parameter
	 * @param shoppingListDao
	 *            -As object parameter
	 */
	public ExternalAPIManager(String moduleName, FindDao findDao) {
		LOG.info("Inside The ExternalAPIManager Contstructor");
		this.moduleName = moduleName;
		this.findDao = findDao;
		LOG.info("Exiting ExternalAPIManager Contstructor");
	}

	/**
	 * Making calls to Externl API.
	 * 
	 * @return ExternalAPIInformation
	 * @throws ScanSeeException
	 *             -Throws if any Exceptions
	 */

	public ExternalAPIInformation getExternalAPIInformation() throws HubCitiException {
		// Get the Configuration and decide the API
		// Form th Request URL with query params
		// then make call to externalAPI
		// check if data Exists and if yes return if no
		// Pick next API.....
		// Get
		final String methodName = "getExternalAPIInformation";

		LOG.info(HubCitiConstants.METHODSTART + methodName);

		/**
		 * List of API vendors.
		 */
		ArrayList<ExternalAPIVendor> externalAPIList = new ArrayList<ExternalAPIVendor>();

		/**
		 * List of search parameters for each vendor.
		 */
		ArrayList<ExternalAPISearchParameters> externalAPIInputParameters = new ArrayList<ExternalAPISearchParameters>();

		/**
		 * Retrieve List of APIs associated to a Submodule
		 */
		externalAPIList = getAPIList();

		/**
		 * Map of APIusageID and API parameters.
		 */
		final HashMap<Integer, ArrayList<ExternalAPISearchParameters>> hmap = new HashMap<Integer, ArrayList<ExternalAPISearchParameters>>();

		final ExternalAPIInformation externalAPIInformation = new ExternalAPIInformation();

		// Iterate the vendor list and get search parameters for each vendor
		for (int i = 0; i < externalAPIList.size(); i++) {
			LOG.info("Fetching Parameters for Vendor:::" + externalAPIList.get(i).getVendorName() + "With APIUsageID::"
					+ externalAPIList.get(i).getApiUsageID());
			externalAPIInputParameters = getExternalAPIInputParameters(Integer.valueOf(externalAPIList.get(i).getApiUsageID()), moduleName);
			Integer usageId = Integer.valueOf(externalAPIList.get(i).getApiUsageID());
			if (null != externalAPIInputParameters) {
				hmap.put(usageId, externalAPIInputParameters);
			}
		}

		if (!(externalAPIList.isEmpty()) && !(hmap.isEmpty())) {
			externalAPIInformation.setVendorList(externalAPIList);
			externalAPIInformation.setSerchParameters(hmap);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return externalAPIInformation;
	}

	/**
	 * This method is used to get API list.
	 * 
	 * @return externalAPIList -list of external APi info.
	 * @throws ScanSeeException
	 *             -Throws if any exceptions
	 */
	public ArrayList<ExternalAPIVendor> getAPIList() throws HubCitiException {
		final String methodName = "getAPIList";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<ExternalAPIVendor> externalAPIList = null;
		externalAPIList = findDao.getExternalAPIList(moduleName);
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return externalAPIList;
	}

	/**
	 * Thsi method is used to get external API input parameters.
	 * 
	 * @param apiUsageID
	 *            -As integer parameter
	 * @param moduleName
	 *            -As String parameter
	 * @return externalAPIInputParameters
	 * @throws ScanSeeException
	 *             -Throws if any Exceptions
	 */
	public ArrayList<ExternalAPISearchParameters> getExternalAPIInputParameters(Integer apiUsageID, String moduleName) throws HubCitiException {
		final String methodName = "getExternalAPIInputParameters";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<ExternalAPISearchParameters> externalAPIInputParameters = null;
		externalAPIInputParameters = findDao.getExternalAPIInputParameters(apiUsageID, moduleName);
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return externalAPIInputParameters;
	}
}
