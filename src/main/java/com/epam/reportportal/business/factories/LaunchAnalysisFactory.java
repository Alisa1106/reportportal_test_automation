package com.epam.reportportal.business.factories;

import com.epam.reportportal.business.models.AnalyzeLaunchRQ;

import java.util.ArrayList;
import java.util.List;

public class LaunchAnalysisFactory {

    private static final String TEST_VALUE = "ERR";

    public static AnalyzeLaunchRQ analyzeLaunchToInvestigate(long launchId) {

        AnalyzeLaunchRQ analyzeLaunchRQ = new AnalyzeLaunchRQ();
        List<String> analyzeItemsMode = new ArrayList<>();
        analyzeItemsMode.add(com.epam.reportportal.business.factories.AnalyzeMode.TO_INVESTIGATE.getMode());
        analyzeLaunchRQ.setAnalyzeItemsMode(analyzeItemsMode);
        analyzeLaunchRQ.setAnalyzerMode(AnalyzerMode.ALL.getMode());
        analyzeLaunchRQ.setAnalyzerTypeName(AnalyzerType.AUTO_ANALYZER.getType());
        analyzeLaunchRQ.setLaunchId(launchId);
        return analyzeLaunchRQ;
    }

    public static AnalyzeLaunchRQ invalidAnalyzeLaunch(long launchId) {

        AnalyzeLaunchRQ analyzeLaunchRQ = new AnalyzeLaunchRQ();
        List<String> analyzeItemsMode = new ArrayList<>();
        analyzeItemsMode.add(TEST_VALUE);
        analyzeLaunchRQ.setAnalyzeItemsMode(analyzeItemsMode);
        analyzeLaunchRQ.setAnalyzerMode(TEST_VALUE);
        analyzeLaunchRQ.setAnalyzerTypeName(TEST_VALUE);
        analyzeLaunchRQ.setLaunchId(launchId);
        return analyzeLaunchRQ;
    }
}
