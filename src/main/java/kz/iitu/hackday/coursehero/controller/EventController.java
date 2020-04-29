package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.entity.Event;
import kz.iitu.hackday.coursehero.entity.EventFeedback;
import kz.iitu.hackday.coursehero.service.EventService;
import kz.iitu.hackday.coursehero.service.impl.UtilService;
import kz.iitu.hackday.coursehero.utils.constants.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private EventService eventService;
    private UtilService utilService;

    @GetMapping
    @ApiOperation(value = "Метод для получения списка всех событии (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "ordering", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query"),
    })
    public ResponseEntity<?> getAllByParam(@RequestParam Map<String, String> allRequestParams) {
        return ResponseEntity.ok(eventService.getAllByParam(allRequestParams));
    }

    @GetMapping("search")
    @ApiOperation(value = "Метод для поиска событии (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "ordering", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query"),
    })
    public ResponseEntity<?> search(Map<String, String> allRequestParams) {
        return ResponseEntity.ok(eventService.search(allRequestParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<?> like(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                  @PathVariable Long id) {
        String token = accessToken.replace("Bearer ","");
        eventService.like(id, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity<?> dislike(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                  @PathVariable Long id) {
        String token = accessToken.replace("Bearer ","");
        eventService.dislike(id, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> comment(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                     @PathVariable Long id,
                                     @RequestBody EventFeedback eventFeedback) {
        String token = accessToken.replace("Bearer ","");
        eventService.comment(id, token, eventFeedback.getText());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                    @RequestBody Event event) {
        String token = accessToken.replace("Bearer ","");
        Long authorId = utilService.getUserIdFromSessionToken(token);
        event.setAuthorId(authorId);

        return ResponseEntity.ok(eventService.create(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Event event) {
        return ResponseEntity.ok(eventService.update(id, event));
    }
}
