package com.epam.reportportal.business.models.response_data.content.statistics.defects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemIssue {

    private int total;
    private int si001;
}
