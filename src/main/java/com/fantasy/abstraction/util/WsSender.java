package com.fantasy.abstraction.util;


import com.fantasy.abstraction.dto.WsEventDto;
import com.fantasy.abstraction.enums.EventType;
import com.fantasy.abstraction.enums.ObjectType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class WsSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter writer = mapper.setConfig(mapper.getSerializationConfig())
                .writerWithView(view);
        return (EventType eventType, T payLoad) -> {
            String value;
            try {
                value = writer.writeValueAsString(payLoad);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            template.convertAndSend("/topic/activity",
                    new WsEventDto(objectType, eventType, value));
        };
    }
}
