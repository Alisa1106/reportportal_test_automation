package com.epam.reportportal.business.models;

import com.epam.reportportal.business.models.Reponse_filters_data.FiltersContent;
import com.epam.reportportal.business.models.Reponse_filters_data.Page;
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
public class ResponseFiltersData {

    private FiltersContent[] content;
    private Page page;
}
