package nz.co.chrisdrake.events.domain.model

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

@PaperParcel data class Event(
        @JvmField val id: Long,
        @JvmField val name: String,
        @JvmField val url: String,
        @JvmField val thumbnailUrl: String?,
        @JvmField val description: String,
        @JvmField val dateTimeSummary: String,
        @JvmField val locationSummary: String,
        @JvmField val hasMultipleSessions: Boolean
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelEvent.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelEvent.writeToParcel(this, dest, flags)
    }
}
