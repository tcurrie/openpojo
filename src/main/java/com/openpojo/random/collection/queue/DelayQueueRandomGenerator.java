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

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.DelayQueue;

import com.openpojo.random.collection.util.BaseCollectionRandomGenerator;
import com.openpojo.random.collection.util.CollectionHelper;
import com.openpojo.random.util.ComparableDelayed;
import com.openpojo.random.util.Helper;
import com.openpojo.reflection.Parameterizable;

/**
 * @author oshoukry
 */
public class DelayQueueRandomGenerator extends BaseCollectionRandomGenerator {
    private static final Class<?>[] TYPES = new Class<?>[] { DelayQueue.class };
    private static final DelayQueueRandomGenerator INSTANCE = new DelayQueueRandomGenerator();

    public static DelayQueueRandomGenerator getInstance() {
        return INSTANCE;
    }

    public Collection doGenerate(Class<?> type) {
        return CollectionHelper.buildCollections(getBasicInstance(type), ComparableDelayed.class);
    }

    public Collection doGenerate(Parameterizable parameterizedType) {
        return CollectionHelper.buildCollections(getBasicInstance(parameterizedType.getType()), parameterizedType.getParameterTypes().get
                (0));
    }

    public Collection<Class<?>> getTypes() {
        return Arrays.asList(TYPES);
    }

    @Override
    protected Collection getBasicInstance(Class<?> type) {
        Helper.assertIsAssignableTo(type, getTypes());
        return new DelayQueue();
    }

    private DelayQueueRandomGenerator() {
    }
}
