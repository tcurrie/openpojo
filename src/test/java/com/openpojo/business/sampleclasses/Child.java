/*
 * Copyright (c) 2010-2015 Osman Shoukry
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License or any
 *    later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.openpojo.business.sampleclasses;

import com.openpojo.business.annotation.BusinessKey;

/**
 * @author oshoukry
 */
public class Child extends Parent {

    @BusinessKey
    private String firstName;

    public Child() {

    }

    /**
     * @param firstName
     */
    public Child(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param firstName
     */
    public Child(final String firstName, final String lastName, final Character sex) {
        super(lastName);
        this.firstName = firstName;
        setSex(sex);
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
}
