package com.objectionmaster.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Objection(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("response")
    val response: String,
    @SerialName("followUp")
    val followUp: String,
    @SerialName("psychology")
    val psychology: String,
    @SerialName("confidenceTip")
    val confidenceTip: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(response)
        parcel.writeString(followUp)
        parcel.writeString(psychology)
        parcel.writeString(confidenceTip)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Objection> {
        override fun createFromParcel(parcel: Parcel): Objection {
            return Objection(parcel)
        }

        override fun newArray(size: Int): Array<Objection?> {
            return arrayOfNulls(size)
        }
    }
}
