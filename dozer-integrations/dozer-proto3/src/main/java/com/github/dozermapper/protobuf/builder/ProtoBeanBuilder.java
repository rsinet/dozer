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
package com.github.dozermapper.protobuf.builder;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.github.dozermapper.core.BeanBuilder;
import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;

/**
 * {@link BeanBuilder} that instantiates {@link Message}
 */
public class ProtoBeanBuilder implements BeanBuilder {

    protected Message.Builder internalProtoBuilder;
    protected Class<? extends Message> beanClass;

    /**
     * {@link BeanBuilder} which is used to create instances of {@link Message} via {@link Message.Builder}
     *
     * @param internalProtoBuilder builder for {@link Message}
     * @param beanClass            type of {@link Message} to create
     */
    public ProtoBeanBuilder(Message.Builder internalProtoBuilder, Class<? extends Message> beanClass) {
        try {
            this.internalProtoBuilder = (Builder) BeanUtils.cloneBean(internalProtoBuilder);
            this.beanClass = (Class<? extends Message>) BeanUtils.cloneBean(beanClass);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Message> beanClass() {
        return beanClass;
    }

    /**
     * Get the {@link Message.Builder}
     *
     * @return instance of {@link Message.Builder}
     */
    public Message.Builder internalProtoBuilder() {
        return internalProtoBuilder.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object build() {
        return internalProtoBuilder.build();
    }
}
