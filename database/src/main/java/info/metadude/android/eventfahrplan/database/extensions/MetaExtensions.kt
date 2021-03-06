package info.metadude.android.eventfahrplan.database.extensions

import android.content.ContentValues
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.*
import info.metadude.android.eventfahrplan.database.models.Meta

fun Meta.toContentValues() = ContentValues().apply {
    put(ETAG, eTag)
    put(NUM_DAYS, numDays)
    put(SUBTITLE, subtitle)
    put(TITLE, title)
    put(VERSION, version)
}
