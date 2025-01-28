package com.manish.mappers;

import com.manish.domains.embedded.Name;
import com.manish.dtos.requests.NameInput;
import com.manish.dtos.responses.NameResponse;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {

    public Name fromNameInputToName(NameInput nameInput) {
        Name name = new Name();
        name.setFirst(nameInput.getFirst());
        name.setMiddle(nameInput.getMiddle());
        name.setLast(nameInput.getLast());
        return name;
    }

    public NameResponse fromNameToNameResponse(Name name) {
        NameResponse nameResponse = new NameResponse();
        nameResponse.setFirst(name.getFirst());
        nameResponse.setMiddle(name.getMiddle());
        nameResponse.setLast(name.getLast());
        return nameResponse;
    }

}
