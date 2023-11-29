package com.epam.reportportal.business.models;

import com.epam.reportportal.business.models.response_launches_data.LaunchesContent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseLaunchesData {

    private LaunchesContent[] content;
}
