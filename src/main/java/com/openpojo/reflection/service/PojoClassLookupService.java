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

package com.openpojo.reflection.service;

import java.util.List;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.registry.Service;

/**
 * A Service to lookup java classes.
 *
 * @author oshoukry
 *
 */
public interface PojoClassLookupService extends Service {

    List<PojoClass> enumerateClassesByExtendingType(final String packageName, final Class<?> type, final PojoClassFilter pojoClassFilter);

    PojoClass getPojoClass(final Class<?> clazz);

    List<PojoClass> getPojoClasses(final String packageName);

    List<PojoClass> getPojoClasses(final String packageName, final PojoClassFilter pojoClassFilter);

    List<PojoClass> getPojoClassesRecursively(final String packageName, final PojoClassFilter pojoClassFilter);


}
