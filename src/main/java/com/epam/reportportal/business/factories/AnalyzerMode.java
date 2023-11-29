package com.epam.reportportal.business.factories;

import lombok.Getter;

@Getter
public enum AnalyzerMode {

    ALL("ALL"),
    LAUNCH_NAME("LAUNCH_NAME"),
    CURRENT_LAUNCH("CURRENT_LAUNCH");

    final String mode;

    AnalyzerMode(String mode) {
        this.mode = mode;
    }
}
