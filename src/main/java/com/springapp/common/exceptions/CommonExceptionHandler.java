package com.springapp.common.exceptions;

import com.alibaba.fastjson.JSON;
import com.springapp.common.application.ApplicationGlobalNames;
import com.springapp.common.busilog.service.SyncLogService;
import com.springapp.common.util.AuthIDUtils;
import com.springapp.common.util.IpAddressUtil;
import com.springapp.mvc.vo.DataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

@ControllerAdvice
public class CommonExceptionHandler {

    @Autowired
    SyncLogService syncLogService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(HttpServletRequest request,
                                           HttpServletResponse response, ApplicationException e){
        String message = e.getMessage();
        logger.info("==>errCode:" + e.getCode() + " errMsg:" + e.getMessage());
        logger.info("==>" + e.fillInStackTrace());

        doLog(request, message, (short)1);

        return getResponseEntity(message, HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(HttpServletRequest request,
                                                              HttpServletResponse response, DuplicateKeyException e){
        String message = "数据库中已存在该记录";
        logger.info("==>errMsg:"+ message);
        logger.info("==>" + e.fillInStackTrace());

        doLog(request, message, (short) 1);
        return getResponseEntity(message, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(HttpServletRequest request,
                                                  HttpServletResponse response, RuntimeException e){
        String message = "操作失败";
        logger.info("==>errMsg:"+ message);
        logger.info("==>" + e.fillInStackTrace());
        return getResponseEntity(message, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(HttpServletRequest request,
                                                  HttpServletResponse response, Exception e){
        String message = "操作失败";
        logger.info("==>errMsg:"+ message);
        logger.info("==>" + e.fillInStackTrace());
        return getResponseEntity(message, HttpStatus.OK);
    }

    private void doLog(HttpServletRequest request, String message, Short LogType){
        String authId = request.getHeader(ApplicationGlobalNames.AUTH_ID);
        String userId = AuthIDUtils.getUserId(authId).get() == null ? "-1": AuthIDUtils.getUserId(authId).get() ;
        String ipAddress = IpAddressUtil.getIdAddr(request);
        syncLogService.doBizErrorLog(LogType, message, userId, ipAddress);
    }

    private ResponseEntity<String> getResponseEntity(String message,HttpStatus httpStatus){
        DataDto dto = new DataDto();
        dto.setMessage(message);
        String result = JSON.toJSONString(dto);

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType=new MediaType("application","json",Charset.forName("UTF-8"));
        headers.setContentType(mediaType);

        return new ResponseEntity<String>(result,headers, httpStatus);
    }

}
