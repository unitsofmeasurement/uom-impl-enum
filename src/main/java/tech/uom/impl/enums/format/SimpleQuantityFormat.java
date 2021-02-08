/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2021, Werner Keil and others.
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
import java.math.BigDecimal;
import java.text.ParsePosition;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.format.MeasurementParseException;

import tech.uom.impl.enums.quantity.Quantities;
import tech.uom.impl.enums.unit.DimensionlessUnit;

/**
 * A simple implementation of QuantityFormat
 */
@SuppressWarnings("rawtypes")
public class SimpleQuantityFormat extends AbstractQuantityFormat {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2447723879527543130L;
	
	/**
	 * Holds the default format instance.
	 */
	private static final SimpleQuantityFormat DEFAULT = new SimpleQuantityFormat();

	@Override
	public Appendable format(Quantity quantity, Appendable dest) throws IOException {
		Unit unit = quantity.getUnit();

		dest.append(quantity.getValue().toString());
		if (quantity.getUnit().equals(DimensionlessUnit.ONE))
			return dest;
		dest.append(' ');
		return SimpleUnitFormat.getInstance().format(unit, dest);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Quantity<?> parse(CharSequence csq, ParsePosition cursor) throws MeasurementParseException {
		int startDecimal = cursor.getIndex();
		while ((startDecimal < csq.length()) && Character.isWhitespace(csq.charAt(startDecimal))) {
			startDecimal++;
		}
		int endDecimal = startDecimal + 1;
		while ((endDecimal < csq.length()) && !Character.isWhitespace(csq.charAt(endDecimal))) {
			endDecimal++;
		}
		BigDecimal decimal = new BigDecimal(csq.subSequence(startDecimal, endDecimal).toString());
		cursor.setIndex(endDecimal + 1);
		Unit unit = SimpleUnitFormat.getInstance().parse(csq);
		return Quantities.getQuantity(decimal, unit);
	}

	@SuppressWarnings("unchecked")
	@Override
	Quantity<?> parse(CharSequence csq, int index) throws MeasurementParseException {
		int startDecimal = index; // cursor.getIndex();
		while ((startDecimal < csq.length()) && Character.isWhitespace(csq.charAt(startDecimal))) {
			startDecimal++;
		}
		int endDecimal = startDecimal + 1;
		while ((endDecimal < csq.length()) && !Character.isWhitespace(csq.charAt(endDecimal))) {
			endDecimal++;
		}
		Double decimal = new Double(csq.subSequence(startDecimal, endDecimal).toString());
		Unit unit = SimpleUnitFormat.getInstance().parse(csq);
		return Quantities.getQuantity(decimal, unit);
	}

	@Override
	public Quantity<?> parse(CharSequence csq) throws MeasurementParseException {
		return parse(csq, new ParsePosition(0));
	}

  /**
   * Returns the quantity format for the default locale. The default format assumes the quantity is composed of a decimal number and a {@link Unit}
   * separated by whitespace(s).
   *
   * @return <code>MeasureFormat.getInstance(NumberFormat.getInstance(), UnitFormat.getInstance())</code>
   */
  public static SimpleQuantityFormat getInstance() {
    return DEFAULT;
  }
}