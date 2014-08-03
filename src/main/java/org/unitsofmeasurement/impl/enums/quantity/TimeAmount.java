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

import static java.lang.Double.NaN;

import org.unitsofmeasurement.impl.enums.AbstractQuantity;
import org.unitsofmeasurement.impl.enums.format.SimpleFormat;
import org.unitsofmeasurement.impl.enums.unit.TimeUnit;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Time;

/**
 * @author Werner Keil
 * @version 0.6, $Date: 2014-08-03 $
 */
public class TimeAmount extends AbstractQuantity<Time> implements Time {
   private final double scalar; // value in reference unit

   private final Double value; // value in unit (Unit unit)

   private final TimeUnit unit;

//    TimeQuantity(){
//    }

    public TimeAmount(Double val, TimeUnit un) {
        value = val;
        unit = un;
        if (val!= null && un != null) {
        	scalar = val.doubleValue() * un.getFactor();
        } 
        else scalar = NaN;        
    }

    @Override
    public boolean isZero() {
        return (value != null) && 0d==(value.doubleValue());
    }

    public TimeAmount add(TimeAmount d1) {
        final TimeAmount dn = new TimeAmount(Double.valueOf(this.value.doubleValue() + d1.value.doubleValue()),
        		this.unit);
        return dn;
    }

    public TimeAmount subtract(TimeAmount d1) {
    	final TimeAmount dn = new TimeAmount(this.value.doubleValue() - d1.value.doubleValue(), this.unit);
        return dn;
    }

    public boolean eq(TimeAmount dq) {
         return dq!=null && dq.getValue().equals(getValue()) && dq.getUnit().equals(getUnit()) &&
                 dq.getScalar().equals(getScalar());
    }

    public boolean ne(TimeAmount d1) {
        return ne((TimeAmount) d1);
    }

    public boolean gt(TimeAmount d1) {
        return gt((TimeAmount) d1);
    }

    public boolean lt(TimeAmount d1) {
        return lt((TimeAmount) d1);
    }

    public boolean ge(TimeAmount d1) {
        return ge((TimeAmount)d1);
    }

    public boolean le(TimeAmount d1) {
        return le((TimeAmount) d1);
    }

    public TimeAmount divide(Double v) {
        return new TimeAmount(value.doubleValue() / v.doubleValue(), unit);
    }

    protected TimeAmount convert(TimeUnit newUnit) {
        return new TimeAmount(value.doubleValue() * (this.unit.getFactor() / newUnit.getFactor()), newUnit);
    }

    @Override
    public Number getScalar() {
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
    public String showInUnit(Unit<?> u, int precision, SimpleFormat.Show showWith) {
        return showInUnit(u, value, precision, showWith);
    }

	@Override
	public Number getValue() {
		 return value;
	}

	@Override
	public Unit<Time> getUnit() {
		 return unit;
	}

	@Override
	public Quantity<Time> multiply(Number that) {
		return new TimeAmount(value.doubleValue() * that.doubleValue(), unit);
	}

	@Override
	public Quantity<Time> to(Unit<Time> unit) {
		if (unit instanceof TimeUnit) {
        	return convert((TimeUnit)unit);
        } else {
        	throw new ArithmeticException("Cannot convert " + this.unit + " to " + unit);
        }
	}

	@Override
	public boolean eq(AbstractQuantity<Time> dq) {
		 return eq((TimeAmount) dq);
	}

	@Override
	public Quantity<?> divide(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Time> subtract(Quantity<Time> that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Time> add(Quantity<Time> that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Time> divide(Number that) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<Time> inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity<?> multiply(Quantity<?> that) {
		return new TimeAmount(value.doubleValue() * that.getValue().doubleValue(), unit);
	}
}
