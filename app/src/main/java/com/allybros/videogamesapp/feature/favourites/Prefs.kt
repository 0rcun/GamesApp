package com.allybros.videogamesapp.feature.favourites

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.putString
import androidx.core.content.edit
import com.allybros.videogamesapp.api.Secrets
import com.allybros.videogamesapp.commons.GameItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Prefs(context: Context) {

    companion object {
        private const val PREFS_FILENAME = "shared_prefs_name"
    }
    private val gson = Gson()
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var myStringArray: Array<String>
        get() = sharedPrefs.getStringSet(Secrets.SharedKey, emptySet())?.toTypedArray()?: emptyArray()
        set(value) = sharedPrefs.edit {
            clear()
            putStringSet(Secrets.SharedKey, value.toSet())
        }
}
