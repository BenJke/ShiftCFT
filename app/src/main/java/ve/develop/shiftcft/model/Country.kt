package ve.develop.shiftcft.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Country(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji:String,
    val currency:String,
    val latitude:Int,
    val longitude:Int
):Parcelable
