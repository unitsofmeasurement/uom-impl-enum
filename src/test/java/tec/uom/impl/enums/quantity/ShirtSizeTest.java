package tec.uom.impl.enums.quantity;

import static org.junit.Assert.*;
import org.junit.Test;

import tec.uom.impl.enums.unit.ShirtSizeUnit;

public class ShirtSizeTest {
	
	@Test
	public void testInstanciate() {
		ShirtSizeEnum s = ShirtSizeEnum.S;
		assertEquals("Small", s.getDescription());
		assertEquals(ShirtSizeUnit.SML, s.getUnit());
		assertEquals("S-M-L", s.getUnit().getSymbol());
	}
	
	@Test
	public void testToString() {
		ShirtSizeEnum s = ShirtSizeEnum.XXL;
		assertEquals("XXL", s.toString());
	}

//	@Test
//	public void testTo() {
//		TimeAmount t = new TimeAmount(Double.valueOf(30d), MINUTE);
//		Quantity<Time> t2 = t.to(SECOND);
//		assertEquals(Double.valueOf(1800), t2.getValue());
//	}
}
