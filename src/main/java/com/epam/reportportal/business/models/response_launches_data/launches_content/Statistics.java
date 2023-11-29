package com.epam.reportportal.business.models.response_launches_data.launches_content;

import com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.Defects;
import com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.Executions;
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
