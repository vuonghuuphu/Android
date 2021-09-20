package huuphu.aprotrain.quanlychitieu2

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Chitieu_mdf (){
    fun findall(table : String) : Cursor{
        val db : SQLiteDatabase = DBhelper.Instances(null)!!.readableDatabase
        val sql : String = "select * from " + table
        val cursor : Cursor =db.rawQuery(sql,null)
    return cursor
    }
    fun add(table: String,data : datathunhap){
        val db : SQLiteDatabase = DBhelper.Instances(null)!!.writableDatabase
        val Giatri= ContentValues()
        Giatri.put("img", data.IMG)
        Giatri.put("tieude", data.TITLE)
        Giatri.put("Tien", data.Tien)
        Giatri.put("Date", data.DATE)
        Giatri.put("ghi_chu", data.Ghichu)
        db.insert(table,null,Giatri)
    }
    fun findall_tk(sql : String) : Cursor? {
        var db : SQLiteDatabase? = DBhelper.Instances(null)?.readableDatabase
        var cursor : Cursor? = db?.rawQuery(sql, null)
        return  cursor
    }
    fun update(data: datathunhap, id: Int,table: String) {
        var db : SQLiteDatabase = DBhelper.Instances(null)!!.writableDatabase
        var giatri = ContentValues()
        giatri.put("img", data.IMG)
        giatri.put("tieude", data.TITLE)
        giatri.put("Tien", data.Tien)
        giatri.put("Date", data.DATE)
        giatri.put("ghi_chu", data.Ghichu)
        db.update(table,giatri,"_id = " + id, null)
    }
    fun delete(id : Int,table: String){
        val db : SQLiteDatabase? = DBhelper.Instances(null)!!.writableDatabase
        db!!.delete(table, "_id = " + id, null)
    }

}