package com.github.manosbatsis.services.event.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Date;

public record UserEventResponse(
        Long userId,
        @JsonFormat(
                        shape = JsonFormat.Shape.STRING,
                        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                        timezone = "UTC")
                Date datetime,
        String type,
        String data) {

    @JsonGetter("id")
    public String getId() {
        return userId + "-" + datetime.getTime();
    }
}
