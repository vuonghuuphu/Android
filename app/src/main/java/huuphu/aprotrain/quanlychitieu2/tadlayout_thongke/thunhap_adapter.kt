package huuphu.aprotrain.quanlychitieu2.tadlayout_thongke

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import huuphu.aprotrain.quanlychitieu2.R
import java.text.DecimalFormat

class thunhap_adapter(var context: Context, var layout: Int, var datalist : ArrayList<thongke>) : BaseAdapter() {
    class viewhoder(row: View) {
        var tieude : TextView
        var tien : TextView
        var lay : LinearLayout
        init {
            lay = row.findViewById(R.id.layoutitem)
            tieude = row.findViewById(R.id.tieudetk)
            tien = row.findViewById(R.id.tientk)
        }
    }
    override fun getCount(): Int {
        return datalist.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val viewh : viewhoder
        if (convertView == null){
    val layoutInflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    view = layoutInflater.inflate(layout,null)
    viewh = viewhoder(view)
    view.tag = viewh
    }else{
        view = convertView
        viewh = convertView.tag as viewhoder
    }
        val data = datalist.get(position)
        viewh.tieude.text = data.tieude
        val Fm = DecimalFormat("###,###,###,###,###,###,###,###,###,###")
        viewh.tien.text =(Fm.format(data.tien)) + " vnd"
        if (data.check == 1){
            viewh.lay.setBackgroundResource(R.color.ql)
            viewh.tieude.setTextColor(Color.WHITE)
            viewh.tien.setTextColor(Color.WHITE)
        }
        return view
    }
}