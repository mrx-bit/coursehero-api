package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.Poll;

import java.util.List;

public interface PollService {

    Poll getById(Long id);
	Poll create(Poll lesson);
    Poll update(Long id, Poll lesson);

    List<Poll> getAllActive();
}
