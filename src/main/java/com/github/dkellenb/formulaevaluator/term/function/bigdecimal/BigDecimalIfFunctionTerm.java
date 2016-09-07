package com.github.dkellenb.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal IF Function.
 */
public class BigDecimalIfFunctionTerm extends GenericFunctionTerm<BigDecimal> implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param conditionTerm the term checking the condition
   * @param ifTerm the term executed if the condition is true
   * @param elseTerm the term executed if the condition is false
   */
  public BigDecimalIfFunctionTerm(Term<BigDecimal> conditionTerm, Term<BigDecimal> ifTerm, Term<BigDecimal> elseTerm) {
    super(conditionTerm, ifTerm, elseTerm);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimalIfFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.IF;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    boolean isTrue = !zero().equals(parameters.get(0));
    return isTrue ? parameters.get(1) : parameters.get(2);
  }

}
