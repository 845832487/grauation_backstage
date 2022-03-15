package com.truman.BackgroundSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedTask {
    private String applicationId;
    private String type;
    private String applicantId;
    private String approvalId;
    private LocalDateTime createDate;
    private Boolean result;
}
