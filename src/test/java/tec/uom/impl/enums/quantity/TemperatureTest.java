/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363, Unit-API nor the names of their contributors may be used to endorse or promote products
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
