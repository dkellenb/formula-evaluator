package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double SIN Function.
 */
public class DoubleSinusFunctionTerm extends GenericFunctionTerm<Double> implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term parameter term
   */
  public DoubleSinusFunctionTerm(Term<Double> term) {
    super(term);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleSinusFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.SIN;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    return Math.sin(Math.toRadians(parameters.get(0)));
  }

}
