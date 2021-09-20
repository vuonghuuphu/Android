package huuphu.aprotrain.quanlychitieu2.Spinner

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import huuphu.aprotrain.quanlychitieu2.R
import kotlinx.android.synthetic.main.item_spinner.view.*

class sp_thunhap_adapter(context: Context, thunhaplist: ArrayList<data_Spinnerthunhap>?) : ArrayAdapter<data_Spinnerthunhap>(context,0, thunhaplist!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView ,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position , convertView ,parent)
    }
    fun initView(position: Int,convertView: View?,parent: ViewGroup): View {
        val thunhap = getItem(position)
        var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.item_spinner,parent,false)
        view.imageView.setImageResource(thunhap!!.img)
        view.thunhap_tieude.text = thunhap.Tieude
        return view
    }
}