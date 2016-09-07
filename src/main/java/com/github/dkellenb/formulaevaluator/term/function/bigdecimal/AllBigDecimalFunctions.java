package com.github.dkellenb.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.mapping.TermFactory;

/**
 * Helper class to hide constructor visibility on each of the terms.
 */
public final class AllBigDecimalFunctions {

  private AllBigDecimalFunctions() {
    // hidden c'tor
  }

  /**
   * Returns all known functions.
   *
   * @return no empty map
   */
  public static Map<Function, TermFactory.FunctionTermSupplier<BigDecimal>> all() {
    Map<Function, TermFactory.FunctionTermSupplier<BigDecimal>> map = new HashMap<>(Function.ALL_FUNCTIONS.size());
    map.put(Function.NOT, BigDecimalNotFunctionTerm::new);
    map.put(Function.IF, BigDecimalIfFunctionTerm::new);
    map.put(Function.RANDOM, BigDecimalRandomFunctionTerm::new);
    map.put(Function.SIN, BigDecimalSinusFunctionTerm::new);
    map.put(Function.COS, BigDecimalCosinusFunctionTerm::new);
    map.put(Function.TAN, BigDecimalTangentFunctionTerm::new);
    map.put(Function.SINH, BigDecimalHyperbolicSinusFunctionTerm::new);
    map.put(Function.COSH, BigDecimalHyberbolicCosinusFunctionTerm::new);
    map.put(Function.TANH, BigDecimalHyperbolicTangentFunctionTerm::new);
    map.put(Function.RAD, BigDecimalRadiansFunctionTerm::new);
    map.put(Function.DEG, BigDecimalDegreesFunctionTerm::new);
    map.put(Function.MIN, BigDecimalMinFunctionTerm::new);
    map.put(Function.MAX, BigDecimalMaxFunctionTerm::new);
    map.put(Function.LOG, BigDecimalNaturalLogarithmFunctionTerm::new);
    map.put(Function.LOG10, BigDecimal10LogarithmFunctionTerm::new);
    map.put(Function.ROUND, BigDecimalRoundFunctionTerm::new);
    map.put(Function.FLOOR, BigDecimalFloorFunctionTerm::new);
    map.put(Function.CEILING, BigDecimalCeilingFunctionTerm::new);
    map.put(Function.ABS, BigDecimalAbsoluteFunctionTerm::new);
    map.put(Function.SQRT, BigDecimalSquareRootFunctionTerm::new);
    return map;
  }

}
