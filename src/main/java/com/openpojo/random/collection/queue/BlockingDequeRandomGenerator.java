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

package com.openpojo.random.collection.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.openpojo.random.collection.util.BaseCollectionRandomGenerator;
import com.openpojo.random.collection.util.CollectionHelper;
import com.openpojo.random.util.Helper;
import com.openpojo.reflection.construct.InstanceFactory;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.reflection.java.load.ClassUtil;

/**
 * @author oshoukry
 */
public class BlockingDequeRandomGenerator extends BaseCollectionRandomGenerator {
    private static final String TYPE = "java.util.concurrent.BlockingDeque";
    private static final String CONCRETE_TYPE = "java.util.concurrent.LinkedBlockingDeque";
    private static final BlockingDequeRandomGenerator INSTANCE = new BlockingDequeRandomGenerator();

    public static BlockingDequeRandomGenerator getInstance() {
        return INSTANCE;
    }

    public Collection<Class<?>> getTypes() {
        List<Class<?>> types = new ArrayList<Class<?>>();
        if (ClassUtil.isClassLoaded(TYPE) && ClassUtil.isClassLoaded(CONCRETE_TYPE))
            types.add(ClassUtil.loadClass(TYPE));
        return types;
    }

    @Override
    protected Collection getBasicInstance(Class<?> type) {
        Helper.assertIsAssignableTo(type, getTypes());
        return (Collection) InstanceFactory.getInstance(PojoClassFactory.getPojoClass(ClassUtil.loadClass(CONCRETE_TYPE)),
                CollectionHelper.MAX_RANDOM_ELEMENTS);
    }

    private BlockingDequeRandomGenerator() {
    }
}
