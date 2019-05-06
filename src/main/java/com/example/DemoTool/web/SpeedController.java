package com.example.DemoTool.web;

import com.example.DemoTool.form.TimeDataForm;
import com.example.DemoTool.model.TimeData;
import com.example.DemoTool.service.ImportFromTimeService;
import org.lfenergy.operatorfabric.time.model.SpeedEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpeedController {

    @Value("${speedError.message}")
    private String errorMessage;

    @Autowired
    ImportFromTimeService importFromTimeService;

    List<SpeedEnum> flowSpeeds = new ArrayList<>();

    String currentSpeed;

    @RequestMapping(value = {"/formSpeed"}, method = RequestMethod.GET)
    public String formSpeed(Model model) {
        TimeDataForm timeDataForm = new TimeDataForm();

        if (flowSpeeds.size() == 0) {
            for (SpeedEnum speedEnum : SpeedEnum.values()) {
                flowSpeeds.add(speedEnum);
            }
        }
        model.addAttribute("actualSpeed", importFromTimeService.getTimeData().getSpeed());
        model.addAttribute("timeDataForm", timeDataForm);
        model.addAttribute("flowSpeeds", flowSpeeds);
        return "time/formSpeed";
    }

    @RequestMapping(value = {"formSpeed"}, method = RequestMethod.POST)
    public String postSpeed(@ModelAttribute("timeDataForm") TimeDataForm timeDataForm, Model model) {
        String speed = timeDataForm.getSpeed();
        currentSpeed = speed;
        importFromTimeService.postSpeed(currentSpeed);
        return "redirect:index";
    }
}
