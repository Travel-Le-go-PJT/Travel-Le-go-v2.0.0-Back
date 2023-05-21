package com.ssafy.travellego.board.tripInfoBoard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.travellego.board.hotPlaceBoard.model.HotPlaceBoardDto;
import com.ssafy.travellego.board.search.SearchDto;
import com.ssafy.travellego.board.tripInfoBoard.model.TripInfoBoardDto;
import com.ssafy.travellego.board.tripInfoBoard.model.service.TripInfoBoardService;
import com.ssafy.travellego.user.model.UserDto;
import com.ssafy.travellego.util.ResultMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping("/tripInfoBoard")
@CrossOrigin("*")
@Api("여행정보 게시판 컨트롤러 V1")
public class TripInfoBoardController {
	private static final Logger logger = LoggerFactory.getLogger(TripInfoBoardController.class);

	private TripInfoBoardService tripInfoBoardService;
	private ResultMessage resultMessage = new ResultMessage();

	@Autowired
	public TripInfoBoardController(TripInfoBoardService tripInfoBoardService) {
		super();
		this.tripInfoBoardService = tripInfoBoardService;
	}

	
	@ApiOperation(value = "게시판목록", notes = "게시판의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/")
	public ResponseEntity<?> list(SearchDto searchdto) throws Exception {
		logger.info("Welcome Trip Info Board List! ");
		try {
			List<TripInfoBoardDto> list = tripInfoBoardService.listArticle(searchdto);
			if (list != null && !list.isEmpty()) {
				
				return new ResponseEntity<List<TripInfoBoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	

	@ApiOperation(value = "게시판", notes = "게시판의 <big>한개의 글</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판글 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/{articleNo}")
	public ResponseEntity<?> view(@PathVariable int articleNo) {
		logger.debug("view articleNo : {}", articleNo);
		try {
			tripInfoBoardService.updateHit(articleNo);
			TripInfoBoardDto tripInfoBoardDto = tripInfoBoardService.getArticle(articleNo);
			if (tripInfoBoardDto != null) {
				return new ResponseEntity<TripInfoBoardDto>(tripInfoBoardDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "게시판", notes = "게시판의 <big>한개의 글</big>을 작성한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판글 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PostMapping(value = "/")
	public ResponseEntity<?> write(@RequestBody TripInfoBoardDto boardDto) {
		logger.debug("write TripInfoBoardDto : {}", boardDto);
		try {
			tripInfoBoardService.writeArticle(boardDto);
			SearchDto searchdto = new SearchDto("", "");
			TripInfoBoardDto dto = tripInfoBoardService.getArticle(boardDto.getArticleNo());
			if (dto != null) {
				resultMessage.setResultSuccess();
				return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "게시판", notes = "게시판의 <big>한개의 글</big>을 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판글 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PutMapping(value = "/")
	public ResponseEntity<?> modify(@RequestBody TripInfoBoardDto boardDto) {
		logger.debug("modify boardDto : {}", boardDto);
		try {
			int result = tripInfoBoardService.modifyArticle(boardDto);
			if (result == 1) {
				resultMessage.setResultSuccess();
				return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	
	@ApiOperation(value = "게시판", notes = "게시판의 <big>한개의 글</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판글 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@DeleteMapping(value = "/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo){
		logger.debug("delete articleNo : {}", articleNo);
		try {
			int result  = tripInfoBoardService.deleteArticle(articleNo);
			if(result >= 1) {
				resultMessage.setResultSuccess();
				return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
			} else {
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
