package com.ssafy.travellego.attraction.model;

public class SidoDto {
	int sidoCode;
	String sidoName;
	
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
	
	
}
