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
/**
 *
 */
package org.unitsofmeasurement.impl.enums.unit;

import org.unitsofmeasurement.impl.enums.DescriptiveEnum;
import org.unitsofmeasurement.impl.enums.function.DoubleFactorSupplier;
import org.unitsofmeasurement.impl.enums.quantity.QuantityFactory;
import org.unitsofmeasurement.impl.model.SimpleDimension;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.function.UnitConverter;
import javax.measure.quantity.Length;

/**
 * @author Werner Keil
 * @version 1.3.1 ($Revision: 444 $), $Date: 2014-03-18 23:55:19 +0100 (Di, 18 Mär 2014) $
 */
public enum DistanceUnit implements Unit<Length>, DoubleFactorSupplier, DescriptiveEnum<DistanceUnit> {
    METRE("m", "m", 1.0), // reference Unit
	KILOMETRE("km", "km", 1.0e3);

    private final String symbol;
    private final String description;
    private final double multFactor;

    private DistanceUnit(final String symbol, final String name, double multF) {
        this.symbol = symbol;
    	this.description = name;
        this.multFactor = multF;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getFactor() {
        return multFactor;
    }

    @Override
	public Unit<Length> getSystemUnit() {
		return METRE;
    }

	@Override
	public String getName() {
		return name();
	}
    
    @Override
    public Map<? extends Unit<Length>, Integer> getProductUnits() {
        Map<Unit<Length>, Integer> prodUnits = new HashMap<Unit<Length>, Integer>();
        prodUnits.put(KILOMETRE, Integer.valueOf(3));
        return prodUnits;
    }

    public static DistanceUnit getBySymbol(String symbol) {
        if (KILOMETRE.getSymbol().equals(symbol)) {
            return KILOMETRE;
        }
        return METRE;
    }

    public UnitConverter getConverterTo(Unit<Length> that)
            throws UnconvertibleException {
        // currently unused
        return null;
    }

    public UnitConverter getConverterToAny(Unit<?> that)
            throws IncommensurableException, UnconvertibleException {
        // currently unused
        return null;
    }

    @Override
    public Unit<Length> alternate(String s) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    public Dimension getDimension() {
        return SimpleDimension.INSTANCE;
    }

     public Unit<?> inverse() {
        return this;
    }

    @Override
    public Unit<Length> divide(double v) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    @Override
    public Unit<?> divide(Unit<?> unit) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    public boolean isCompatible(Unit<?> that) {
        if (that instanceof DistanceUnit) return true;
        return false;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T extends Quantity<T>> Unit<T> asType(Class<T> tClass) {
        Unit<T> metricUnit = QuantityFactory.getInstance(tClass).getMetricUnit();
         if ((metricUnit == null) || metricUnit.isCompatible(this))
          return (Unit<T>) this;
           throw new ClassCastException("The unit: " + this //$NON-NLS-1$
               + " is not of parameterized type " + tClass); //$NON-NLS-1$
    }

    public Unit<Length> multiply(double factor) {
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

    public Unit<Length> transform(UnitConverter operation) {
        return this;
    }

    @Override
    public Unit<Length> shift(double v) {
        return this;
    }

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public DescriptiveEnum<DistanceUnit>[] iValues() {
		return DistanceUnit.values();
	}
}
