package com.example.DemoTool.web;

import com.example.DemoTool.service.ImportFromTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FastForwardsController {

    @Autowired
    ImportFromTimeService importFromTimeService;

    @RequestMapping(value = {"/fastForwards"}, method = RequestMethod.GET)
    public String form(Model model) {
        String dayComputedNow = importFromTimeService.convertLongToStringDate(importFromTimeService.getTimeData().getComputedNow());
        model.addAttribute("computedNow", dayComputedNow);
        return "time/fastForwards";
    }

    @RequestMapping(value = {"/fastForwards"}, method = RequestMethod.POST)
    public String post(@RequestParam(value = "action") String action) {
        String forReturn = "time/fastForwards";
        long computedNow = importFromTimeService.getTimeData().getComputedNow();

        switch (action) {
            case "Previous":
                System.out.println("Previous has been selected");
                System.out.println(computedNow + " " + importFromTimeService.convertLongToStringDate(computedNow));
                importFromTimeService.postPreviousCardWithoutMillisTime();
                forReturn = "redirect:index";
                break;
            case "Next":
                System.out.println("Next has been selected");
                importFromTimeService.postNextCardWithoutMillisTime();
                forReturn = "redirect:index";
                break;
        }
        return forReturn;
    }
}
