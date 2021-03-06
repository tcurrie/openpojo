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

package com.openpojo.random.collection.list;

import java.util.Arrays;
import java.util.Collection;
import javax.management.relation.RoleUnresolvedList;

import com.openpojo.random.RandomGenerator;
import com.openpojo.random.collection.util.CollectionHelper;
import com.openpojo.random.util.Helper;
import com.openpojo.random.util.SomeRoleUnresolved;

/**
 * @author oshoukry
 */
public class RoleUnresolvedListRandomGenerator implements RandomGenerator {
    private static final Class<?>[] TYPES = new Class<?>[] { RoleUnresolvedList.class };
    private static final RoleUnresolvedListRandomGenerator INSTANCE = new RoleUnresolvedListRandomGenerator();

    public static RoleUnresolvedListRandomGenerator getInstance() {
        return INSTANCE;
    }

    public Collection<Class<?>> getTypes() {
        return Arrays.asList(TYPES);
    }

    public Collection doGenerate(Class<?> type) {
        Helper.assertIsAssignableTo(type, getTypes());
        return CollectionHelper.buildCollections(new RoleUnresolvedList(), SomeRoleUnresolved.class);
    }

    private RoleUnresolvedListRandomGenerator() {
    }
}
