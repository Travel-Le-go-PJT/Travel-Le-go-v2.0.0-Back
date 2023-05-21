package com.ssafy.travellego.board.tripPlanBoard.model;

public class TripPlanDto {
	private int tripPlanArticleNo;
	private int attractionContentId;
	private int tripPlanOrder;
	

	public int gettripPlanArticleNo() {
		return tripPlanArticleNo;
	}



	public void settripPlanArticleNo(int tripPlanArticleNo) {
		this.tripPlanArticleNo = tripPlanArticleNo;
	}



	public int getattractionContentId() {
		return attractionContentId;
	}



	public void setattractionContentId(int attractionContentId) {
		this.attractionContentId = attractionContentId;
	}



	public int gettripPlanOrder() {
		return tripPlanOrder;
	}



	public void settripPlanOrder(int tripPlanOrder) {
		this.tripPlanOrder = tripPlanOrder;
	}



	public TripPlanDto(int tripPlanArticleNo, int attractionContentId, int tripPlanOrder) {
		super();
		this.tripPlanArticleNo = tripPlanArticleNo;
		this.attractionContentId = attractionContentId;
		this.tripPlanOrder = tripPlanOrder;
	}
	
	

}
