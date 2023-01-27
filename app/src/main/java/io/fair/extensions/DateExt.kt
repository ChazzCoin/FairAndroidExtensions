package io.fair.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



fun getTimeStamp(): String {
    val current: Locale = Locale.US
    return try {
        val formatter = SimpleDateFormat(DateUtils.TIMESTAMP, current)
        formatter.timeZone = TimeZone.getDefault()
        formatter.format(Calendar.getInstance().time)
    } catch (e: Exception) {
        SimpleDateFormat(DateUtils.TIMESTAMP, current)
            .format(Calendar.getInstance().time)
    }
}

/**
 * 1. isDateOld()
 * 2. isDateWithinTwoWeeks()
 **/

class DateUtils {

    companion object {
        var TIMEZONE_UTC = "CST"

        var TIMESTAMP = "dd-MM-yyyy hh:mm"

        var format_FT = "yyyy-MM-d"
        var format_FULLDAY = "EEEE"
        var format_FULLDAY_MONTH_DAY_YEAR = "EEEE, MMMM dd yyyy"
        var format_MONTH_DAY_YEAR = "MMMM dd yyyy"
        var format_FULLDAY_MONTH_DAY = "EEEE, MMMM dd"
        var format_MONTH_DAY = "MMMM dd"
        var format_YEAR_MONTH_DAY = "yyyy-MM-dd"
        var format_DATE_TIME = "dd-MM-yyyy hh:mm"
        var format_DATE_MILITARY_TIME = "dd-MM-yyyy HH:mm"

        var format_MONTH_YEAR_for_FIREBASE = "MMMyyyy"
    }


    fun getDiff(toyBornTime: String?, schema: String?): Long? {
        val dateFormat = SimpleDateFormat(schema, Locale.US)
        try {
            val oldDate: Date = dateFormat.parse(toyBornTime) ?: Date()
            val currentDate = Date()
            val diff: Long = oldDate.time - currentDate.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            return diff
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return java.lang.Long.valueOf(0)
    }

    fun getCurrentDayTime(): String? {
        val current: Locale = Locale.US
        return try {
            val formatter = SimpleDateFormat(format_DATE_TIME, current)
            formatter.timeZone = TimeZone.getDefault()
            formatter.format(Calendar.getInstance().time)
        } catch (e: Exception) {
            SimpleDateFormat(format_DATE_TIME, current)
                .format(Calendar.getInstance().time)
        }
    }

    fun getCurrentDay(): String? {
        val current: Locale = Locale.US
        return try {
            val formatter = SimpleDateFormat(format_FULLDAY, current)
            formatter.timeZone = TimeZone.getDefault()
            formatter.format(Calendar.getInstance().time)
        } catch (e: Exception) {
            SimpleDateFormat(format_FULLDAY, current)
                .format(Calendar.getInstance().time)
        }
    }

    fun getCurrentDateObject(): Date? {
        return Calendar.getInstance().time
    }

    fun getCurrentDateString(): String {
        val current: Locale = Locale.US
        return try {
            val formatter = SimpleDateFormat(format_YEAR_MONTH_DAY, current)
            formatter.timeZone = TimeZone.getDefault()
            formatter.format(Calendar.getInstance().time)
        } catch (e: Exception) {
            SimpleDateFormat(format_YEAR_MONTH_DAY, current)
                .format(Calendar.getInstance().time)
        }
    }

    fun getCurrentDateForScheme(schema: String?): String {
        val current: Locale = Locale.US
        return try {
            var formatter: SimpleDateFormat? = null
            formatter = if (schema != null) {
                SimpleDateFormat(schema, current)
            } else {
                SimpleDateFormat(format_FULLDAY_MONTH_DAY_YEAR, current)
            }
            formatter.timeZone = TimeZone.getDefault()
            formatter.format(Calendar.getInstance().time)
        } catch (e: Exception) {
            SimpleDateFormat(format_FULLDAY_MONTH_DAY_YEAR, current)
                .format(Calendar.getInstance().time)
        }
    }

    fun getStringDateByScheme(dateStr: String, schema: String? = null): String {
        val inputFormat = SimpleDateFormat(format_YEAR_MONTH_DAY, Locale.US)
        return try {
            val inputDate: Date = inputFormat.parse(dateStr) ?: Date()
            var formatter: SimpleDateFormat? = null
            formatter = if (schema != null) {
                SimpleDateFormat(schema, Locale.US)
            } else {
                SimpleDateFormat(format_DATE_TIME, Locale.US)
            }
            formatter.format(inputDate)
        } catch (e: Exception) {
            e.printStackTrace()
            dateStr
        }
    }

    fun getDateByScheme(dateStr: String?): Date? {
        val inputFormat = SimpleDateFormat(format_YEAR_MONTH_DAY, Locale.US)
        return try {
            inputFormat.parse(dateStr)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun isToday(dateString: String): Boolean {
        getStringDateByScheme(dateString, format_YEAR_MONTH_DAY)
        return getStringDateByScheme(dateString, format_YEAR_MONTH_DAY) == getCurrentDateForScheme(
            format_YEAR_MONTH_DAY
        )
    }


    fun minutesDifference(dateStr: String?): Int {
        val MILLI_TO_MINUTE = 1000 * 60
        val formatter = SimpleDateFormat("yyyyy-MM-dd H:m:s")
        val currentDate = Date()
        try {
            formatter.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC))
            var date: Date = formatter.parse(dateStr)
            formatter.setTimeZone(TimeZone.getDefault())
            val newDate: String = formatter.format(date)
            date = formatter.parse(newDate)
            return (currentDate.getTime() - date.getTime()) as Int / MILLI_TO_MINUTE
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    fun getDateToMillSeconds(dateStr: String?): Long {
        val formatter = SimpleDateFormat(format_DATE_MILITARY_TIME)
        try {
            formatter.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC))
            val date: Date = formatter.parse(dateStr)
            return date.getTime()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

}

