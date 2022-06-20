package lookandsaytest;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;

/**
 * Junit testing for look and say iterator class.
 */

public class LookAndSayIteratorTest {

  private RIterator firstSequence;
  private RIterator secondSequence;
  private RIterator thirdSequence;
  private RIterator largeSequence;


  @Before
  public void setUp() {

    firstSequence = new LookAndSayIterator<BigInteger>();
    secondSequence = new LookAndSayIterator<BigInteger>(new BigInteger("33"),
            new BigInteger("2113321113121211"));

    thirdSequence = new LookAndSayIterator<BigInteger>(new BigInteger("33"),
            new BigInteger("33333"));


    StringBuilder largeNumber = new StringBuilder();
    for (int index = 0; index < 8; index++) {
      largeNumber.append("1234567");
    }

    largeSequence = new LookAndSayIterator<BigInteger>(new BigInteger(largeNumber.toString()));
  }


  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorTest() {
    new LookAndSayIterator<BigInteger>(BigInteger.valueOf(-10), BigInteger.valueOf(121));
    new LookAndSayIterator<BigInteger>(BigInteger.valueOf(121), BigInteger.valueOf(200));
    new LookAndSayIterator<BigInteger>(BigInteger.valueOf(230), BigInteger.valueOf(121));
  }

  @Test(expected = IllegalArgumentException.class)
  public void SecondConstructorTest() {
    new LookAndSayIterator<BigInteger>(BigInteger.valueOf(-10));
    new LookAndSayIterator<BigInteger>(BigInteger.valueOf(121));
    new LookAndSayIterator<BigInteger>(BigInteger.valueOf(230));
  }

  @Test
  public void prev() {

    assertEquals("1", firstSequence.next().toString());
    assertEquals("11", firstSequence.next().toString());
    assertEquals("21", firstSequence.next().toString());
    assertEquals("11", firstSequence.prev().toString());
    assertEquals("1", firstSequence.prev().toString());

    assertEquals("33", secondSequence.next().toString());
    assertEquals("23", secondSequence.next().toString());
    assertEquals("1213", secondSequence.next().toString());
    assertEquals("11121113", secondSequence.next().toString());
    assertEquals("1213", secondSequence.prev().toString());
    assertEquals("23", secondSequence.prev().toString());
    assertEquals("33", secondSequence.prev().toString());
  }

  @Test
  public void hasPrevious() {
    thirdSequence.next();
    thirdSequence.next();
    thirdSequence.prev();
    assertEquals(true, thirdSequence.hasPrevious());
    thirdSequence.prev();
    assertEquals(false, thirdSequence.hasPrevious());

  }

  @Test
  public void hasNext() {
    assertEquals(true, secondSequence.hasNext());
    assertEquals(true, secondSequence.hasNext());
    assertEquals(true, firstSequence.hasNext());
    thirdSequence.next();
    thirdSequence.next();
    thirdSequence.next();
    assertEquals(false, thirdSequence.hasNext());
  }

  @Test
  public void next() {

    assertEquals("1", firstSequence.next().toString());
    assertEquals("11", firstSequence.next().toString());
    assertEquals("21", firstSequence.next().toString());
    assertEquals("33", secondSequence.next().toString());
    assertEquals("23", secondSequence.next().toString());
    assertEquals("1213", secondSequence.next().toString());
    assertEquals("11121113", secondSequence.next().toString());

  }

  @Test(expected = NoSuchElementException.class)
  public void testNextWithInvalidSequence() {
    largeSequence.next();
    largeSequence.next();
  }

  @Test(expected = NoSuchElementException.class)
  public void testPrev_IfPreviousSequenceIsInvalid() {
    secondSequence.next();
    secondSequence.next();
    secondSequence.next();
    assertEquals("The sequence is 1213, and end value is 2113321113121211",
            secondSequence.toString());

    assertEquals("23", secondSequence.prev().toString());
    assertEquals("33", secondSequence.prev().toString());
    secondSequence.prev();
    secondSequence.prev();
  }
}