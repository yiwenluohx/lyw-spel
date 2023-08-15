package com.study.lywspel.param;

import java.io.Serializable;

/**
 * DemoParam
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/15 上午10:11
 * @menu DemoParam
 */
public class DemoParam implements Serializable {
    private Long eid;
    private String companyName;

    /**
     * Gets the value of eid.
     *
     * @return the value of eid
     */
    public Long getEid() {
        return eid;
    }

    /**
     * Sets the eid. *
     * <p>You can use getEid() to get the value of eid</p>
     * * @param eid eid
     */
    public void setEid(Long eid) {
        this.eid = eid;
    }

    /**
     * Gets the value of companyName.
     *
     * @return the value of companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the companyName. *
     * <p>You can use getCompanyName() to get the value of companyName</p>
     * * @param companyName companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
