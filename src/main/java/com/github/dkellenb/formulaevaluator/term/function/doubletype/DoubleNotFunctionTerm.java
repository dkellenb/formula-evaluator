package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

import static java.lang.Math.floor;

/**
 * Double NOT Function.
 */
public class DoubleNotFunctionTerm extends GenericFunctionTerm<Double> implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term parameter term
   */
  public DoubleNotFunctionTerm(Term<Double> term) {
    super(term);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleNotFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.NOT;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    boolean zero = floor(parameters.get(0)) == 0d;
    return zero ? one() : zero();
  }

}
