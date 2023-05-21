package com.ssafy.travellego.board.tripInfoBoard.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.travellego.board.search.SearchDto;
import com.ssafy.travellego.board.tripInfoBoard.model.TripInfoBoardDto;

public interface TripInfoBoardService {
	void writeArticle(TripInfoBoardDto boardDto) throws Exception;
	List<TripInfoBoardDto> listArticle(SearchDto searchdto) throws Exception;
	TripInfoBoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	int modifyArticle(TripInfoBoardDto boardDto) throws Exception;
	int deleteArticle(int articleNo) throws Exception;
//	List<TripInfoBoardDto> listArticleByPatternMatching(Map<String, String> map) throws Exception;
	
}
