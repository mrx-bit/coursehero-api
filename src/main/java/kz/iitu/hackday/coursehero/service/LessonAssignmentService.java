package kz.iitu.hackday.coursehero.service;

import kz.iitu.hackday.coursehero.entity.LessonAssignment;

import java.util.List;

public interface LessonAssignmentService {

    LessonAssignment getById(Long id);
	LessonAssignment create(LessonAssignment lessonAssignment);
    LessonAssignment update(Long id, LessonAssignment lessonAssignment);

    List<LessonAssignment> getAllByLessonId(Long lessonId);
}
