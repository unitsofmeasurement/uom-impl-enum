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
package tec.uom.impl.enums.model;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * This class represents the standard model. 
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author  <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5, August 03, 2014
 */
class StandardModel extends DimensionalModel {

	private static final StandardModel INSTANCE = new StandardModel();
	
    /**
     * Default constructor.
     */
    public StandardModel() {
    }
    
    /**
     * Returns the singleton instance of this class.
     *
     * @return the metric system instance.
     */
    public static StandardModel getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("rawtypes")
	protected final Map<Class<? extends Quantity>, Unit>
            quantityToUnit = new HashMap<Class<? extends Quantity>, Unit>(); // Diamond (Java 7+)
    
    @SuppressWarnings("unchecked")
    public <Q extends Quantity<Q>> Unit<Q> getUnit(Class<Q> quantityType) {
        return quantityToUnit.get(quantityType);
    }
	
}