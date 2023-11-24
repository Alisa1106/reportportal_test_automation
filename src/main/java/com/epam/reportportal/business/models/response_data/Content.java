package com.epam.reportportal.business.models.response_data;

import com.epam.reportportal.business.models.response_data.content.Analysing;
import com.epam.reportportal.business.models.response_data.content.Attributes;
import com.epam.reportportal.business.models.response_data.content.Statistics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private String owner;
    private String description;
    private long id;
    private String uuid;
    private String name;
    private int number;
    private long startTime;
    private long endTime;
    private long lastModified;
    private String status;
    private Statistics statistics;
    private Attributes[] attributes;
    private String mode;
    private Analysing[] analysing;
    private double approximateDuration;
    private boolean hasRetries;
    private boolean rerun;
}
