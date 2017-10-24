package com.hubciti.common.pojos;


import com.hubciti.common.utility.Utility;

public class RSSFeedMessage {

	private String title;
	
	private String link;
	
	private String pubDate;
	
	private String type;
	
	private String text;
	
	private String mediaContent;
	
	private String mediaText;
	
	private String imgPath;
	
	private Boolean isPushNotification;

	public Boolean getIsPushNotification() {
		return isPushNotification;
	}

	public void setIsPushNotification(Boolean isPushNotification) {
		this.isPushNotification = isPushNotification;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(null !=this.getIsPushNotification() && this.getIsPushNotification()){
			this.title = title;
		}else if (!"".equals(Utility.checkNull(title))) {
			this.title = title;
		} else if (title.contains("<![CDATA[")) {
				this.title = title;
		} else {
				this.title = "<![CDATA[" + title + "]]>";
		}
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		if(null !=this.getIsPushNotification() && this.getIsPushNotification()){
			this.link = link;
		}else if (!"".equals(Utility.checkNull(link))) {
			this.link = link;
		} else if (link.contains("<![CDATA[")) {
				this.link = link;
		} else {
				this.link = "<![CDATA[" + link + "]]>";
		}
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (!"".equals(Utility.checkNull(type))) {
			this.type = type;
		} else if (type.contains("<![CDATA[")) {
				this.type = type;
		} else {
				this.type = "<![CDATA[" + type + "]]>";
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(String mediaContent) {
		this.mediaContent = mediaContent;
	}

	public String getMediaText() {
		return mediaText;
	}

	public void setMediaText(String mediaText) {
		this.mediaText = mediaText;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
