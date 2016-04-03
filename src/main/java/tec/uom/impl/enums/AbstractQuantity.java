/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
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
package tec.uom.impl.enums;

import tec.uom.impl.enums.format.UnitStyle;
import tec.uom.impl.enums.function.DoubleFactorSupplier;
import tec.uom.impl.enums.function.QuantityConverter;

import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * @author Werner Keil
 * @version 0.13, $Date: 2016-04-03 $
 */
public abstract class AbstractQuantity<Q extends Quantity<Q>> implements 
	Quantity<Q>, QuantityConverter<Q>, Comparable<Quantity<Q>> {

    protected abstract Number getScalar();

    protected abstract boolean eq(AbstractQuantity<Q> dq);

    protected abstract boolean isZero();

    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object o) {
        if (o instanceof AbstractQuantity) {
            return eq((AbstractQuantity<Q>) o);
        }
        return false;
    }
    
    /**
     * Returns the hash code for this measure.
     *
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
      return getUnit().hashCode() + getValue().hashCode();
    }

    public abstract String toString(boolean withUnit, boolean withSpace, 
            int precision);

    public String toString(boolean withUnit, boolean withSpace) {
        return toString(withUnit, withSpace, 0);
    }

    /**
     * Casts this quantity to a parameterized quantity of specified nature or throw a
     * <code>ClassCastException</code> if the dimension of the specified
     * quantity and its unit's dimension do not match. For
     * example:<br/><code>
     *     Quantity<Length> length = BaseQuantity.of("2 km").asType(Length.class);
     * </code>
     *
     * @param type the quantity class identifying the nature of the measure.
     * @return this measure parameterized with the specified type.
     * @throws ClassCastException if the dimension of this unit is different
     *         from the specified quantity dimension.
     * @throws UnsupportedOperationException
     *             if the specified quantity class does not have a public static
     *             field named "UNIT" holding the SI unit for the quantity.
     * @see Unit#asType(Class)
     */
    @SuppressWarnings("unchecked")
    public final <T extends Quantity<T>> AbstractQuantity<T> asType(Class<T> type)
            throws ClassCastException {
        this.getUnit().asType(type); // Raises ClassCastException if dimension mismatches.
        return (AbstractQuantity<T>) this;
    }
    
    protected String toString(boolean withSpace) {
        return toString(true, withSpace);
    }

    protected String toString(int precision) {
        return toString(true, false, precision);
    }

    @Override
    public String toString() {
        return toString(false);
    }

    protected abstract String showInUnit(Unit<?> u, int precision, 
    		UnitStyle style);

    protected String showInUnit(Unit<?> u, Number s, int precision, 
    		UnitStyle style, boolean withSpace) {
        if (u == null) {
            throw new IllegalArgumentException("Null unit not allowed!");  //$NON-NLS-1$
        }
        double result;
        if (u instanceof DoubleFactorSupplier) {
            result = s.doubleValue() / ((DoubleFactorSupplier)u).getFactor();
        } else {
            result = s.doubleValue();
        }

        final String str = getStr(Double.valueOf(result), precision);
        StringBuilder sb;
        switch (style) {
            case NAME:
                sb = new StringBuilder(str);
                if (withSpace) sb.append(' ');
                return sb.append(u.getName()).toString();
                //return str;
            default:
                sb = new StringBuilder(str);
                if (withSpace) sb.append(' ');
                return sb.append(u.getSymbol()).toString();
        }
    }
    
    protected String showInUnit(Unit<?> u, Double s, int precision, 
    		UnitStyle style) {
        return showInUnit(u, s, precision, style, true);
    }

    /**
     *
     * @param u
     * @param precision number of decimal places
     * @return
     */
    protected String showInUnit(Unit<?> u, int precision) {
        return showInUnit(u, precision, UnitStyle.SYMBOL);
    }

    protected String getStr(Number val, int precision) {
//    	if (val instanceof BigDecimal) { //TODO for #JavaME disable that part
//    		BigDecimal num = ((BigDecimal)val).setScale(precision, RoundingMode.HALF_UP);
//    		String str = num.toString();
//    		return str;
//    	} 
    	return String.valueOf(val);
    }
}
