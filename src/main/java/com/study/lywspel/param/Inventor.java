package com.study.lywspel.param;

import java.util.Date;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/15 上午11:32
 * @menu
 */
public class Inventor {
    private String name;
    private String nationality;
    private String[] inventions;
    private Date birthdate;
    private PlaceOfBirth placeOfBirth;

    public Inventor(String name, Date birthdate, String nationality) {
      this.name = name;
      this.birthdate = birthdate;
      this.nationality = nationality;
    }

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
     * Gets the value of nationality.
     *
     * @return the value of nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality. *
     * <p>You can use getNationality() to get the value of nationality</p>
     * * @param nationality nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the value of inventions.
     *
     * @return the value of inventions
     */
    public String[] getInventions() {
        return inventions;
    }

    /**
     * Sets the inventions. *
     * <p>You can use getInventions() to get the value of inventions</p>
     * * @param inventions inventions
     */
    public void setInventions(String[] inventions) {
        this.inventions = inventions;
    }

    /**
     * Gets the value of birthdate.
     *
     * @return the value of birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the birthdate. *
     * <p>You can use getBirthdate() to get the value of birthdate</p>
     * * @param birthdate birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets the value of placeOfBirth.
     *
     * @return the value of placeOfBirth
     */
    public PlaceOfBirth getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the placeOfBirth. *
     * <p>You can use getPlaceOfBirth() to get the value of placeOfBirth</p>
     * * @param placeOfBirth placeOfBirth
     */
    public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
