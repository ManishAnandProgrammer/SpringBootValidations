package com.manish.mapper;

import com.manish.domain.embedded.Name;
import com.manish.dto.request.NameInput;
import com.manish.dto.response.NameResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameMapper {
    Name from(NameInput nameInput);
    NameResponse from(Name name);
}