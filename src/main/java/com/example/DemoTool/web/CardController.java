package com.example.DemoTool.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/card")
public class CardController {

    @RequestMapping(value = {"/publisher"}, method = RequestMethod.GET)
    public String cardPublisher() {
        return "card/publisher";
    }

}
