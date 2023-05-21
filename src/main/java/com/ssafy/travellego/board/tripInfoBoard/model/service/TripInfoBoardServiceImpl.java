package com.ssafy.travellego.board.tripInfoBoard.model.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travellego.board.search.SearchDto;
import com.ssafy.travellego.board.tripInfoBoard.model.TripInfoBoardDto;
import com.ssafy.travellego.board.tripInfoBoard.model.mapper.TripInfoBoardMapper;

@Service
public class TripInfoBoardServiceImpl implements TripInfoBoardService {
	
	private TripInfoBoardMapper tripInfoBoardMapper;
	
	@Autowired
	public TripInfoBoardServiceImpl(TripInfoBoardMapper tripInfoBoardMapper) {
		super();
		this.tripInfoBoardMapper = tripInfoBoardMapper;
	}

	
	@Override
	public void writeArticle(TripInfoBoardDto boardDto) throws Exception {
		tripInfoBoardMapper.writeArticle(boardDto);
	}

	@Override
	public List<TripInfoBoardDto>listArticle(SearchDto searchdto) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		String key = searchdto.getKey();
//		if("userid".equals(key))
//			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", searchdto.getWord() == null ? "" : searchdto.getWord());
		
		return tripInfoBoardMapper.listArticle(param);
	}

	@Override
	public TripInfoBoardDto getArticle(int articleNo) throws Exception {
		return tripInfoBoardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		tripInfoBoardMapper.updateHit(articleNo);
		
	}

	@Override
	public int modifyArticle(TripInfoBoardDto boardDto) throws Exception {
		return tripInfoBoardMapper.modifyArticle(boardDto);

	}

	@Override
	public int deleteArticle(int articleNo) throws Exception {
		return tripInfoBoardMapper.deleteArticle(articleNo);
	}


//	@Override
//	public List<TripInfoBoardDto> listArticleByPatternMatching(Map<String, String> map) throws Exception {
//		String key = map.get("key");
//		String word = map.get("word");
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		List<TripInfoBoardDto> articles = tripInfoBoardMapper.listAllArticle();
//		if (!key.isEmpty() && !word.isEmpty()) {
//			articles = searchBykey(articles, key, word);
//		}
//		return limitArticles(articles, start);
//	}

//	private List<TripInfoBoardDto> limitArticles(List<TripInfoBoardDto> articles, int start) {
//		return articles.stream()
//				.skip(start)
//				.limit(SizeConstant.LIST_SIZE)
//				.collect(Collectors.toList());
//	}
//
//	private List<TripInfoBoardDto> searchBykey(List<TripInfoBoardDto> articles, String key, String word) {
//		return articles.stream().filter((article) -> hasPattern(article, key, word))
//				.collect(Collectors.toList());
//	}
//	private boolean hasPattern(TripInfoBoardDto article, String key, String pattern) {
//		String text = article.getInfoByField(key);
//		return KMP.hasPattern(text, pattern);
//	}

}
