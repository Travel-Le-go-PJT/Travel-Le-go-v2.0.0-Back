package com.ssafy.travellego.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travellego.encryption.service.EncryptionService;
import com.ssafy.travellego.user.model.UserDto;
import com.ssafy.travellego.user.model.service.JwtServiceImpl;
import com.ssafy.travellego.user.model.service.UserService;
import com.ssafy.travellego.util.ResultMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api("User Controller V1")
public class UserController extends HttpServlet {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	private EncryptionService encService;
	private ResultMessage resultMessage = new ResultMessage();

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	public UserController(UserService userService, EncryptionService encService) {
		super();
		this.userService = userService;
		this.encService = encService;

	}

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto dto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		dto.setUserPwd(encService.getEncryptedPw(dto.getUserPwd()));
		try {
			UserDto login = userService.login(dto);
			if (login != null && login.getuserRole() != 0) {
				// access, refresh token 발급
				String accessToken = jwtService.createAccessToken("userId", login.getUserId());
				String refreshToken = jwtService.createRefreshToken("userId", login.getUserId());

				// refresh token을 DB에 저장
				userService.saveRefreshToken(dto.getUserId(), refreshToken);

				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);

				// 응답 설정
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "FAIL");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId,
			HttpServletRequest request) {
		logger.debug("getInfo - userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.getUser(userId);
				logger.debug("getInfo - userId : {} ", userId);
				logger.debug("getInfo - userId : {} ", userDto);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "FAIL");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	@PutMapping
	public ResponseEntity<?> modifyUser(@RequestBody UserDto dto) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			UserDto userDto = userService.getUser(dto.getUserId());
			logger.debug("원래 디비에 저장된 비번 : "+userDto.getUserPwd());
			logger.debug("날라온 암호화 되기  비번 : "+dto.getUserPwd());
			if(!userDto.getUserPwd().equals(dto.getUserPwd())) {
				dto.setUserPwd(encService.getEncryptedPw(dto.getUserPwd()));
			}
			HttpStatus status = HttpStatus.UNAUTHORIZED;
			if (userService.modifyUser(dto)) {
				userDto = userService.getUser(dto.getUserId());
				resultMap.put("userInfo", userDto);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "FAIL");
				status = HttpStatus.UNAUTHORIZED;
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userId);
			resultMap.put("message", "SUCCESS");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, userDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserId()))) {
				String accessToken = jwtService.createAccessToken("userId", userDto.getUserId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody UserDto dto) {
		dto.setUserPwd(encService.getEncryptedPw(dto.getUserPwd()));
		try {
			if (!dto.getUserId().equals("알수없음")) {  // 사용자가 아이디를 "알수 없음"외의 아이디를  입력한 경우
				if (!userService.iSDuplicate(dto.getUserId())) { // 사용자가 중복된 아이디를 입력하지 않은 경우
					if (userService.joinUser(dto)) { //회원가입이 성공한 경우
						resultMessage.setResultSuccess();
						return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
					} else {  // 회원가입이 실패된 경우
						resultMessage.setResultFail();
						return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.NO_CONTENT);
					}
				}else { //사용자가 중복된 아이디를 입력한 경우 
					if(userService.getUser(dto.getUserId()).getuserRole() == 0) { //그 아이디의 계정 상태가 유효한계정인지 탈퇴한 계정인지 판별
						if(userService.modifyUser(dto)) {//회원가입이 성공한 경우
							resultMessage.setResultSuccess();
							return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
						} else {  // 회원가입이 실패된 경우
							resultMessage.setResultFail();
							return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.NO_CONTENT);
						}
					}
					else{
						resultMessage.setResult("DUPLICATE");
						return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
					}
				}
			} else {
				resultMessage.setResult("알수없음 DUPLICATE");
				return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
			}

			// session.setAttribute("msg", null);
		} catch (Exception e) {
			return exceptionHandling(e);
			// session.setAttribute("msg", "회원가입 중 에러 발생!!!");
		}
	}

	@GetMapping("/idcheck/{userId}")
	public ResponseEntity<?> iSDuplicate(@PathVariable("userId") String userId, HttpServletRequest request) throws Exception {
		logger.debug("getInfo - userId : {} ", userId);
		if (!userId.equals("알수없음") && !userService.iSDuplicate(userId)) {
			logger.debug("SUCCESS {} ", userId);
			resultMessage.setResultSuccess();
			return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
		} else {
			if(userService.getUser(userId).getuserRole() == 0) { //그 아이디의 계정 상태가 유효한계정인지 탈퇴한 계정인지 판별
				resultMessage.setResultSuccess();
				return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
			}
			resultMessage.setResult("DUPLICATE");
			logger.debug("DUPLICATE {} ", userId);
			return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> userWithdraw(@PathVariable String userId) {
		try {
			userService.userWithdraw(userId);
			// TODO 알맞은 조건 써서 제대로 탈퇴상태로 바꼈는지 확인
			// user role 이 0인지 확인 , 글중에 그 userid 가 있는지 확인
			if (userService.checkUserWithdraw(userId)) {
				resultMessage.setResultSuccess();
				return new ResponseEntity<ResultMessage>(resultMessage, HttpStatus.OK);
			} else {
				resultMessage.setResultFail();
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/count/{userId}")
	public ResponseEntity<?> getCount(@PathVariable String userId) {
		try {
			int count = userService.getCount(userId);
			if (count >=0) {
				return new ResponseEntity<Object>(count, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping
	public ResponseEntity<?> manageMem() {
		try {
			List<UserDto> list = userService.userList();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getMem(@PathVariable String userId) {
		UserDto dto;
		try {
			dto = userService.getUser(userId);
			if (dto != null) {
				return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
