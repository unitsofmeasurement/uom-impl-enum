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
package tech.uom.impl.enums.quantity;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;

import tech.uom.impl.enums.AbstractQuantity;
import tech.uom.impl.enums.format.UnitStyle;
import tech.uom.impl.enums.unit.DimensionlessUnit;

/**
 * @author Werner Keil
 * @version 0.7
 */
public class DimensionlessQuantity extends AbstractQuantity<Dimensionless> implements Dimensionless {
	private final double scalar;
	private final Double value; // value in unit (Unit unit)

	private final Unit unit;

	
	public DimensionlessQuantity(double val, Unit un) {
		value = val;
		unit = un;
		scalar = 1;
	}

	public DimensionlessQuantity(Number val, Unit un) {
		this(val.doubleValue(), un);
	}
	
	public DimensionlessQuantity multiply(double v) {
		return new DimensionlessQuantity(value * v, (DimensionlessUnit) unit);
	}

	public DimensionlessQuantity divide(double v) {
		return new DimensionlessQuantity(value / v, (DimensionlessUnit) unit);
	}

	public DimensionlessQuantity convert(DimensionlessUnit newUnit) {
		return new DimensionlessQuantity(scalar, newUnit);
	}

	public Quantity<?> divide(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Dimensionless> to(Unit<Dimensionless> unit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Dimensionless> subtract(Quantity<Dimensionless> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Dimensionless> add(Quantity<Dimensionless> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Dimensionless> divide(Number that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Dimensionless> inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Dimensionless> multiply(Number that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<?> multiply(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Area multiply(Dimensionless l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Unit<Dimensionless> getUnit() {
		return unit;
	}

	@Override
	public int compareTo(Quantity<Dimensionless> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Number getScalar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean eq(AbstractQuantity<Dimensionless> dq) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString(boolean withUnit, boolean withSpace, int precision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String showInUnit(Unit<?> u, int precision, UnitStyle style) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Dimensionless> negate() {
		return new DimensionlessQuantity(-value.doubleValue(), unit);
	}
}
