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
package net.sf.dozer.functional_tests.proxied;

import org.junit.Ignore;
import org.junit.Test;

import net.sf.dozer.functional_tests.DataObjectInstantiator;
import net.sf.dozer.functional_tests.GenericCollectionMappingTest;

/**
 * @author tierney.matt
 */
public class ProxiedGenericCollectionMappingTest extends GenericCollectionMappingTest {
  
  @Test
  @Ignore("Fails.  Needs investigation")
  public void testDeepMapIndexed() throws Exception {
    super.testDeepMapIndexed();
  }

  @Test
  @Ignore("Fails.  Needs investigation")
  public void testDeepMapInvIndexed() throws Exception {
    super.testDeepMapInvIndexed();
  }

  @Test
  @Ignore("Fails.  Needs investigation")
  public void testDeepMappingWithIndexOnDestField() {
    super.testDeepMappingWithIndexOnDestField();
  }

  @Test
  @Ignore("Fails.  Needs investigation")
  public void testDeepMappingWithIndexOnSrcField() {
    super.testDeepMappingWithIndexOnSrcField();
  }

  @Override
  protected DataObjectInstantiator getDataObjectInstantiator() {
    return ProxyDataObjectInstantiator.INSTANCE;
  }

}