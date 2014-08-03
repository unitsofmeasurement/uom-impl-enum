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
package org.unitsofmeasurement.impl.enums;

import org.unitsofmeasurement.impl.enums.function.DescriptionSupplier;

/**
 * This interface is used to provide a <code>getDescription()</code> method to enums.
 * @author Werner Keil
 * @version 0.2
 */
public interface DescriptiveEnum<I extends DescriptiveEnum<I>> extends DescriptionSupplier {
	String name(); // this is just a compatibility measure with the original enum
	String getDescription();
	DescriptiveEnum<I>[] iValues();
}
