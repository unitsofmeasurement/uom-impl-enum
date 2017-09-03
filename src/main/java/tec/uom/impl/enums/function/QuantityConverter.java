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
package tec.uom.impl.enums.function;

import javax.measure.Quantity;
import javax.measure.Unit;

/**
 *  Represents an operation to convert values.
 * <p>
 * This is a <a href=
 * "http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html#package.description"
 * >functional interface</a> whose functional method is {@link #to()}.
 *
*  @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5, 2014-11-02
 * @param <Q>
 *            The type to convert to.
 * @see <a href="http://en.wikipedia.org/wiki/Data_conversion">Wikipedia: Data Conversion</a>
 * @deprecated Use Lib Common in the next version
 */
//equivalent to @FunctionalInterface
public interface QuantityConverter<Q extends Quantity<Q>> {

    /**
     * Returns a quantity converted into another unit.
     * @param type
     * @return the converted result.
     */
    Quantity<Q> to(Unit<Q> type);
}
