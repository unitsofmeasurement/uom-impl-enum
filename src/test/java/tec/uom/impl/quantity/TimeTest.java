package tec.uom.impl.quantity;

import static org.junit.Assert.*;
import static tec.uom.impl.enums.unit.TimeUnit.*;

import javax.measure.Quantity;
import javax.measure.quantity.Time;

import org.junit.Test;

import tec.uom.impl.enums.quantity.TimeAmount;

public class TimeTest {
	
	@Test
	public void testInstanciate() {
		Time t =  new TimeAmount(23d, HOUR); // 23.0 h
		assertEquals(Double.valueOf(23.0d), t.getValue());
		assertEquals(HOUR, t.getUnit());
		//assertEquals("km", l.getUnit().getSymbol());
	}
	
	@Test
	public void testToString() {
		Time t =  new TimeAmount(23.0d, MINUTE); // 23.0 min
		assertEquals("23.0m", t.toString());
	}
	
	@Test
	public void testTemperatureQuantityDoubleTemperatureUnit() {
		Time t = new TimeAmount(Double.valueOf(20d), HOUR);
		assertEquals(Double.valueOf(20d), t.getValue());
	}

	@Test
	public void testTo() {
		TimeAmount t = new TimeAmount(Double.valueOf(30d), MINUTE);
		Quantity<Time> t2 = t.to(SECOND);
		assertEquals(Double.valueOf(1800), t2.getValue());
	}
}
