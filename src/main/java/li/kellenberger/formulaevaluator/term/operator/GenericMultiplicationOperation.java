package li.kellenberger.formulaevaluator.term.operator;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Operator;
import li.kellenberger.formulaevaluator.term.Term;

/**
 * Generic multiplication.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericMultiplicationOperation<T>
  extends GenericStackableOperatorTerm<T> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param multiplier the multiplier
   * @param multiplicands all multiplicands
   */
  @SafeVarargs
  public GenericMultiplicationOperation(Term<T> multiplier, Term<T>... multiplicands) {
    super(multiplier, multiplicands);
  }

  @Override
  public String getOperatorName() {
    return Operator.MULTIPLY.getOperatorName();
  }

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param multiplier Operand 1.
   * @param multiplicand Operand 2.
   * @return The result of the operation.
   */
  protected T calculate(FormulaEvaluatorConfiguration conf, T multiplier, T multiplicand) {
    switch (conf.getMultiplicationNullHandling()) {
      case IDENTITY:
        return multiplier == null && multiplicand == null
          ? null
          : super.calculate(conf, multiplier == null ? one() : multiplier, multiplicand == null ? one() : multiplicand);
      case INHERIT:
      default:
        return super.calculate(conf, multiplier, multiplicand);
    }
  }

}
