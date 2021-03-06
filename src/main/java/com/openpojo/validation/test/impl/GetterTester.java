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

package com.openpojo.validation.test.impl;

import com.openpojo.log.LoggerFactory;
import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoField;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.IdentityHandlerStub;
import com.openpojo.validation.utils.ValidationHelper;

/**
 * Test the getter and ensure it retrieves from the field being tested if and only if it has a getter defined.
 *
 * @author oshoukry
 */
public class GetterTester implements Tester {

    public void run(final PojoClass pojoClass) {
        final Object classInstance = ValidationHelper.getBasicInstance(pojoClass);
        for (final PojoField fieldEntry : pojoClass.getPojoFields()) {
            if (fieldEntry.hasGetter()) {
                Object value = fieldEntry.get(classInstance);

                if (!fieldEntry.isFinal()) {
                    value = RandomFactory.getRandomValue(fieldEntry);
                    fieldEntry.set(classInstance, value);
                }

                IdentityHandlerStub.registerIdentityHandlerStubForValue(value);

                LoggerFactory.getLogger(this.getClass()).debug("Testing Field [{0}] with value [{1}]", fieldEntry, value);

                Affirm.affirmEquals("Getter returned non equal value for field=[" + fieldEntry + "]", value,
                                    fieldEntry.invokeGetter(classInstance));
                IdentityHandlerStub.unregisterIdentityHandlerStubForValue(value);
            } else {
                LoggerFactory.getLogger(this.getClass()).debug("Field [{0}] has no getter skipping", fieldEntry);
            }
        }
    }
}
