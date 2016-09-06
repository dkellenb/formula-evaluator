package li.kellenberger.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Operator;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * BigDecimal == Operator.
 */
public class BigDecimalEqualOperator extends GenericStackableOperatorTerm<BigDecimal>
  implements BigDecimalOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicant applicant
   */
  public BigDecimalEqualOperator(Term<BigDecimal> base, Term<BigDecimal> applicant) {
    super(base, applicant);
  }

  @Override
  public String getOperatorName() {
    return Operator.EQUAL2.getOperatorName();
  }

  @Override
  protected BigDecimal calculate(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    if (v1 == null && v2 == null) {
      return one();
    } else {
      return super.calculate(conf, v1, v2);
    }
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    return v1.compareTo(v2) == 0 ? one() : zero();
  }

}
