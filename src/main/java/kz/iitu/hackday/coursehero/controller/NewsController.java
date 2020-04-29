package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.entity.News;
import kz.iitu.hackday.coursehero.entity.NewsFeedback;
import kz.iitu.hackday.coursehero.service.NewsService;
import kz.iitu.hackday.coursehero.service.impl.UtilService;
import kz.iitu.hackday.coursehero.utils.constants.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;
    private UtilService utilService;

    @GetMapping
    @ApiOperation(value = "Метод для получения списка всех новостей (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "ordering", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query"),
    })
    public ResponseEntity<?> getAllByParam(@RequestParam Map<String, String> allRequestParams) {
        return ResponseEntity.ok(newsService.getAllByParam(allRequestParams));
    }

    @GetMapping("search")
    @ApiOperation(value = "Метод для поиска новостей (with pagination and params)", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "ordering", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query"),
    })
    public ResponseEntity<?> search(Map<String, String> allRequestParams) {
        return ResponseEntity.ok(newsService.search(allRequestParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> comment(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                     @PathVariable Long id,
                                     @RequestBody NewsFeedback newsFeedback) {
        String token = accessToken.replace("Bearer ","");
        newsService.comment(id, token, newsFeedback.getText());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME) final String accessToken,
                                    @RequestBody News news) {
        String token = accessToken.replace("Bearer ","");
        Long authorId = utilService.getUserIdFromSessionToken(token);
        news.setAuthorId(authorId);

        return ResponseEntity.ok(newsService.create(news));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody News news) {
        return ResponseEntity.ok(newsService.update(id, news));
    }
}
