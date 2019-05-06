package com.example.DemoTool.web;

import com.example.DemoTool.form.VirtualTimeForm;
import com.example.DemoTool.service.ImportFromTimeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;

@Controller
public class DateController {

    @Value("${dateError.message}")
    private String dateErrorMessage;

    @Value("${timeError.message}")
    private String timeErrorMessage;

    ImportFromTimeService importFromTimeService = new ImportFromTimeService();

    long currentDate;
    long currentTime;
    long now = System.currentTimeMillis();
    String actualChoosenDate = importFromTimeService.convertLongToStringDate(importFromTimeService.getTimeData().getComputedNow());
    String actualDate = importFromTimeService.convertLongToStringDate(now);

    @RequestMapping(value = {"/formDate"}, method = RequestMethod.GET)
    public String formDate(Model model) {

        VirtualTimeForm virtualTimeForm = new VirtualTimeForm();
        model.addAttribute("actualChoosenDate", actualChoosenDate);
        model.addAttribute("actualDate", actualDate);
        model.addAttribute("virtualTimeForm", virtualTimeForm);
        return "time/formDate";
    }

    @RequestMapping(value = {"formDate"}, method = RequestMethod.POST)
    public String postCurrentTime(@ModelAttribute("virtualTimeForm") VirtualTimeForm virtualTimeForm, Model model) throws ParseException {

        if (virtualTimeForm.getDate().isEmpty()) {
            model.addAttribute("actualChoosenDate", actualChoosenDate);
            model.addAttribute("actualDate", actualDate);
            model.addAttribute("dateErrorMessage", dateErrorMessage);
            return "time/formDate";
        } else {
            currentDate = importFromTimeService.convertDateToLong(virtualTimeForm.getDate());
            if (virtualTimeForm.getTime().isEmpty()) {
                model.addAttribute("actualChoosenDate", actualChoosenDate);
                model.addAttribute("actualDate", actualDate);
                model.addAttribute("timeErrorMessage", timeErrorMessage);
                return "time/formDate";
            } else {
                currentTime = importFromTimeService.convertTimeToLong(virtualTimeForm.getTime());
                importFromTimeService.postCurrentTime(String.valueOf(currentDate + currentTime));
                return "redirect:index";
            }
        }
    }
}
