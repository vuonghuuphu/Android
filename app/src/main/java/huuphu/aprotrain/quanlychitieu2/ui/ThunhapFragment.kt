package huuphu.aprotrain.quanlychitieu2.ui


import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import huuphu.aprotrain.quanlychitieu2.Chitieu_mdf
import huuphu.aprotrain.quanlychitieu2.DBhelper
import huuphu.aprotrain.quanlychitieu2.R
import huuphu.aprotrain.quanlychitieu2.cursoradapter

class ThunhapFragment : Fragment() {
    lateinit var listview_thunhap : ListView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DBhelper.Instances(context)
        val view : View = inflater.inflate(R.layout.fragment_thunhap,container,false)
        listview_thunhap= view.findViewById(R.id.listview_thunhap)
        var cursor : Cursor = Chitieu_mdf().findall("THUNHAP")
        val C_adapter = cursoradapter(context,cursor)
        listview_thunhap.adapter = C_adapter
        C_adapter.notifyDataSetChanged()
        return view
    }
}