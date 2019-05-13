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

    ImportFromTimeService importFromTimeService = new ImportFromTimeService();

    private long now = System.currentTimeMillis();

    @RequestMapping(value = {"/fastForwards"}, method = RequestMethod.GET)
    public String form(Model model) {
        String dayComputedNow = importFromTimeService.convertLongToStringDate(importFromTimeService.getTimeData().getComputedNow());
        model.addAttribute("computedNow", dayComputedNow);
        model.addAttribute("now", now);
        return "time/fastForwards";
    }

    @RequestMapping(value = {"/fastForwards"}, method = RequestMethod.POST)
    public String post(@RequestParam(value = "action", required = true) String action) {

        long computedNow = importFromTimeService.getTimeData().getComputedNow();

        if (action.equals("Previous")) {
            System.out.println("Previous has been selected");
            System.out.println(computedNow + " " + importFromTimeService.convertLongToStringDate(computedNow));
            importFromTimeService.postPreviousCard(importFromTimeService.getTimeData().getComputedNow());
        }
        if (action.equals("Next")) {
            System.out.println("Next has been selected");
            importFromTimeService.postNextCard(importFromTimeService.getTimeData().getComputedNow());
        }
        return "redirect:index";
    }

}
