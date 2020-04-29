package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Session;
import kz.iitu.hackday.coursehero.service.SessionService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.constants.PageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.UnauthorisedException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UtilService {

    private SessionService sessionService;

    public Long getUserIdFromSessionToken(String token) {
        Session session = sessionService.getActiveSessionByToken(token);

        if (session == null) {
            SecurityContextHolder.clearContext();
            throw new UnauthorisedException(ErrorMessageConstants.UnauthorizedError.MESSAGE,
                    ErrorMessageConstants.UnauthorizedError.ERROR_CODE);
        }

        return session.getUserId();
    }

    protected PageRequest initPageRequest(Map<String, String> allRequestParams){
        int pageNumber = PageConstants.DEFAUT_PAGE_NUMBER_0;
        int pageSize = PageConstants.DEFAUT_PAGE_SIZE;

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        if (allRequestParams.containsKey("ordering")) {
            sort = allRequestParams.get("ordering").charAt(0) != '-'
                    ? Sort.by(Sort.Direction.ASC, allRequestParams.get("ordering"))
                    : Sort.by(Sort.Direction.DESC, allRequestParams.get("ordering").substring(1));
        }

        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("pageSize")) {
            pageSize = Integer.parseInt(allRequestParams.get("pageSize"));
        }
        return PageRequest.of(pageNumber > 0 ? (pageNumber - 1) : pageNumber, pageSize, sort);
    }
}
