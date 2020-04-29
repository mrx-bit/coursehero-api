package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.entity.Poll;
import kz.iitu.hackday.coursehero.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/poll")
@AllArgsConstructor
public class PollController {

    private PollService pollService;

    @GetMapping
    @ApiOperation(value = "Метод для получения списка всех курсов (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", dataType = "long", value = "Идентификатор курса", paramType = "query"),
    })
    public ResponseEntity<?> getAllActive() {
        return ResponseEntity.ok(pollService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pollService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Poll poll) {
        return ResponseEntity.ok(pollService.create(poll));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody Poll poll) {
        return ResponseEntity.ok(pollService.update(id, poll));
    }
}
