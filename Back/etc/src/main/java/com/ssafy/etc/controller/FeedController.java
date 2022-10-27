package com.ssafy.etc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FeedController {
    private final static String OK = "success";
    private final static String FAIL = "fail";

    @Autowired
    FeedController(){

    }

}
