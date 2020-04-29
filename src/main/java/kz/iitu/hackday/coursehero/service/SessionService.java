package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.Session;

import java.util.Date;

public interface SessionService {
    Session getActiveSessionByToken(String token);
    Session create(String email, String token, String context);
    Session create(Session session);
    Session updateExpireDate(Long id, Date newExpireDate);
    Session updateLastUpdatedDate(Long id);
    Session closeSession(Long id);
}
