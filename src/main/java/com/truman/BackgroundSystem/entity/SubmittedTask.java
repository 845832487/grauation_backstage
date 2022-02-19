package com.truman.BackgroundSystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmittedTask {
    private String applicationId;
    private String type;
    private String condition;
    private LocalDateTime createDate;
}
