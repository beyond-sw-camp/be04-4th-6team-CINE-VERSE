package com.cineverse.cineversebackend.event.board.service.event;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;

import java.util.List;

public interface EventBoardService {
    void registEvent(EventBoardDTO newEvent);

    EventBoard modifyEvent(int eventId, EventBoardDTO event);

    EventBoard deleteEvent(int eventId);

    List<EventBoard> findEventList();

    EventBoardDTO findEventById(int eventId);

}
