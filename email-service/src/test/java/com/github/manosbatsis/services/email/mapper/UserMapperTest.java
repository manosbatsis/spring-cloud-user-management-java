package com.github.manosbatsis.services.email.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.manosbatsis.services.email.model.UserEvent;
import com.github.manosbatsis.services.user.messages.EventType;
import com.github.manosbatsis.services.user.messages.UserEventMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Import(UserMapperImpl.class)
@ActiveProfiles({"test"})
class UserMapperTest {

    @Autowired private UserMapper userMapper;

    @Test
    void testCreateUserEvent() {
        String eventId = UUID.randomUUID().toString();
        Date datetime = new Date();
        EventType eventType = EventType.CREATED;
        Long userId = 1L;
        String userJson = "{\"email\":\"email\",\"fullName\":\"fullName\",\"active\":true}";

        UserEventMessage userEventMessage =
                UserEventMessage.newBuilder()
                        .setEventId(eventId)
                        .setEventTimestamp(datetime.getTime())
                        .setEventType(eventType)
                        .setUserId(userId)
                        .setUserJson(userJson)
                        .build();

        UserEvent userEvent =
                userMapper.createUserEvent(MessageBuilder.withPayload(userEventMessage).build());

        assertThat(userEvent).isNotNull();
        assertThat(userEvent.getKey().getUserId()).isEqualTo(userId);
        assertThat(userEvent.getKey().getDatetime()).isEqualTo(datetime);
        assertThat(userEvent.getData()).isEqualTo(userJson);
        assertThat(userEvent.getType()).isEqualTo(eventType.name());
    }
}
