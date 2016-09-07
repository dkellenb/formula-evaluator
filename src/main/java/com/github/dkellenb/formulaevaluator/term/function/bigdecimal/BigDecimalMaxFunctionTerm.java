package com.github.dkellenb.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;
import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * BigDecimal MAX Function.
 */
public class BigDecimalMaxFunctionTerm extends GenericFunctionTerm<BigDecimal> implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term1 the first argument
   * @param term2 the second argument
   */
  public BigDecimalMaxFunctionTerm(Term<BigDecimal> term1, Term<BigDecimal> term2) {
    super(term1, term2);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimalMaxFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.MAX;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    BigDecimal v1 = parameters.get(0);
    BigDecimal v2 = parameters.get(1);
    return v1.compareTo(v2) > 0 ? v1 : v2;
  }

}
