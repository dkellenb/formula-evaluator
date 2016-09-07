package li.kellenberger.formulaevaluator;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Tests the Tokenizer.
 */
public class FormulaCompilerTokenizerTest {

  /** see method name. */
  @Test
  public void testNumbers() {
    FormulaCompiler<BigDecimal> e;
    Iterator<String> i;
    e = FormulaCompiler.create("1");
    i = e.getExpressionTokenizer();
    assertEquals("1", i.next());
    assertFalse(i.hasNext());
    assertNull(i.next());

    e = FormulaCompiler.create("-1");
    i = e.getExpressionTokenizer();
    assertEquals("-1", i.next());
    assertFalse(i.hasNext());
    assertNull(i.next());

    e = FormulaCompiler.create("123");
    i = e.getExpressionTokenizer();
    assertEquals("123", i.next());
    assertFalse(i.hasNext());
    assertNull(i.next());

    e = FormulaCompiler.create("-123");
    i = e.getExpressionTokenizer();
    assertEquals("-123", i.next());
    assertFalse(i.hasNext());
    assertNull(i.next());

    e = FormulaCompiler.create("123.4");
    i = e.getExpressionTokenizer();
    assertEquals("123.4", i.next());
    assertFalse(i.hasNext());
    assertNull(i.next());

    e = FormulaCompiler.create("-123.456");
    i = e.getExpressionTokenizer();
    assertEquals("-123.456", i.next());
    assertFalse(i.hasNext());
    assertNull(i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer1() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("1+2");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("1", i.next());
    assertEquals("+", i.next());
    assertEquals("2", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer2() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("1 + 2");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("1", i.next());
    assertEquals("+", i.next());
    assertEquals("2", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer3() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create(" 1 + 2 ");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("1", i.next());
    assertEquals("+", i.next());
    assertEquals("2", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer4() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("1+2-3/4*5");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("1", i.next());
    assertEquals("+", i.next());
    assertEquals("2", i.next());
    assertEquals("-", i.next());
    assertEquals("3", i.next());
    assertEquals("/", i.next());
    assertEquals("4", i.next());
    assertEquals("*", i.next());
    assertEquals("5", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer5() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("1+2.1-3.45/4.982*5.0");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("1", i.next());
    assertEquals("+", i.next());
    assertEquals("2.1", i.next());
    assertEquals("-", i.next());
    assertEquals("3.45", i.next());
    assertEquals("/", i.next());
    assertEquals("4.982", i.next());
    assertEquals("*", i.next());
    assertEquals("5.0", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer6() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("-3+4*-1");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("-3", i.next());
    assertEquals("+", i.next());
    assertEquals("4", i.next());
    assertEquals("*", i.next());
    assertEquals("-1", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer7() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("(-3+4)*-1/(7-(5*-8))");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("(", i.next());
    assertEquals("-3", i.next());
    assertEquals("+", i.next());
    assertEquals("4", i.next());
    assertEquals(")", i.next());
    assertEquals("*", i.next());
    assertEquals("-1", i.next());
    assertEquals("/", i.next());
    assertEquals("(", i.next());
    assertEquals("7", i.next());
    assertEquals("-", i.next());
    assertEquals("(", i.next());
    assertEquals("5", i.next());
    assertEquals("*", i.next());
    assertEquals("-8", i.next());
    assertEquals(")", i.next());
    assertEquals(")", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizer8() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("(1.9+2.8)/4.7");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("(", i.next());
    assertEquals("1.9", i.next());
    assertEquals("+", i.next());
    assertEquals("2.8", i.next());
    assertEquals(")", i.next());
    assertEquals("/", i.next());
    assertEquals("4.7", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizerFunction1() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("ABS(3.5)");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("ABS", i.next());
    assertEquals("(", i.next());
    assertEquals("3.5", i.next());
    assertEquals(")", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizerFunction2() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("3-ABS(3.5)/9");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("3", i.next());
    assertEquals("-", i.next());
    assertEquals("ABS", i.next());
    assertEquals("(", i.next());
    assertEquals("3.5", i.next());
    assertEquals(")", i.next());
    assertEquals("/", i.next());
    assertEquals("9", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizerFunction3() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("MAX(3.5,5.2)");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("MAX", i.next());
    assertEquals("(", i.next());
    assertEquals("3.5", i.next());
    assertEquals(",", i.next());
    assertEquals("5.2", i.next());
    assertEquals(")", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizerFunction4() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("3-MAX(3.5,5.2)/9");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("3", i.next());
    assertEquals("-", i.next());
    assertEquals("MAX", i.next());
    assertEquals("(", i.next());
    assertEquals("3.5", i.next());
    assertEquals(",", i.next());
    assertEquals("5.2", i.next());
    assertEquals(")", i.next());
    assertEquals("/", i.next());
    assertEquals("9", i.next());
  }

  /** see method name. */
  @Test
  public void testTokenizerFunction5() {
    FormulaCompiler<BigDecimal> e = FormulaCompiler.create("3/MAX(-3.5,-5.2)/9");
    Iterator<String> i = e.getExpressionTokenizer();
    assertEquals("3", i.next());
    assertEquals("/", i.next());
    assertEquals("MAX", i.next());
    assertEquals("(", i.next());
    assertEquals("-3.5", i.next());
    assertEquals(",", i.next());
    assertEquals("-5.2", i.next());
    assertEquals(")", i.next());
    assertEquals("/", i.next());
    assertEquals("9", i.next());
  }
}
