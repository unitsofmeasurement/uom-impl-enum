/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2021, Werner Keil and others.
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
 * 3. Neither the name of JSR-385, Unit-API nor the names of their contributors may be used to endorse or promote products
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
package tech.uom.impl.enums.quantity;

import static tech.uom.impl.enums.unit.TemperatureUnit.*;
import static javax.measure.Quantity.Scale.*;
import static  org.junit.jupiter.api.Assertions.assertEquals;

import javax.measure.Quantity;
import javax.measure.quantity.Temperature;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TemperatureTest {

    @Test
    public void testInstanciate() {
        Temperature t = new TemperatureQuantity(23.0, CELSIUS); // 23.0 °C
        assertEquals(Double.valueOf(23.0d), t.getValue());
        assertEquals(CELSIUS, t.getUnit());
        assertEquals(RELATIVE, t.getScale());
    }

    @Test
    public void testToString() {
        Temperature t = new TemperatureQuantity(23.0d, CELSIUS); // 23.0 °C
        assertEquals("23.0 °C", t.toString());
    }

    @Test
    public void testTemperatureQuantityDoubleTemperatureUnit() {
        Temperature t = new TemperatureQuantity(Double.valueOf(20d), CELSIUS);
        assertEquals(Double.valueOf(20d), t.getValue());
    }

    @Test
    public void testNegate() {
        TemperatureQuantity t = new TemperatureQuantity(Double.valueOf(30d), CELSIUS);
        assertEquals(Double.valueOf(-30d), t.negate().getValue());
    }

    @Test
    @Disabled
    public void testTo() {
        TemperatureQuantity t = new TemperatureQuantity(Double.valueOf(30d), CELSIUS);
        Quantity<Temperature> t2 = t.to(FAHRENHEIT);
        assertEquals(RELATIVE, t.getScale());
        assertEquals(Double.valueOf(20d), t2.getValue());
    }

    @Test
    public void testKelvin() {
        Temperature t = new TemperatureQuantity(25.0d, KELVIN); // 25.0 K
        assertEquals("25.0 K", t.toString());
        assertEquals(ABSOLUTE, t.getScale());
    }
}
