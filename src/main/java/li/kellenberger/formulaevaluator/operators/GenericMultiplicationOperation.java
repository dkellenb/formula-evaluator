package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * Generic multiplication.
 *
 * @param <I> the input object needed for the evaluation
 * @param <R> the result object after the evaluation
 */
public abstract class GenericMultiplicationOperation<I extends VariableValueProvider, R>
  extends GenericStackableOperatorTerm<I, R> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param multiplier the multiplier
   * @param multiplicands all multiplicands
   */
  @SafeVarargs
  public GenericMultiplicationOperation(Term<I, R> multiplier, Term<I, R>... multiplicands) {
    super(multiplier, multiplicands);
  }

  @Override
  public String getOperatorName() {
    return "*";
  }

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param multiplier Operand 1.
   * @param multiplicand Operand 2.
   * @return The result of the operation.
   */
  R calculate(FormulaEvaluatorConfiguration conf, R multiplier, R multiplicand) {
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
