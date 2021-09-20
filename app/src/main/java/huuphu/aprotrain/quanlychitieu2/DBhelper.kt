package huuphu.aprotrain.quanlychitieu2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
val Name : String= "Quanlichitieu";
val Version : Int = 1;
class DBhelper(
    context: Context?,
) : SQLiteOpenHelper(context, Name, null, Version) {
    companion object{
        var instance : DBhelper? = null
        @Synchronized
        fun Instances(context: Context?): DBhelper? {
            if (instance == null){
                if (context != null){
                    instance =DBhelper(context)
                }
            }
            return instance
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val sql_thunhap : String = "Create table THUNHAP (" +
                "_id integer primary key autoincrement," +
                "img integer,"+
                "tieude Text," +
                "Tien Int," +
                "Date text," +
                "ghi_chu text )"
        db!!.execSQL(sql_thunhap)
        val sql_chitieu : String = "Create table CHITIEU (" +
                "_id integer primary key autoincrement," +
                "img integer,"+
                "tieude Text," +
                "Tien Int," +
                "Date text," +
                "ghi_chu text )"
        db!!.execSQL(sql_chitieu)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}