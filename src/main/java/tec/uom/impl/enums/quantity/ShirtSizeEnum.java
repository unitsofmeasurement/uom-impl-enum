/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2014, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
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

import static tec.uom.impl.enums.unit.ShirtSizeUnit.SML;

import javax.measure.Measurement;
import javax.measure.Unit;

import tec.uom.impl.enums.DescriptiveEnum;

/**
 * International shirt sizes represented by a Java {@linkplain Enum} implementing {@link Measurement}
 * @author Werner Keil
 *
 */
public enum ShirtSizeEnum implements Measurement<ShirtSize>, DescriptiveEnum<ShirtSizeEnum> {
	XS("X-Small"), S("Small"), M("Medium"), L("Large"), XL("X-Large"), XXL("XX-Large");

	private final String description;
	
	private ShirtSizeEnum(String desc) {
		description = desc;
	}
	

	public Unit<ShirtSize> getUnit() {
		return SML;
	}

	public Measurement<ShirtSize> to(Unit<ShirtSize> arg0) {
		return null;
	}

	public String getDescription() {
		return description;
	}

	public DescriptiveEnum<ShirtSizeEnum>[] iValues() {
		return values();
	}

}
