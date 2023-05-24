package com.ssafy.travellego.attraction.model.service;

import java.util.List;

import com.ssafy.travellego.attraction.model.AttractionDto;
import com.ssafy.travellego.attraction.model.SidoDto;

public interface AttractionService {
	List<AttractionDto> getRandomAttraction(int num) throws Exception;
	List<AttractionDto> attractionList(AttractionDto dto) throws Exception;
	List<SidoDto> getSido() throws Exception;
	AttractionDto getAttraction(int contentId) throws Exception;
	List<AttractionDto> getBestAttractions();
}
