package com.hubciti.govqa.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.webqa.WebQAServiceRequestType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.helper.SortGovQARequestByName;
import com.hubciti.common.helper.SortTableGroup;
import com.hubciti.common.helper.SortTableGroupType;
import com.hubciti.common.pojos.Table;
import com.hubciti.common.pojos.TableGroup;
import com.hubciti.common.pojos.WebQAServiceReqType;
import com.hubciti.common.utility.PropertiesReader;

/**
 * The Helper class for GovQAHelper.
 * 
 * @author Kumar D
 */
public class GovQAHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GovQAHelper.class);

	
	/**
	 * This GovQAHelper method to get Group by Category, Alphabet and Sort by name.
	 * 
	 * @param objTable list.
	 * @return TableGroup with Group items.
	 */
	public static TableGroup sortTableGroup(Table objTable) {
		LOG.info("Inside GovQAHelper : sortTableGroup");

		final HashMap<String, TableGroup> tableGroupMap = new HashMap<String, TableGroup>();
		final String strCatLabel = PropertiesReader.getPropertyValue(HubCitiConstants.CATEGORY_LABEL);
		final String strPopularTypes = PropertiesReader.getPropertyValue(HubCitiConstants.POPULAR_REQ_TYPES);
		
		TableGroup objTableGroup = null;
		String key = null;
		ArrayList<WebQAServiceReqType> arReqTypeList = null;

		final String strGroupBy = objTable.getGroupBy();
		final String strSortBy = objTable.getSortBy();

		for (WebQAServiceRequestType objWebQAServiceReqType :  objTable.getArWebQAServiceReqTypeList()) {

			if ("type".equalsIgnoreCase(strGroupBy)) {
				
				if (strPopularTypes.toLowerCase().contains(objWebQAServiceReqType.getName().toLowerCase())) {
					key = strCatLabel;
					objWebQAServiceReqType.setCategory(key);
				} else {
					key = objWebQAServiceReqType.getCategory();
				}
				
			} else {
				key = Character.toString(objWebQAServiceReqType.getName().charAt(0)).toUpperCase();
			} 


			if (key != null && !"".equals(key)) {

				if (tableGroupMap.containsKey(key)) {

					objTableGroup = tableGroupMap.get(key);
					arReqTypeList = (ArrayList<WebQAServiceReqType>) objTableGroup.getArServiceReqTypeList();

					if (arReqTypeList != null && !arReqTypeList.isEmpty()) {

						final WebQAServiceReqType objReqType = new WebQAServiceReqType();
						objReqType.setTypeNo(objWebQAServiceReqType.getTypeNo());
						objReqType.setName(objWebQAServiceReqType.getName());
						objReqType.setCategory(objWebQAServiceReqType.getCategory());
						objReqType.setKeywords(objWebQAServiceReqType.getKeywords());


						arReqTypeList.add(objReqType);
						objTableGroup.setArServiceReqTypeList(arReqTypeList);
					}

				} else {
					
					objTableGroup = new TableGroup();

					if ("type".equalsIgnoreCase(strGroupBy)) {
						
						objTableGroup.setGroupContent(objWebQAServiceReqType.getCategory());
						objTableGroup.setGroupId(2);
						
					} else  {
						objTableGroup.setGroupContent(Character.toString(objWebQAServiceReqType.getName().charAt(0)).toUpperCase());
						objTableGroup.setGroupId(1);
					}


					arReqTypeList = new ArrayList<WebQAServiceReqType>();

					final WebQAServiceReqType objReqType = new WebQAServiceReqType();
					objReqType.setTypeNo(objWebQAServiceReqType.getTypeNo());
					objReqType.setName(objWebQAServiceReqType.getName());
					objReqType.setCategory(objWebQAServiceReqType.getCategory());
					objReqType.setKeywords(objWebQAServiceReqType.getKeywords());


					arReqTypeList.add(objReqType);
					objTableGroup.setArServiceReqTypeList(arReqTypeList);
				}
			}

			tableGroupMap.put(key, objTableGroup);
		}

		
		final Set<Map.Entry<String, TableGroup>> set = tableGroupMap.entrySet();

		final ArrayList<TableGroup> arTableGrpList = new ArrayList<TableGroup>();
		
		for (Map.Entry<String, TableGroup> entry : set) {
			arTableGrpList.add(entry.getValue());
		}
	
		
		
		if ("type".equalsIgnoreCase(strGroupBy)) {
			
			SortTableGroupType sortByType = new SortTableGroupType();
			Collections.sort(arTableGrpList, sortByType);
			
		} else  {
			SortTableGroup sortByAlphabet = new SortTableGroup();
			Collections.sort(arTableGrpList, sortByAlphabet);
		}
		
		
		for (TableGroup objTableGrp : arTableGrpList) {

			/* To sort by GovQA name. */
			if ("name".equalsIgnoreCase(strSortBy)) {
				
				SortGovQARequestByName sortReqByName = new SortGovQARequestByName();
				Collections.sort(objTableGrp.getArServiceReqTypeList(), sortReqByName);
				
			} 
		}
		
		TableGroup objTableGrp = new TableGroup();
		objTableGrp.setTableGroup(arTableGrpList);
		return objTableGrp;
	}

}