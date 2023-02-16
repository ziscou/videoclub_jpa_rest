package org.iesvdm.videoclub.controller;

import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoriaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CategoriaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CategoriaNotFoundHandler(CategoriaNotFoundException CategoriaNotFoundException) {
        return CategoriaNotFoundException.getMessage();
    }

}
