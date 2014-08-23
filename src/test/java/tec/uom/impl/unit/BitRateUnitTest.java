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
package tec.uom.impl.unit;

import org.hamcrest.Matchers;
import org.junit.Test;

import tec.uom.impl.enums.unit.BitRateUnit;
import static org.junit.Assert.assertThat;

/**
 * @author Werner Keil
 * Date: 7/23/11
 * Time: 4:50 PM
 */
public class BitRateUnitTest {

    @Test
    public void testAlternate() throws Exception {
        BitRateUnit bru = BitRateUnit.Kbps;
        BitRateUnit alternate = (BitRateUnit)bru.alternate("K");
        assertThat(alternate, Matchers.equalTo(BitRateUnit.Kbps));
    }

    @Test
    public void testGetBySymbolLower() throws Exception {
        BitRateUnit bru = BitRateUnit.getBySymbol("kbps");
        assertThat(bru, Matchers.equalTo(BitRateUnit.Kbps));
    }

    @Test
    public void testGetBySymbolUpper() throws Exception {
        BitRateUnit bru = BitRateUnit.getBySymbol("MBPS");
        assertThat(bru, Matchers.equalTo(BitRateUnit.Mbps));
    }
}
