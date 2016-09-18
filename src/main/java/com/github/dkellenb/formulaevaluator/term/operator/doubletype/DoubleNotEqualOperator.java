package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * Double != Operator.
 */
public class DoubleNotEqualOperator extends GenericStackableOperatorTerm<Double>
  implements DoubleOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicant further term
   */
  public DoubleNotEqualOperator(Term<Double> base, Term<Double> applicant) {
    super(base, applicant);
  }

  @Override
  protected Double calculate(FormulaEvaluatorConfiguration conf, Double v1, Double v2) {
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
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, Double v1, Double v2) {
    return v1.compareTo(v2) != 0 ? one() : zero();
  }

}
