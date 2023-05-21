package com.ssafy.travellego.board.favorite.model;

public class FavoriteDto {
	private int articleNo;
	private String userId;
	
	
	public FavoriteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavoriteDto(int articleNo, String userId) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
