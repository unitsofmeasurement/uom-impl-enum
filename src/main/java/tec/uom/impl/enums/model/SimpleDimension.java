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
package tec.uom.impl.enums.model;


import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;

/**
 * 
 * @author  <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.3, Date: 2014-08-03
 */
public final class SimpleDimension implements Dimension {
    public static final Dimension INSTANCE = new SimpleDimension();
    
    public static final Dimension getInstance() {
    	return INSTANCE;
    }

    @Override
    public Dimension multiply(Dimension dimension) {
        return this;
    }

    @Override
    public Dimension divide(Dimension dimension) {
        return this;
    }

    @Override
    public Dimension pow(int i) {
        return this;
    }

    @Override
    public Dimension root(int i) {
        return this;
    }

    @Override
    public Map<? extends Dimension, Integer> getProductDimensions() {
        Map<Dimension, Integer> products = new HashMap<Dimension, Integer>();
        products.put(this, Integer.valueOf(1));
        return products;
    }
}
