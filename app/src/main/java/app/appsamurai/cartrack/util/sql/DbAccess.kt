package app.appsamurai.cartrack.util.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
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

    fun getEmail(input: String): String {
        var email = ""
        val cursor = db.query(
            Constant.DB_TABLE_NAME, null, Constant.DB_COLUMN_EMAIL + "='" + input + "'",
            null, null, null, null, null
        )

        if (cursor.count > 0) {
            cursor.moveToFirst()
            email = cursor.getString(3)
            cursor.close()
        }

        return email
    }

    fun getPassword(email: String): String? {
        var password: String? = ""
        val cursor = db.query(
            Constant.DB_TABLE_NAME, null, Constant.DB_COLUMN_EMAIL + "='" + email + "'",
            null, null, null, null, null
        )

        if (cursor.count > 0) {
            cursor.moveToFirst()
            password = cursor.getString(2)
            cursor.close()
        }

        return password
    }

    fun isEmailExist(email: String): Boolean {
        var isExist = false
        val cursor = db.query(
            Constant.DB_TABLE_NAME, null, Constant.DB_COLUMN_EMAIL + "='" + email + "'",
            null, null, null, null, null
        )

        if (cursor.count > 0) {
            cursor.moveToFirst()

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