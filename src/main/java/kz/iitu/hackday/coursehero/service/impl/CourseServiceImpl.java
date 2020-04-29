package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Course;
import kz.iitu.hackday.coursehero.entity.CourseFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import kz.iitu.hackday.coursehero.repository.CourseFeedbackRepository;
import kz.iitu.hackday.coursehero.repository.CourseRepository;
import kz.iitu.hackday.coursehero.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CourseFeedbackRepository feedbackRepository;
    private UtilService utilService;

    @Override
    public Course getById(Long id) {
        log.info("CourseServiceImpl.getById id " + id);
        return courseRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public void like(Long id, String token) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Course dbCourse = courseRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbCourse.setLikesCount(dbCourse.getLikesCount() + 1);
        courseRepository.saveAndFlush(dbCourse);

        CourseFeedback feedback = CourseFeedback.builder()
                .courseId(id)
                .userId(userId)
                .type(FeedbackType.LIKE)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void dislike(Long id, String token) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Course dbCourse = courseRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbCourse.setDislikesCount(dbCourse.getDislikesCount() + 1);
        courseRepository.saveAndFlush(dbCourse);

        CourseFeedback feedback = CourseFeedback.builder()
                .courseId(id)
                .userId(userId)
                .type(FeedbackType.DISLIKE)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void comment(Long id, String token, String comment) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Course dbCourse = courseRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        CourseFeedback feedback = CourseFeedback.builder()
                .courseId(dbCourse.getId())
                .userId(userId)
                .text(comment)
                .type(FeedbackType.COMMENT)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public Course create(Course course) {
        course.setId(null);
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public Course update(Long id, Course course) {
        log.info(String.format("CourseServiceImpl.update: %d. Course {}: %s", id, course));

        Course dbCourse = courseRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbCourse.setTitle(course.getTitle());
        dbCourse.setShortRawDescription(course.getShortRawDescription());
        dbCourse.setFullRawDescription(course.getFullRawDescription());
        dbCourse.setPrice(course.getPrice());

        return courseRepository.saveAndFlush(dbCourse);
    }

    @Override
    public Page<Course> getAllByParam(Map<String, String> allRequestParams) {
        PageRequest pageRequest = utilService.initPageRequest(allRequestParams);

        return courseRepository.findAllByIsActiveTrue(pageRequest);
    }

    @Override
    public Page<Course> search(Map<String, String> allRequestParams) {
        log.info("search search search" + allRequestParams.toString());
        PageRequest pageRequest = utilService.initPageRequest(allRequestParams);

        return courseRepository.search(allRequestParams.get("searchString").trim(), pageRequest);
    }
}
