package com.ssafy.travellego.board.tripPlanBoard.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.travellego.board.favorite.model.FavoriteDto;
import com.ssafy.travellego.board.tripPlanBoard.model.TripPlanBoardDto;
import com.ssafy.travellego.board.tripPlanBoard.model.TripPlanDto;

@Mapper
public interface TripPlanBoardMapper {

	void writeArticle(TripPlanBoardDto boardDto) throws SQLException;
	List<TripPlanBoardDto> listArticle(Map<String, Object> map) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	TripPlanBoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	int modifyArticle(TripPlanBoardDto boardDto) throws SQLException;
	int deleteArticle(int articleNo) throws SQLException;
	void writePlan(TripPlanDto planDto);
	Integer getArticleNo();
	int[] getPlan(int articleNo);
	void deleteAllPlan(int articleNo);
	int addFavorite(FavoriteDto dto);
	int cancelFavorite(FavoriteDto dto);
	int getFavorite(FavoriteDto dto);
	int getFavoriteCount(int articleNo);
	List<TripPlanBoardDto> getFavoriteArticles(String userId);
	List<TripPlanBoardDto> getBestPlans();
	List<TripPlanBoardDto> searchPlans(String keyword);
}
