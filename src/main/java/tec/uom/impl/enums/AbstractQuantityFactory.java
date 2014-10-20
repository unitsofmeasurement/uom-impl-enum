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
package tec.uom.impl.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.function.QuantityFactory;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Information;
import javax.measure.quantity.InformationRate;
import javax.measure.quantity.Length;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Time;

import tec.uom.impl.enums.unit.*;

/**
 * A factory producing simple quantities instances (tuples {@link Number}/
 * {@link Unit}).
 * 
 * For example:<br>
 * <code>Mass m = AbstractQuantityFactory.getInstance(Mass.class).create(23.0, KILOGRAM); // 23.0 kg<br>
 * Time m = AbstractQuantityFactory.getInstance(Time.class).create(124, MILLI(SECOND));
 * // 124 ms </code>
 * 
 * @param <Q>
 *            The type of the quantity.
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.6 $Date: 2014-09-18 $
 * 
 */
public abstract class AbstractQuantityFactory<Q extends Quantity<Q>> implements
		QuantityFactory<Q> {

	/**
	 * Holds the current instances.
	 */
	@SuppressWarnings("rawtypes")
	private static final Map<Class, AbstractQuantityFactory> INSTANCES = new HashMap<Class, AbstractQuantityFactory>();

	private static final Logger logger = Logger.getLogger(AbstractQuantityFactory.class
			.getName());

	private static final Level LOG_LEVEL = Level.FINE;

	/**
	 * Returns the default instance for the specified quantity type.
	 * 
	 * @param <Q>
	 *            The type of the quantity
	 * @param type
	 *            the quantity type
	 * @return the quantity factory for the specified type
	 */
	@SuppressWarnings("unchecked")
	public static <Q extends Quantity<Q>> AbstractQuantityFactory<Q> getInstance(
			final Class<Q> type) {

		logger.log(LOG_LEVEL, "Type: " + type + ": " + type.isInterface());
		AbstractQuantityFactory<Q> factory;
		if (!type.isInterface()) {
			if (type != null && type.getInterfaces() != null
					& type.getInterfaces().length > 0) {
				logger.log(LOG_LEVEL, "Type0: " + type.getInterfaces()[0]);
				Class<?> type2 = type.getInterfaces()[0];

				factory = INSTANCES.get(type2);
				if (factory != null)
					return factory;
				if (!Quantity.class.isAssignableFrom(type2))
					// This exception is not documented because it should never
					// happen if the
					// user don't try to trick the Java generic types system
					// with unsafe cast.
					throw new ClassCastException();
				factory = new Default<Q>((Class<Q>) type2);
				INSTANCES.put(type2, factory);
			} else {
				factory = INSTANCES.get(type);
				if (factory != null)
					return factory;
				if (!Quantity.class.isAssignableFrom(type))
					// This exception is not documented because it should never
					// happen if the
					// user don't try to trick the Java generic types system
					// with unsafe cast.
					throw new ClassCastException();
				factory = new Default<Q>(type);
				INSTANCES.put(type, factory);
			}
		} else {
			factory = INSTANCES.get(type);
			if (factory != null)
				return factory;
			if (!Quantity.class.isAssignableFrom(type))
				// This exception is not documented because it should never
				// happen if the
				// user don't try to trick the Java generic types system with
				// unsafe cast.
				throw new ClassCastException();
			factory = new Default<Q>(type);
			INSTANCES.put(type, factory);
		}
		return factory;
	}

	/**
	 * Overrides the default implementation of the factory for the specified
	 * quantity type.
	 * 
	 * @param <Q>
	 *            The type of the quantity
	 * @param type
	 *            the quantity type
	 * @param factory
	 *            the quantity factory
	 */
	@SuppressWarnings("rawtypes")
	protected static <Q extends Quantity> void setInstance(final Class<Q> type,
			AbstractQuantityFactory factory) {
		if (!Quantity.class.isAssignableFrom(type))
			// This exception is not documented because it should never happen
			// if the
			// user don't try to trick the Java generic types system with unsafe
			// cast.
			throw new ClassCastException();
		INSTANCES.put(type, factory);
	}

	/**
	 * Returns the quantity for the specified number stated in the specified
	 * unit.
	 * 
	 * @param number
	 *            the numeric value stated in the specified unit
	 * @param unit
	 *            the unit
	 * @return the corresponding quantity
	 */
	 public abstract <N extends Number, U extends Unit<Q>> Q create(N number, U unit);

	/**
	 * Returns the metric unit for quantities produced by this factory or
	 * <code>null</code> if unknown.
	 * 
	 * @return the metric units for this factory quantities.
	 */
	public abstract Unit<Q> getMetricUnit();

	/**
	 * The default factory implementation. This factory uses reflection for
	 * providing a default implementation for every
	 * {@link tec.uom.impl.enums.AbstractQuantity} sub-types.
	 * 
	 * @param <Q>
	 *            The type of the quantity
	 */
	private static final class Default<Q extends Quantity<Q>> extends
			AbstractQuantityFactory<Q> {

		/**
		 * The type of the quantities created by this factory.
		 */
		private final Class<Q> type;

		/**
		 * The metric unit for quantities created by this factory.
		 */
		private final Unit<Q> metricUnit;

		/**
		 * Creates a new factory for quantities of the given type.
		 * 
		 * @param type
		 *            The type of the quantities created by this factory.
		 */
		@SuppressWarnings("unchecked")
		Default(final Class<Q> type) {
			this.type = type;
			metricUnit = CLASS_TO_METRIC_UNIT.get(type);
		}

		@SuppressWarnings("rawtypes")
		static final Map<Class, Unit> CLASS_TO_METRIC_UNIT = new HashMap<Class, Unit>(); // XXX
																							// could
																							// use
																							// Diamond
		static {
			CLASS_TO_METRIC_UNIT
					.put(Dimensionless.class, DimensionlessUnit.ONE);
			CLASS_TO_METRIC_UNIT.put(Length.class, DistanceUnit.METRE);
			CLASS_TO_METRIC_UNIT.put(Time.class, TimeUnit.SECOND);
			CLASS_TO_METRIC_UNIT.put(Information.class, BitUnit.BIT);
			CLASS_TO_METRIC_UNIT.put(InformationRate.class, BitRateUnit.bps);
			CLASS_TO_METRIC_UNIT.put(Temperature.class, TemperatureUnit.KELVIN);
		}

		@Override
		public <N extends Number, U extends Unit<Q>> Q create(N number, U unit) {
			// return (Q) Proxy
			// .newProxyInstance(type.getClassLoader(),
			// new Class<?>[] { type }, new GenericHandler<Q>(
			// value, unit));
			return null;
		}

		@Override
		public Unit<Q> getMetricUnit() {
			return metricUnit;
		}
	}
}
