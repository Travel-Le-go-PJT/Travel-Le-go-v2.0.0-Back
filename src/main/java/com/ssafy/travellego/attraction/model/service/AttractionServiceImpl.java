package com.ssafy.travellego.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travellego.attraction.model.AttractionDto;
import com.ssafy.travellego.attraction.model.SidoDto;
import com.ssafy.travellego.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService{
	
	private AttractionMapper attrMapper;
	
	@Autowired
	public AttractionServiceImpl(AttractionMapper attrMapper) {
		super();
		this.attrMapper = attrMapper;
	}

	@Override
	public List<AttractionDto> getRandomAttraction(int num) {
		return attrMapper.getRandAttractionInfo(num);
	}

	
	@Override
	public List<AttractionDto> attractionList(AttractionDto dto) {
		
		return attrMapper.attractionList(dto);
	}

	@Override
	public List<SidoDto> getSido() throws Exception {
		return attrMapper.getSido();
	}

	@Override
	public AttractionDto getAttraction(int contentId) throws Exception {
		return attrMapper.getAttraction(contentId);
	}

	@Override
	public List<AttractionDto> getBestAttractions() {
		return attrMapper.getBestAttractions();
	}

}
