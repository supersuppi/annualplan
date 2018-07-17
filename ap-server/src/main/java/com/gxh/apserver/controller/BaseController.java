package com.gxh.apserver.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxh.apserver.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
    /**
     * The Logger for this class.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(
            Exception ex, HttpServletResponse response) {
        logger.error("Exception :", ex);
        return getErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ErrorMessage> getErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(message),
                status);
    }
}
