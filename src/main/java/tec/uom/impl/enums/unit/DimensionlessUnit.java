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
package tec.uom.impl.enums.unit;

import tec.uom.impl.enums.AbstractQuantityFactory;
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
import javax.measure.quantity.Dimensionless;

/**
 * @author Werner Keil
 * @version 1.1 ($Revision: 444 $), $Date: 2014-03-18 23:55:19 +0100 (Di, 18 Mär 2014) $
 */
public enum DimensionlessUnit implements Unit<Dimensionless>, DoubleFactorSupplier {
    ONE("", 1.0); // reference Unit

    private final String description;
    private final double multFactor;

    private DimensionlessUnit(String name, double multF) {
        this.description = name;
        this.multFactor = multF;
    }

    public String getSymbol() {
        return description;
    }

    public double getFactor() {
        return multFactor;
    }

    
	public Unit<Dimensionless> getSystemUnit() {
		return ONE;
    }

    
    public Map<? extends Unit<?>, Integer> getProductUnits() {
        Map<Unit<Dimensionless>, Integer> prodUnits = new HashMap<Unit<Dimensionless>, Integer>();
        prodUnits.put(ONE, Integer.valueOf(1));
        return prodUnits;
    }

    public static DimensionlessUnit getByName(String symbol) {
        return ONE;
    }

    public UnitConverter getConverterTo(Unit<Dimensionless> that)
            throws UnconvertibleException {
        // currently unused
        return null;
    }

    public UnitConverter getConverterToAny(Unit<?> that)
            throws IncommensurableException, UnconvertibleException {
        // currently unused
        return null;
    }

    
    public Unit<Dimensionless> alternate(String s) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

	
	public String getName() {
		return name();
	}
    
    public Dimension getDimension() {
        return SimpleDimension.INSTANCE;
    }

     public Unit<?> inverse() {
        return this;
    }

    
    public Unit<Dimensionless> divide(double v) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    
    public Unit<?> divide(Unit<?> unit) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    public boolean isCompatible(Unit<?> that) {
        if (that instanceof DimensionlessUnit) return true;
        return false;
    }

    @SuppressWarnings("unchecked")
	
    public <T extends Quantity<T>> Unit<T> asType(Class<T> tClass) {
        Unit<T> metricUnit = AbstractQuantityFactory.getInstance(tClass).getMetricUnit();
         if ((metricUnit == null) || metricUnit.isCompatible(this))
          return (Unit<T>) this;
           throw new ClassCastException("The unit: " + this //$NON-NLS-1$
               + " is not of parameterized type " + tClass); //$NON-NLS-1$
    }

    public Unit<Dimensionless> multiply(double factor) {
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

    public Unit<Dimensionless> transform(UnitConverter operation) {
        return this;
    }

    
    public Unit<Dimensionless> shift(double v) {
        return this;
    }
}
