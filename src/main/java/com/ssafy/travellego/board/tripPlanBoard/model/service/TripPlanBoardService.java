package com.ssafy.travellego.board.tripPlanBoard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.travellego.board.favorite.model.FavoriteDto;
import com.ssafy.travellego.board.search.SearchDto;
import com.ssafy.travellego.board.tripPlanBoard.model.TripPlanBoardDto;

@Service
public interface TripPlanBoardService {
	
	void writeArticle(TripPlanBoardDto boardDto) throws Exception;
	List<TripPlanBoardDto> listArticle(SearchDto searchdto) throws Exception;
	TripPlanBoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	int modifyArticle(TripPlanBoardDto boardDto) throws Exception;
	int deleteArticle(int articleNo) throws Exception;
	void writePlan(int[] plan, int articleNo);
	int getArticleNo();
	int[] getPlan(int articleNo);
	int addFavorite(FavoriteDto dto);
	int cancelFavorite(FavoriteDto dto);
	Boolean getFavorite(FavoriteDto dto);
	int getFavoriteCount(int articleNo);
	List<TripPlanBoardDto> getFavoriteArticles(String userId);
	List<TripPlanBoardDto> getBestPlans();
}
