package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Session;
import kz.iitu.hackday.coursehero.entity.SessionState;
import kz.iitu.hackday.coursehero.entity.User;
import kz.iitu.hackday.coursehero.entity.enums.SessionStates;
import kz.iitu.hackday.coursehero.repository.SessionRepository;
import kz.iitu.hackday.coursehero.repository.SessionStateRepository;
import kz.iitu.hackday.coursehero.repository.UserRepository;
import kz.iitu.hackday.coursehero.service.SessionService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants.*;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private SessionStateRepository sessionStateRepository;
    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Override
    public Session create(String email, String token, String context) {
        log.info("creating session for user: " + email + " with token: " + token + " context: " + context);

        // deactivate all previous sessions
        sessionRepository.deactivateAllUserSessions(email,
                sessionStateRepository.findByName(SessionStates.opened).getId(),
                new Date());

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));

        Date created = new Date();
        Session session = new Session();
        session.setUserId(user.getId());
        session.setEmail(email);
        session.setToken(token);
        session.setCreated(created);
        session.setUpdated(created);
        session.setContext(context);
        session.setStateId(sessionStateRepository.findByName(SessionStates.opened).getId());

        return sessionRepository.saveAndFlush(session);
    }
  
    @Override
    public Session getActiveSessionByToken(String token) {
        return sessionRepository.getSessionByTokenAndAndStateId(token,
                sessionStateRepository.findByName(SessionStates.opened).getId());
    }

    @Override
    public Session create(Session session) {
        log.info("creating session for user: " + session.getEmail() + " with token: " + session.getToken());

        // deactivate all previous sessions
        sessionRepository.deactivateAllUserSessions(session.getEmail(),
                sessionStateRepository.findByName(SessionStates.closed).getId(),
                new Date());

        Date created = new Date();
        session.setId(null);
        session.setCreated(created);
        session.setUpdated(created);
        session.setStateId(sessionStateRepository.findByName(SessionStates.opened).getId());

        return sessionRepository.saveAndFlush(session);
    }

    @Override
    public Session updateExpireDate(Long id, Date newExpireDate) {
        log.info("SessionServiceImpl.updateExpireDate: " + id + ". newExpireDate: " + newExpireDate);

        Optional<Session> existing = sessionRepository.findById(id);
        Session dbSession = existing.orElseThrow(
                () -> new NoContentFoundException(DataNotFound.MESSAGE, DataNotFound.ERROR_CODE)
        );

        dbSession.setUpdated(newExpireDate);

        return sessionRepository.saveAndFlush(dbSession);
    }

    @Override
    public Session updateLastUpdatedDate(Long id) {
        log.info("SessionServiceImpl.updateExpireDate: " + id);

        Optional<Session> existing = sessionRepository.findById(id);
        Session dbSession = existing.orElseThrow(
                () -> new NoContentFoundException(DataNotFound.MESSAGE, DataNotFound.ERROR_CODE)
        );

        dbSession.setUpdated(new Date());

        return sessionRepository.saveAndFlush(dbSession);
    }

    @Override
    public Session closeSession(Long id) {
        log.info("SessionServiceImpl.closeSession: " + id);

        Optional<Session> existing = sessionRepository.findById(id);
        Session dbSession = existing.orElse(null);

        SessionState closed = sessionStateRepository.findByName(SessionStates.closed);
        if (dbSession != null && dbSession.getStateId() != closed.getId()) {
            // if session exists and is not closed yet
            dbSession.setStateId(closed.getId());
            dbSession.setClosed(new Date());
        }

        return sessionRepository.saveAndFlush(dbSession);
    }
}
