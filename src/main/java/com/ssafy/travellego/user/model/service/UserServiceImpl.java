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
		return userMapper.joinUser(userDto) == 1;
	}
	
	@Override
	public boolean IdDuplicateCheck(String userId) {
		int cntresult = userMapper.IdDuplicateCheck(userId);
		if(cntresult >= 1) {
			return false;
		}
		return true;
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
	public boolean delete(String userId) {
		return userMapper.deleteUser(userId) >= 1;
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

}
