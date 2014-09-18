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
