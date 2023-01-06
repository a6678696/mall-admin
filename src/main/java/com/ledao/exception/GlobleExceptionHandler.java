package com.ledao.exception;

import com.ledao.entity.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author LeDao
 * @company
 * @create 2023-01-07 4:27
 */
@ResponseBody
@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(Exception e) {
        return R.error(e.getMessage());
    }
}
