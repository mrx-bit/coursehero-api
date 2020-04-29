package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.News;
import kz.iitu.hackday.coursehero.entity.NewsFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import kz.iitu.hackday.coursehero.repository.NewsFeedbackRepository;
import kz.iitu.hackday.coursehero.repository.NewsRepository;
import kz.iitu.hackday.coursehero.service.NewsService;
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
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;
    private NewsFeedbackRepository feedbackRepository;
    private UtilService utilService;

    @Override
    public News getById(Long id) {
        log.info("NewsServiceImpl.getById id " + id);
        return newsRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public void comment(Long id, String token, String comment) {
        Long userId = utilService.getUserIdFromSessionToken(token);
        
        News dbNews = newsRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        NewsFeedback feedback = NewsFeedback.builder()
                .newsId(dbNews.getId())
                .userId(userId)
                .text(comment)
                .type(FeedbackType.COMMENT)
                .build();
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public News create(News news) {
        news.setId(null);
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News update(Long id, News news) {
        log.info(String.format("NewsServiceImpl.update: %d. News {}: %s", id, news));

        News dbNews = newsRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbNews.setTitle(news.getTitle());
        dbNews.setShortRawDescription(news.getShortRawDescription());
        dbNews.setFullRawDescription(news.getFullRawDescription());

        return newsRepository.saveAndFlush(dbNews);
    }

    @Override
    public Page<News> getAllByParam(Map<String, String> allRequestParams) {
        PageRequest pageRequest = utilService.initPageRequest(allRequestParams);

        return newsRepository.findAllByIsActiveTrue(pageRequest);
    }

    @Override
    public Page<News> search(Map<String, String> allRequestParams) {
        log.info("search search search" + allRequestParams.toString());
        PageRequest pageRequest = utilService.initPageRequest(allRequestParams);

        return newsRepository.search(allRequestParams.get("searchString").trim(), pageRequest);
    }
}
