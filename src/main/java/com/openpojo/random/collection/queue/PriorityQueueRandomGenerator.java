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
import java.util.PriorityQueue;

import com.openpojo.random.collection.util.BaseCollectionRandomGenerator;
import com.openpojo.random.util.Helper;

/**
 * @author oshoukry
 */
public class PriorityQueueRandomGenerator extends BaseCollectionRandomGenerator {
    private final Class<?>[] TYPES = new Class<?>[] { PriorityQueue.class };
    public static final PriorityQueueRandomGenerator INSTANCE = new PriorityQueueRandomGenerator();

    public static PriorityQueueRandomGenerator getInstance() {
        return INSTANCE;
    }

    public Collection<Class<?>> getTypes() {
        return Arrays.asList(TYPES);
    }

    @Override
    protected Collection getBasicInstance(Class<?> type) {
        Helper.assertIsAssignableTo(type, getTypes());
        return new PriorityQueue();
    }

    private PriorityQueueRandomGenerator() {
    }
}
