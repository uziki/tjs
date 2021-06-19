package com.tsystems.javaschool.web;

import com.tsystems.javaschool.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.tsystems.javaschool.web.SecurityUtil.setAuthUserName;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = NotFoundException.class)
    public String notFoundExceptionHandler(HttpServletRequest request, Exception e, Model model) {
        log.error("Exception at request " + request.getRequestURL(), e);
        model.addAttribute("exception", e);
        setAuthUserName(model);
        return "errors/error404";
    }

    @ExceptionHandler
    public String defaultExceptionHandler(HttpServletRequest request, Exception e, Model model) {
        log.error("Exception at request " + request.getRequestURL(), e);
        model.addAttribute("exception", e);
        setAuthUserName(model);
        return "errors/exception";
    }
}
