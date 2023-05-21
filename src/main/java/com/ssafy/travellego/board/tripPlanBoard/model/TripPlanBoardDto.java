package com.ssafy.travellego.board.tripPlanBoard.model;

public class TripPlanBoardDto {

	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
	private int plan[];
	
	
	
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	

	public int[] getPlan() {
		return plan;
	}

	public void setPlan(int[] plan) {
		this.plan = plan;
	}

	public TripPlanBoardDto(int articleNo, String userId, String subject, String content ) {
	
		this.articleNo = articleNo;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
	}
	
	public TripPlanBoardDto(int articleNo, String userId, String subject, String content, int hit,
			String registerTime) {
	 
		this.articleNo = articleNo;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
	}
	
	
	
	public TripPlanBoardDto(int articleNo, String userId, String subject, String content, int hit, String registerTime,
			int[] plan) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
		this.plan = plan;
	}

	public TripPlanBoardDto() {
		super();
	}
	
	
	
}
