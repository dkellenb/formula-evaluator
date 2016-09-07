package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.operator.GenericSubtractionOperator;
import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalSubtractionOperator
    extends GenericSubtractionOperator<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param minuend the minuend term
   * @param subtrahends one or many subtrahends
   */
  @SafeVarargs
  public BigDecimalSubtractionOperator(Term<BigDecimal> minuend, Term<BigDecimal>... subtrahends) {
    super(minuend, subtrahends);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    return v1.subtract(v2, conf.getMathContext());
  }

}
