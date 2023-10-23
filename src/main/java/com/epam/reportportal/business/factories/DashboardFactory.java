package com.epam.reportportal.business.factories;

import com.epam.reportportal.business.models.Dashboards;

public class DashboardFactory {

    public static Dashboards demoDashboard() {

        Dashboards dashboard = new Dashboards();
        dashboard.setDashboardCreated(true);
        return dashboard;
    }
}
