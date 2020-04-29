package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.entity.LessonAssignment;
import kz.iitu.hackday.coursehero.service.LessonAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course/lesson/assignment")
@AllArgsConstructor
public class LessonAssignmentController {

    private LessonAssignmentService lessonAssignmentService;

    @GetMapping
    @ApiOperation(value = "Метод для получения списка всех курсов (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", dataType = "long", value = "Идентификатор курса", paramType = "query"),
    })
    public ResponseEntity<?> getAllActive(@RequestParam Long lessonId) {
        return ResponseEntity.ok(lessonAssignmentService.getAllByLessonId(lessonId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonAssignmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LessonAssignment lessonAssignment) {
        return ResponseEntity.ok(lessonAssignmentService.create(lessonAssignment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody LessonAssignment lessonAssignment) {
        return ResponseEntity.ok(lessonAssignmentService.update(id, lessonAssignment));
    }
}
