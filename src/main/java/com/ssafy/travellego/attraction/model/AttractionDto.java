package com.ssafy.travellego.attraction.model;

public class AttractionDto {
	int contentId;
	int contentTypeId;
	String title;
	String addr1;
	String image;
	String description;
	int sidoCode;
	int gugunCode;
	double latitude;
	double longitude;
	
	
	public AttractionDto() {
		super();
	}

	
	
	public AttractionDto(int contentTypeId, String title, int sidoCode) {
		super();
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.sidoCode = sidoCode;
	}

	
	
	

	public AttractionDto(int contentId, String title, String addr1, String image) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.addr1 = addr1;
		this.image = image;
	}
	
	



	public AttractionDto(int contentId, String title, String addr1, String image, String description) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.addr1 = addr1;
		this.image = image;
		this.description = description;
	}



	public AttractionDto(int contentId, String title, String addr1, String image,
			String description, double latitude, double longitude) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.addr1 = addr1;
		this.image = image;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public AttractionDto(int contentId, String title, String addr1, String image, double latitude, double longitude) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.addr1 = addr1;
		this.image = image;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public AttractionDto(int contentId, int contentTypeId, String title, String addr1, String image,
			String description, int sidoCode, int gugunCode, double latitude, double longitude) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr1 = addr1;
		this.image = image;
		this.description = description;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public int getcontentId() {
		return contentId;
	}


	public void setcontentId(int contentId) {
		this.contentId = contentId;
	}


	public int getcontentTypeId() {
		return contentTypeId;
	}


	public void setcontentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAddr1() {
		return addr1;
	}


	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getsidoCode() {
		return sidoCode;
	}


	public void setsidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}


	public int getgugunCode() {
		return gugunCode;
	}


	public void setgugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
	
	
	
	
	
	
}
