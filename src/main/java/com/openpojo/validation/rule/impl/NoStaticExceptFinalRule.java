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

package com.openpojo.validation.rule.impl;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoField;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

/**
 * This rule ensures that there are no static fields unless they are final.
 * Another best practice, using static fields for memory sharing and allowing read/write
 * should be very tightly controlled, and generally don't belong in POJOs or other similar
 * class of data repositories.
 *
 * @author oshoukry
 */
public class NoStaticExceptFinalRule implements Rule {

    public void evaluate(final PojoClass pojoClass) {
        for (PojoField fieldEntry : pojoClass.getPojoFields()) {
            if (fieldEntry.isStatic() && !fieldEntry.isFinal()) {
                Affirm.fail(String.format("Static fields=[%s] not marked final are not allowed", fieldEntry));
            }
        }
    }
}
