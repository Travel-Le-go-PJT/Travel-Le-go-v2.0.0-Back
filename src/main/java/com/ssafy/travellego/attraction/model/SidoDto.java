package com.ssafy.travellego.attraction.model;

public class SidoDto {
	int sidoCode;
	String sidoName;
	String engName;
	String image;

	public SidoDto(int sidoCode, String sidoName, String engName, String image) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
		this.engName = engName;
		this.image = image;
	}

	public SidoDto() {
		super();
	}

	public SidoDto(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}

	public int getsidoCode() {
		return sidoCode;
	}

	public void setsidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getsidoName() {
		return sidoName;
	}

	public void setsidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
