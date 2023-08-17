package com.study.lywspel.param;

import java.io.Serializable;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/17 上午10:03
 * @menu
 */
public class User implements Serializable {
    private String name;
    private Long id;

    /**
     * Gets the value of name.
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name. *
     * <p>You can use getName() to get the value of name</p>
     * * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of id.
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id. *
     * <p>You can use getId() to get the value of id</p>
     * * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
