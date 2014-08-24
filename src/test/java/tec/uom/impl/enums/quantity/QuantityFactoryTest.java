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
package tec.uom.impl.enums.quantity;

import static org.junit.Assert.*;
import static tec.uom.impl.enums.unit.DistanceUnit.KILOMETRE;
import static tec.uom.impl.enums.unit.TimeUnit.MINUTE;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Time;

import org.junit.Ignore;
import org.junit.Test;

import tec.uom.impl.enums.quantity.QuantityFactory;


/**
 * @author Werner Keil
 *
 */
@Ignore
public class QuantityFactoryTest {


	@Test
	public void testLength() {
		Length l =  QuantityFactory.getInstance(Length.class).create(23.5, KILOMETRE); // 23.0 km
		assertEquals(Double.valueOf(23.5d), l.getValue());
		assertEquals(KILOMETRE, l.getUnit());
		assertEquals("km", l.getUnit().getSymbol());
	}

	
	@Test
	public void testTime() {
		Quantity<Time> t = QuantityFactory.getInstance(Time.class).create(40, MINUTE); // 40 min
		assertEquals(Integer.valueOf(40), t.getValue());
		assertEquals(MINUTE, t.getUnit());
		assertEquals("m", t.getUnit().getSymbol());
		assertEquals("40 MINUTE", t.toString());
	}

}
