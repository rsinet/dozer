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
package com.github.dozermapper.core.builder.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.classmap.ClassMap;
import com.github.dozermapper.core.classmap.Configuration;
import com.github.dozermapper.core.config.BeanContainer;
import com.github.dozermapper.core.factory.DestBeanCreator;
import com.github.dozermapper.core.propertydescriptor.PropertyDescriptorFactory;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The document root.
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "mappings")
public class MappingsDefinition {

    @XmlTransient
    private String schemaLocation;

    @XmlElement(name = "configuration")
    protected ConfigurationDefinition configuration;

    @XmlElement(name = "mapping")
    protected List<MappingDefinition> mapping;

    // Fluent API
    //-------------------------------------------------------------------------
    public ConfigurationDefinition withConfiguration() {
        if (this.configuration == null) {
            setConfiguration(new ConfigurationDefinition(this));
        }

        return configuration;
    }

    public MappingDefinition addMapping() {
        if (this.mapping == null) {
            setMapping(new ArrayList<>());
        }

        MappingDefinition mapping = new MappingDefinition(this);
        getMapping().add(mapping);

        return mapping;
    }

    public List<ClassMap> build(Configuration configuration, BeanContainer beanContainer, DestBeanCreator destBeanCreator, PropertyDescriptorFactory propertyDescriptorFactory) {
        List<ClassMap> answer = new ArrayList<>();

        if (mapping != null) {
            for (MappingDefinition current : mapping) {
                answer.add(current.build(configuration, beanContainer, destBeanCreator, propertyDescriptorFactory));
            }
        }

        return answer;
    }
}
