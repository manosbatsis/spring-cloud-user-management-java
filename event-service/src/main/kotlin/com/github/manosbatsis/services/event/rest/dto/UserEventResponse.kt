package com.github.manosbatsis.services.event.rest.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class UserEventResponse(
    val userId: Long,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        timezone = "UTC",
    )
    val datetime: Date,
    val type: String,
    val data: String,
) {
    val id: String = userId.toString() + "-" + datetime.time
}
