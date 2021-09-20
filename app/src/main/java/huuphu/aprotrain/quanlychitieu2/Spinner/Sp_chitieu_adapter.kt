package huuphu.aprotrain.quanlychitieu2.Spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import huuphu.aprotrain.quanlychitieu2.R
import kotlinx.android.synthetic.main.item_spinner.view.*

class sp_chitieu_adapter(context: Context, chitieulist: ArrayList<data_Spinnerchitieu>?) : ArrayAdapter<data_Spinnerchitieu>(context,0, chitieulist!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView ,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position , convertView ,parent)
    }
    fun initView(position: Int,convertView: View?,parent: ViewGroup): View {
        val chitieu = getItem(position)
        var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.item_spinner,parent,false)
        view.imageView.setImageResource(chitieu!!.img)
        view.thunhap_tieude.text = chitieu.Tieude
        return view
    }
}