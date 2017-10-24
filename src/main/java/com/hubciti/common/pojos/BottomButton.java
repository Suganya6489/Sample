/**
 * 
 */
package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * @author kumar_dodda
 */
public class BottomButton {
	/**
	 * 
	 */
	private Integer bottomBtnID;
	/**
	 * 
	 */
	private String bottomBtnName;
	/**
	 * 
	 */
	private String bottomBtnImg;
	/**
	 * 
	 */
	private String bottomBtnImgOff;
	/**
	 * 
	 */
	private Integer btnLinkTypeID;
	/**
	 * 
	 */
	private String btnLinkTypeName;
	/**
	 * 
	 */
	private Integer btnLinkID;
	/**
	 * 
	 */
	private Integer position;
	


	/**
	 * @return
	 */
	public Integer getBottomBtnID() {
		return bottomBtnID;
	}

	/**
	 * @param bottomBtnID
	 */
	public void setBottomBtnID(Integer bottomBtnID) {
		this.bottomBtnID = bottomBtnID;
	}

	/**
	 * @return
	 */
	public String getBottomBtnName() {
		return bottomBtnName;
	}

	/**
	 * @param bottomBtnName
	 */
	public void setBottomBtnName(String bottomBtnName) {
		this.bottomBtnName = bottomBtnName;
	}

	/**
	 * @return
	 */
	public String getBottomBtnImg() {
		return bottomBtnImg;
	}

	/**
	 * @param bottomBtnImg
	 */
	public void setBottomBtnImg(String bottomBtnImg) {
		if ("".equals(Utility.checkNull(bottomBtnImg))) {
			this.bottomBtnImg = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bottomBtnImg = bottomBtnImg;
		}
	}

	/**
	 * @return the bottomBtnImgOff
	 */
	public String getBottomBtnImgOff() {
		return bottomBtnImgOff;
	}

	/**
	 * @param bottomBtnImgOff
	 *            the bottomBtnImgOff to set
	 */
	public void setBottomBtnImgOff(String bottomBtnImgOff) {
		if ("".equals(Utility.checkNull(bottomBtnImgOff))) {
			this.bottomBtnImgOff = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bottomBtnImgOff = bottomBtnImgOff;
		}
	}

	/**
	 * @return
	 */
	public Integer getBtnLinkTypeID() {
		return btnLinkTypeID;
	}

	/**
	 * @param btnLinkTypeID
	 */
	public void setBtnLinkTypeID(Integer btnLinkTypeID) {
		this.btnLinkTypeID = btnLinkTypeID;
	}

	/**
	 * @return the btnLinkTypeName
	 */
	public String getBtnLinkTypeName() {
		return btnLinkTypeName;
	}

	/**
	 * @param btnLinkTypeName
	 *            the btnLinkTypeName to set
	 */
	public void setBtnLinkTypeName(String btnLinkTypeName) {
		this.btnLinkTypeName = btnLinkTypeName;
	}

	/**
	 * @return
	 */
	public Integer getBtnLinkID() {
		return btnLinkID;
	}

	/**
	 * @param btnLinkID
	 */
	public void setBtnLinkID(Integer btnLinkID) {
		this.btnLinkID = btnLinkID;
	}

	/**
	 * @return
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
}
