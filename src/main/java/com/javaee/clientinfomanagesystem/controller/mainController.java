package com.javaee.clientinfomanagesystem.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/")
    public String index() {
        return "redirect:/user/login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/user/list")
    public String userList() { return "user/userlist"; }

    @GetMapping("/changePassword")
    public String changePwd() { return "includes/update_password"; }
}