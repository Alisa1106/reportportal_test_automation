package com.epam.reportportal.business.models.response_data.content.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Executions {

    private int total;
    private int passed;
    private int failed;
    private int skipped;
}
