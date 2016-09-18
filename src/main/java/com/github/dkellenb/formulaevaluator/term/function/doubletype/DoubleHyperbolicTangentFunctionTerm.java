package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double TANH Function.
 */
public class DoubleHyperbolicTangentFunctionTerm extends GenericFunctionTerm<Double>
    implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term parameter term
   */
  public DoubleHyperbolicTangentFunctionTerm(Term<Double> term) {
    super(term);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleHyperbolicTangentFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.TANH;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    return Math.tanh(Math.toRadians(parameters.get(0)));
  }

}
