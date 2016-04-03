/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363, Unit-API nor the names of their contributors may be used to endorse or promote products
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
package tec.uom.impl.enums.format;

import java.io.IOException;

import javax.measure.Unit;
import javax.measure.format.ParserException;
import javax.measure.format.UnitFormat;

/**
 * <p>
 * This class provides the interface for formatting and parsing
 * {@linkplain javax.measure.Unit units}.
 * </p>
 * 
 * <p>
 * The {@linkplain #getStandard standard} instance (UCUM) recognizes all metric
 * units and the 20 SI prefixes used to form decimal multiples and some
 * customory units (see <a href="http://org.unitsofmeasure">UCUM</a>
 * specification). For example:
 * 
 * <pre><code>
 *        AbstractFormat.getInstance().parse("kW").equals(KILO(WATT))
 *        AbstractFormat.getInstance().parse("[ft_i]").equals(METRE.multiply(3048).divide(10000))
 *        AbstractFormat.getInstance(Locale.USA).parse("ft").equals(METRE.multiply(3048).divide(10000))
 * [/code]
 * </p>
 * 
 * <p>
 * OSGi bundles should use {@link javax.measure.format.UnitFormat} to
 * parse/format {@linkplain #getStandardInstance() standard} (UCUM) units.
 * </p>
 * 
 * @author <a href="mailto:werner.keil@gmail.com">Werner Keil</a>
 * @version 0.5.1 $Date: 2015-09-25 $
 * @see <a href="http://unitsofmeasure.org">Unified Code of Measure (UCUM)</a>
 */
abstract class AbstractFormat implements UnitFormat {
	// TODO for #JavaME don't use java.text

	/** The serialVersionUID */
	// private static final long serialVersionUID = 7765623276257908888L;

	/**
	 * Returns the unit format for the default locale.
	 * 
	 * @return the locale format.
	 */
	public static UnitFormat getInstance() {
		return SimpleUnitFormat.getInstance();
	}

	/**
	 * Base constructor.
	 */
	protected AbstractFormat() {
	}

	/**
	 * Formats the specified unit.
	 * 
	 * @param unit
	 *            the unit to format.
	 * @param appendable
	 *            the appendable destination.
	 * @return The appendable destination passed in as {@code appendable}, with
	 *         formatted text appended.
	 * @throws java.io.IOException
	 *             if an error occurs.
	 */
	public abstract Appendable format(Unit<?> unit, Appendable appendable)
			throws IOException;

	/**
	 * Formats an object to produce a string. This is equivalent to <blockquote>
	 * {@link #format(Unit, StringBuilder) format}<code>(unit,
	 *         new StringBuilder()).toString();</code> </blockquote>
	 *
	 * @param obj
	 *            The object to format
	 * @return Formatted string.
	 * @exception IllegalArgumentException
	 *                if the Format cannot format the given object
	 */
	public final String format(Unit<?> unit) {
		try {
			return (this.format(unit, new StringBuilder())).toString();
		} catch (IOException ex) {
			throw new ParserException(ex); // Should never happen.
		}

	}

	/**
	 * Parses a portion of the specified <code>CharSequence</code> from the
	 * specified position to produce a unit. If there is no unit to parse the
	 * unitary unit (dimensionless) is returned.
	 * 
	 * @param csq
	 *            the <code>CharSequence</code> to parse.
	 * @param cursor
	 *            the cursor holding the current parsing index or <code>
	 *        null</code> to parse the whole character sequence.
	 * @return the unit parsed from the specified character sub-sequence.
	 * @throws IllegalArgumentException
	 *             if any problem occurs while parsing the specified character
	 *             sequence (e.g. illegal syntax).
	 */
	// public Unit<?> parse(CharSequence csq, ParsePosition cursor)
	// throws IllegalArgumentException {
	// throw new UnsupportedOperationException("Not supported yet.");
	// }

	/**
	 * Parses the specified character sequence to produce a unit (convenience
	 * method). If the specified sequence is empty, the unitary unit
	 * (dimensionless) is returned.
	 * 
	 * @param csq
	 *            the <code>CharSequence</code> to parse.
	 * @return the unit parsed from the specified character sub-sequence.
	 * @throws IllegalArgumentException
	 *             if any problem occurs while parsing the specified character
	 *             sequence (e.g. illegal syntax).
	 */
	public final Unit<?> parse(CharSequence csq)
			throws IllegalArgumentException {
		return parse(csq);
	}

	public final StringBuffer format(Object obj, final StringBuffer toAppendTo) {
		if (!(obj instanceof Unit<?>))
			throw new IllegalArgumentException("obj: Not an instance of Unit"); //$NON-NLS-1$
		if ((toAppendTo == null))
			throw new NullPointerException(); // Format contract.
		try {
			return (StringBuffer) format((Unit<?>) obj, (Appendable) toAppendTo);
		} catch (IOException ex) {
			throw new Error(ex); // Cannot happen.
		}
	}

	public final Unit<?> parseObject(String source) {
		try {
			return parse(source);
		} catch (IllegalArgumentException e) {
			return null; // Unfortunately the message why the parsing failed
		} // is lost; but we have to follow the Format spec.

	}

}
