package com.epam.reportportal.business.factories;

import lombok.Getter;

@Getter
public enum AnalyzeMode {

    TO_INVESTIGATE("TO_INVESTIGATE"),
    AUTO_ANALYZED("AUTO_ANALYZED"),
    MANUALLY_ANALYZED("MANUALLY_ANALYZED");

    final String mode;

    AnalyzeMode(String mode) {
        this.mode = mode;
    }
}
