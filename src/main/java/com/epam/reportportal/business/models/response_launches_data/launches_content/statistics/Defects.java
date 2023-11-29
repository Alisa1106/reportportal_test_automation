package com.epam.reportportal.business.models.response_launches_data.launches_content.statistics;

import com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.defects.AutomationBug;
import com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.defects.ProductBug;
import com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.defects.SystemIssue;
import com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.defects.ToInvestigate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Defects {

    @JsonProperty("system_issue")
    private SystemIssue systemIssue;
    @JsonProperty("product_bug")
    private ProductBug productBug;
    @JsonProperty("to_investigate")
    private ToInvestigate toInvestigate;
    @JsonProperty("automation_bug")
    private AutomationBug automationBug;
}
