/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2018, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
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
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;

import javax.measure.Unit;
import javax.measure.format.MeasurementParseException;
import javax.measure.format.UnitFormat;

import tech.uom.impl.enums.unit.CompoundUnit;
import tech.uom.impl.enums.unit.DimensionlessUnit;
import tech.uom.impl.enums.unit.DistanceUnit;

/**
 * <p>
 * This class provides a simple interface for formatting and parsing {@linkplain org.unitsofmeasurement.unit.Unit units}.
 * </p>
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.7.2 ($Revision: 473 $), $Date: 2018-08-08 $
 */
public class SimpleUnitFormat extends AbstractUnitFormat {
    /**
     *
     */
    // private static final long serialVersionUID = -7753687108842507677L;

    private final Map<String, String> symbolMap = new HashMap<String, String>(); // Diamond (Java 7+)
    /**
     * Holds the unique symbols collection.
     */
    private static final Map<String, Unit<?>> unitMap = new HashMap<>();

    private static final UnitFormat DEFAULT = new SimpleUnitFormat();

    // /////////////////
    // Class methods //
    // /////////////////
    /** Returns the default instance for formatting */
    public static UnitFormat getInstance() {
        return DEFAULT;
    }

    // ////////////////
    // Constructors //
    // ////////////////
    /**
     * Base constructor.
     */
    SimpleUnitFormat() {
        unitMap.put("m", DistanceUnit.METRE);
    }

    // //////////////
    // Formatting //
    // //////////////
    public Appendable format(Unit<?> unit, Appendable appendable) throws IOException {
        // Compound unit.
        if (unit instanceof CompoundUnit) {
            CompoundUnit<?> cpdUnit = (CompoundUnit<?>) unit;
            final StringBuilder compoundable = new StringBuilder();
            compoundable.append(cpdUnit.getUpper().getSymbol());
            compoundable.append(":"); // FIXME we need a more flexible pattern here
            compoundable.append(cpdUnit.getLower().getSymbol());
            return compoundable;
        } else {
            CharSequence symbol;

            @SuppressWarnings("unlikely-arg-type")
            String mapSymbol = symbolMap.get(unit);
            if (mapSymbol != null) {
                symbol = mapSymbol;
            } else {
                throw new IllegalArgumentException("Symbol mapping for unit of type " + //$NON-NLS-1$
                        unit.getClass().getName() + " has not been set " + //$NON-NLS-1$
                        "(see UnitFormat.SymbolMap)"); //$NON-NLS-1$
            }

            appendable.append(symbol);

            return appendable;
        }
    }

    public void label(Unit<?> unit, String label) {
        // do nothing
    }

    public boolean isLocaleSensitive() {
        return false;
    }

    protected Unit<?> parse(CharSequence csq, int index) throws MeasurementParseException {
        // Parsing reads the whole character sequence from the parse position.
        int start = index; // cursor != null ? cursor.getIndex() : 0;
        int end = csq.length();
        if (end <= start) {
            return DimensionlessUnit.ONE;
        }
        final Unit<?> result = unitMap.get(csq);
        if (result != null) {
            return result;
        }
        throw new MeasurementParseException("Error", csq, index);
    }

    /**
     * Parses the specified character sequence to produce a unit (convenience method). If the specified sequence is empty, the unitary unit
     * (dimensionless) is returned.
     *
     * @param csq
     *            the <code>CharSequence</code> to parse.
     * @return the unit parsed from the specified character sub-sequence.
     * @throws ParseException
     *             if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
     */
    public final Unit<?> parse(CharSequence csq, ParsePosition pos) throws MeasurementParseException {
        return parse(csq, pos.getIndex());
    }
}
