package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.LessonAssignment;
import kz.iitu.hackday.coursehero.repository.LessonAssignmentRepository;
import kz.iitu.hackday.coursehero.service.LessonAssignmentService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class LessonAssignmentServiceImpl implements LessonAssignmentService {

    private LessonAssignmentRepository lessonRepository;

    @Override
    public LessonAssignment getById(Long id) {
        log.info("LessonAssignmentServiceImpl.getById id " + id);
        return lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public LessonAssignment create(LessonAssignment lesson) {
        lesson.setId(null);
        return lessonRepository.saveAndFlush(lesson);
    }

    @Override
    public LessonAssignment update(Long id, LessonAssignment lesson) {
        log.info(String.format("LessonAssignmentServiceImpl.update: %d. LessonAssignment {}: %s", id, lesson));

        LessonAssignment dbLessonAssignment = lessonRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbLessonAssignment.setOrderVal(lesson.getOrderVal());
        dbLessonAssignment.setRawText(lesson.getRawText());

        return lessonRepository.saveAndFlush(dbLessonAssignment);
    }

    @Override
    public List<LessonAssignment> getAllByLessonId(Long lessonId) {

        return lessonRepository.findAllByLessonIdAndIsActiveTrueOrderByOrderVal(lessonId);
    }

}
