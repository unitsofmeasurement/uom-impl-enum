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
package tech.uom.impl.enums.unit;

import javax.measure.UnitConverter;

import tech.uom.impl.enums.format.SimpleUnitFormat;

import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;

/**
 * <p>
 * This class represents multi-radix units (such as "hour:min:sec" or "ft, in"). Instances of this class are created using the {@link Unit#mix
 * Unit.mix} method.
 * </p>
 * 
 * <p>
 * Examples of mixed units:<code> Unit<Time> HOUR_MINUTE_SECOND = HOUR.mix(MINUTE).mix(SECOND); <br>Unit<Length> FOOT_INCH =
 * FOOT.mix(INCH); </code>
 * </p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:werner@units.tech">Werner Keil</a>
 * @version 2.0, Oct 3, 2020
 * @since 2.0
 */
public final class MixedUnit<Q extends Quantity<Q>> implements Unit<Q> {

    /**
     * Holds the upper unit.
     */
    private final Unit<Q> upper;

    /**
     * Holds the lower unit.
     */
    private final Unit<Q> lower;

    /**
     * Creates a mixed unit from the specified units.
     *
     * @param up
     *            the upper unit.
     * @param low
     *            the lower unit(s)
     * @throws IllegalArgumentException
     *             if both units do not the same system unit.
     */
    public MixedUnit(Unit<Q> up, Unit<Q> low) {
        if (!up.getSystemUnit().equals(low.getSystemUnit()))
            throw new IllegalArgumentException("Both units do not have the same system unit");
        upper = up;
        lower = low;
    }

    /**
     * Returns the lower unit of this mixed unit.
     *
     * @return the lower unit.
     */
    public Unit<Q> getLower() {
        return lower;
    }

    /**
     * Returns the upper unit of this mixed unit.
     *
     * @return the upper unit.
     */
    public Unit<Q> getUpper() {
        return upper;
    }

    /**
     * Indicates if this mixed unit is considered equals to the specified object (both are mixed units with same composing units in the same
     * order).
     *
     * @param obj
     *            the object to compare for equality.
     * @return <code>true</code> if <code>this</code> and <code>obj</code> are considered equal; <code>false</code>otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MixedUnit) {
            MixedUnit<?> thatUnit = (MixedUnit<?>) obj;
            return this.upper.equals(thatUnit.upper) && this.lower.equals(thatUnit.lower);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return upper.hashCode() ^ lower.hashCode();
    }

    @Override
    public Unit<Q> getSystemUnit() {
        return lower.getSystemUnit();
    }

    @Override
    public Dimension getDimension() {
        return lower.getDimension();
    }

    @Override
    public String getSymbol() {
        return upper.getSymbol() + ":" + lower.getSymbol();
    }

    @Override
    public String getName() {
        return upper.getName() + ":" + lower.getName();
    }

    @Override
    public Map<? extends Unit<?>, Integer> getBaseUnits() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isCompatible(Unit<?> that) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T extends Quantity<T>> Unit<T> asType(Class<T> type) throws ClassCastException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> that) throws UnconvertibleException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UnitConverter getConverterToAny(Unit<?> that) throws IncommensurableException, UnconvertibleException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unit<Q> alternate(String symbol) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unit<Q> shift(double offset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unit<Q> multiply(double multiplier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unit<?> multiply(Unit<?> multiplier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unit<?> inverse() {
        return this;
    }

    @Override
    public Unit<Q> divide(double divisor) {
    	return this;
    }

    @Override
    public Unit<?> divide(Unit<?> divisor) {
    	return this;
    }

    @Override
    public Unit<?> root(int n) {
    	return this;
    }

    @Override
    public Unit<?> pow(int n) {
    	return this;
    }

    @Override
    public Unit<Q> transform(UnitConverter operation) {
    	return this;
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        return this.multiply(Math.pow(prefix.getValue().doubleValue(), prefix.getExponent()));
    }
    
    public Unit<Q> mix(Unit<Q> that) {
        return new MixedUnit<Q>(this, that);
    }
    
    /**
     * Returns the standard representation of this physics unit. The string produced
     * for a given unit is always the same; it is not affected by the locale. It can
     * be used as a canonical string representation for exchanging units, or as a
     * key for a Hashtable, etc.
     *
     * Locale-sensitive unit parsing could be handled using {@link LocalUnitFormat}
     * in subclasses of AbstractUnit.
     *
     * @return <code>SimpleUnitFormat.getInstance().format(this)</code>
     */
    @Override
    public String toString() {
        return SimpleUnitFormat.getInstance().format(this);
    }

	@Override
	public Unit<Q> shift(Number offset) {
		return this;
	}

	@Override
	public Unit<Q> multiply(Number multiplier) {
		return this;
	}

	@Override
	public Unit<Q> divide(Number divisor) {
		return this;
	}

	@Override
	public boolean isEquivalentTo(Unit<Q> that) {
		return equals(that);
	}
}
