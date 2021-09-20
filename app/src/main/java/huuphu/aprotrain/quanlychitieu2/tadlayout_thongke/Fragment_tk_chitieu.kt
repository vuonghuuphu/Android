package huuphu.aprotrain.quanlychitieu2.tadlayout_thongke

import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import huuphu.aprotrain.quanlychitieu2.Chitieu_mdf
import huuphu.aprotrain.quanlychitieu2.R
import huuphu.aprotrain.quanlychitieu2.Spinner.data_Spinnerchitieu
import huuphu.aprotrain.quanlychitieu2.Spinner.data_Spinnerthunhap
import kotlinx.android.synthetic.main.fragment_tk_chitieu.*
import kotlinx.android.synthetic.main.fragment_tk_thunhap.*
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class Fragment_tk_chitieu : Fragment() {
    val cal : Calendar = Calendar.getInstance()
    var nam : Int = (cal.get(Calendar.YEAR))
    var thang : Int = (cal.get(Calendar.MONTH)+1)
    var arraythongke : ArrayList<thongke> = ArrayList()
    lateinit var thongke : thongke
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_tk_chitieu,container,false)
        val piechar : PieChart = view.findViewById(R.id.pieChartchitieutk)
        val spinnerthang : Spinner = view.findViewById(R.id.sp_ct_thang)
        val spinnernam : Spinner = view.findViewById(R.id.sp_ct_nam)
        val array_thang_tn : ArrayList<Int> = ArrayList()

        val pieList1: ArrayList<PieEntry> = ArrayList()
        pieList1.add(PieEntry(1f,"chitieu"))
        initPieChart(piechar)
        bieudo(pieList1,piechar)

        val array_nam_tn : ArrayList<Int> = ArrayList()
        for (j in 2018 .. nam + 10){
            array_nam_tn.add(j)
        }
        for (i in 1 .. 12 ){
            array_thang_tn.add(i)
        }
        val adapterthang: ArrayAdapter<Int> = ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item, array_thang_tn)
        val adapternam: ArrayAdapter<Int> = ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item, array_nam_tn)
        adapterthang.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        adapternam.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spinnerthang.adapter = adapterthang
        spinnernam.adapter = adapternam
        if (thang != null) {
            val spinnerPosition_t: Int = adapterthang.getPosition(thang)
            spinnerthang.setSelection(spinnerPosition_t)
        }
        if (nam != null) {
            val spinnerPosition_n: Int = adapternam.getPosition(nam)
            spinnernam.setSelection(spinnerPosition_n)
        }
        spinnerthang.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                arraythongke.clear()
                var tong = 0
                var tongtatca = 0
                for (j in data_Spinnerchitieu.Chitieu.list!!.indices){
                val sql =" select Tien from CHITIEU where CHITIEU.tieude = '${data_Spinnerchitieu.Chitieu.list!![j].Tieude}' AND CHITIEU.Date like '%/_${array_thang_tn[spinnerthang.selectedItemPosition]}/${array_nam_tn[spinnernam.selectedItemPosition]}'"
                val cursor : Cursor = Chitieu_mdf().findall_tk(sql)!!
                while (cursor.moveToNext()){
                    val tien_db = cursor.getInt(0)
                    tong += tien_db
                }
                if (tong != 0){
                    val tk = thongke(data_Spinnerchitieu.Chitieu.list!![j].Tieude,tong,0)
                    arraythongke.add(tk)
                }
                tong = 0
                }
                val pieList: ArrayList<PieEntry> = ArrayList()
                if(arraythongke.count() == 0){
                    pieList.add(PieEntry(1f,"chiteu"))
                }else{
                for(i in arraythongke.indices){
                    pieList.add(PieEntry(arraythongke[i].tien.toFloat(),arraythongke[i].tieude))
                }
                }
                initPieChart(piechar)
                bieudo(pieList,piechar)
                if(arraythongke.count() == 0 ){
                    val add = thongke("Tổng thu nhập:",0,1)
                    arraythongke.add(add)
                }else{
                    val add1 = thongke("Tổng thu nhập:",tongtatca,1)
                    arraythongke.add(add1)
                }
                lv_tkchitieu.adapter = thunhap_adapter(context!!,R.layout.item_lv_thongke,arraythongke)
            for (i in arraythongke.indices){
                tongtatca += arraythongke[i].tien
            }
        }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        spinnernam.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                arraythongke.clear()
                var tong = 0
                var tongtatca = 0
                for (j in data_Spinnerchitieu.Chitieu.list!!.indices){
                    val sql =" select Tien from CHITIEU where CHITIEU.tieude = '${data_Spinnerchitieu.Chitieu.list!![j].Tieude}' AND CHITIEU.Date like '%/_${array_thang_tn[spinnerthang.selectedItemPosition]}/${array_nam_tn[spinnernam.selectedItemPosition]}'"
                    val cursor : Cursor = Chitieu_mdf().findall_tk(sql)!!
                    while (cursor.moveToNext()){
                        val tien_db = cursor.getInt(0)
                        tong += tien_db
                    }
                    if (tong != 0){
                        val tk = thongke(data_Spinnerchitieu.Chitieu.list!![j].Tieude,tong,0)
                        arraythongke.add(tk)
                    }
                    tong = 0
                }



                for (i in arraythongke.indices){
                    tongtatca += arraythongke[i].tien
                }

                val pieList: ArrayList<PieEntry> = ArrayList()
                if(arraythongke.count() == 0){
                    pieList.add(PieEntry(1f,"chiteu"))
                }else{
                    pieList.clear()
                    for(i in arraythongke.indices){
                        pieList.add(PieEntry(arraythongke[i].tien.toFloat(),arraythongke[i].tieude))
                    }
                }
                initPieChart(piechar)
                bieudo(pieList,piechar)
                if(arraythongke.count() == 0 ){
                    val add = thongke("Tổng thu nhập:",0,1)
                    arraythongke.add(add)
                }else{
                    val add1 = thongke("Tổng thu nhập:",tongtatca,1)
                    arraythongke.add(add1)
                }
                lv_tkchitieu.adapter = thunhap_adapter(context!!,R.layout.item_lv_thongke,arraythongke)
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        return view;
    }
    fun loadct (context : Context, lv : ListView,piechar : PieChart){
        val arraythongke : ArrayList<thongke> = ArrayList()
        val thang : Int = (cal.get(Calendar.MONTH)+1)
        val nam : Int = (cal.get(Calendar.YEAR))
        var tong = 0
        var tongtatca = 0
        val array_thang_tn : ArrayList<Int> = ArrayList()
        val array_nam_tn : ArrayList<Int> = ArrayList()
        for (j in 2018 .. nam + 10){
            array_nam_tn.add(j)
        }
        for (i in 1 .. 12 ){
            array_thang_tn.add(i)
        }
        for (j in data_Spinnerchitieu.Chitieu.list!!.indices){
            val sql =" select Tien from CHITIEU where CHITIEU.tieude = '${data_Spinnerthunhap.Thunhap.list!![j].Tieude}' AND CHITIEU.Date like '%/_${thang}/${nam}'"
            val cursor : Cursor = Chitieu_mdf().findall_tk(sql)!!
            while (cursor.moveToNext()){
                val tien_db = cursor.getInt(0)
                tong += tien_db
            }
            if (tong != 0){
                val tk = thongke(data_Spinnerchitieu.Chitieu.list!![j].Tieude,tong,0)
                arraythongke.add(tk)
            }
            tong = 0
        }
        val pieList: ArrayList<PieEntry> = ArrayList()
        for(i in arraythongke.indices){
            pieList.add(PieEntry(arraythongke[i].tien.toFloat(),arraythongke[i].tieude))
        }
        initPieChart(piechar)
        bieudo(pieList,piechar)
        lv.adapter = thunhap_adapter(context,R.layout.item_lv_thongke,arraythongke)
        for (i in arraythongke.indices){
            tongtatca += arraythongke[i].tien
        }
        val add1 = thongke("Tổng thu nhập:",tongtatca,1)
        arraythongke.add(add1)
        lv.adapter = thunhap_adapter(context,R.layout.item_lv_thongke,arraythongke)
    }



    fun initPieChart(piechar : PieChart) {
        piechar.setUsePercentValues(true)
        piechar.description.text = ""
        piechar.isDrawHoleEnabled = false
        piechar.setTouchEnabled(true)
        piechar.setDrawEntryLabels(false)
        piechar.setExtraOffsets(20f, 0f, 20f, 20f)
        piechar.setUsePercentValues(true)
        piechar.isRotationEnabled = false
        piechar.setDrawEntryLabels(false)
        piechar.legend.orientation = Legend.LegendOrientation.VERTICAL
        piechar.legend.isWordWrapEnabled = true

    }
    fun bieudo(pieList: ArrayList<PieEntry>,piechar : PieChart){
        val piedata : PieData
        piechar.setUsePercentValues(true)
        val arrcolor = ArrayList<Int>()
        arrcolor.add(Color.RED)
        arrcolor.add(Color.GREEN)
        val pieDataSet = PieDataSet(pieList,"")
        pieDataSet.sliceSpace = 2f
        pieDataSet.setValueFormatter(PercentFormatter())
        pieDataSet.valueTextSize = 16f
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.colors = arrcolor
        piedata = PieData(pieDataSet)
        piechar.animateY(1500)
        piechar.transparentCircleRadius = 61f
        piechar.holeRadius = 58f
        piechar.isDrawHoleEnabled = true
        piechar.setHoleColor(Color.WHITE)
        val legend : Legend? = piechar.legend
        legend!!.textSize = 16f
        piechar.data = piedata
        piechar.invalidate()
    }
}