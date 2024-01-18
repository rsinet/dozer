/*
 * Copyright 2005-2019 Dozer Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dozermapper.protobuf.propertydescriptor;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.github.dozermapper.core.config.BeanContainer;
import com.github.dozermapper.core.factory.DestBeanCreator;
import com.github.dozermapper.core.fieldmap.HintContainer;
import com.github.dozermapper.core.propertydescriptor.DozerPropertyDescriptor;
import com.github.dozermapper.core.propertydescriptor.PropertyDescriptorCreationStrategy;
import com.github.dozermapper.core.propertydescriptor.PropertyDescriptorFactory;
import com.github.dozermapper.core.util.MappingUtils;
import com.github.dozermapper.protobuf.util.ProtoUtils;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;

/**
 * {@link PropertyDescriptorCreationStrategy} which is used to build {@link ProtoFieldPropertyDescriptor}
 * via {@link #buildFor(Class, String, boolean, int, HintContainer, HintContainer)}.
 */
public class ProtoFieldPropertyDescriptorCreationStrategy implements PropertyDescriptorCreationStrategy {

    private BeanContainer beanContainer;
    private DestBeanCreator destBeanCreator;
    private PropertyDescriptorFactory propertyDescriptorFactory;

    /**
     * {@link PropertyDescriptorCreationStrategy} which is used to create instances of {@link DozerPropertyDescriptor}
     *
     * @param beanContainer             {@link BeanContainer} instance
     * @param destBeanCreator           {@link DestBeanCreator} instance
     * @param propertyDescriptorFactory {@link PropertyDescriptorFactory} instance
     */
    public ProtoFieldPropertyDescriptorCreationStrategy(BeanContainer beanContainer, DestBeanCreator destBeanCreator, PropertyDescriptorFactory propertyDescriptorFactory) {
        try {
            this.beanContainer = (BeanContainer) BeanUtils.cloneBean(beanContainer);
            this.destBeanCreator = (DestBeanCreator) BeanUtils.cloneBean(destBeanCreator);
            this.propertyDescriptorFactory = (PropertyDescriptorFactory) BeanUtils.cloneBean(propertyDescriptorFactory);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DozerPropertyDescriptor buildFor(Class<?> clazz, String fieldName, boolean isIndexed, int index, HintContainer srcDeepIndexHintContainer,
                                            HintContainer destDeepIndexHintContainer) {
        return new ProtoFieldPropertyDescriptor(clazz, fieldName, isIndexed, index, srcDeepIndexHintContainer, destDeepIndexHintContainer,
                                                beanContainer, destBeanCreator, propertyDescriptorFactory);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean isApplicable(Class<?> clazz, String fieldName) {
        if (!Message.class.isAssignableFrom(clazz)) {
            return false;
        }

        Class<? extends Message> messageClass = (Class<? extends Message>)clazz;
        Descriptors.FieldDescriptor foundDescriptor = ProtoUtils.getFieldDescriptor(messageClass, fieldName);

        return foundDescriptor != null || MappingUtils.isDeepMapping(fieldName);
    }
}
