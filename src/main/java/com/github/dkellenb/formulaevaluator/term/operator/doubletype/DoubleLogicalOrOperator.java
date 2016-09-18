package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * Double || Operator.
 */
public class DoubleLogicalOrOperator extends GenericStackableOperatorTerm<Double>
  implements DoubleOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicants further terms (not null)
   */
  @SafeVarargs
  public DoubleLogicalOrOperator(Term<Double> base, Term<Double>... applicants) {
    super(base, applicants);
  }

  @Override
  public String getOperatorName() {
    return Operator.LOGICAL_OR.getOperatorName();
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, Double v1, Double v2) {
    boolean b1 = !zero().equals(v1);
    boolean b2 = !zero().equals(v2);
    return b1 || b2 ? one() : zero();
  }

}
