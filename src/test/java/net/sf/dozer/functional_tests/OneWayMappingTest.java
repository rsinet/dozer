package net.sf.dozer.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import net.sf.dozer.Mapper;
import net.sf.dozer.vo.oneway.DestClass;
import net.sf.dozer.vo.oneway.Holder;
import net.sf.dozer.vo.oneway.SourceClass;

import org.junit.Test;

/**
 * @author Dmitry Buzdin
 */
public class OneWayMappingTest extends AbstractFunctionalTest {

  @Test
  public void testOneWay() {
    Mapper mapper = getMapper("oneWayMapping.xml");

    SourceClass source = newInstance(SourceClass.class, new Object[] {"A"});

    Holder holder = mapper.map(source, Holder.class);
    DestClass dest = holder.getDest();

    assertNotNull(dest);
    assertEquals("A", dest.anonymousAccessor());
  }

  @Override
  protected DataObjectInstantiator getDataObjectInstantiator() {
    return NoProxyDataObjectInstantiator.INSTANCE;
  }
}
