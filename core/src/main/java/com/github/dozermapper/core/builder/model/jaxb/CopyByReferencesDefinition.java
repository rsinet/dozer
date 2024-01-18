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

import com.github.dozermapper.core.classmap.CopyByReference;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "copy-by-references")
public class CopyByReferencesDefinition {

    @XmlTransient
    private final ConfigurationDefinition parent;

    @XmlElement(name = "copy-by-reference", required = true)
    protected List<String> copyByReference;

    public CopyByReferencesDefinition() {
        this(null);
    }

    public CopyByReferencesDefinition(ConfigurationDefinition parent) {
        this.parent = parent;
    }

    // Fluent API
    //-------------------------------------------------------------------------
    public CopyByReferencesDefinition addCopyByReference(String copyByReference) {
        if (getCopyByReference() == null) {
            setCopyByReference(new ArrayList<>());
        }

        getCopyByReference().add(copyByReference);

        return this;
    }

    public List<CopyByReference> build() {
        List<CopyByReference> answer = new ArrayList<>();
        for (String current : copyByReference) {
            answer.add(new CopyByReference(current));
        }

        return answer;
    }

    public ConfigurationDefinition end() {
        return parent;
    }
}
