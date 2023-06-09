package com.github.manosbatsis.services.event.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.github.manosbatsis.services.event.model.UserEvent;
import com.github.manosbatsis.services.event.model.UserEventKey;
import com.github.manosbatsis.services.event.repository.UserEventRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Import(UserEventServiceImpl.class)
class UserEventServiceImplTest {

    @Autowired private UserEventService userEventService;

    @MockBean private UserEventRepository userEventRepository;

    @Test
    void testGetUserEventsWhenThereIsNone() {
        given(userEventRepository.findByKeyUserId(anyLong())).willReturn(Collections.emptyList());

        List<UserEvent> userEvents = userEventService.findByUserId(1L);

        assertThat(userEvents).isNotNull();
        assertThat(userEvents).isEmpty();
    }

    @Test
    void testGetUserEventsWhenThereIsOne() {
        UserEvent userEvent = getDefaultUserEvent();
        given(userEventRepository.findByKeyUserId(anyLong()))
                .willReturn(Collections.singletonList(userEvent));

        List<UserEvent> userEvents = userEventService.findByUserId(1L);

        assertThat(userEvents).isNotNull();
        assertThat(userEvents.size()).isEqualTo(1);
        assertThat(userEvents.get(0)).isEqualTo(userEvent);
    }

    @Test
    void testSaveUserEvent() {
        UserEvent userEvent = getDefaultUserEvent();
        given(userEventRepository.save(any(UserEvent.class))).willReturn(userEvent);

        UserEvent userEventSaved = userEventService.save(userEvent);

        assertThat(userEventSaved).isNotNull();
        assertThat(userEventSaved).isEqualTo(userEvent);
    }

    private UserEvent getDefaultUserEvent() {
        return new UserEvent(new UserEventKey(1L, new Date()), "type", "data");
    }
}
