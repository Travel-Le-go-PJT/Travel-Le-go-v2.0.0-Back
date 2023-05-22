package com.ssafy.travellego.user.model.service;

import java.util.List;

import com.ssafy.travellego.user.model.UserDto;


public interface UserService {

	boolean joinUser(UserDto userDto) throws Exception;
	//MemberDto loginMember(String userId, String userPwd) throws Exception;
	UserDto login(UserDto userDto) throws Exception ;
	List<UserDto> userList() throws Exception;
    UserDto getUser(String userId) throws Exception;
    boolean modifyUser(UserDto userDto) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	boolean iSDuplicate(String userId);
	void userWithdraw(String userId);
	boolean checkUserWithdraw(String userId);
	boolean updateJoinUser(UserDto dto);
}
