package com.epam.reportportal.business.factories;

import lombok.Getter;

@Getter
public enum AnalyzerType {

    AUTO_ANALYZER("autoAnalyzer"),
    PATTERN_ANALYZER("patternAnalyzer");

    final String type;

    AnalyzerType(String type) {
        this.type = type;
    }
}
