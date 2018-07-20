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
package tech.uom.impl.enums.quantity;

import static java.lang.Double.NaN;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Time;

import tech.uom.impl.enums.AbstractQuantity;
import tech.uom.impl.enums.format.UnitStyle;
import tech.uom.impl.enums.unit.TimeUnit;

/**
 * @author Werner Keil
 * @version 0.8, $Date: 2018-07-21 $
 */
public class TimeQuantity extends AbstractQuantity<Time> implements Time {
	private final double scalar; // value in reference unit

	private final Double value; // value in unit (Unit unit)
	private final TimeUnit unit;

	public TimeQuantity(Number val, TimeUnit un) {
		value = val.doubleValue();
		unit = un;
		if (val != null && un != null) {
			scalar = val.doubleValue() * un.getFactor();
		} else
			scalar = NaN;
	}

	public TimeQuantity(Number val, @SuppressWarnings("rawtypes") Unit un) {
		this(val.doubleValue(), (TimeUnit) un);
	}

	public boolean isZero() {
		return (value != null) && 0d == (value.doubleValue());
	}

	public TimeQuantity add(TimeQuantity d1) {
		final TimeQuantity dn = new TimeQuantity(Double.valueOf(this.value.doubleValue() + d1.value.doubleValue()),
				this.unit);
		return dn;
	}

	public TimeQuantity subtract(TimeQuantity d1) {
		final TimeQuantity dn = new TimeQuantity(this.value.doubleValue() - d1.value.doubleValue(), this.unit);
		return dn;
	}

	public boolean eq(TimeQuantity dq) {
		return dq != null && dq.getValue().equals(getValue()) && dq.getUnit().equals(getUnit())
				&& dq.getScalar().equals(getScalar());
	}

	public boolean ne(TimeQuantity d1) {
		return ne((TimeQuantity) d1);
	}

	public boolean gt(TimeQuantity d1) {
		return gt((TimeQuantity) d1);
	}

	public boolean lt(TimeQuantity d1) {
		return lt((TimeQuantity) d1);
	}

	public boolean ge(TimeQuantity d1) {
		return ge((TimeQuantity) d1);
	}

	public boolean le(TimeQuantity d1) {
		return le((TimeQuantity) d1);
	}

	public TimeQuantity divide(Double v) {
		return new TimeQuantity(value.doubleValue() / v.doubleValue(), unit);
	}

	protected TimeQuantity convert(TimeUnit newUnit) {
		return new TimeQuantity(value.doubleValue() * (this.unit.getFactor() / newUnit.getFactor()), newUnit);
	}

	public Number getScalar() {
		return scalar;
	}

	public String toString(boolean withUnit, boolean withSpace, int precision) {
		final StringBuilder sb = new StringBuilder();
		sb.append(getValue());
		if (withUnit) {
			if (withSpace)
				sb.append(" ");
			sb.append(getUnit().getSymbol());
		}
		return sb.toString();
	}

	public String showInUnit(Unit<?> u, int precision, UnitStyle style) {
		return showInUnit(u, value, precision, style);
	}

	public Number getValue() {
		return value;
	}

	public Unit<Time> getUnit() {
		return unit;
	}

	public Quantity<Time> multiply(Number that) {
		return new TimeQuantity(value.doubleValue() * that.doubleValue(), unit);
	}

	public Quantity<Time> to(Unit<Time> unit) {
		if (unit instanceof TimeUnit) {
			return convert((TimeUnit) unit);
		} else {
			throw new ArithmeticException("Cannot convert " + this.unit + " to " + unit);
		}
	}

	public boolean eq(AbstractQuantity<Time> dq) {
		return eq((TimeQuantity) dq);
	}

	public Quantity<?> divide(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Time> subtract(Quantity<Time> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Time> add(Quantity<Time> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Time> divide(Number that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Time> inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<?> multiply(Quantity<?> that) {
		return new TimeQuantity(value.doubleValue() * that.getValue().doubleValue(), unit);
	}

	public int compareTo(Quantity<Time> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Quantity<Time> negate() {
		return new TimeQuantity(-value, unit);
	}
}
