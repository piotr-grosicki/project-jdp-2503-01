package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.isBlocked(),
                new ArrayList<>()
        );
    }
    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.isBlocked()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}


