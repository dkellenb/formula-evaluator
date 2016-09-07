package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * BigDecimal != Operator.
 */
public class BigDecimalNotEqualOperator extends GenericStackableOperatorTerm<BigDecimal>
  implements BigDecimalOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicant further term
   */
  public BigDecimalNotEqualOperator(Term<BigDecimal> base, Term<BigDecimal> applicant) {
    super(base, applicant);
  }

  @Override
  protected BigDecimal calculate(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    if (v1 == null && v2 == null) {
      return zero();
    } else {
      return super.calculate(conf, v1, v2);
    }
  }

  @Override
  public String getOperatorName() {
    return Operator.NOT_EQUAL.getOperatorName();
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    return v1.compareTo(v2) != 0 ? one() : zero();
  }

}
