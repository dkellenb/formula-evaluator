package li.kellenberger.formulaevaluator.term.operator;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.term.value.BigDecimalVariable;
import li.kellenberger.formulaevaluator.term.value.ConstantBigDecimalTerm;

/**
 * Generic test class for all operators
 */
public class OperatorTest {

  /**
   * Creation of a variable (used for printFormula testing).
   *
   * @param name variable name
   * @return new instance
   */
  protected static BigDecimalVariable v(String name) {
    return new BigDecimalVariable(name);
  }

  /**
   * Creation of constant value (used for calculation testing).
   *
   * @param value the value
   * @return constant term
   */
  protected static ConstantBigDecimalTerm c(BigDecimal value) {
    return new ConstantBigDecimalTerm(value);
  }

  protected static final BigDecimal TWO = new BigDecimal(2);

  protected static final BigDecimal THREE = new BigDecimal(3);

  protected static final BigDecimal FOUR = new BigDecimal(4);

  protected static final BigDecimal FIVE = new BigDecimal(5);

  protected static final BigDecimal SIX = new BigDecimal(6);

  protected static final BigDecimal EIGHT = new BigDecimal(8);

  protected static final BigDecimal NINE = new BigDecimal(9);

}
