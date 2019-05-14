package com.example.DemoTool.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeDataForm {

    private long referenceTime;
    private long virtualTime;
    private VirtualTimeForm computedNow;
    private String speed;
}
