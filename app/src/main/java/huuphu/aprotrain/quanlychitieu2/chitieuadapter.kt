package huuphu.aprotrain.quanlychitieu2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import huuphu.aprotrain.quanlychitieu2.Spinner.data_Spinnerthunhap
import huuphu.aprotrain.quanlychitieu2.Spinner.sp_thunhap_adapter
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class chitieuadapter (context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return  LayoutInflater.from(context).inflate(R.layout.item_listview,null)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val img : ImageView = view!!.findViewById(R.id.hinhanh_item_lv)
        val tieude : TextView = view.findViewById(R.id.tieude_itemlv)
        val ghichu : TextView = view.findViewById(R.id.ghichu_itemlv)
        val date : TextView = view.findViewById(R.id.ngaythang_itemlv)
        val tien : TextView = view.findViewById(R.id.giatien_itemlv)

        val id : Int= cursor!!.getInt(cursor.getColumnIndex("_id"))
        val hinhanh : Int= cursor!!.getInt(cursor.getColumnIndex("img"))
        val Tieude : String= cursor!!.getString(cursor.getColumnIndex("tieude"))
        val Tien : Int = cursor!!.getInt(cursor.getColumnIndex("Tien"))
        val Ghichu : String= cursor!!.getString(cursor.getColumnIndex("ghi_chu"))
        val Date : String= cursor!!.getString(cursor.getColumnIndex("Date"))

        img.setImageResource(hinhanh)
        tieude.text = Tieude
        ghichu.text = Ghichu
        date.text= Date
        tien.setTextColor(Color.parseColor("#FF0000"))
        val Fm = DecimalFormat("###,###,###,###,###,###,###,###,###,###")
        tien.text =(Fm.format(Tien)).toString()

        val edit : ImageView = view.findViewById(R.id.edit_itemlv)
        edit.setOnClickListener {
            val builder = AlertDialog.Builder(context!!)
            val alter : View = LayoutInflater.from(context).inflate(R.layout.edit_dialog,null)
            builder.setView(alter)
            val dialog : Dialog = builder.create()
            dialog.show()

            val table = "CHITIEU"

            val td: TextView = alter.findViewById(R.id.tieude_dialog_edit)
            td.text = "chi tieu"
//  Spinner thu nhap
            val spinnerthunhap : Spinner = alter.findViewById(R.id.spinneredit)
            val spinnerthunahpadapter = sp_thunhap_adapter(context, data_Spinnerthunhap.Thunhap.list)
            spinnerthunhap.adapter = spinnerthunahpadapter
//  calender thu nhap
            val textv_date: TextView = alter.findViewById(R.id.calenderdate_edit)
            val calender: Calendar = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            textv_date.text = (simpleDateFormat.format(calender.getTime()))
            val day: Int = calender.get(Calendar.DATE)
            val month: Int = calender.get(Calendar.MONTH)
            val year: Int = calender.get(Calendar.YEAR)
            val btn_date: ImageButton = alter.findViewById(R.id.calendrimg_edit)
            btn_date.setOnClickListener {
                val datepicker: DatePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calender.set(year, month, dayOfMonth)
                    val Date = SimpleDateFormat("dd/MM/yyyy")
                    textv_date.text = (Date.format(calender.time))
                }, year, month, day)
                datepicker.show()
            }
//  Cancel
            val btn_cancel : Button = alter.findViewById(R.id.btn_cancel_edit)
            btn_cancel.setOnClickListener {
                dialog.dismiss();
            }
//  sua
            val ghi_chu: EditText = alter.findViewById(R.id.ghichuthunhap_edit)
            val tienedt: EditText = alter.findViewById(R.id.tien_dialog_edit)
            val btn_addthunhap : Button = alter.findViewById(R.id.btn_addedit)
            btn_addthunhap.setOnClickListener {
                val iddl = spinnerthunhap.selectedItemPosition
                val imgdanhmuc: Int = data_Spinnerthunhap.Thunhap.list!![iddl].img
                val tieudedanhmuc: String = data_Spinnerthunhap.Thunhap.list!![iddl].Tieude
                val tientn: Int = tienedt.text.toString().toInt()
                val ghichutn: String = (ghi_chu.text.toString())
                val datetn: String = textv_date.text.toString()
                val data = datathunhap(imgdanhmuc, tieudedanhmuc, datetn, tientn, ghichutn)
                val id_update = id
                Chitieu_mdf().update(data,id_update,table)
                load(table)
                dialog.dismiss()
            }
//Xoa
            val btn_delete : Button = alter.findViewById(R.id.btn_deleteedit)
            btn_delete.setOnClickListener {
                val id_delete = id
                Chitieu_mdf().delete(id_delete,table)
                load(table)
                dialog.dismiss()
            }
        }
    }
    fun load(table : String){
        var cursor : Cursor = Chitieu_mdf().findall(table)
        swapCursor(cursor)
        notifyDataSetChanged()
    }

}