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
import org.junit.Test;

import tec.uom.impl.enums.unit.ShirtSizeUnit;

/**
 * 
 * @author Werner Keil
 *
 */
public class ShirtSizeTest {
	
	@Test
	public void testInstanciate() {
		ShirtSizeEnum s = ShirtSizeEnum.S;
		assertEquals("Small", s.getDescription());
	}
	
	@Test
	public void testToString() {
		ShirtSizeEnum s = ShirtSizeEnum.XXL;
		assertEquals("XXL", s.toString());
	}

	@Test
	public void testGetDescription() {
		ShirtSizeEnum s = ShirtSizeEnum.XL;
		assertEquals("X-Large", s.getDescription());
	}
	
	@Test
	public void testGetUnit() {
		ShirtSizeEnum s = ShirtSizeEnum.L;
		assertEquals(ShirtSizeUnit.SML, s.getUnit());
		assertEquals("S-M-L", s.getUnit().getSymbol());
	}
}
