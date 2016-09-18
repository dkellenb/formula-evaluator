package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double Random Function.
 */
public class DoubleRandomFunctionTerm extends GenericFunctionTerm<Double> implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   */
  public DoubleRandomFunctionTerm() {
    super();
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleRandomFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.RANDOM;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    return Math.random();
  }

}
