package tec.uom.impl.enums.quantity;

import static tec.uom.impl.enums.unit.ShirtSizeUnit.SML;

import javax.measure.Measurement;
import javax.measure.Unit;

import tec.uom.impl.enums.DescriptiveEnum;

public enum ShirtSizeEnum implements Measurement<ShirtSize, Enum>, DescriptiveEnum<ShirtSizeEnum> {
	XS("X-Small"), S("Small"), M("Medium"), L("Large"), XL("X-Large"), XXL("XX-Large");

	private final String description;
	
	private ShirtSizeEnum(String desc) {
		description = desc;
	}
	
	@Override
	public Unit<ShirtSize> getUnit() {
		return SML;
	}

	@Override
	public Measurement<ShirtSize, Enum> to(Unit<ShirtSize> arg0) {
		return null;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public DescriptiveEnum<ShirtSizeEnum>[] iValues() {
		return values();
	}

}
