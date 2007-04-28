/*
 * Copyright 2005-2007 the original author or authors.
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
package net.sf.dozer.util.mapping;

import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.util.MapperConstants;

/**
 * Singleton wrapper for the DozerBeanMapper.  Only supports a single mapping file named dozerBeanMapping.xml, so configuration is limited.
 * Instead, it is recommended that the MapperIF is configured via an IOC framework, such as Spring, with singleton property set to "true"
 * 
 * @author garsombke.franz
 */
public class DozerBeanMapperSingletonWrapper {

  private static MapperIF instance;
  
  private DozerBeanMapperSingletonWrapper() {}

  public static synchronized MapperIF getInstance() {
    if (instance == null) {
        List mappingFiles = new ArrayList();
        mappingFiles.add(MapperConstants.DEFAULT_MAPPING_FILE);
        instance = new DozerBeanMapper(mappingFiles);
    }
    return instance;
  }
}