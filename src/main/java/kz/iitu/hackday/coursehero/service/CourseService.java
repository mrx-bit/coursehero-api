package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.Course;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface CourseService {

    Course getById(Long id);
    void like(Long id, String token);
    void dislike(Long id, String token);
    void comment(Long id, String token, String comment);
	Course create(Course course);
    Course update(Long id, Course course);

    Page<Course> getAllByParam(Map<String, String> allRequestParams);
    Page<Course> search(Map<String, String> allRequestParams);
}
