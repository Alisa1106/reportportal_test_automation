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
public class Launches {

    private Long[] ids;

    public void setIdList(List<Long> idList) {
        this.ids = idList.toArray(new Long[0]);
    }
}
