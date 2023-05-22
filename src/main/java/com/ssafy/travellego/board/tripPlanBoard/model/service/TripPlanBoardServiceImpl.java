package com.ssafy.travellego.board.tripPlanBoard.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travellego.board.favorite.model.FavoriteDto;
import com.ssafy.travellego.board.search.SearchDto;
import com.ssafy.travellego.board.tripPlanBoard.model.TripPlanBoardDto;
import com.ssafy.travellego.board.tripPlanBoard.model.TripPlanDto;
import com.ssafy.travellego.board.tripPlanBoard.model.mapper.TripPlanBoardMapper;

@Service
public class TripPlanBoardServiceImpl implements TripPlanBoardService {
	private static final Logger logger = LoggerFactory.getLogger(TripPlanBoardServiceImpl.class);
	
	private TripPlanBoardMapper planMapper;

	@Autowired
	public TripPlanBoardServiceImpl(TripPlanBoardMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	public void writeArticle(TripPlanBoardDto boardDto) throws Exception {
		planMapper.writeArticle(boardDto);

	}

	@Override
	public List<TripPlanBoardDto> listArticle(SearchDto searchdto) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = searchdto.getKey();
		logger.info("{}",key);
		
		param.put("key", key == null ? "" : key);
		param.put("word", searchdto.getWord() == null ? "" : searchdto.getWord());
		return planMapper.listArticle(param);
	}

	@Override
	public TripPlanBoardDto getArticle(int articleNo) throws Exception {
		logger.info("{}",articleNo);
		TripPlanBoardDto returnDto = planMapper.getArticle(articleNo);
		
	
		int[] plan= getPlan(articleNo);
		returnDto.setPlan(plan);
		
		return returnDto;
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		planMapper.updateHit(articleNo);

	}

	@Override
	public int modifyArticle(TripPlanBoardDto boardDto) throws Exception {
		planMapper.deleteAllPlan(boardDto.getArticleNo());
		writePlan(boardDto.getPlan(), boardDto.getArticleNo());
		return planMapper.modifyArticle(boardDto);
	}

	
	@Override
	public int deleteArticle(int articleNo) throws Exception {
		return planMapper.deleteArticle(articleNo);
	}


	@Override
	public void writePlan(int[] plan, int articleNo) {
		for (int i = 0; i < plan.length; i++) {
			TripPlanDto planDto = new TripPlanDto(articleNo, plan[i], i+1);
			planMapper.writePlan(planDto);
		}
	}

	@Override
	public int getArticleNo() {
		Integer val = planMapper.getArticleNo();
		if(val == null) {
			val = 1;
		}
		return val;
	}

	@Override
	public int[] getPlan(int articleNo) {
		return planMapper.getPlan(articleNo);
	}

	@Override
	public int addFavorite(FavoriteDto dto) {
		return planMapper.addFavorite(dto);
	}

	@Override
	public int cancelFavorite(FavoriteDto dto) {
		return planMapper.cancelFavorite(dto);
	}

	@Override
	public Boolean getFavorite(FavoriteDto dto) {
		if(planMapper.getFavorite(dto) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getFavoriteCount(int articleNo) {
		return planMapper.getFavoriteCount(articleNo);
	}

	@Override
	public List<TripPlanBoardDto> getFavoriteArticles(String userId) {
		return planMapper.getFavoriteArticles(userId);
	}
	

}
