package app.appsamurai.cartrack.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.util.Log
import app.appsamurai.cartrack.Constant

/**
 * Created by Ukyo on 2019-06-21.
 *
 */
class DbAccess(context: Context) {
    var db: SQLiteDatabase = DbHelper.getDatabase(context)

    fun addUser(name: String, password: String, email: String) {
        val values = ContentValues()
        values.put(Constant.DB_COLUMN_USER, name)
        values.put(Constant.DB_COLUMN_PASSWORD, password)
        values.put(Constant.DB_COLUMN_EMAIL, email)
        db.insert(Constant.DB_TABLE_NAME, null, values)
    }

    fun getUser(input: String): String {
        var name = ""
        val cursor = db.query(
            Constant.DB_TABLE_NAME, null, Constant.DB_COLUMN_EMAIL + "='" + input + "'",
            null, null, null, null, null
        )

        if (cursor.count > 0) {
            cursor.moveToFirst()
            name = cursor.getString(1)
            Log.e("getUser", name)
            cursor.close()
        }

        return name
    }

    fun getPassword(name: String): String? {
        var password: String? = ""
        val cursor = db.query(
            Constant.DB_TABLE_NAME, null, Constant.DB_COLUMN_USER + "='" + name + "'",
            null, null, null, null, null
        )

        if (cursor.count > 0) {
            cursor.moveToFirst()
            password = cursor.getString(2)
            Log.e("getPassword", password)
            cursor.close()
        }

        return password
    }

    fun isUserExist(name: String): Boolean {
        var isExist = false
        val cursor = db.query(
            Constant.DB_TABLE_NAME, null, Constant.DB_COLUMN_USER + "='" + name + "'",
            null, null, null, null, null
        )

        if (cursor.count > 0) {
            cursor.moveToFirst()

            Log.e("isUserExist",cursor.getString(1))

            if (!TextUtils.isEmpty(cursor.getString(1))) {
                isExist = true
            }
            cursor.close()
        }

        return isExist
    }

    fun closeDB() {
        if (db.isOpen) {
            db.close()
        }
    }

}