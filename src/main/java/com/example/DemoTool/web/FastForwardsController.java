package com.example.DemoTool.web;

import com.example.DemoTool.service.Converter;
import com.example.DemoTool.service.ImportFromTimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FastForwardsController {

    ImportFromTimeService importFromTimeService = new ImportFromTimeService();
    Converter converter = new Converter();

    private long now = System.currentTimeMillis();
    String dayComputedNow = converter.convertLongToStringDate(importFromTimeService.getTimeData().getComputedNow());

    @RequestMapping(value = {"/fastForwards"}, method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("computedNow", dayComputedNow);
        model.addAttribute("now", now);
        return "time/fastForwards";
    }

    @RequestMapping(value = {"/fastForwards"}, method = RequestMethod.POST)
    public String postForCard(@RequestParam(value = "action") String action, Model model) {
        model.addAttribute("computedNow", dayComputedNow);

        if (action.equals("Previous")) {
            System.out.println("Previous has been selected");
            importFromTimeService.postPreviousCard(importFromTimeService.getTimeData().getComputedNow());
            model.addAttribute("previousStatusCode", "previous ok");
        }
        if (action.equals("Next")) {
            System.out.println("Next has been selected");
            importFromTimeService.postNextCard(importFromTimeService.getTimeData().getComputedNow());
            model.addAttribute("nextStatusCode", "next ok");
        }
        return "redirect:/fastForwards";
    }

}
