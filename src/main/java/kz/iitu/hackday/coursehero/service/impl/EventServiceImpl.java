package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Event;
import kz.iitu.hackday.coursehero.entity.EventFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import kz.iitu.hackday.coursehero.repository.EventFeedbackRepository;
import kz.iitu.hackday.coursehero.repository.EventRepository;
import kz.iitu.hackday.coursehero.service.EventService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private EventFeedbackRepository feedbackRepository;
    private UtilService utilService;

    @Override
    public Event getById(Long id) {
        log.info("EventServiceImpl.getById id " + id);
        return eventRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public void like(Long id, String token) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Event dbEvent = eventRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbEvent.setLikesCount(dbEvent.getLikesCount() + 1);
        eventRepository.saveAndFlush(dbEvent);

        EventFeedback feedback = EventFeedback.builder()
                .eventId(id)
                .userId(userId)
                .type(FeedbackType.LIKE)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void dislike(Long id, String token) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Event dbEvent = eventRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbEvent.setDislikesCount(dbEvent.getDislikesCount() + 1);
        eventRepository.saveAndFlush(dbEvent);

        EventFeedback feedback = EventFeedback.builder()
                .eventId(id)
                .userId(userId)
                .type(FeedbackType.DISLIKE)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void comment(Long id, String token, String comment) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Event dbEvent = eventRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        EventFeedback feedback = EventFeedback.builder()
                .eventId(dbEvent.getId())
                .userId(userId)
                .text(comment)
                .type(FeedbackType.COMMENT)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public Event create(Event event) {
        event.setId(null);
        return eventRepository.saveAndFlush(event);
    }

    @Override
    public Event update(Long id, Event event) {
        log.info(String.format("EventServiceImpl.update: %d. Event {}: %s", id, event));

        Event dbEvent = eventRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbEvent.setTitle(event.getTitle());
        dbEvent.setStartDate(event.getStartDate());
        dbEvent.setEndDate(event.getEndDate());
        dbEvent.setShortRawDescription(event.getShortRawDescription());
        dbEvent.setFullRawDescription(event.getFullRawDescription());
        dbEvent.setPrice(event.getPrice());

        return eventRepository.saveAndFlush(dbEvent);
    }

    @Override
    public Page<Event> getAllByParam(Map<String, String> allRequestParams) {
        PageRequest pageRequest = utilService.initPageRequest(allRequestParams);

        return eventRepository.findAllByIsActiveTrue(pageRequest);
    }

    @Override
    public Page<Event> search(Map<String, String> allRequestParams) {
        log.info("search search search" + allRequestParams.toString());
        PageRequest pageRequest = utilService.initPageRequest(allRequestParams);

        return eventRepository.search(allRequestParams.get("searchString").trim(), pageRequest);
    }
}
