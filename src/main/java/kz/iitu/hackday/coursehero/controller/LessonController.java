package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.entity.Lesson;
import kz.iitu.hackday.coursehero.entity.LessonFeedback;
import kz.iitu.hackday.coursehero.service.LessonService;
import kz.iitu.hackday.coursehero.utils.constants.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course/lesson")
@AllArgsConstructor
public class LessonController {

    private LessonService lessonService;

    @GetMapping
    @ApiOperation(value = "Метод для получения списка всех курсов (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", dataType = "long", value = "Идентификатор курса", paramType = "query"),
    })
    public ResponseEntity<?> getAllByCourseId(@RequestParam Long courseId) {
        return ResponseEntity.ok(lessonService.getAllByCourseId(courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<?> like(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                  @PathVariable Long id) {
        String token = accessToken.replace("Bearer ","");
        lessonService.like(id, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity<?> dislike(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                  @PathVariable Long id) {
        String token = accessToken.replace("Bearer ","");
        lessonService.dislike(id, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> comment(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                     @PathVariable Long id,
                                     @RequestBody LessonFeedback lessonFeedback) {
        String token = accessToken.replace("Bearer ","");
        lessonService.comment(id, token, lessonFeedback.getText());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.create(lesson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.update(id, lesson));
    }
}
