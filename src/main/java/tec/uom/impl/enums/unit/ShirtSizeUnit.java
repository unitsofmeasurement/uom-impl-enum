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
package tec.uom.impl.enums.unit;

import tec.uom.impl.enums.DescriptiveEnum;
import tec.uom.impl.enums.quantity.ShirtSize;
import tec.uom.impl.enums.quantity.SimpleDimension;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.UnitConverter;

/**
 * @author Werner Keil
 * @version 0.1, $Date: 2014-08-28 $
 */
public enum ShirtSizeUnit implements Unit<ShirtSize>,
	DescriptiveEnum<ShirtSizeUnit> {
    SML("S-M-L", "Small to Large");

    private final String symbol;
    private final String description;

    private ShirtSizeUnit(final String symbol, final String name) {
	this.symbol = symbol;
	this.description = name;
    }

    public String getSymbol() {
	return symbol;
    }

    public Unit<ShirtSize> getSystemUnit() {
	return SML;
    }

    public String getName() {
	return name();
    }

    public Map<? extends Unit<ShirtSize>, Integer> getBaseUnits() {
	Map<Unit<ShirtSize>, Integer> prodUnits = new HashMap<Unit<ShirtSize>, Integer>();
	prodUnits.put(SML, Integer.valueOf(1));
	return prodUnits;
    }

    public static ShirtSizeUnit getBySymbol(String symbol) {
	if (SML.getSymbol().equals(symbol)) {
	    return SML;
	}
	return SML;
    }

    public UnitConverter getConverterTo(Unit<ShirtSize> that)
	    throws UnconvertibleException {
	// currently unused
	return null;
    }

    public UnitConverter getConverterToAny(Unit<?> that)
	    throws IncommensurableException, UnconvertibleException {
	// currently unused
	return null;
    }

    public Unit<ShirtSize> alternate(String s) {
	return null; // To change body of implemented methods use File |
		     // Settings | File TemplateBuilder.
    }

    public Dimension getDimension() {
	return SimpleDimension.INSTANCE;
    }

    public Unit<?> inverse() {
	return this;
    }

    public Unit<ShirtSize> divide(double v) {
	return null; // To change body of implemented methods use File |
		     // Settings | File TemplateBuilder.
    }

    public Unit<?> divide(Unit<?> unit) {
	return null; // To change body of implemented methods use File |
		     // Settings | File TemplateBuilder.
    }

    public boolean isCompatible(Unit<?> that) {
	if (that instanceof ShirtSizeUnit)
	    return true;
	return false;
    }

    @SuppressWarnings("unchecked")
    public <T extends Quantity<T>> Unit<T> asType(Class<T> tClass) {
	Unit<T> metricUnit = (Unit<T>) getSystemUnit();
	if ((metricUnit == null) || metricUnit.isCompatible(this))
	    return (Unit<T>) this;
	throw new ClassCastException("The unit: " + this //$NON-NLS-1$
		+ " is not of parameterized type " + tClass); //$NON-NLS-1$
    }

    public Unit<ShirtSize> multiply(double factor) {
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

    public Unit<ShirtSize> transform(UnitConverter operation) {
	return this;
    }

    public Unit<ShirtSize> shift(double v) {
	return this;
    }

    public String getDescription() {
	return description;
    }

    public DescriptiveEnum<ShirtSizeUnit>[] dValues() {
	return ShirtSizeUnit.values();
    }

    public Map getProductUnits() {
	throw new UnsupportedOperationException("Use getBaseUnits() instead");
    }
}
