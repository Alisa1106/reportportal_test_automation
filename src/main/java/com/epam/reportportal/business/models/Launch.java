package com.epam.reportportal.business.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Launch {

    private String name;
    private Date startTime;
    private int totalNumber;
    private int passedNumber;
    private int failedNumber;
    private int skippedNumber;
    private int productBugsNumber;
    private int autoBugsNumber;
    private int systemIssuesNumber;
    private int launchToInvestigateNumber;
}
