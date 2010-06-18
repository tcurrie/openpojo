/**
 * Copyright (C) 2010 Osman Shoukry
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.openpojo.validation.rule.impl;

import com.openpojo.business.utils.BusinessPojoHelper;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

/**
 * @author oshoukry
 */
public class BusinessKeyMustExistRule implements Rule {

    public void evaluate(final PojoClass pojoClass) {
        Affirm.affirmTrue(String.format("[%s] doesn't declare any BusinessKeys!!", pojoClass), BusinessPojoHelper
                .getBusinessKeyFields(pojoClass.getClazz()).size() > 0);
    }
}
