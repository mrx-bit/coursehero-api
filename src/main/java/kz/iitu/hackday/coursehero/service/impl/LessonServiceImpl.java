package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Lesson;
import kz.iitu.hackday.coursehero.entity.LessonFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import kz.iitu.hackday.coursehero.repository.LessonFeedbackRepository;
import kz.iitu.hackday.coursehero.repository.LessonRepository;
import kz.iitu.hackday.coursehero.service.LessonService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;
    private LessonFeedbackRepository feedbackRepository;
    private UtilService utilService;

    @Override
    public Lesson getById(Long id) {
        log.info("LessonServiceImpl.getById id " + id);
        return lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public void like(Long id, String token) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Lesson dbLesson = lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbLesson.setLikesCount(dbLesson.getLikesCount() + 1);
        lessonRepository.saveAndFlush(dbLesson);

        LessonFeedback feedback = LessonFeedback.builder()
                .lessonId(id)
                .userId(userId)
                .type(FeedbackType.LIKE)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void dislike(Long id, String token) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Lesson dbLesson = lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbLesson.setDislikesCount(dbLesson.getDislikesCount() + 1);
        lessonRepository.saveAndFlush(dbLesson);

        LessonFeedback feedback = LessonFeedback.builder()
                .lessonId(id)
                .userId(userId)
                .type(FeedbackType.DISLIKE)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void comment(Long id, String token, String comment) {
        Long userId = utilService.getUserIdFromSessionToken(token);

        Lesson dbLesson = lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        LessonFeedback feedback = LessonFeedback.builder()
                .lessonId(dbLesson.getId())
                .userId(userId)
                .text(comment)
                .type(FeedbackType.COMMENT)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public Lesson create(Lesson lesson) {
        lesson.setId(null);
        return lessonRepository.saveAndFlush(lesson);
    }

    @Override
    public Lesson update(Long id, Lesson lesson) {
        log.info(String.format("LessonServiceImpl.update: %d. Lesson {}: %s", id, lesson));

        Lesson dbLesson = lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbLesson.setTitle(lesson.getTitle());
        dbLesson.setRawText(lesson.getRawText());

        return lessonRepository.saveAndFlush(dbLesson);
    }

    @Override
    public List<Lesson> getAllByCourseId(Long courseId) {

        return lessonRepository.findAllByCourseIdAndIsActiveTrueOrderByOrderVal(courseId);
    }

}
