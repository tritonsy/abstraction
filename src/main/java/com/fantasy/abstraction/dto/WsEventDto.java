package com.fantasy.abstraction.dto;

import com.fantasy.abstraction.entity.Views;
import com.fantasy.abstraction.enums.EventType;
import com.fantasy.abstraction.enums.ObjectType;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;
}
