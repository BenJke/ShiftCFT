package ve.develop.shiftcft.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Number(
    val length: Int,
    val luhn: Boolean,
) : Parcelable
