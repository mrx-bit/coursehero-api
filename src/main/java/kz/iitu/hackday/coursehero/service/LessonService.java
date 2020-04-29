package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.Lesson;

import java.util.List;

public interface LessonService {

    Lesson getById(Long id);
    void like(Long id, String token);
    void dislike(Long id, String token);
    void comment(Long id, String token, String comment);
	Lesson create(Lesson lesson);
    Lesson update(Long id, Lesson lesson);

    List<Lesson> getAllByCourseId(Long courseId);
}
