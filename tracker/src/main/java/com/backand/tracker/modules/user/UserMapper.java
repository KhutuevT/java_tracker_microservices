package com.backand.tracker.modules.user;

import com.backand.tracker.modules.user.dto.res.UserDto;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

    private final ModelMapper mapper;

    @Autowired
    public UserMapper(
            ModelMapper mapper
    ) {
        super(User.class, UserDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(User.class, UserDto.class);
        mapper.createTypeMap(UserDto.class, User.class);
    }
}
