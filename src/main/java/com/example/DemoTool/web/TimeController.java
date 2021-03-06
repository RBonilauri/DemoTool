package com.example.DemoTool.web;

import com.example.DemoTool.service.Converter;
import com.example.DemoTool.service.ImportFromTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TimeController {

    @Autowired
    ImportFromTimeService importFromTimeService;

    Converter converter = new Converter();

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        String dayComputedNow = converter.convertLongToStringDate(importFromTimeService.getTimeData().getComputedNow());
        String referenceTime = "Real server time at last time change : " + converter.convertLongToStringDate(importFromTimeService.getTimeData().getReferenceTime()) + "\n";
        String virtualTime = "Chosen virtual time start at last time change : " + converter.convertLongToStringDate(importFromTimeService.getTimeData().getVirtualTime()) + "\n";
        String computedNow = "Current virtual time computed by server : " + converter.convertLongToStringDate(importFromTimeService.getTimeData().getComputedNow()) + "\n";
        String speed = "Speed of virtual time flow : " + importFromTimeService.getTimeData().getSpeed();
        String timeInfo = referenceTime + virtualTime + computedNow + speed;

        model.addAttribute("timeInfo", timeInfo);
        model.addAttribute("computedNow", dayComputedNow);
        model.addAttribute("speed", speed);

        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String deleteTime() {
        importFromTimeService.deleteTime();
        return "redirect:index";
    }
}
