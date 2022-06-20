package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * Look and say iterator class represented in terms of the current number and the next number
 * obtained by reading out loud the current number and writing it out.
 *
 * @param <T> the type of element that is being iterated over.
 */
public class LookAndSayIterator<T> implements RIterator<T> {

  private BigInteger current;
  private BigInteger next;
  private final BigInteger end;
  private static final BigInteger DEFAULT_SEED = new BigInteger("1");
  private static final BigInteger DEFAULT_END = new BigInteger("9".repeat(100));

  /**
   * Constructor for look and say class that takes a starting seed and an end value.
   *
   * @param seed starting seed value.
   * @param end  end value.
   * @throws IllegalArgumentException if seed is greater than end or less than zero or
   *                                  contains zeroes in it.
   */

  public LookAndSayIterator(BigInteger seed, BigInteger end) throws IllegalArgumentException {
    if (seed.compareTo(new BigInteger("0")) <= 0
            || seed.compareTo(end) >= 0
            || seed.toString().contains("0")) {
      throw new IllegalArgumentException("Invalid seed: " + seed);
    }
    this.next = seed;
    this.current = this.next;
    this.end = end;
  }

  /**
   * Constructor for look and say class that takes a starting seed and an default end value.
   *
   * @param seed starting seed value.
   * @throws IllegalArgumentException if seed is greater than end or less than zero or
   *                                  contains zeroes in it.
   */

  public LookAndSayIterator(BigInteger seed) throws IllegalArgumentException {
    this(seed, DEFAULT_END);
    if (seed.compareTo(new BigInteger("0")) <= 0
            || seed.compareTo(end) >= 0
            || seed.toString().contains("0")) {
      throw new IllegalArgumentException("Invalid seed: " + seed);
    }
  }

  /**
   * Constructor for look and say class that takes a default seed and default end value.
   */
  public LookAndSayIterator() {
    this(DEFAULT_SEED, DEFAULT_END);
  }

  @Override
  public T prev() throws NoSuchElementException {
    if (!this.hasPrevious()) {
      throw new NoSuchElementException("Previous sequence does not exist");
    }
    this.next = this.current;
    this.current = getPreviousNumberSequence(this.current);
    return (T) this.current;
  }


  @Override
  public boolean hasPrevious() {
    if (this.current.equals(DEFAULT_SEED)) {
      return false;
    }
    try {
      return (getPreviousNumberSequence(this.current).compareTo(end)) < 0;
    } catch (StringIndexOutOfBoundsException e) {
      return false;
    }
  }

  @Override
  public boolean hasNext() {
    return this.next.compareTo(this.end) <= 0;
  }

  @Override
  public T next() throws NoSuchElementException {
    if (!this.hasNext()) {
      throw new NoSuchElementException("Next sequence does not exist");
    }
    this.current = this.next;
    this.next = getNextNumberSequence();
    return (T) this.current;
  }

  @Override
  public String toString() {
    return String.format("The sequence is %d, and end value is %d", this.current,
            this.end);
  }


  private BigInteger getNextNumberSequence() {
    String currentNumberSequence = this.next.toString();
    StringBuilder nextNumberSequence = new StringBuilder();
    int counter = 1;
    int priorDigit = Character.getNumericValue(currentNumberSequence.charAt(0));
    for (int i = 1; i < currentNumberSequence.length(); i++) {
      int currentDigit = Character.getNumericValue(currentNumberSequence.charAt(i));
      if (currentDigit == priorDigit) {
        counter = counter + 1;
      } else {
        nextNumberSequence.append(counter).append(priorDigit);
        priorDigit = currentDigit;
        counter = 1;
      }
    }
    nextNumberSequence.append(counter).append(priorDigit);
    return new BigInteger(nextNumberSequence.toString());
  }


  private BigInteger getPreviousNumberSequence(BigInteger current) {
    String currentNumberSequence = current.toString();
    StringBuilder previousNumberSequence = new StringBuilder();

    for (int index = 0; index < currentNumberSequence.length(); index += 2) {
      int count = Character.digit(currentNumberSequence.charAt(index), 10);
      int currentDigit = Character.digit(currentNumberSequence.charAt(index + 1), 10);
      previousNumberSequence.append(String.valueOf(currentDigit).repeat(Math.max(0, count)));
    }
    return new BigInteger(previousNumberSequence.toString());
  }
}
