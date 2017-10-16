package edu.mum.se.poseidon.web.mapper;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.models.User;
import edu.mum.se.poseidon.web.services.dto.UserDto;

@Component
public class UserMapper {

	public User getUser(UserDto userDto) {
		if(userDto == null) return null;
		User user = new User();
		user.setId(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setType(userDto.getType());
		return user;
	}
	
	public UserDto getUserDto(User user) {
		if(user == null) return null;
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setType(user.getType());
		return userDto;
	}
}
