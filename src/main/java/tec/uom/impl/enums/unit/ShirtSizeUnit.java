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

import tec.uom.impl.enums.DescriptiveEnum;
import tec.uom.impl.enums.model.SimpleDimension;
import tec.uom.impl.enums.quantity.QuantityFactory;
import tec.uom.impl.enums.quantity.ShirtSize;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.function.UnitConverter;

/**
 * @author Werner Keil
 * @version 0.1, $Date: 2014-08-28 $
 */
public enum ShirtSizeUnit implements Unit<ShirtSize>, DescriptiveEnum<ShirtSizeUnit> {
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

    @Override
	public Unit<ShirtSize> getSystemUnit() {
		return null;
    }

	@Override
	public String getName() {
		return name();
	}
    
    @Override
    public Map<? extends Unit<ShirtSize>, Integer> getProductUnits() {
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

    @Override
    public Unit<ShirtSize> alternate(String s) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    public Dimension getDimension() {
        return SimpleDimension.INSTANCE;
    }

     public Unit<?> inverse() {
        return this;
    }

    @Override
    public Unit<ShirtSize> divide(double v) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    @Override
    public Unit<?> divide(Unit<?> unit) {
        return null;  //To change body of implemented methods use File | Settings | File TemplateBuilder.
    }

    public boolean isCompatible(Unit<?> that) {
        if (that instanceof ShirtSizeUnit) return true;
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

    @Override
    public Unit<ShirtSize> shift(double v) {
        return this;
    }

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public DescriptiveEnum<ShirtSizeUnit>[] iValues() {
		return ShirtSizeUnit.values();
	}
}
