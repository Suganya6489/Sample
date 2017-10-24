package com.hubciti.scannow.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.helper.SortScanNowHistoryByDate;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.WishListHistoryDetails;
import com.hubciti.common.pojos.WishListResultSet;

/**
 * This class contains methods for scan history display.
 * 
 * @author shyamsundara_hm
 */
public class ScanNowHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ScanNowHelper.class);

	/**
	 * for ScanNowHelper constructor.
	 */
	protected ScanNowHelper() {
		LOG.info("Inside ScanNowHelper class");
	}

	/**
	 * This method for displaying scan history details based on scan date wise.
	 * 
	 * @param wishlistHistorylst
	 *            , the list contains scan history product details.
	 * @return xml with scan history group by scan date.
	 */

	public static WishListHistoryDetails getScanNowHistory(final List<ProductDetail> wishlistHistorylst) {

		final WishListHistoryDetails wishListHistoryDetails = new WishListHistoryDetails();

		final HashMap<String, WishListResultSet> wishListResultMap = new HashMap<String, WishListResultSet>();

		ArrayList<ProductDetail> productDetaillst = null;

		WishListResultSet wishListResultSetInfo = null;
		String key = null;
		for (ProductDetail listResultSet : wishlistHistorylst) {
			key = listResultSet.getScanDate();

			if (wishListResultMap.containsKey(key)) {

				wishListResultSetInfo = wishListResultMap.get(key);
				productDetaillst = (ArrayList<ProductDetail>) wishListResultSetInfo.getProductDetail();
				if (null != productDetaillst)

				{
					final ProductDetail productDetail = new ProductDetail();
					productDetail.setRowNumber(listResultSet.getRowNumber());
					productDetail.setProductId(listResultSet.getProductId());
					productDetail.setProductName(listResultSet.getProductName());
					productDetail.setProductImagePath(listResultSet.getProductImagePath());
					productDetail.setScanStatus(listResultSet.getScanStatus());
					productDetail.setProductLongDescription(listResultSet.getProductLongDescription());
					productDetail.setBarCode(listResultSet.getBarCode());
					productDetail.setScanType(listResultSet.getScanType());
					productDetail.setScanHistoryId(listResultSet.getScanHistoryId());
					productDetaillst.add(productDetail);
					wishListResultSetInfo.setProductDetail(productDetaillst);
				}
			} else {
				wishListResultSetInfo = new WishListResultSet();
				wishListResultSetInfo.setDateScanned(listResultSet.getScanDate());
				productDetaillst = new ArrayList<ProductDetail>();
				final ProductDetail productDetail = new ProductDetail();
				productDetail.setRowNumber(listResultSet.getRowNumber());
				productDetail.setProductName(listResultSet.getProductName());
				productDetail.setProductId(listResultSet.getProductId());
				productDetail.setProductImagePath(listResultSet.getProductImagePath());
				productDetail.setScanStatus(listResultSet.getScanStatus());
				productDetail.setProductLongDescription(listResultSet.getProductLongDescription());
				productDetail.setBarCode(listResultSet.getBarCode());
				productDetail.setScanType(listResultSet.getScanType());
				productDetail.setScanHistoryId(listResultSet.getScanHistoryId());
				productDetaillst.add(productDetail);
				wishListResultSetInfo.setProductDetail(productDetaillst);

				wishListResultMap.put(key, wishListResultSetInfo);
			}
		}

		final Set<Map.Entry<String, WishListResultSet>> set = wishListResultMap.entrySet();

		final ArrayList<WishListResultSet> wishListResultSetlst = new ArrayList<WishListResultSet>();

		for (Map.Entry<String, WishListResultSet> entry : set) {
			wishListResultSetlst.add(entry.getValue());
		}

		/**
		 * Calling Comparator interface for sorting scan date. It sorts and
		 * displays according to current date.
		 */

		SortScanNowHistoryByDate sortHistoryByDateObj = new SortScanNowHistoryByDate();
		Collections.sort(wishListResultSetlst, sortHistoryByDateObj);
		Collections.reverse(wishListResultSetlst);
		wishListHistoryDetails.setProductHistoryInfo(wishListResultSetlst);
		return wishListHistoryDetails;
	}
}
