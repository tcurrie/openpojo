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

package com.openpojo.reflection.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.openpojo.reflection.java.type.Resolver;
import com.openpojo.reflection.PojoField;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.reflection.exception.ReflectionException;
import com.openpojo.reflection.utils.ToStringHelper;

/**
 * This is the default implementation for PojoField Interface.
 * This is an immutable object and is not supposed to be created directly.
 * see {@link PojoClassFactory}
 *
 * @author oshoukry
 */
class PojoFieldImpl implements PojoField {

    private final Field field;
    private final PojoMethod fieldGetter;
    private final PojoMethod fieldSetter;

    PojoFieldImpl(final Field field) {
        this.field = field;
        this.field.setAccessible(true);
        fieldGetter = PojoMethodFactory.getFieldGetter(field);
        fieldSetter = PojoMethodFactory.getFieldSetter(field);
    }

    public Object get(final Object instance) {
        try {
            return field.get(instance);
        } catch (IllegalArgumentException e) {
            throw ReflectionException.getInstance(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw ReflectionException.getInstance(e.getMessage(), e);
        } catch (NullPointerException e) {
            throw ReflectionException.getInstance(e.getMessage(), e);
        }
    }

    public String getName() {
        return field.getName();
    }

    public void set(final Object instance, final Object value) {
        try {
            field.set(instance, value);
        } catch (IllegalArgumentException e) {
            throw ReflectionException.getInstance(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw ReflectionException.getInstance(e.getMessage(), e);
        } catch (NullPointerException e) {
            throw ReflectionException.getInstance(e.getMessage(), e);
        }
    }

    public boolean hasGetter() {
        return fieldGetter != null;
    }

    public Object invokeGetter(final Object instance) {
        try {
            return fieldGetter.invoke(instance, (Object[]) null);
        } catch (NullPointerException e) {
            String message = "Null pointer exception invoking [" + fieldGetter + "] on instance [" + instance + "]";
            throw ReflectionException.getInstance(message, e);
        }

    }

    public boolean hasSetter() {
        return fieldSetter != null;
    }

    public void invokeSetter(final Object instance, final Object value) {
        try {
            fieldSetter.invoke(instance, value);
        } catch (NullPointerException e) {
            String message = "Null pointer exception invoking [" + fieldSetter + "] on instance [" + instance + "] with value [" + value +
                    "]";
            throw ReflectionException.getInstance(message, e);
        }
    }

    public Class<?> getType() {
        return field.getType();
    }

    public boolean isParameterized() {
        Type type = field.getGenericType();
        return type instanceof ParameterizedType;
    }

    public List<Type> getParameterTypes() {
        List<Type> genericTypes = new LinkedList<Type>();
        if (isParameterized()) Collections.addAll(genericTypes, Resolver.getParameterTypes(field.getGenericType()));
        return genericTypes;
    }

    public <T extends Annotation> T getAnnotation(final Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    public List<? extends Annotation> getAnnotations() {
        return Arrays.asList(field.getAnnotations());
    }

    public boolean isPrimitive() {
        return getType().isPrimitive();
    }

    public boolean isFinal() {
        return Modifier.isFinal(field.getModifiers());
    }

    public boolean isStatic() {
        return Modifier.isStatic(field.getModifiers());
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(field.getModifiers());
    }

    public boolean isProtected() {
        return Modifier.isProtected(field.getModifiers());
    }

    public boolean isPublic() {
        return Modifier.isPublic(field.getModifiers());
    }

    public boolean isTransient() {
        return Modifier.isTransient(field.getModifiers());
    }

    public boolean isVolatile() {
        return Modifier.isVolatile(field.getModifiers());
    }

    public boolean isSynthetic() {
        return field.isSynthetic();
    }

    public boolean isArray() {
        return field.getType().isArray();
    }

    @Override
    public String toString() {
        return String.format("PojoFieldImpl [field=%s, fieldGetter=%s, fieldSetter=%s]", field, fieldGetter, fieldSetter);
    }

    public String toString(final Object instance) {
        return ToStringHelper.nameValuePair(getName(), get(instance));
    }
}
