package com.ssafy.travellego.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.travellego.attraction.model.AttractionDto;
import com.ssafy.travellego.attraction.model.SidoDto;

@Mapper
public interface AttractionMapper {
	List<AttractionDto> getRandAttractionInfo(int num);
	List<AttractionDto> attractionList(AttractionDto dto);
	List<SidoDto> getSido();
	AttractionDto getAttraction(int contentId);
	List<AttractionDto> getBestAttractions();
	List<SidoDto> searchSido(String keyword);
}
