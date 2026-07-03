package com.javaee.clientinfomanagesystem.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class exceptionController {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model){
        model.addAttribute("exception_info", e.getStackTrace());
        return "exception/exception";
    }
}
