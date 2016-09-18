package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * Double &lt; Operator.
 */
public class DoubleSmallerOperator extends GenericStackableOperatorTerm<Double>
  implements DoubleOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicant further term
   */
  public DoubleSmallerOperator(Term<Double> base, Term<Double> applicant) {
    super(base, applicant);
  }

  @Override
  public String getOperatorName() {
    return Operator.SMALLER.getOperatorName();
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, Double v1, Double v2) {
    return v1.compareTo(v2) == -1 ? one() : zero();
  }

}
