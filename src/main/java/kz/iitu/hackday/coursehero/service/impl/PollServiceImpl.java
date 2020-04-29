package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Poll;
import kz.iitu.hackday.coursehero.repository.PollRepository;
import kz.iitu.hackday.coursehero.service.PollService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PollServiceImpl implements PollService {

    private PollRepository pollRepository;

    @Override
    public Poll getById(Long id) {
        log.info("PollServiceImpl.getById id " + id);
        return pollRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Poll create(Poll poll) {
        poll.setId(null);
        return pollRepository.saveAndFlush(poll);
    }

    @Override
    public Poll update(Long id, Poll poll) {
        log.info(String.format("PollServiceImpl.update: %d. Poll {}: %s", id, poll));

        Poll dbPoll = pollRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbPoll.setText(poll.getText());
        dbPoll.setOrderVal(poll.getOrderVal());

        return pollRepository.saveAndFlush(dbPoll);
    }

    @Override
    public List<Poll> getAllActive() {

        return pollRepository.findAllByIsActiveTrueOrderByOrderVal();
    }

}
