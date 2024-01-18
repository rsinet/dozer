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
package com.github.dozermapper.protobuf;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.beanutils.BeanUtils;

import com.github.dozermapper.core.DozerModule;
import com.github.dozermapper.core.builder.BeanBuilderCreationStrategy;
import com.github.dozermapper.core.classmap.generator.BeanFieldsDetector;
import com.github.dozermapper.core.config.BeanContainer;
import com.github.dozermapper.core.factory.DestBeanCreator;
import com.github.dozermapper.core.propertydescriptor.PropertyDescriptorCreationStrategy;
import com.github.dozermapper.core.propertydescriptor.PropertyDescriptorFactory;
import com.github.dozermapper.protobuf.builder.ByProtobufBuilder;
import com.github.dozermapper.protobuf.classmap.generator.ProtobufBeanFieldsDetector;
import com.github.dozermapper.protobuf.propertydescriptor.ProtoFieldPropertyDescriptorCreationStrategy;

/**
 * {@link DozerModule} which adds support for Protobuf.
 * This is the main integration point into Dozer and is loaded via an SPI mechanism.
 */
public class ProtobufSupportModule implements DozerModule {

    private BeanContainer beanContainer;
    private DestBeanCreator destBeanCreator;
    private PropertyDescriptorFactory propertyDescriptorFactory;

    /**
     * {@inheritDoc}
     *
     * @deprecated DozerModule needs rethink, as exposes internals
     */
    @Deprecated
    @Override
    public void init(BeanContainer beanContainer, DestBeanCreator destBeanCreator, PropertyDescriptorFactory propertyDescriptorFactory) {
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
     * No-op
     */
    @Override
    public void init() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<BeanBuilderCreationStrategy> getBeanBuilderCreationStrategies() {
        return Collections.singleton(new ByProtobufBuilder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<BeanFieldsDetector> getBeanFieldsDetectors() {
        return Collections.singleton(new ProtobufBeanFieldsDetector());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<PropertyDescriptorCreationStrategy> getPropertyDescriptorCreationStrategies() {
        return Collections.singleton(new ProtoFieldPropertyDescriptorCreationStrategy(beanContainer, destBeanCreator, propertyDescriptorFactory));
    }
}
