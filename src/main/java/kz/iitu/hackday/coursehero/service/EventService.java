package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.Event;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface EventService {

    Event getById(Long id);
    void like(Long id, String token);
    void dislike(Long id, String token);
    void comment(Long id, String token, String comment);
	Event create(Event event);
    Event update(Long id, Event event);

    Page<Event> getAllByParam(Map<String, String> allRequestParams);
    Page<Event> search(Map<String, String> allRequestParams);
}
