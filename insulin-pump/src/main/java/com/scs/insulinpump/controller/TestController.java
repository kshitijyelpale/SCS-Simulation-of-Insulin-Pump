package com.scs.insulinpump.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping(path = "/randomBSL")
    public @ResponseBody int getRandomBSL() throws InterruptedException {
        Thread.sleep(1000);
        Random random = new Random();
        int min = 100, max = 200;
        return random.nextInt(max - min) + min;
    }
}
