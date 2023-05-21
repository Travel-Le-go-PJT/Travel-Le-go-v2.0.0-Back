package com.ssafy.travellego.board.tripPlanBoard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.ssafy.travellego.board.favorite.model.FavoriteDto;
import com.ssafy.travellego.board.search.SearchDto;
import com.ssafy.travellego.board.tripPlanBoard.model.TripPlanBoardDto;
import com.ssafy.travellego.board.tripPlanBoard.model.service.TripPlanBoardService;
import com.ssafy.travellego.util.ResultMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tripPlanBoard")
@CrossOrigin("*")
@Api("여행계획 컨트롤러 V1")
public class TripPlanBoardController {
	private static final Logger logger = LoggerFactory.getLogger(TripPlanBoardController.class);
	
	private TripPlanBoardService tripPlanBoardService;
	private ResultMessage resultMessage = new ResultMessage();
	
	@Autowired
	public TripPlanBoardController(TripPlanBoardService tripPlanBoardService) {
		this.tripPlanBoardService = tripPlanBoardService;
	}
	
	@ApiOperation(value = "게시판목록", notes = "게시판의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/")
	public ResponseEntity<?> list(SearchDto searchdto) throws Exception {
		logger.info("Welcome Hot Place Board List! ");
		try {
			List<TripPlanBoardDto> list = tripPlanBoardService.listArticle(searchdto);
			if (list != null && !list.isEmpty()) {
				
				return new ResponseEntity<List<TripPlanBoardDto>>(list, HttpStatus.OK);
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
			tripPlanBoardService.updateHit(articleNo);
			TripPlanBoardDto tripPlanBoardDto = tripPlanBoardService.getArticle(articleNo);
			if (tripPlanBoardDto != null) {
				return new ResponseEntity<TripPlanBoardDto>(tripPlanBoardDto, HttpStatus.OK);
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
	public ResponseEntity<?> write(@RequestBody TripPlanBoardDto boardDto) {
		logger.debug("write TripPlanBoardDto : {}", boardDto);
		try {
			int articleno = tripPlanBoardService.getArticleNo();
			boardDto.setArticleNo(articleno);
			logger.info("122asdsasda11{}", articleno);
			tripPlanBoardService.writeArticle(boardDto);
			logger.info("12211{}", articleno);
			tripPlanBoardService.writePlan(boardDto.getPlan(), articleno);
			
			TripPlanBoardDto dto = tripPlanBoardService.getArticle(boardDto.getArticleNo());
			if (dto != null) {
				resultMessage.setResultSuccess();
				return new ResponseEntity<ResultMessage> (resultMessage, HttpStatus.OK);
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
	public ResponseEntity<?> modify(@RequestBody TripPlanBoardDto boardDto) {
		logger.debug("modify boardDto : {}", boardDto);
		try {
			int resultArticle = tripPlanBoardService.modifyArticle(boardDto);
			if (resultArticle == 1) {
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
			
			int result  = tripPlanBoardService.deleteArticle(articleNo);
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
	
	@GetMapping(value = "/favorite")
	public ResponseEntity<?> getFavorite(@RequestParam int articleNo, @RequestParam String userId){
		try {
			FavoriteDto dto = new FavoriteDto(articleNo, userId);
			Boolean result  = tripPlanBoardService.getFavorite(dto);
			logger.debug("Favorite :{}", result);
			if(result) {
				resultMessage.setResultSuccess();
				return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping(value = "/favorite")
	public ResponseEntity<?> addFavorite(@RequestBody FavoriteDto dto){
		logger.debug("add Favorite : {}", dto);
		try {
			
			int result  = tripPlanBoardService.addFavorite(dto);
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

	@DeleteMapping(value = "/favorite")
	public ResponseEntity<?> cancelFavorite(@RequestBody FavoriteDto dto){
		logger.debug("cancel Favorite : {}", dto);
		try {
			
			int result  = tripPlanBoardService.cancelFavorite(dto);
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
