package com.py.controller;

import com.py.service.write.WriteUserService;
import com.py.service.read.ReadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by py on 2017/11/24.
 */
@Controller
@RequestMapping("/test")
public class testController {
    @Autowired
    private ReadUserService readUserService;
    @Autowired
    private WriteUserService writeUserService;
    @GetMapping("/testRead")
    @ResponseBody
    public String testRead(){
        return readUserService.findUserNmae();
    }

    @GetMapping("/testWrite")
    @ResponseBody
    public String testWrite(){
        return  writeUserService.findUserNmae();
    }
}
