package com.epam.reportportal.business.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeLaunchRQ {

    private List<String> analyzeItemsMode;
    private String analyzerMode;
    private String analyzerTypeName;
    private long launchId;
}
