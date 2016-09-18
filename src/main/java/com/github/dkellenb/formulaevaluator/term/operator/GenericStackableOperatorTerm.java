package com.github.dkellenb.formulaevaluator.term.operator;

import java.lang.reflect.Array;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;

/**
 * Generic stackable operation.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericStackableOperatorTerm<T> extends GenericOperatorTerm<T> {

  private final Term<T> base;
  private final Term<T>[] applicants;

  /**
   * Initializes the calculator based on the terms.
   *
   * @param base the base
   * @param applicants all applicants
   */
  @SafeVarargs
  public GenericStackableOperatorTerm(Term<T> base, Term<T>... applicants) {
    this.base = base;
    this.applicants = applicants;
  }

  @Override
  public T evaluate(VariableValueProvider<T> input, FormulaEvaluatorConfiguration conf) {
    // Simple case: Only one element
    if (applicants.length == 0) {
      return base.evaluate(input, conf);
    }

    // Simple case: Only one applicant
    if (applicants.length == 1) {
      return calculate(conf, base.evaluate(input, conf), applicants[0].evaluate(input, conf));
    }

    // If we have applicants, calculate them
    @SuppressWarnings("unchecked")
    final T[] values = (T[]) Array.newInstance(getResultClass(), applicants.length);
    for (int i = 0; i < applicants.length; i++) {
      values[i] = applicants[i].evaluate(input, conf);
    }

    T result = base.evaluate(input, conf);
    for (T value : values) {
      result = calculate(conf, result, value);
    }

    return result;
  }

  @Override
  public String printFormula() {
    final StringBuilder sb = new StringBuilder();
    sb.append("(");
    sb.append(base.printFormula());
    for (Term<T> subtrahend : applicants) {
      sb.append(getOperatorName());
      sb.append(subtrahend.printFormula());
    }
    sb.append(")");
    return sb.toString();
  }

}
