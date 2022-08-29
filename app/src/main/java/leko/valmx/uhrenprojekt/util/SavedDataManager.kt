package leko.valmx.uhrenprojekt.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object SavedDataManager {

    fun saveArrayList(list: java.util.ArrayList<Array<String>>?, key: String?, context: Context, view: View? = null) {
        var prefs: SharedPreferences? = null
        if(context != null) prefs = PreferenceManager.getDefaultSharedPreferences(context)
        else{
            prefs = PreferenceManager.getDefaultSharedPreferences(view!!.context)
        }
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?, context: Context, view: View? = null): ArrayList<Array<String>> {
        var prefs: SharedPreferences? = null
        if(context != null) prefs = PreferenceManager.getDefaultSharedPreferences(context)
        else{
            prefs = PreferenceManager.getDefaultSharedPreferences(view!!.context)
        }
        val gson = Gson()
        val json: String? = prefs!!.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Array<String>>>() {}.getType()
        return gson.fromJson(json, type)
    }
}