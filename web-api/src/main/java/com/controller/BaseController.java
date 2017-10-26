package com.controller;

import com.commons.web.GeneralResponse;
import com.commons.web.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author pengqingsong
 * @date 28/02/2017 18:23
 */
@Slf4j
public class BaseController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @ExceptionHandler(Throwable.class)
    public GeneralResponse exception(Exception e) {
        log.error(e.getMessage(), e);
        return GeneralResponse.unknownExceptionResponse(e);
    }

    public long getRequestedUserId() {
        return HttpHeaders.getHeaderUid(request);
    }
}
