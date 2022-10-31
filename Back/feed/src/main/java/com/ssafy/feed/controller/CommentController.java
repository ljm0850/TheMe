package com.ssafy.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {
    private final static String OK = "success";
    private final static String FAIL = "fail";

    @Autowired
    CommentController(){

    }
}
