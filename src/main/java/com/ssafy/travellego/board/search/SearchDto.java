package com.ssafy.travellego.board.search;

public class SearchDto {
	private String key;
	private String word;
	
	public SearchDto(String key, String word) {
		super();
		this.key = key;
		this.word = word;
	}

	
	
	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getWord() {
		return word;
	}



	public void setWord(String word) {
		this.word = word;
	}



	@Override
	public String toString() {
		return "SearchDto [key=" + key + ", word=" + word + "]";
	}
	
	

}
