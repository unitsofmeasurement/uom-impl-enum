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
package tech.uom.impl.enums.unit;

import tech.uom.lib.common.function.DoubleFactorSupplier;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.Time;

/**
 * @author Werner Keil
 * @version 1.7, $Date: 2020-10-03 $
 */
public enum TimeUnit implements Unit<Time>, DoubleFactorSupplier {

	SECOND("s", 1.0), // reference Unit
	MINUTE("m", 60), HOUR("h", 60 * 60),
	MILLISECOND("ms", .001);

	private final String symbol;
	private final double multFactor;

	private TimeUnit(String s, double multF) {
		this.symbol = s;
		this.multFactor = multF;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name();
	}

	public double getFactor() {
		return multFactor;
	}

	public Unit<Time> getSystemUnit() {
		return SECOND;
	}

	public Map<? extends Unit<?>, Integer> getBaseUnits() {
		Map<Unit<Time>, Integer> prodUnits = new HashMap<Unit<Time>, Integer>();
		prodUnits.put(HOUR, Integer.valueOf(2));
		return prodUnits;
	}

	public static TimeUnit getBySymbol(String symbol) {
		if (HOUR.name().equals(symbol)) {
			return HOUR;
		}
		if (MINUTE.name().equals(symbol)) {
			return MINUTE;
		}
		return SECOND;
	}

	public UnitConverter getConverterTo(Unit<Time> that) throws UnconvertibleException {
		// currently unused
		return null;
	}

	public UnitConverter getConverterToAny(Unit<?> that) throws IncommensurableException, UnconvertibleException {
		// currently unused
		return null;
	}

	public Unit<Time> alternate(String s) {
		return this;
	}

	public Dimension getDimension() {
		return SimpleDimension.INSTANCE;
	}

	public Unit<?> inverse() {
		return this;
	}

	public Unit<Time> divide(double v) {
		return null; // To change body of implemented methods use File |
		// Settings | File TemplateBuilder.
	}

	public Unit<?> divide(Unit<?> unit) {
		return null; // To change body of implemented methods use File |
		// Settings | File TemplateBuilder.
	}

	public boolean isCompatible(Unit<?> that) {
		if (that instanceof TimeUnit)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public <T extends Quantity<T>> Unit<T> asType(Class<T> tClass) {
		Unit<T> metricUnit = (Unit<T>) getSystemUnit(); // AbstractQuantityFactory.getInstance(tClass).getSystemUnit();
		if ((metricUnit == null) || metricUnit.isCompatible(this))
			return (Unit<T>) this;
		throw new ClassCastException("The unit: " + this //$NON-NLS-1$
				+ " is not of parameterized type " + tClass); //$NON-NLS-1$
	}

	public Unit<Time> multiply(double factor) {
		for (TimeUnit tu : values()) {
			if (tu.getFactor() == factor) {
				return tu;
			}
		}
		return this;
	}

	public Unit<?> multiply(Unit<?> that) {
		return this;
	}

	public Unit<?> pow(int n) {
		return this;
	}

	public Unit<?> root(int n) {
		return this;
	}

	public Unit<Time> transform(UnitConverter operation) {
		return this;
	}

	public Unit<Time> shift(double v) {
		return this;
	}

	@Override
	public Unit<Time> prefix(Prefix prefix) {
		return this.multiply(Math.pow(prefix.getValue().doubleValue(), prefix.getExponent()));
	}

	@Override
	public Unit<Time> shift(Number offset) {
		return this;
	}

	@Override
	public Unit<Time> multiply(Number multiplier) {
		return this;
	}

	@Override
	public Unit<Time> divide(Number divisor) {
		return this;
	}

	@Override
	public boolean isEquivalentTo(Unit<Time> that) {
		return equals(that);
	}
}