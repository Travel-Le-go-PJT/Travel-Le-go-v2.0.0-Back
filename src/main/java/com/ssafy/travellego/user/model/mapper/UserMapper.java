package com.ssafy.travellego.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.travellego.user.model.UserDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Mapper
public interface UserMapper {
	int idCheck(String userId) throws SQLException;
	int joinUser(UserDto userDto) throws SQLException;
	UserDto loginUser(UserDto userDto) throws SQLException;
	List<UserDto> userList();
	int deleteUser(String userId);
	UserDto getUser(String userId);
	int modifyUser(UserDto userDto);
	void saveRefreshToken(Map<String, String> map);
	Object getRefreshToken(String userId);
	void deleteRefreshToken(Map<String, String> map);
}
