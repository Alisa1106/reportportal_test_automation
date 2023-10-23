package com.epam.reportportal.business.models.response_data.content;

import com.epam.reportportal.business.models.response_data.content.statistics.Defects;
import com.epam.reportportal.business.models.response_data.content.statistics.Executions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    private Executions executions;
    private Defects defects;
}
