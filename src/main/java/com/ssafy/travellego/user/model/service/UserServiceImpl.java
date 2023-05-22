package com.ssafy.travellego.user.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travellego.user.model.UserDto;
import com.ssafy.travellego.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;
	@Autowired
	public UserServiceImpl(UserMapper userMapper) throws NoSuchAlgorithmException {
		super();
		this.userMapper = userMapper;
	}


	@Override
	public boolean joinUser(UserDto userDto) throws Exception {
		// TODO 삭제한 회원중 같은 아이디를 가진 계정이 있는지 확인
		//있다면 덮어쓰기 update
		//없다면 추가 insert
		return userMapper.joinUser(userDto) == 1;
	}
	
	@Override
	public boolean updateJoinUser(UserDto userDto) {
		return userMapper.updateJoinUser(userDto) == 1;
	}
	
	
	@Override
	public boolean iSDuplicate(String userId) {
		int cntresult = userMapper.iSDuplicate(userId);
		if(cntresult >= 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public UserDto login(UserDto user) throws Exception {
		return userMapper.loginUser(user);
	}
	
	@Override
	public List<UserDto> userList() throws Exception {
		return userMapper.userList();
	}
	@Override
	public UserDto getUser(String userId) {
		return userMapper.getUser(userId);
	}

	@Override
	public boolean modifyUser(UserDto dto) {
		return userMapper.modifyUser(dto) == 1;
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}


	@Override
	public void userWithdraw(String userId) {
		// 탈퇴한 회원으로 상태 변경 user_Role = 0 으로 변경
		userMapper.userWithdraw(userId);
		
		//탈퇴한 회원이 누른 좋아요 삭제
		userMapper.deleteFavorite(userId);
		
		// 탈퇴한 회원이 작성한 글들의 작성자를 none 으로 변경
		
		userMapper.changeFromInfoToNone(userId);
		userMapper.changeFromPlanToNone(userId);
	}


	@Override
	public boolean checkUserWithdraw(String userId) {
		if(userMapper.checkRole(userId) == 0 && userMapper.countInfoAricle(userId) == 0&&userMapper.countPlanAricle(userId)==0) {
			return true;
		}
		return false;
	}


	

}
