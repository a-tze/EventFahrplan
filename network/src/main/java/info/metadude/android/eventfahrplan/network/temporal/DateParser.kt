package info.metadude.android.eventfahrplan.network.temporal

import info.metadude.android.eventfahrplan.commons.temporal.Moment
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

class DateParser {

    companion object {

        /**
         * Parses given [text] and returns its date value represented in milliseconds.
		 * For datetime representations without timezone information, current system's timezone is used.
		 * For date-only representations, UTC is used.
         *
         * @param text either ISO-8601 date and time format (e.g. 2019-01-01T00:00:00Z)
         * or ISO-8601 date format (i.e. 2019-01-01).
         */
        @JvmStatic
        fun getDateTime(text: String) = if (text.length > 10) {
			var formatter = DateTimeFormatter.ISO_DATE_TIME
			if (text.length < 20) {
				formatter = formatter.withZone(ZoneId.systemDefault())
			}
            val parsed = Instant.from(formatter.parse(text))
            val atUTCOffset = parsed.atOffset(ZoneOffset.UTC)
            atUTCOffset.toEpochSecond() * 1000
        } else {
            val parsed = LocalDate.parse(text)
            val atUTCOffset = parsed.atTime(0, 0).atOffset(ZoneOffset.UTC)
            atUTCOffset.toEpochSecond() * 1000
        }

        /**
         * Returns [Moment.minuteOfDay] of given parse [text].
         *
         * @param text see [DateParser.getDateTime] for valid formats
         */
        @JvmStatic
        fun getDayChange(text: String): Int {
            val timeUTC = getDateTime(text)
            return Moment(timeUTC).minuteOfDay
        }
    }
}
