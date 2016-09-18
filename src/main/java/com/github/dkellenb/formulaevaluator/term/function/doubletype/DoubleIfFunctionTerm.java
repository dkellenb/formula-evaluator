package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

import static java.lang.Math.floor;

/**
 * Double IF Function.
 */
public class DoubleIfFunctionTerm extends GenericFunctionTerm<Double> implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param conditionTerm the term checking the condition
   * @param ifTerm the term executed if the condition is true
   * @param elseTerm the term executed if the condition is false
   */
  public DoubleIfFunctionTerm(Term<Double> conditionTerm, Term<Double> ifTerm, Term<Double> elseTerm) {
    super(conditionTerm, ifTerm, elseTerm);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleIfFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.IF;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    boolean isTrue = floor(parameters.get(0)) == 0d;
    return isTrue ? parameters.get(1) : parameters.get(2);
  }

}
