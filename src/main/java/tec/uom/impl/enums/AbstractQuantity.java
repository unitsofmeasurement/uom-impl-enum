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
package tec.uom.impl.enums;

import tec.uom.impl.enums.format.SimpleFormat;
import tec.uom.impl.enums.function.DoubleFactorSupplier;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.function.Nameable;

/**
 * @author Werner Keil
 * @version 1.10 ($Revision: 450 $), $Date: 2014-04-03 03:37:47 +0200 (Do, 03 Apr 2014) $
 */
public abstract class AbstractQuantity<Q extends Quantity<Q>> implements 
	Quantity<Q> {

    protected abstract Number getScalar();

    protected abstract boolean eq(AbstractQuantity<Q> dq);

    protected abstract boolean isZero();

    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object o) {
        if (o instanceof AbstractQuantity) {
            return eq((AbstractQuantity<Q>) o);
        }
        return false;
    }

    public abstract String toString(boolean withUnit, boolean withSpace, 
            int precision);

    public String toString(boolean withUnit, boolean withSpace) {
        return toString(withUnit, withSpace, 0);
    }

    protected String toString(boolean withSpace) {
        return toString(true, withSpace);
    }

    protected String toString(int precision) {
        return toString(true, false, precision);
    }

    @Override
    public String toString() {
        return toString(false);
    }

    protected abstract String showInUnit(Unit<?> u, int precision, 
            SimpleFormat.Show show);

    protected String showInUnit(Unit<?> u, Number s, int precision, 
            SimpleFormat.Show show, boolean withSpace) {
        if (u == null) {
            throw new IllegalArgumentException("Null unit not allowed!");  //$NON-NLS-1$
        }
        double result;
        if (u instanceof DoubleFactorSupplier) {
            result = s.doubleValue() / ((DoubleFactorSupplier)u).getFactor();
        } else {
            result = s.doubleValue();
        }

        String str = getStr(Double.valueOf(result), precision);

        switch (show) {
            case NAME:
                if (u instanceof Nameable) {
                    StringBuilder sb = new StringBuilder(str);
                    if (withSpace) sb.append(' ');
                    return sb.append(((Nameable)u).getName()).toString();
                }
                return str;
            default:
                StringBuilder sb = new StringBuilder(str);
                if (withSpace) sb.append(' ');
                return sb.append(u.getSymbol()).toString();
        }
    }
    
    protected String showInUnit(Unit<?> u, Double s, int precision, 
            SimpleFormat.Show showItem) {
        return showInUnit(u, s, precision, showItem, true);
    }

    /**
     *
     * @param u
     * @param precision number of decimal places
     * @return
     */
    protected String showInUnit(Unit<?> u, int precision) {
        return showInUnit(u, precision, SimpleFormat.Show.SYMBOL);
    }

    protected String getStr(Number val, int precision) {
//    	if (val instanceof BigDecimal) { //TODO for #JavaME disable that part
//    		BigDecimal num = ((BigDecimal)val).setScale(precision, RoundingMode.HALF_UP);
//    		String str = num.toString();
//    		return str;
//    	} 
    	return String.valueOf(val);
    }
}
