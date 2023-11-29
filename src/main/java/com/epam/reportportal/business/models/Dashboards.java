package com.epam.reportportal.business.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dashboards {

    @JsonProperty("createDashboard")
    private boolean isDashboardCreated;
}
