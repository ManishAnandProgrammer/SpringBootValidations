package com.manish.mappers;

import com.manish.domains.embedded.Name;
import com.manish.dtos.requests.NameInput;
import com.manish.dtos.responses.NameResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameMapper {
    Name fromNameInputToName(NameInput nameInput);
    NameResponse fromNameToNameResponse(Name name);
}
