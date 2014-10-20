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
package tec.uom.impl.enums.unit;

import static tec.uom.impl.enums.unit.Constants.*;
import tec.uom.impl.enums.AbstractQuantityFactory;
import tec.uom.impl.enums.DescriptiveEnum;
import tec.uom.impl.enums.function.DoubleFactorSupplier;
import tec.uom.impl.enums.model.SimpleDimension;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.function.UnitConverter;
import javax.measure.quantity.Information;

/**
 * Implements a measure of information. The metric system unit for this quantity is "bit".
 * @author  <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.7.1 ($Revision: 444 $), $Date: 2014-03-18 23:55:19 +0100 (Di, 18 Mär 2014) $
 */
public enum BitUnit implements Unit<Information>, DoubleFactorSupplier, DescriptiveEnum<BitUnit> {
	
    BIT(BIT_NAME, 1.0), // reference Unit
    Byte(BYTE_NAME, BYTE_FACTOR),
	kb(KB_NAME, 1.0e3),
    Mb(MB_NAME, 1.0e6),
    Gb(GB_NAME, 1.0e9),
    Tb(TB_NAME, 1.012),
    Pb(PB_NAME, 1.015),
    Eb(EB_NAME, 1.018),
    KB(KBYTE_NAME, BYTE_FACTOR * 1.0e3),
    MB(MBYTE_NAME, BYTE_FACTOR * 1.0e6),
    GB(GBYTE_NAME, BYTE_FACTOR * 1.0e9),
    TB(TBYTE_NAME, BYTE_FACTOR * 1.0e12),
    PB(PBYTE_NAME, BYTE_FACTOR * 1.0e15),
    EB(EBYTE_NAME, BYTE_FACTOR * 1.0e18);

    private final String description;
    private final double multFactor;

    private BitUnit(String name, double multF) {
        this.description = name;
        this.multFactor = multF;
    }

    
    public String getSymbol() {
        return name();
    }

    
    public String getDescription() {
        return description;
    }

    
    public double getFactor() {
        return multFactor;
    }

    
	public Unit<Information> getSystemUnit() {
		return BIT;
    }

	
	public String getName() {
		return name();
	}
	
    public static BitUnit getByName(String symbol) {
        if (kb.name().equals(symbol)) {
            return kb;
        } else if (Mb.name().equals(symbol)) {
            return Mb;
        } else if (Gb.name().equals(symbol)) {
            return Gb;
        } else if (Tb.name().equals(symbol)) {
            return Tb;
        }
        return BIT;
    }

    
    public Map<Unit<?>, Integer> getProductUnits() {
        Map<Unit<?>, Integer> prodUnits = new HashMap<Unit<?>, Integer>();
        prodUnits.put(kb, Integer.valueOf(3));
        prodUnits.put(Mb, Integer.valueOf(6));
        prodUnits.put(Gb, Integer.valueOf(9));
        prodUnits.put(Tb, Integer.valueOf(12));
        prodUnits.put(Byte, Integer.valueOf((int)BYTE_FACTOR));
        prodUnits.put(KB, Integer.valueOf(3 * (int)BYTE_FACTOR));
        prodUnits.put(MB, Integer.valueOf(6 * (int)BYTE_FACTOR));
        prodUnits.put(GB, Integer.valueOf(9 * (int)BYTE_FACTOR));
        prodUnits.put(TB, Integer.valueOf(12 * (int)BYTE_FACTOR));
        return prodUnits;
    }

    
    public Unit<Information> shift(double offset) {
        return this;
    }

    
    public Unit<Information> alternate(String symbol) {
        return this;
    }

    @SuppressWarnings("unchecked")
	
    public <T extends Quantity<T>> Unit<T> asType(Class<T> type)
            throws ClassCastException {
        Unit<T> metricUnit = AbstractQuantityFactory.getInstance(type).getMetricUnit();
        if ((metricUnit == null) || metricUnit.isCompatible(this))
         return (Unit<T>) this;
          throw new ClassCastException("The unit: " + this //$NON-NLS-1$
              + " is not of parameterized type " + type); //$NON-NLS-1$
    }

    
    public Unit<Information> divide(double divisor) {
        return this;
    }

    
    public Unit<?> divide(Unit<?> that) {
        return this;
    }

    
    public UnitConverter getConverterTo(Unit<Information> that)
            throws UnconvertibleException {
        // currently unused
        return null;
    }

    
    public UnitConverter getConverterToAny(Unit<?> that)
            throws IncommensurableException, UnconvertibleException {
        // currently unused
        return null;
    }

    
    public Dimension getDimension() {
        return SimpleDimension.INSTANCE;
    }

    
    public Unit<?> inverse() {
        return this;
    }

    
    public boolean isCompatible(Unit<?> that) {
        if (that instanceof BitUnit) return true;
        return false;
    }

    
    public Unit<Information> multiply(double factor) {
        return this;
    }

    
    public Unit<?> multiply(Unit<?> that) {
    	if (!(that instanceof BitUnit)) {
    		throw new UnconvertibleException("Incompatible unit");
    	}
//        return new BitUnit(this.getSymbol(), this.longName(), 
//        		this.getMultFactor() * ((BitUnit)that).getMultFactor());
    	return this;
    }

    
    public Unit<?> pow(int n) {
        return this;
    }

    
    public Unit<?> root(int n) {
        return this;
    }

    
    public Unit<Information> transform(UnitConverter operation) {
        return this;
    }

    
    public DescriptiveEnum<BitUnit>[] iValues() {
		return BitUnit.values();
	}
}
