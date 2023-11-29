package com.epam.reportportal.business.factories;

import com.epam.reportportal.business.models.Reponse_filters_data.FiltersContent;
import com.epam.reportportal.business.models.Reponse_filters_data.filters_content.Conditions;
import com.epam.reportportal.business.models.Reponse_filters_data.filters_content.Orders;
import org.apache.commons.lang3.RandomStringUtils;

public class FiltersFactory {

    private static final String TEST_VALUE = "test";

    public static FiltersContent filterByNumber(long filterId) {

        Conditions conditions = new Conditions();
        conditions.setCondition("cnt");
        conditions.setFilteringField("name");
        conditions.setValue(TEST_VALUE);

        Orders orders = new Orders();
        orders.setAsc(false);
        orders.setSortingColumn("number");

        FiltersContent filtersContent = new FiltersContent();
        filtersContent.setOwner("alisa_harodnik");
        filtersContent.setId(filterId);
        filtersContent.setConditions(new Conditions[]{conditions});
        filtersContent.setDescription(RandomStringUtils.random(10, true, false));
        filtersContent.setName(TEST_VALUE);
        filtersContent.setOrders(new Orders[]{orders});
        filtersContent.setType("Launch");

        return filtersContent;
    }
}
