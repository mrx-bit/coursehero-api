package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.News;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface NewsService {

    News getById(Long id);
    void comment(Long id, String token, String comment);
	News create(News news);
    News update(Long id, News news);

    Page<News> getAllByParam(Map<String, String> allRequestParams);
    Page<News> search(Map<String, String> allRequestParams);
}
