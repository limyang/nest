package com.ebay.nest.utils.format;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.spi.LocaleServiceProvider;
import sun.util.LocaleServiceProviderPool;
import sun.util.TimeZoneNameUtility;
import sun.util.calendar.ZoneInfo;
import sun.util.resources.LocaleData;

public class DateFormatSymbols implements Serializable, Cloneable {

	/**
	 * Construct a DateFormatSymbols object by loading format data from resources for the default locale. This
	 * constructor can only construct instances for the locales supported by the Java runtime environment, not for those
	 * supported by installed {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider} implementations.
	 * For full locale coverage, use the {@link #getInstance(Locale) getInstance} method.
	 * 
	 * @see #getInstance()
	 * @exception java.util.MissingResourceException
	 *                if the resources for the default locale cannot be found or cannot be loaded.
	 */
	public DateFormatSymbols() {
		initializeData(Locale.getDefault());
	}

	/**
	 * Construct a DateFormatSymbols object by loading format data from resources for the given locale. This constructor
	 * can only construct instances for the locales supported by the Java runtime environment, not for those supported
	 * by installed {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider} implementations. For full
	 * locale coverage, use the {@link #getInstance(Locale) getInstance} method.
	 * 
	 * @see #getInstance(Locale)
	 * @exception java.util.MissingResourceException
	 *                if the resources for the specified locale cannot be found or cannot be loaded.
	 */
	public DateFormatSymbols(Locale locale) {
		initializeData(locale);
	}

	/**
	 * Era strings. For example: "AD" and "BC". An array of 2 strings, indexed by <code>Calendar.BC</code> and
	 * <code>Calendar.AD</code>.
	 * 
	 * @serial
	 */
	String eras[] = null;

	/**
	 * Month strings. For example: "January", "February", etc. An array of 13 strings (some calendars have 13 months),
	 * indexed by <code>Calendar.JANUARY</code>, <code>Calendar.FEBRUARY</code>, etc.
	 * 
	 * @serial
	 */
	String months[] = null;

	/**
	 * Short month strings. For example: "Jan", "Feb", etc. An array of 13 strings (some calendars have 13 months),
	 * indexed by <code>Calendar.JANUARY</code>, <code>Calendar.FEBRUARY</code>, etc.
	 * 
	 * @serial
	 */
	String shortMonths[] = null;

	/**
	 * Weekday strings. For example: "Sunday", "Monday", etc. An array of 8 strings, indexed by
	 * <code>Calendar.SUNDAY</code>, <code>Calendar.MONDAY</code>, etc. The element <code>weekdays[0]</code> is ignored.
	 * 
	 * @serial
	 */
	String weekdays[] = null;

	/**
	 * Short weekday strings. For example: "Sun", "Mon", etc. An array of 8 strings, indexed by
	 * <code>Calendar.SUNDAY</code>, <code>Calendar.MONDAY</code>, etc. The element <code>shortWeekdays[0]</code> is
	 * ignored.
	 * 
	 * @serial
	 */
	String shortWeekdays[] = null;

	/**
	 * AM and PM strings. For example: "AM" and "PM". An array of 2 strings, indexed by <code>Calendar.AM</code> and
	 * <code>Calendar.PM</code>.
	 * 
	 * @serial
	 */
	String ampms[] = null;

	/**
	 * Localized names of time zones in this locale. This is a two-dimensional array of strings of size <em>n</em> by
	 * <em>m</em>, where <em>m</em> is at least 5. Each of the <em>n</em> rows is an entry containing the localized
	 * names for a single <code>TimeZone</code>. Each such row contains (with <code>i</code> ranging from 0..<em>n</em>
	 * -1):
	 * <ul>
	 * <li><code>zoneStrings[i][0]</code> - time zone ID</li>
	 * <li><code>zoneStrings[i][1]</code> - long name of zone in standard time</li>
	 * <li><code>zoneStrings[i][2]</code> - short name of zone in standard time</li>
	 * <li><code>zoneStrings[i][3]</code> - long name of zone in daylight saving time</li>
	 * <li><code>zoneStrings[i][4]</code> - short name of zone in daylight saving time</li>
	 * </ul>
	 * The zone ID is <em>not</em> localized; it's one of the valid IDs of the {@link java.util.TimeZone TimeZone} class
	 * that are not <a href="../java/util/TimeZone.html#CustomID">custom IDs</a>. All other entries are localized names.
	 * 
	 * @see java.util.TimeZone
	 * @serial
	 */
	String zoneStrings[][] = null;

	/**
	 * Indicates that zoneStrings is set externally with setZoneStrings() method.
	 */
	transient boolean isZoneStringsSet = false;

	/**
	 * Unlocalized date-time pattern characters. For example: 'y', 'd', etc. All locales use the same these unlocalized
	 * pattern characters.
	 */
	static final String patternChars = "GyMdkHmsSEDFwWahKzZ";

	/**
	 * Localized date-time pattern characters. For example, a locale may wish to use 'u' rather than 'y' to represent
	 * years in its date format pattern strings. This string must be exactly 18 characters long, with the index of the
	 * characters described by <code>DateFormat.ERA_FIELD</code>, <code>DateFormat.YEAR_FIELD</code>, etc. Thus, if the
	 * string were "Xz...", then localized patterns would use 'X' for era and 'z' for year.
	 * 
	 * @serial
	 */
	String localPatternChars = null;

	/**
	 * The locale which is used for initializing this DateFormatSymbols object.
	 * 
	 * @since 1.6
	 * @serial
	 */
	Locale locale = null;

	/* use serialVersionUID from JDK 1.1.4 for interoperability */
	static final long serialVersionUID = -5987973545549424702L;

	/**
	 * Returns an array of all locales for which the <code>getInstance</code> methods of this class can return localized
	 * instances. The returned array represents the union of locales supported by the Java runtime and by installed
	 * {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider} implementations. It must contain at
	 * least a <code>Locale</code> instance equal to {@link java.util.Locale#US Locale.US}.
	 * 
	 * @return An array of locales for which localized <code>DateFormatSymbols</code> instances are available.
	 * @since 1.6
	 */
	public static Locale[] getAvailableLocales() {
		LocaleServiceProviderPool pool = LocaleServiceProviderPool.getPool(DateFormatSymbolsProvider.class);
		return pool.getAvailableLocales();
	}

	/**
	 * Gets the <code>DateFormatSymbols</code> instance for the default locale. This method provides access to
	 * <code>DateFormatSymbols</code> instances for locales supported by the Java runtime itself as well as for those
	 * supported by installed {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider} implementations.
	 * 
	 * @return a <code>DateFormatSymbols</code> instance.
	 * @since 1.6
	 */
	public static final DateFormatSymbols getInstance() {
		return getInstance(Locale.getDefault());
	}

	/**
	 * Gets the <code>DateFormatSymbols</code> instance for the specified locale. This method provides access to
	 * <code>DateFormatSymbols</code> instances for locales supported by the Java runtime itself as well as for those
	 * supported by installed {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider} implementations.
	 * 
	 * @param locale
	 *            the given locale.
	 * @return a <code>DateFormatSymbols</code> instance.
	 * @exception NullPointerException
	 *                if <code>locale</code> is null
	 * @since 1.6
	 */
	public static final DateFormatSymbols getInstance(Locale locale) {

		return (DateFormatSymbols) getCachedInstance(locale).clone();
	}

	/**
	 * Returns a DateFormatSymbols provided by a provider or found in the cache. Note that this method returns a cached
	 * instance, not its clone. Therefore, the instance should never be given to an application.
	 */
	static final DateFormatSymbols getInstanceRef(Locale locale) {

		return getCachedInstance(locale);
	}

	/**
	 * Returns a cached DateFormatSymbols if it's found in the cache. Otherwise, this method returns a newly cached
	 * instance for the given locale.
	 */
	private static DateFormatSymbols getCachedInstance(Locale locale) {
		SoftReference<DateFormatSymbols> ref = cachedInstances.get(locale);
		DateFormatSymbols dfs = null;
		if (ref == null || (dfs = ref.get()) == null) {
			dfs = new DateFormatSymbols(locale);
			ref = new SoftReference<DateFormatSymbols>(dfs);
			SoftReference<DateFormatSymbols> x = cachedInstances.putIfAbsent(locale, ref);
			if (x != null) {
				DateFormatSymbols y = x.get();
				if (y != null) {
					dfs = y;
				} else {
					// Replace the empty SoftReference with ref.
					cachedInstances.put(locale, ref);
				}
			}
		}
		return dfs;
	}

	/**
	 * Gets era strings. For example: "AD" and "BC".
	 * 
	 * @return the era strings.
	 */
	public String[] getEras() {
		return Arrays.copyOf(eras, eras.length);
	}

	/**
	 * Sets era strings. For example: "AD" and "BC".
	 * 
	 * @param newEras
	 *            the new era strings.
	 */
	public void setEras(String[] newEras) {
		eras = Arrays.copyOf(newEras, newEras.length);
	}

	/**
	 * Gets month strings. For example: "January", "February", etc.
	 * 
	 * @return the month strings.
	 */
	public String[] getMonths() {
		return Arrays.copyOf(months, months.length);
	}

	/**
	 * Sets month strings. For example: "January", "February", etc.
	 * 
	 * @param newMonths
	 *            the new month strings.
	 */
	public void setMonths(String[] newMonths) {
		months = Arrays.copyOf(newMonths, newMonths.length);
	}

	/**
	 * Gets short month strings. For example: "Jan", "Feb", etc.
	 * 
	 * @return the short month strings.
	 */
	public String[] getShortMonths() {
		return Arrays.copyOf(shortMonths, shortMonths.length);
	}

	/**
	 * Sets short month strings. For example: "Jan", "Feb", etc.
	 * 
	 * @param newShortMonths
	 *            the new short month strings.
	 */
	public void setShortMonths(String[] newShortMonths) {
		shortMonths = Arrays.copyOf(newShortMonths, newShortMonths.length);
	}

	/**
	 * Gets weekday strings. For example: "Sunday", "Monday", etc.
	 * 
	 * @return the weekday strings. Use <code>Calendar.SUNDAY</code>, <code>Calendar.MONDAY</code>, etc. to index the
	 *         result array.
	 */
	public String[] getWeekdays() {
		return Arrays.copyOf(weekdays, weekdays.length);
	}

	/**
	 * Sets weekday strings. For example: "Sunday", "Monday", etc.
	 * 
	 * @param newWeekdays
	 *            the new weekday strings. The array should be indexed by <code>Calendar.SUNDAY</code>,
	 *            <code>Calendar.MONDAY</code>, etc.
	 */
	public void setWeekdays(String[] newWeekdays) {
		weekdays = Arrays.copyOf(newWeekdays, newWeekdays.length);
	}

	/**
	 * Gets short weekday strings. For example: "Sun", "Mon", etc.
	 * 
	 * @return the short weekday strings. Use <code>Calendar.SUNDAY</code>, <code>Calendar.MONDAY</code>, etc. to index
	 *         the result array.
	 */
	public String[] getShortWeekdays() {
		return Arrays.copyOf(shortWeekdays, shortWeekdays.length);
	}

	/**
	 * Sets short weekday strings. For example: "Sun", "Mon", etc.
	 * 
	 * @param newShortWeekdays
	 *            the new short weekday strings. The array should be indexed by <code>Calendar.SUNDAY</code>,
	 *            <code>Calendar.MONDAY</code>, etc.
	 */
	public void setShortWeekdays(String[] newShortWeekdays) {
		shortWeekdays = Arrays.copyOf(newShortWeekdays, newShortWeekdays.length);
	}

	/**
	 * Gets ampm strings. For example: "AM" and "PM".
	 * 
	 * @return the ampm strings.
	 */
	public String[] getAmPmStrings() {
		return Arrays.copyOf(ampms, ampms.length);
	}

	/**
	 * Sets ampm strings. For example: "AM" and "PM".
	 * 
	 * @param newAmpms
	 *            the new ampm strings.
	 */
	public void setAmPmStrings(String[] newAmpms) {
		ampms = Arrays.copyOf(newAmpms, newAmpms.length);
	}

	/**
	 * Gets time zone strings. Use of this method is discouraged; use {@link java.util.TimeZone#getDisplayName()
	 * TimeZone.getDisplayName()} instead.
	 * <p>
	 * The value returned is a two-dimensional array of strings of size <em>n</em> by <em>m</em>, where <em>m</em> is at
	 * least 5. Each of the <em>n</em> rows is an entry containing the localized names for a single
	 * <code>TimeZone</code>. Each such row contains (with <code>i</code> ranging from 0..<em>n</em>-1):
	 * <ul>
	 * <li><code>zoneStrings[i][0]</code> - time zone ID</li>
	 * <li><code>zoneStrings[i][1]</code> - long name of zone in standard time</li>
	 * <li><code>zoneStrings[i][2]</code> - short name of zone in standard time</li>
	 * <li><code>zoneStrings[i][3]</code> - long name of zone in daylight saving time</li>
	 * <li><code>zoneStrings[i][4]</code> - short name of zone in daylight saving time</li>
	 * </ul>
	 * The zone ID is <em>not</em> localized; it's one of the valid IDs of the {@link java.util.TimeZone TimeZone} class
	 * that are not <a href="../util/TimeZone.html#CustomID">custom IDs</a>. All other entries are localized names. If a
	 * zone does not implement daylight saving time, the daylight saving time names should not be used.
	 * <p>
	 * If {@link #setZoneStrings(String[][]) setZoneStrings} has been called on this <code>DateFormatSymbols</code>
	 * instance, then the strings provided by that call are returned. Otherwise, the returned array contains names
	 * provided by the Java runtime and by installed {@link java.util.spi.TimeZoneNameProvider TimeZoneNameProvider}
	 * implementations.
	 * 
	 * @return the time zone strings.
	 * @see #setZoneStrings(String[][])
	 */
	public String[][] getZoneStrings() {
		return getZoneStringsImpl(true);
	}

	/**
	 * Sets time zone strings. The argument must be a two-dimensional array of strings of size <em>n</em> by <em>m</em>,
	 * where <em>m</em> is at least 5. Each of the <em>n</em> rows is an entry containing the localized names for a
	 * single <code>TimeZone</code>. Each such row contains (with <code>i</code> ranging from 0..<em>n</em>-1):
	 * <ul>
	 * <li><code>zoneStrings[i][0]</code> - time zone ID</li>
	 * <li><code>zoneStrings[i][1]</code> - long name of zone in standard time</li>
	 * <li><code>zoneStrings[i][2]</code> - short name of zone in standard time</li>
	 * <li><code>zoneStrings[i][3]</code> - long name of zone in daylight saving time</li>
	 * <li><code>zoneStrings[i][4]</code> - short name of zone in daylight saving time</li>
	 * </ul>
	 * The zone ID is <em>not</em> localized; it's one of the valid IDs of the {@link java.util.TimeZone TimeZone} class
	 * that are not <a href="../util/TimeZone.html#CustomID">custom IDs</a>. All other entries are localized names.
	 * 
	 * @param newZoneStrings
	 *            the new time zone strings.
	 * @exception IllegalArgumentException
	 *                if the length of any row in <code>newZoneStrings</code> is less than 5
	 * @exception NullPointerException
	 *                if <code>newZoneStrings</code> is null
	 * @see #getZoneStrings()
	 */
	public void setZoneStrings(String[][] newZoneStrings) {
		String[][] aCopy = new String[newZoneStrings.length][];
		for (int i = 0; i < newZoneStrings.length; ++i) {
			int len = newZoneStrings[i].length;
			if (len < 5) {
				throw new IllegalArgumentException();
			}
			aCopy[i] = Arrays.copyOf(newZoneStrings[i], len);
		}
		zoneStrings = aCopy;
		isZoneStringsSet = true;
	}

	/**
	 * Gets localized date-time pattern characters. For example: 'u', 't', etc.
	 * 
	 * @return the localized date-time pattern characters.
	 */
	public String getLocalPatternChars() {
		return new String(localPatternChars);
	}

	/**
	 * Sets localized date-time pattern characters. For example: 'u', 't', etc.
	 * 
	 * @param newLocalPatternChars
	 *            the new localized date-time pattern characters.
	 */
	public void setLocalPatternChars(String newLocalPatternChars) {
		localPatternChars = new String(newLocalPatternChars);
	}

	/**
	 * Overrides Cloneable
	 */
	public Object clone() {
		try {
			DateFormatSymbols other = (DateFormatSymbols) super.clone();
			copyMembers(this, other);
			return other;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	/**
	 * Override hashCode. Generates a hash code for the DateFormatSymbols object.
	 */
	public int hashCode() {
		int hashcode = 0;
		String[][] zoneStrings = getZoneStringsWrapper();
		for (int index = 0; index < zoneStrings[0].length; ++index)
			hashcode ^= zoneStrings[0][index].hashCode();
		return hashcode;
	}

	/**
	 * Override equals
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		DateFormatSymbols that = (DateFormatSymbols) obj;
		return (Arrays.equals(eras, that.eras) && Arrays.equals(months, that.months)
				&& Arrays.equals(shortMonths, that.shortMonths) && Arrays.equals(weekdays, that.weekdays)
				&& Arrays.equals(shortWeekdays, that.shortWeekdays) && Arrays.equals(ampms, that.ampms)
				&& Arrays.deepEquals(getZoneStringsWrapper(), that.getZoneStringsWrapper()) && ((localPatternChars != null && localPatternChars
				.equals(that.localPatternChars)) || (localPatternChars == null && that.localPatternChars == null)));
	}

	// =======================privates===============================

	/**
	 * Useful constant for defining time zone offsets.
	 */
	static final int millisPerHour = 60 * 60 * 1000;

	/**
	 * Cache to hold DateFormatSymbols instances per Locale.
	 */
	private static final ConcurrentMap<Locale, SoftReference<DateFormatSymbols>> cachedInstances = new ConcurrentHashMap<Locale, SoftReference<DateFormatSymbols>>(
			3);

	private void initializeData(Locale desiredLocale) {
		locale = desiredLocale;

		// Copy values of a cached instance if any.
		SoftReference<DateFormatSymbols> ref = cachedInstances.get(locale);
		DateFormatSymbols dfs;
		if (ref != null && (dfs = ref.get()) != null) {
			copyMembers(dfs, this);
			return;
		}

		// Initialize the fields from the ResourceBundle for locale.
		ResourceBundle resource = LocaleData.getDateFormatData(locale);

		eras = resource.getStringArray("Eras");
		months = resource.getStringArray("MonthNames");
		shortMonths = resource.getStringArray("MonthAbbreviations");
		ampms = resource.getStringArray("AmPmMarkers");
		localPatternChars = resource.getString("DateTimePatternChars");

		// Day of week names are stored in a 1-based array.
		weekdays = toOneBasedArray(resource.getStringArray("DayNames"));
		shortWeekdays = toOneBasedArray(resource.getStringArray("DayAbbreviations"));
	}

	private static String[] toOneBasedArray(String[] src) {
		int len = src.length;
		String[] dst = new String[len + 1];
		dst[0] = "";
		for (int i = 0; i < len; i++) {
			dst[i + 1] = src[i];
		}
		return dst;
	}

	/**
	 * Package private: used by SimpleDateFormat Gets the index for the given time zone ID to obtain the time zone
	 * strings for formatting. The time zone ID is just for programmatic lookup. NOT LOCALIZED!!!
	 * 
	 * @param ID
	 *            the given time zone ID.
	 * @return the index of the given time zone ID. Returns -1 if the given time zone ID can't be located in the
	 *         DateFormatSymbols object.
	 * @see java.util.SimpleTimeZone
	 */
	final int getZoneIndex(String ID) {
		String[][] zoneStrings = getZoneStringsWrapper();
		for (int index = 0; index < zoneStrings.length; index++) {
			if (ID.equals(zoneStrings[index][0]))
				return index;
		}

		return -1;
	}

	/**
	 * Wrapper method to the getZoneStrings(), which is called from inside the java.text package and not to mutate the
	 * returned arrays, so that it does not need to create a defensive copy.
	 */
	final String[][] getZoneStringsWrapper() {
		if (isSubclassObject()) {
			return getZoneStrings();
		} else {
			return getZoneStringsImpl(false);
		}
	}

	private final String[][] getZoneStringsImpl(boolean needsCopy) {
		if (zoneStrings == null) {
			zoneStrings = TimeZoneNameUtility.getZoneStrings(locale);
		}

		if (!needsCopy) {
			return zoneStrings;
		}

		int len = zoneStrings.length;
		String[][] aCopy = new String[len][];
		for (int i = 0; i < len; i++) {
			aCopy[i] = Arrays.copyOf(zoneStrings[i], zoneStrings[i].length);
		}
		return aCopy;
	}

	private final boolean isSubclassObject() {
		return !getClass().getName().equals("java.text.DateFormatSymbols");
	}

	/**
	 * Clones all the data members from the source DateFormatSymbols to the target DateFormatSymbols. This is only for
	 * subclasses.
	 * 
	 * @param src
	 *            the source DateFormatSymbols.
	 * @param dst
	 *            the target DateFormatSymbols.
	 */
	private final void copyMembers(DateFormatSymbols src, DateFormatSymbols dst) {
		dst.eras = Arrays.copyOf(src.eras, src.eras.length);
		dst.months = Arrays.copyOf(src.months, src.months.length);
		dst.shortMonths = Arrays.copyOf(src.shortMonths, src.shortMonths.length);
		dst.weekdays = Arrays.copyOf(src.weekdays, src.weekdays.length);
		dst.shortWeekdays = Arrays.copyOf(src.shortWeekdays, src.shortWeekdays.length);
		dst.ampms = Arrays.copyOf(src.ampms, src.ampms.length);
		if (src.zoneStrings != null) {
			dst.zoneStrings = src.getZoneStringsImpl(true);
		} else {
			dst.zoneStrings = null;
		}
		dst.localPatternChars = new String(src.localPatternChars);
	}

	/**
	 * Write out the default serializable data, after ensuring the <code>zoneStrings</code> field is initialized in
	 * order to make sure the backward compatibility.
	 * 
	 * @since 1.6
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		if (zoneStrings == null) {
			zoneStrings = TimeZoneNameUtility.getZoneStrings(locale);
		}
		stream.defaultWriteObject();
	}

}
