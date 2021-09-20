package huuphu.aprotrain.quanlychitieu2.ui

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import huuphu.aprotrain.quanlychitieu2.Chitieu_mdf
import huuphu.aprotrain.quanlychitieu2.R
import huuphu.aprotrain.quanlychitieu2.chitieuadapter
import huuphu.aprotrain.quanlychitieu2.cursoradapter

class ChitieuFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_chitieu,container,false)
        val listv_chitieu : ListView = view.findViewById(R.id.lv_chitieu)
        val cursor : Cursor = Chitieu_mdf().findall("CHITIEU")
        var Adapterct = chitieuadapter(context,cursor)
        listv_chitieu.adapter = Adapterct
        Adapterct.notifyDataSetChanged()
        return view
    }
}