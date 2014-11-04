/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2014, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
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
package tec.uom.impl.enums.quantity;

import tec.uom.impl.enums.AbstractQuantity;
import tec.uom.impl.enums.format.UnitStyle;
import tec.uom.impl.enums.unit.TemperatureUnit;

import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.Temperature;

/**
 * @author Werner Keil
 * @version 0.7.2, $Date: 2014-11-02 $
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
    		UnitStyle style) {
        return showInUnit(u, value, precision, style);
    }


	public Number getValue() {
		 return value;
	}


	public Unit<Temperature> getUnit() {
		 return unit;
	}


	public Quantity<Temperature> multiply(Number that) {
		return new TemperatureAmount(value.doubleValue() * 
                        that.doubleValue(), unit);
	}
	

	public Quantity<?> multiply(Quantity<?> that) {
		return new TemperatureAmount(value.doubleValue() * 
                that.getValue().doubleValue(), unit);
	}


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


	protected boolean eq(AbstractQuantity<Temperature> dq) {
		 return eq((TemperatureAmount) dq);
	}


	public Quantity<?> divide(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}


	public Quantity<Temperature> subtract(Quantity<Temperature> that) {
		// TODO Auto-generated method stub
		return null;
	}


	public Quantity<Temperature> add(Quantity<Temperature> that) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Quantity<Temperature> divide(Number that) {
		// TODO Auto-generated method stub
		return null;
	}

	public int compareTo(Quantity<Temperature> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
