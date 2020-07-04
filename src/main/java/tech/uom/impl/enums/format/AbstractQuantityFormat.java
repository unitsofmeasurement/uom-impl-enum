/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2020, Werner Keil and others.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Unit-API nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tech.uom.impl.enums.format;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import javax.measure.Quantity;
import javax.measure.format.MeasurementParseException;
import javax.measure.format.QuantityFormat;

import tech.uom.lib.common.function.Parser;

/**
 * <p>
 * This class provides the interface for formatting and parsing {@link Quantity quantities}.
 * </p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:werner@uom.technology">Werner Keil</a>
 * @version 1.1, $Date: 2019-03-11 $
 * @since 2.0
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractQuantityFormat extends Format implements QuantityFormat, Parser<CharSequence, Quantity> {
  // TODO for later, see https://github.com/unitsofmeasurement/indriya/issues/17
  // * <p>
  // * Instances of this class should be able to format quantities stated in {@link MixedUnit}.
  // * </p>

  /**
   *
   */
  private static final long serialVersionUID = -4628006924354248662L;

  /**
   * Formats the specified quantity into an <code>Appendable</code>.
   *
   * @param quantity
   *          the quantity to format.
   * @param dest
   *          the appendable destination.
   * @return the specified <code>Appendable</code>.
   * @throws IOException
   *           if an I/O exception occurs.
   */
  public abstract Appendable format(Quantity<?> quantity, Appendable dest) throws IOException;

  /**
   * Parses a portion of the specified <code>CharSequence</code> from the specified position to produce an object. If parsing succeeds, then the index
   * of the <code>cursor</code> argument is updated to the index after the last character used.
   *
   * @param csq
   *          the <code>CharSequence</code> to parse.
   * @param cursor
   *          the cursor holding the current parsing index.
   * @return the object parsed from the specified character sub-sequence.
   * @throws IllegalArgumentException
   *           if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
   */
  public abstract Quantity<?> parse(CharSequence csq, ParsePosition cursor) throws IllegalArgumentException, MeasurementParseException;

  /**
   * Parses a portion of the specified <code>CharSequence</code> from the specified position to produce an object. If parsing succeeds, then the index
   * of the <code>cursor</code> argument is updated to the index after the last character used.
   *
   * @param csq
   *          the <code>CharSequence</code> to parse.
   * @param cursor
   *          the cursor holding the current parsing index.
   * @return the object parsed from the specified character sub-sequence.
   * @throws IllegalArgumentException
   *           if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
   */
  @Override
  public abstract Quantity<?> parse(CharSequence csq) throws MeasurementParseException;

  /**
   * Parses a portion of the specified <code>CharSequence</code> from the specified position to produce an object. If parsing succeeds, then the index
   * of the <code>cursor</code> argument is updated to the index after the last character used.
   * 
   * @param csq
   *          the <code>CharSequence</code> to parse.
   * @param index
   *          the current parsing index.
   * @return the object parsed from the specified character sub-sequence.
   * @throws IllegalArgumentException
   *           if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
   */
  abstract Quantity<?> parse(CharSequence csq, int index) throws IllegalArgumentException, MeasurementParseException;

  @Override
  public final StringBuffer format(Object obj, final StringBuffer toAppendTo, FieldPosition pos) {
    if (!(obj instanceof Quantity<?>))
      throw new IllegalArgumentException("obj: Not an instance of Quantity");
    if ((toAppendTo == null) || (pos == null))
      throw new NullPointerException();
    return (StringBuffer) format((Quantity<?>) obj, toAppendTo);
  }

  @Override
  public final Quantity<?> parseObject(String source, ParsePosition pos) {
    try {
      return parse(source, pos);
    } catch (IllegalArgumentException | MeasurementParseException e) {
      return null;
    }
  }

  /**
   * Convenience method equivalent to {@link #format(Quantity, Appendable)} except it does not raise an IOException.
   *
   * @param quantity
   *          the quantity to format.
   * @param dest
   *          the appendable destination.
   * @return the specified <code>StringBuilder</code>.
   */
  protected final StringBuffer format(Quantity<?> quantity, StringBuffer dest) {
    try {
      return (StringBuffer) this.format(quantity, (Appendable) dest);
    } catch (IOException ex) {
      throw new RuntimeException(ex); // Should not happen.
    }
  }
  
  public final String format(Quantity<?> quantity) {
        return (this.format(quantity, new StringBuffer())).toString();
  }
}
