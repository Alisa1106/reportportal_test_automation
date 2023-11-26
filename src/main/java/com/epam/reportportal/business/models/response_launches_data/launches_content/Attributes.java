package com.epam.reportportal.business.models.response_launches_data.launches_content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {

    private String key;
    private String value;
}
