package com.github.dkellenb.formulaevaluator.definition;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

/**
 * Abstract definition of a supported expression function. A function is defined by a name, the number of parameters and
 * the actual processing implementation.
 */
public class Function {

  /**
   * Name of this function.
   */
  private String name;

  /**
   * Number of parameters expected for this function.
   */
  private int numParams;

  /**
   * Creates a new function with given name and parameter count.
   *
   * @param name the name of the function.
   * @param numParams the number of parameters for this function.
   */
  public Function(String name, int numParams) {
    this.name = name.toUpperCase();
    this.numParams = numParams;
  }

  /**
   * The name of the function.
   *
   * @return the name of the function
   */
  public String getName() {
    return name;
  }

  /**
   * Number of parameters expected for this function.
   *
   * @return number of parameters expected
   */
  public int getNumParams() {
    return numParams;
  }

  /** NOT function. */
  public static final Function NOT = new Function("NOT", 1);

  /** IF function. */
  public static final Function IF = new Function("IF", 3);

  /** RANDOM function. */
  public static final Function RANDOM = new Function("RANDOM", 0);

  /** SIN function. */
  public static final Function SIN = new Function("SIN", 1);

  /** COS function. */
  public static final Function COS = new Function("COS", 1);

  /** TAN function. */
  public static final Function TAN = new Function("TAN", 1);

  /** SINH function. */
  public static final Function SINH = new Function("SINH", 1);

  /** COSH function. */
  public static final Function COSH = new Function("COSH", 1);

  /** TANH function. */
  public static final Function TANH = new Function("TANH", 1);

  /** RAD function. */
  public static final Function RAD = new Function("RAD", 1);

  /** DEG function. */
  public static final Function DEG = new Function("DEG", 1);

  /** MAX function. */
  public static final Function MAX = new Function("MAX", 2);

  /** MIN function. */
  public static final Function MIN = new Function("MIN", 2);

  /** ABS function. */
  public static final Function ABS = new Function("ABS", 1);

  /** LOG function. */
  public static final Function LOG = new Function("LOG", 1);

  /** LOG10 function. */
  public static final Function LOG10 = new Function("LOG10", 1);

  /** ROUND function. */
  public static final Function ROUND = new Function("ROUND", 2);

  /** FLOOR function. */
  public static final Function FLOOR = new Function("FLOOR", 1);

  /** CEILING function. */
  public static final Function CEILING = new Function("CEILING", 1);

  /** SQRT function. */
  public static final Function SQRT = new Function("SQRT", 1);

  /** All operations. */
  public static final List<Function> ALL_FUNCTIONS = unmodifiableList(Arrays.asList(
    NOT, IF, RANDOM,
    SIN, COS, TAN, SINH, COSH, TANH, RAD, DEG,
    MAX, MIN, ABS, LOG, LOG10,
    ROUND, FLOOR, CEILING, SQRT
  ));

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Function function = (Function) o;
    return Objects.equals(name, function.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

}
