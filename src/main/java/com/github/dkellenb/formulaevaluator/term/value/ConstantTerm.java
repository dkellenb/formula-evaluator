package com.github.dkellenb.formulaevaluator.term.value;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;
import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * BigDecimal Term.
 *
 * @param <T> calculation base type
 */
public class ConstantTerm<T> implements Term<T> {

  private final T value;

  /**
   * Creates a BigDecimal Term.
   * @param value the value
   */
  public ConstantTerm(T value) {
    this.value = value;
  }

  @Override
  public T evaluate(VariableValueProvider<T> input, FormulaEvaluatorConfiguration conf) {
    return value;
  }

  @Override
  public String printFormula() {
    return value == null ? null : value.toString();
  }

}
