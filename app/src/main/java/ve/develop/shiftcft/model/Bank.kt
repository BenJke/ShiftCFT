package ve.develop.shiftcft.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Bank(
    val name: String = "Unknown",
    val url: String = "Unknown",
    val phone: String = "Unknown",
    val city: String = "Unknown"
):Parcelable
