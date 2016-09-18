package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.HashMap;
import java.util.Map;

import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.mapping.TermFactory;

/**
 * Helper class to hide constructor visibility on each of the terms.
 */
public final class AllDoubleFunctions {

  private AllDoubleFunctions() {
    // hidden c'tor
  }

  /**
   * Returns all known functions.
   *
   * @return no empty map
   */
  public static Map<Function, TermFactory.FunctionTermSupplier<Double>> all() {
    Map<Function, TermFactory.FunctionTermSupplier<Double>> map = new HashMap<>(Function.ALL_FUNCTIONS.size());
    map.put(Function.NOT, DoubleNotFunctionTerm::new);
    map.put(Function.IF, DoubleIfFunctionTerm::new);
    map.put(Function.RANDOM, DoubleRandomFunctionTerm::new);
    map.put(Function.SIN, DoubleSinusFunctionTerm::new);
    map.put(Function.COS, DoubleCosinusFunctionTerm::new);
    map.put(Function.TAN, DoubleTangentFunctionTerm::new);
    map.put(Function.SINH, DoubleHyperbolicSinusFunctionTerm::new);
    map.put(Function.COSH, DoubleHyberbolicCosinusFunctionTerm::new);
    map.put(Function.TANH, DoubleHyperbolicTangentFunctionTerm::new);
    map.put(Function.RAD, DoubleRadiansFunctionTerm::new);
    map.put(Function.DEG, DoubleDegreesFunctionTerm::new);
    map.put(Function.MIN, DoubleMinFunctionTerm::new);
    map.put(Function.MAX, DoubleMaxFunctionTerm::new);
    map.put(Function.LOG, DoubleNaturalLogarithmFunctionTerm::new);
    map.put(Function.LOG10, Double10LogarithmFunctionTerm::new);
    map.put(Function.ROUND, DoubleRoundFunctionTerm::new);
    map.put(Function.FLOOR, DoubleFloorFunctionTerm::new);
    map.put(Function.CEILING, DoubleCeilingFunctionTerm::new);
    map.put(Function.ABS, DoubleAbsoluteFunctionTerm::new);
    map.put(Function.SQRT, DoubleSquareRootFunctionTerm::new);
    return map;
  }

}
