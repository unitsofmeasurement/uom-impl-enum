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
package tec.uom.impl.enums;
// FIXME move to new "spi" package.
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Information;
import javax.measure.quantity.InformationRate;
import javax.measure.quantity.Length;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Time;
import javax.measure.spi.QuantityFactory;

import tec.uom.impl.enums.unit.*;

/**
 * A factory producing simple quantities instances (tuples {@link Number}/
 * {@link Unit}).
 * 
 * For example:<br>
 * <code>Mass m = TestQuantityFactory.getInstance(Mass.class).create(23.0, KILOGRAM); // 23.0 kg<br>
 * Time m = TestQuantityFactory.getInstance(Time.class).create(124, MILLI(SECOND));
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
