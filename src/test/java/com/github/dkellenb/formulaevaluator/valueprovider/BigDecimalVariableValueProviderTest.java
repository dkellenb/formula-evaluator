package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Test for BigDecimalVariableValueProvider.
 */
public class BigDecimalVariableValueProviderTest {

  @Test
  public void shouldBeAbleToAddValuesByConstructorAndWithWith() {
    // given
    Map<String, BigDecimal> map = new HashMap<>();
    map.put("a", ZERO);
    map.put("b", ONE);

    // when
    BigDecimalVariableValueProvider valueProvider = new BigDecimalVariableValueProvider(map);
    valueProvider.with("c", TEN);

    // then
    assertThat(valueProvider.getValue("a"), equalTo(ZERO));
    assertThat(valueProvider.getValue("b"), equalTo(ONE));
    assertThat(valueProvider.getValue("c"), equalTo(TEN));
    assertThat(valueProvider.getValue("d"), nullValue());
  }

  @Test
  public void shouldRegisterNullValuesAsVariables() {
    // given
    Map<String, BigDecimal> map = new HashMap<>();
    map.put("a", ZERO);
    map.put("b", ONE);
    map.put("c", null);

    // when
    BigDecimalVariableValueProvider valueProvider = new BigDecimalVariableValueProvider(map);
    valueProvider.with("d", null);

    // then
    Set<String> variables = valueProvider.getVariables();
    assertThat(variables, hasItems("a", "b", "c", "d"));
  }

}
