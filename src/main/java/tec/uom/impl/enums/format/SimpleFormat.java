/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright 2013-2014, Jean-Marie Dautelle, Werner Keil, V2COM and individual
 *  contributors by the @author tag.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package tec.uom.impl.enums.format;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;

/**
 * <p>
 * This class provides a simple interface for formatting and parsing
 * {@linkplain org.unitsofmeasurement.unit.Unit units}.
 * </p>
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.7.1 ($Revision: 473 $), $Date: 2014-07-01 21:26:05 +0200 (Di, 01 Jul 2014) $
 */
public class SimpleFormat extends AbstractFormat {
    /**
     *
     */
    private static final long serialVersionUID = -7753687108842507677L;

    private final Map<String, String> symbolMap = new HashMap<String, String>(); // Diamond (Java 7+)

    private static final UnitFormat DEFAULT = new SimpleFormat();

    public static enum Show {
        SYMBOL, NAME;
    }

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
    SimpleFormat() {
    }

    // //////////////
    // Formatting //
    // //////////////
    public Appendable format(Unit<?> unit, Appendable appendable)
            throws IOException {
        CharSequence symbol;

        String mapSymbol = symbolMap.get(unit);
        if (mapSymbol != null) {
            symbol = mapSymbol;
        } else {
            throw new IllegalArgumentException(
                    "Symbol mapping for unit of type " + //$NON-NLS-1$
                            unit.getClass().getName() + " has not been set " + //$NON-NLS-1$
                            "(see UnitFormat.SymbolMap)"); //$NON-NLS-1$
        }

        appendable.append(symbol);

        return appendable;
    }
    
    public Locale getLocale() {
        return Locale.getDefault();
    }
}
