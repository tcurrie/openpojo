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

package com.openpojo.random.map.util;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.impl.ParameterizableFactory;

/**
 * This Helper class populates the randomly generated map with some random elements.<br>
 * It is configured to generate anywhere between 1 - 5 elements in the map.
 *
 * @author oshoukry
 */
public class MapHelper {

    private static final Random RANDOM = new Random(new Date().getTime());
    private static final int MAX_RANDOM_ELEMENTS = 5;

    @SuppressWarnings("unchecked")
    public static Map buildMap(Map map, Type key, Type value) {
        if (key == null || value == null || map == null) return map;

        int counter = RANDOM.nextInt(MAX_RANDOM_ELEMENTS) + 1;
        map.clear();

        while (counter-- > 0) {
            Object nextKey = RandomFactory.getRandomValue(ParameterizableFactory.getInstance(key));
            Object nextValue = RandomFactory.getRandomValue(ParameterizableFactory.getInstance(value));
            map.put(nextKey, nextValue);
        }
        return map;

    }
}
