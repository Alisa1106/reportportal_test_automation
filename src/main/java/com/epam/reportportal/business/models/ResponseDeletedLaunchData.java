package com.epam.reportportal.business.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDeletedLaunchData {

    private Long[] successfullyDeleted;
    private Long[] notFound;
    private String[] errors;
}
