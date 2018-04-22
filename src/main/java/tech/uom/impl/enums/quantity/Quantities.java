/*
 * Units of Measurement Enum Implementation
 * Copyright © 2005-2018, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.Unit;

import tech.uom.impl.enums.unit.TimeUnit;


/**
 * Singleton class for accessing {@link Quantity} instances.
 *
 * @author werner
 * @author otaviojava
 * @since 1.0
 */
public final class Quantities {
  /**
   * Private singleton constructor.
   */
  private Quantities() {
  }

  /**
   * Returns the scalar measurement. When the {@link Number} was {@link BigDecimal} or {@link BigInteger} will uses {@link DecimalQuantity}, when the
   * {@link Number} was {@link Double} will {@link DoubleQuantity} otherwise will {@link NumberQuantity}. in the specified unit.
   *
   * @param value
   *          the measurement value.
   * @param unit
   *          the measurement unit.
   * @return the corresponding <code>numeric</code> measurement.
   * @throws NullPointerException
   *           when value or unit were null
   */
  @SuppressWarnings("unchecked")
  public static <Q extends Quantity<Q>> Quantity<Q> getQuantity(Number value, Unit<Q> unit) {
    Objects.requireNonNull(value);
    Objects.requireNonNull(unit);
    if (TemperatureQuantity.class.isInstance(unit)) {
      return (Quantity<Q>) new TemperatureQuantity(value, unit);
    } else if (TimeUnit.class.isInstance(unit)) {
      return (Quantity<Q>) new TimeQuantity(value, unit);
    }
    return (Quantity<Q>) new DimensionlessQuantity(value, unit);
  }
}
