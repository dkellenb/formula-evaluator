package li.kellenberger.formulaevaluator.operators;

import java.lang.reflect.Array;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * Generic stackable operation.
 *
 * @param <I> the input object needed for the evaluation
 * @param <R> the result object after the evaluation
 */
abstract class GenericStackableOperatorTerm<I extends VariableValueProvider, R> extends GenericOperatorTerm<I, R> {

  private final Term<I, R> base;
  private final Term<I, R>[] applicants;

  /**
   * Initializes the calculator based on the terms.
   *
   * @param base the base
   * @param applicants all applicants
   */
  @SafeVarargs
  GenericStackableOperatorTerm(Term<I, R> base, Term<I, R>... applicants) {
    this.base = base;
    this.applicants = applicants;
  }

  @Override
  public R evaluate(I input, FormulaEvaluatorConfiguration conf) {
    // Simple case: Only one element
    if (applicants.length == 0) {
      return base.evaluate(input, conf);
    }

    // Simple case: Only one subtrahend
    if (applicants.length == 1) {
      return calculate(conf, base.evaluate(input, conf), applicants[0].evaluate(input, conf));
    }

    // If we have applicants, calculate them
    @SuppressWarnings("unchecked")
    final R[] values = (R[]) Array.newInstance(getResultClass(), applicants.length);
    for (int i = 0; i < applicants.length; i++) {
      values[i] = applicants[i].evaluate(input, conf);
    }

    R result = base.evaluate(input, conf);
    for (R value : values) {
      result = calculate(conf, result, value);
    }

    return result;
  }

  @Override
  public String printFormula() {
    final StringBuilder sb = new StringBuilder();
    sb.append("(");
    sb.append(base.printFormula());
    for (Term<I, R> subtrahend : applicants) {
      sb.append(getOperatorName());
      sb.append(subtrahend.printFormula());
    }
    sb.append(")");
    return sb.toString();
  }

}
