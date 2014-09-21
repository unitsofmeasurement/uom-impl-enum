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
package tec.uom.impl.enums.function;

import java.util.ArrayList;
import java.util.List;

import javax.measure.function.UnitConverter;

/**
 * <p> The base class for our {@link UnitConverter} physics implementations.</p>
 *
 * @author  <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.7.1, $Date: 2014-09-07 $
 */
public abstract class AbstractConverter implements UnitConverter {
	
    /**
	 * 
	 */
	//private static final long serialVersionUID = 5790242858468427131L;

	/**
     * The ratio of the circumference of a circle to its diameter.
     **/
    protected static final double PI = 3.1415926535897932384626433832795;
    
    /**
     * Holds identity converter.
     */
    public static final AbstractConverter IDENTITY = new Identity();

    /**
     * Default constructor.
     */
    protected AbstractConverter() {
    }

    /**
     * Concatenates this physics converter with another physics converter.
     * The resulting converter is equivalent to first converting by the
     * specified converter (right converter), and then converting by
     * this converter (left converter).
     *
     * @param that the other converter.
     * @return the concatenation of this converter with that converter.
     */
    public AbstractConverter concatenate(AbstractConverter that) {
        return (that == IDENTITY) ? this : new Pair(this, that);
    }

    public boolean isIdentity() {
        return false;
    }

    @Override
    public abstract boolean equals(Object cvtr);

    @Override
    public abstract int hashCode();

    public abstract AbstractConverter inverse();

    public UnitConverter concatenate(UnitConverter converter) {
        return (converter == IDENTITY) ? this : new Pair(this, converter);
    }

    public List<? extends UnitConverter> getConversionSteps() {
        List<AbstractConverter> converters = new ArrayList<AbstractConverter>();
        converters.add(this);
        return converters;
    }

    public Number convert(Number value) {
        return convert(Double.valueOf(value.doubleValue()));
    }

    public abstract double convert(double value);
    		    
    /**
     * This class represents the identity converter (singleton).
     */
    private static final class Identity extends AbstractConverter {

        @Override
        public boolean isIdentity() {
            return true;
        }

        @Override
        public Identity inverse() {
            return this;
        }

        @Override
        public double convert(double value) {
            return value;
        }


        @Override
        public UnitConverter concatenate(UnitConverter converter) {
            return converter;
        }

        @Override
        public boolean equals(Object cvtr) {
            return (cvtr instanceof Identity) ? true : false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        public boolean isLinear() {
            return true;
        }
    }

    /**
     * This class represents converters made up of two or more separate
     * converters (in matrix notation <code>[pair] = [left] x [right]</code>).
     */
    private static final class Pair extends AbstractConverter {

        /**
         * Holds the first converter.
         */
        private UnitConverter left;

        /**
         * Holds the second converter.
         */
        private UnitConverter right;

        /**
         * Creates a converter pair from the combined
         * transformation of the specified converters.
         *
         * @param  left the left converter.
         * @param  right the right converter.
         */
        public Pair(UnitConverter left, UnitConverter right) {
            this.left = left;
            this.right = right;
        }

        public boolean isLinear() {
            return left.isLinear() && right.isLinear();
        }

        @Override
        public boolean isIdentity() {
            return false;
        }

        @Override
        public List<UnitConverter> getConversionSteps() {
            final List<UnitConverter> steps = new ArrayList<UnitConverter>();
            List<? extends UnitConverter> leftCompound = left.getConversionSteps();
            List<? extends UnitConverter> rightCompound = right.getConversionSteps();
            steps.addAll(leftCompound);
            steps.addAll(rightCompound);
            return steps;
        }

        @Override
        public Pair inverse() {
            return new Pair(right.inverse(), left.inverse());
        }

        @Override
        public double convert(double value) {
            return left.convert(right.convert(value));
        }

        @Override
        public boolean equals(Object cvtr) {
            if (this == cvtr) return true;
            if (!(cvtr instanceof Pair)) return false;
            Pair that = (Pair) cvtr;
            return (this.left.equals(that.left)) && (this.right.equals(that.right));
        }

        @Override
        public int hashCode() {
            return left.hashCode() + right.hashCode();
        }

    }

}