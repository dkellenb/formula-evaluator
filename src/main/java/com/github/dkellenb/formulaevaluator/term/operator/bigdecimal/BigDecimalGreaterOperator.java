package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * BigDecimal &gt; Operator.
 */
public class BigDecimalGreaterOperator extends GenericStackableOperatorTerm<BigDecimal>
  implements BigDecimalOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicant further term
   */
  public BigDecimalGreaterOperator(Term<BigDecimal> base, Term<BigDecimal> applicant) {
    super(base, applicant);
  }

  @Override
  public String getOperatorName() {
    return Operator.GREATER.getOperatorName();
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    return v1.compareTo(v2) == 1 ? one() : zero();
  }

}
