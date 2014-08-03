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
package org.unitsofmeasurement.impl.enums.quantity;

import org.unitsofmeasurement.impl.enums.AbstractQuantity;
import org.unitsofmeasurement.impl.enums.format.SimpleFormat;
import org.unitsofmeasurement.impl.enums.unit.TemperatureUnit;

import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.function.UnitConverter;
import javax.measure.quantity.Temperature;

/**
 * @author Werner Keil
 * @version 0.7, $Date: 2014-08-03 $
 */
public final class TemperatureAmount extends AbstractQuantity<Temperature> 
  implements Temperature {
    private final Double scalar; // value in reference unit

    private final Double value; // value in unit (Unit unit)

    private final TemperatureUnit unit;

    public TemperatureAmount(Double val, TemperatureUnit un) {
        value = val;
        unit = un;
        if (val!= null && un != null) {
        	scalar = val.doubleValue() * un.getFactor();
        } 
        else scalar = null;        
    }
    
    public TemperatureAmount(Double val, Unit<Temperature> u) {
    	this(val, (TemperatureUnit)u);
    }

    @Override
    public boolean isZero() {
        return (value != null) && 0d==(value.doubleValue());
    }

    public TemperatureAmount add(TemperatureAmount d1) {
        final TemperatureAmount dn = new TemperatureAmount(Double.valueOf(
                this.value.doubleValue() + d1.value.doubleValue()),
        		this.unit);
        return dn;
    }

    public TemperatureAmount subtract(TemperatureAmount d1) {
    	final TemperatureAmount dn = new TemperatureAmount(
                this.value.doubleValue() - d1.value.doubleValue(), this.unit);
        return dn;
    }

    protected boolean eq(TemperatureAmount dq) {
         return dq!=null && dq.getValue().equals(getValue()) && 
                 dq.getUnit().equals(getUnit()) &&
                 dq.getScalar().equals(getScalar());
    }

    boolean ne(TemperatureAmount d1) {
        return ne((TemperatureAmount) d1);
    }

    boolean gt(TemperatureAmount d1) {
        return gt((TemperatureAmount) d1);
    }

    public boolean lt(TemperatureAmount d1) {
        return lt((TemperatureAmount) d1);
    }

    public boolean ge(TemperatureAmount d1) {
        return ge((TemperatureAmount)d1);
    }

    public boolean le(TemperatureAmount d1) {
        return le((TemperatureAmount) d1);
    }

    public TemperatureAmount divide(Double v) {
        return new TemperatureAmount(value.doubleValue() / v.doubleValue(), 
                unit);
    }

    protected TemperatureAmount convert(TemperatureUnit newUnit) {
        return new TemperatureAmount(value.doubleValue() /  
                newUnit.getFactor(), newUnit);
    }

    @Override
    public Double getScalar() {
        return scalar;
    }

    @Override
    public String toString(boolean withUnit, boolean withSpace, int precision) {
        final StringBuilder sb = new StringBuilder();
    	sb.append(getValue());
    	if(withUnit) {
        	if(withSpace) sb.append(" ");
    		sb.append(getUnit().getSymbol());
    	}
    	return sb.toString();
    }

    @Override
    public String showInUnit(Unit<?> u, int precision, 
            SimpleFormat.Show showWith) {
        return showInUnit(u, value, precision, showWith);
    }

	@Override
	public Number getValue() {
		 return value;
	}

	@Override
	public Unit<Temperature> getUnit() {
		 return unit;
	}

	@Override
	public Quantity<Temperature> multiply(Number that) {
		return new TemperatureAmount(value.doubleValue() * 
                        that.doubleValue(), unit);
	}
	
	@Override
	public Quantity<?> multiply(Quantity<?> that) {
		return new TemperatureAmount(value.doubleValue() * 
                that.getValue().doubleValue(), unit);
	}

	@Override
	public Quantity<Temperature> inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Measurement#doubleValue(javax.measure.Unit)
	 */
	protected double doubleValue(Unit<Temperature> unit) {
		Unit<Temperature> myUnit = getUnit();
		try {
			UnitConverter converter = unit.getConverterTo(myUnit);
			return converter.convert(getValue().doubleValue());
		} catch (UnconvertibleException e) {
			throw e;
		} // catch (IncommensurableException e) {
		// throw new IllegalArgumentException(e.getMessage());
		// }
	}
	
	@Override
	public Quantity<Temperature> to(Unit<Temperature> unit) {
        if (this.unit.equals(unit)) {
            return this;
        }
        if (unit instanceof TemperatureUnit) {
//        	final TemperatureUnit asTU = (TemperatureUnit)unit;
//        	for (TemperatureUnit tu : TemperatureUnit.values()) {
//        		if (asTU.equals(tu)) {
//        			return new TemperatureQuantity( asTU)
//        		}
//        	}
        	return convert((TemperatureUnit)unit);
        } else {
        	throw new ArithmeticException("Cannot convert " + this.unit + " to " + unit);
        }
	}

	@Override
	protected boolean eq(AbstractQuantity<Temperature> dq) {
		 return eq((TemperatureAmount) dq);
	}

	@Override
	public Quantity<?> divide(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Temperature> subtract(Quantity<Temperature> that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Temperature> add(Quantity<Temperature> that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Temperature> divide(Number that) {
		// TODO Auto-generated method stub
		return null;
	}
}
