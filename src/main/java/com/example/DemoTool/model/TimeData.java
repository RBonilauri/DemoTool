package com.example.DemoTool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeData {

    private long referenceTime;
    private long virtualTime;
    private long computedNow;
    private String speed;
}
