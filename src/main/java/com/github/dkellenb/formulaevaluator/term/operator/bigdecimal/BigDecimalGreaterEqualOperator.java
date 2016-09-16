package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.operator.GenericStackableOperatorTerm;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * BigDecimal &gt;= Operator.
 */
public class BigDecimalGreaterEqualOperator extends GenericStackableOperatorTerm<BigDecimal>
  implements BigDecimalOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicant applicant
   */
  public BigDecimalGreaterEqualOperator(Term<BigDecimal> base, Term<BigDecimal> applicant) {
    super(base, applicant);
  }

  @Override
  public String getOperatorName() {
    return Operator.GREATER_EQUAL.getOperatorName();
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    return v1.compareTo(v2) >= 0 ? one() : zero();
  }

}
