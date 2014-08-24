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
package tec.uom.impl.enums.quantity;

import static org.junit.Assert.*;
import static tec.uom.impl.enums.unit.TemperatureUnit.*;

import javax.measure.Quantity;
import javax.measure.quantity.Temperature;

import org.junit.Ignore;
import org.junit.Test;

import tec.uom.impl.enums.quantity.TemperatureAmount;

public class TemperatureTest {
	
	@Test
	public void testInstanciate() {
		Temperature t =  new TemperatureAmount(23.0, CELSIUS); // 23.0 °C
		assertEquals(Double.valueOf(23.0d), t.getValue());
		assertEquals(CELSIUS, t.getUnit());
		//assertEquals("km", l.getUnit().getSymbol());
	}
	
	@Test
	public void testToString() {
		Temperature t =  new TemperatureAmount(23.0d, CELSIUS); // 23.0 °C
		assertEquals("23.0°C", t.toString());
	}
	
	@Test
	public void testTemperatureQuantityDoubleTemperatureUnit() {
		Temperature t = new TemperatureAmount(Double.valueOf(20d), CELSIUS);
		assertEquals(Double.valueOf(20d), t.getValue());
	}

	@Test
	@Ignore
	public void testTo() {
		TemperatureAmount t = new TemperatureAmount(Double.valueOf(30d), CELSIUS);
		Quantity<Temperature> t2 = t.to(FAHRENHEIT);
		assertEquals(Double.valueOf(20d), t2.getValue());
	}

}
