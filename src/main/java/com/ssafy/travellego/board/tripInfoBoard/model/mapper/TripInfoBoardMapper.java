package com.ssafy.travellego.board.tripInfoBoard.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.travellego.board.tripInfoBoard.model.TripInfoBoardDto;

@Mapper
public interface TripInfoBoardMapper {
	void writeArticle(TripInfoBoardDto boardDto) throws SQLException;
	List<TripInfoBoardDto> listArticle(Map<String, Object> map) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	TripInfoBoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	int modifyArticle(TripInfoBoardDto boardDto) throws SQLException;
	int deleteArticle(int articleNo) throws SQLException;
	List<TripInfoBoardDto> getBestInfos();
}
