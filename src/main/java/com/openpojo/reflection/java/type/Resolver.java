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

package com.openpojo.reflection.java.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import com.openpojo.reflection.java.type.impl.NoResolveTypeResolver;
import com.openpojo.reflection.java.type.impl.ParameterizedTypeResolver;
import com.openpojo.reflection.java.type.impl.TypeVariableResolver;
import com.openpojo.reflection.java.type.impl.WildcardTypeResolver;

/**
 * @author oshoukry
 */
public class Resolver {
    private static final WildcardTypeResolver WILDCARD_TYPE_RESOLVER = new WildcardTypeResolver();
    private static final ParameterizedTypeResolver PARAMETERIZED_TYPE_RESOLVER = new ParameterizedTypeResolver();
    private static final TypeVariableResolver TYPE_VARIABLE_RESOLVER = new TypeVariableResolver();
    private static final NoResolveTypeResolver NO_RESOLVE_TYPE_RESOLVER = new NoResolveTypeResolver();

    @SuppressWarnings("unchecked")
    public static Type resolve(Type type) {
        return getTypeResolver(type).resolveType(type);
    }

    @SuppressWarnings("unchecked")
    public static Type getEnclosingType(Type type) {
        return getTypeResolver(type).getEnclosingType(type);
    }

    @SuppressWarnings("unchecked")
    public static Type[] getParameterTypes(Type type) {
        return getTypeResolver(type).getParameterTypes(type);
    }

    private static <T> TypeResolver getTypeResolver(T type) {
        if (type instanceof WildcardType)
            return WILDCARD_TYPE_RESOLVER;
        if (type instanceof ParameterizedType)
            return PARAMETERIZED_TYPE_RESOLVER;
        if (type instanceof TypeVariable)
            return TYPE_VARIABLE_RESOLVER;
        return NO_RESOLVE_TYPE_RESOLVER;
    }
}
