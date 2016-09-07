package com.github.dkellenb.formulaevaluator.definition;

import java.util.Arrays;
import java.util.List;

import lombok.EqualsAndHashCode;

import static java.util.Collections.unmodifiableList;

/**
 * Definition of a supported operator. An operator is defined by its name (pattern), precedence and if it is
 * left- or right associative.
 */
@EqualsAndHashCode(of = "operatorName")
public class Operator {

  /**
   * This operators name (pattern).
   */
  private String operatorName;

  /**
   * Operators precedence.
   */
  private int precedence;

  /**
   * Operator is left associative.
   */
  private boolean leftAssoc;

  /**
   * Creates a new operator.
   *
   * @param operatorName the operator name (pattern).
   * @param precedence the operators precedence.
   * @param leftAssoc @code{true} if the operator is left associative, else @code{false}.
   */
  public Operator(String operatorName, int precedence, boolean leftAssoc) {
    this.operatorName = operatorName;
    this.precedence = precedence;
    this.leftAssoc = leftAssoc;
  }

  /**
   * Operators name (pattern).
   *
   * @return Operators name (pattern)
   */
  public String getOperatorName() {
    return operatorName;
  }

  /**
   * Operators precedence.
   *
   * @return Operators precedence
   */
  public int getPrecedence() {
    return precedence;
  }

  /**
   * Operator is left associative.
   *
   * @return @code{true} if the operator is left associative, else @code{false}.
   */
  public boolean isLeftAssoc() {
    return leftAssoc;
  }

  /** PLUS operation. */
  public static final Operator PLUS = new Operator("+", 20, true);

  /** MINUS operation. */
  public static final Operator MINUS = new Operator("-", 20, true);

  /** MULTIPLY operation. */
  public static final Operator MULTIPLY = new Operator("*", 30, true);

  /** DIVISION operation. */
  public static final Operator DIVISION = new Operator("/", 30, true);

  /** MODULO operation. */
  public static final Operator MODULO = new Operator("%", 30, true);

  /** FRACTIONAL operation. */
  public static final Operator FRACTION = new Operator("^", 40, false);

  /** AND operation. */
  public static final Operator LOGICAL_AND = new Operator("&&", 4, false);

  /** OR operation. */
  public static final Operator LOGICAL_OR = new Operator("||", 2, false);

  /** GREATER operation. */
  public static final Operator GREATER = new Operator(">", 10, false);

  /** GREATER EQUAL operation. */
  public static final Operator GREATER_EQUAL = new Operator(">=", 10, false);

  /** SMALLER operation. */
  public static final Operator SMALLER = new Operator("<", 10, false);

  /** SMALLER EQUAL operation. */
  public static final Operator SMALLER_EQUAL = new Operator("<=", 10, false);

  /** EQUAL operation. */
  public static final Operator EQUAL = new Operator("=", 7, false);

  /** EQUAL operation. */
  public static final Operator EQUAL2 = new Operator("==", 7, false);

  /** NOT EQUAL operation. */
  public static final Operator NOT_EQUAL = new Operator("!=", 7, false);

  /** NOT_EQUAL operation. */
  public static final Operator NOT_EQUAL2 = new Operator("<>", 7, false);

  /** All operations. */
  public static final List<Operator> ALL_OPERATORS = unmodifiableList(Arrays.asList(
    PLUS, MINUS, MULTIPLY, DIVISION, MODULO, FRACTION, LOGICAL_AND, LOGICAL_OR,
    GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL,
    EQUAL, EQUAL2, NOT_EQUAL, NOT_EQUAL2
  ));

}
