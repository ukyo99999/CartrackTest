package app.appsamurai.cartrack.util.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import app.appsamurai.cartrack.Constant

/**
 * Created by Ukyo on 2019-06-21.
 *
 */
class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "cartrack.db"
        const val DB_VERSION = 1

        fun getDatabase(context: Context): SQLiteDatabase {
            return DbHelper(context).writableDatabase
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        val DATABASE_CREATE_TABLE_CONTACT = "CREATE TABLE " + Constant.DB_TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constant.DB_COLUMN_USER + " TEXT," +
                Constant.DB_COLUMN_PASSWORD + " TEXT," +
                Constant.DB_COLUMN_EMAIL + " TEXT)"

        db.execSQL(DATABASE_CREATE_TABLE_CONTACT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + Constant.DB_TABLE_NAME)
    }

}