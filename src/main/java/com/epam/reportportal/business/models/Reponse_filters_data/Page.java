package com.epam.reportportal.business.models.Reponse_filters_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private int number;
    private int size;
    private int totalElements;
    private int totalPages;
}
