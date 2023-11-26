package com.epam.reportportal.business.models.response_launches_data.launches_content.statistics.defects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBug {

    private int total;
    private int pb001;
}