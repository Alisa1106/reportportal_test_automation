package com.epam.reportportal.business.models.Reponse_filters_data;

import com.epam.reportportal.business.models.Reponse_filters_data.filters_content.Conditions;
import com.epam.reportportal.business.models.Reponse_filters_data.filters_content.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltersContent {

    private String owner;
    private long id;
    private String name;
    private Conditions[] conditions;
    private Orders[] orders;
    private String type;
    private String description;
}
