package com.ssafy.travellego.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travellego.attraction.model.AttractionDto;
import com.ssafy.travellego.attraction.model.SidoDto;
import com.ssafy.travellego.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
@Api("attraction Controller V1")
public class AttractionController {
	
	private AttractionService aservice;
	
	@Autowired
	public AttractionController(AttractionService aservice) {
		super();
		this.aservice = aservice;
	}


	@GetMapping
	public ResponseEntity<?> home() {
		List<AttractionDto> list;
		try {
			list = aservice.getRandomAttraction(20);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	

	@PostMapping("/search")
	public ResponseEntity<?> tripsearch(@RequestBody AttractionDto dto) {
		List<AttractionDto> list;
		try {
			list = aservice.attractionList(dto);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}	
	
	@GetMapping("/sido")
	public ResponseEntity<?> getSido() {
		List<SidoDto> list;
		try {
			list = aservice.getSido();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<SidoDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{contentId}")
	public ResponseEntity<?> getAttraction(@PathVariable int contentId) {
		AttractionDto dto;
		try {
			dto = aservice.getAttraction(contentId);
			if(dto != null) {
				return new ResponseEntity<AttractionDto>(dto, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/best")
	public ResponseEntity<?> getBestAttractions() {
		List<AttractionDto> list;
		try {
			list = aservice.getBestAttractions();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
