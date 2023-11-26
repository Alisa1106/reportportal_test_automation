package com.epam.reportportal.business.models.Reponse_filters_data.filters_content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conditions {

    private String filteringField;
    private String condition;
    private String value;
}
