package huuphu.aprotrain.quanlychitieu2

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.mikephil.charting.charts.PieChart
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import huuphu.aprotrain.quanlychitieu2.Spinner.data_Spinnerchitieu
import huuphu.aprotrain.quanlychitieu2.Spinner.data_Spinnerthunhap
import huuphu.aprotrain.quanlychitieu2.Spinner.sp_chitieu_adapter
import huuphu.aprotrain.quanlychitieu2.Spinner.sp_thunhap_adapter
import huuphu.aprotrain.quanlychitieu2.databinding.ActivityMainBinding
import huuphu.aprotrain.quanlychitieu2.databinding.FragmentChitieuBinding
import huuphu.aprotrain.quanlychitieu2.tadlayout_thongke.Fragment_tk_chitieu
import huuphu.aprotrain.quanlychitieu2.tadlayout_thongke.Fragment_tk_thunhap
import huuphu.aprotrain.quanlychitieu2.tadlayout_thongke.thunhap_adapter
import huuphu.aprotrain.quanlychitieu2.ui.TongquanFragment
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_chitieu.*
import kotlinx.android.synthetic.main.fragment_thunhap.*
import kotlinx.android.synthetic.main.item_spinner.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_Tongquan,R.id.nav_Thunhap, R.id.nav_Chitieu, R.id.nav_Thongke
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val bottomnavigation : BottomNavigationView = findViewById(R.id.bottomnavigation)
        bottomnavigation.background = null
        val navcontrollerbo = findNavController(R.id.nav_host_fragment_content_main)
        bottomnavigation.setupWithNavController(navcontrollerbo)


        DBhelper.Instances(this)
// Add
            floatingActionButton_add.isEnabled = true
            floatingActionButton_add.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val alter : View = LayoutInflater.from(this).inflate(R.layout.add_dialog2,null)
            builder.setView(alter)
            val dialog : Dialog = builder.create()
            dialog.show()
            val btn_huy_dla2 : Button = alter.findViewById(R.id.btn_huy_add2)
            btn_huy_dla2.setOnClickListener {
                dialog.dismiss()
            }

// Thu nhap
                val themthunhap : LinearLayout = alter.findViewById(R.id.themthunhap)
                themthunhap.setOnClickListener {
                    dialog.dismiss()
                    val table = "THUNHAP"
                    val builderthunhap = AlertDialog.Builder(this)
                    val alterthunhap : View = LayoutInflater.from(this).inflate(R.layout.dialog_add,null)
                    builderthunhap.setView(alterthunhap)
                    val dialogthunhap : Dialog = builderthunhap.create()
                    dialogthunhap.show()
                    val td: TextView = alterthunhap.findViewById(R.id.tieude_dialog)
                    td.text = "Thêm thu nhập"
//  Spinner thu nhap
                    val spinnerthunhap : Spinner = alterthunhap.findViewById(R.id.spinnerthunhap)
                    val spinnerthunahpadapter = sp_thunhap_adapter(this,data_Spinnerthunhap.Thunhap.list)
                    spinnerthunhap.adapter = spinnerthunahpadapter
//  calender thu nhap
                    val textv_date: TextView = alterthunhap.findViewById(R.id.calenderdate)
                    val calender: Calendar = Calendar.getInstance()
                    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                    textv_date.text = (simpleDateFormat.format(calender.getTime()))
                    val day: Int = calender.get(Calendar.DATE)
                    val month: Int = calender.get(Calendar.MONTH)
                    val year: Int = calender.get(Calendar.YEAR)
                    val btn_date: ImageButton = alterthunhap.findViewById(R.id.calendrimg)
                    btn_date.setOnClickListener {
                        val datepicker: DatePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            calender.set(year, month, dayOfMonth)
                            val Date = SimpleDateFormat("dd/MM/yyyy")
                            textv_date.text = (Date.format(calender.getTime()))
                        }, year, month, day)
                        datepicker.show()
                    }
//  Cancel thu nhap
                    val btn_cancel : Button = alterthunhap.findViewById(R.id.btn_cancel_thunhap)
                    btn_cancel.setOnClickListener {
                        dialogthunhap.dismiss();
                    }
//  Add thu nhap
                    val ghi_chu: EditText = alterthunhap.findViewById(R.id.ghichuthunhap)
                    val tienedt: EditText = alterthunhap.findViewById(R.id.tien_edit)
                    val btn_addthunhap : Button = alterthunhap.findViewById(R.id.btn_addthunhap)
                    btn_addthunhap.setOnClickListener {
                        val id = spinnerthunhap.selectedItemPosition
                        val imgdanhmuc: Int = data_Spinnerthunhap.Thunhap.list!![id].img
                        val tieudedanhmuc: String = data_Spinnerthunhap.Thunhap.list!![id].Tieude
                        val tientn: Int = tienedt.text.toString().toFloat().toInt()
                        val ghichutn: String = (ghi_chu.text.toString())
                        val datetn: String = textv_date.text.toString()
                        val data = datathunhap(imgdanhmuc, tieudedanhmuc, datetn, tientn, ghichutn)
                        Chitieu_mdf().add(table,data)
                       if(bottomnavigation.getSelectedItemId() == bottomnavigation.menu.findItem(R.id.nav_Thunhap).itemId){
                           val list : ListView = findViewById(R.id.listview_thunhap)
                           load(list)
                       }else if(bottomnavigation.getSelectedItemId() == bottomnavigation.menu.findItem(R.id.nav_Chitieu).itemId){
                           val list : ListView = findViewById(R.id.lv_chitieu)
                           load2(list)
                       }else if(bottomnavigation.getSelectedItemId() == bottomnavigation.menu.findItem(R.id.nav_Tongquan).itemId){
                           val lv : ListView = findViewById(R.id.lv_all)
                           val piechar : PieChart = findViewById(R.id.pieChartall)
                           TongquanFragment().loadall(this,lv,piechar)
                       }else{
                        val listview : ListView = findViewById(R.id.lv_tkthunhap)
                        val piechar : PieChart = findViewById(R.id.pieChartthunhaptk)
                        Fragment_tk_thunhap().load(this,listview,piechar)}
                        dialogthunhap.dismiss()
                    }
                }

// Chi tieu
                val themchitieu : LinearLayout = alter.findViewById(R.id.themchitieu)
                themchitieu.setOnClickListener {
                    dialog.dismiss()
                    val table = "CHITIEU";
                    val builderchitieu = AlertDialog.Builder(this)
                    val alterchitieu : View = LayoutInflater.from(this).inflate(R.layout.dialog_add,null)
                    builderchitieu.setView(alterchitieu)
                    val dialogchitieu : Dialog = builderchitieu.create()
                    dialogchitieu.show()
                    val td : TextView = alterchitieu.findViewById(R.id.tieude_dialog)
                    td.text = "Thêm chi tiêu"
//  Spinner chi tieu
                    val spinnerthunhap : Spinner = alterchitieu.findViewById(R.id.spinnerthunhap)
                    val spinnerchitieuadapter = sp_chitieu_adapter(this,data_Spinnerchitieu.Chitieu.list)
                    spinnerthunhap.adapter = spinnerchitieuadapter
//  calender chi tieu
                    val textv_date: TextView = alterchitieu.findViewById(R.id.calenderdate)

                    val calender: Calendar = Calendar.getInstance()
                    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                    textv_date.text = (simpleDateFormat.format(calender.getTime()))
                    val day: Int = calender.get(Calendar.DATE)
                    val month: Int = calender.get(Calendar.MONTH)
                    val year: Int = calender.get(Calendar.YEAR)
                    val btn_date: ImageButton = alterchitieu.findViewById(R.id.calendrimg)
                    btn_date.setOnClickListener {
                        val datepicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            calender.set(year, month, dayOfMonth)
                            val Date = SimpleDateFormat("dd/MM/yyyy")
                            textv_date.text = (Date.format(calender.getTime()))
                        }, year, month, day)
                        datepicker.show()
                    }
//  Cancel chi tieu
                    val btn_cancel : Button = alterchitieu.findViewById(R.id.btn_cancel_thunhap)
                    btn_cancel.setOnClickListener {
                        dialogchitieu.dismiss();
                    }
//  Add chi tieu
                    val ghi_chu: EditText = alterchitieu.findViewById(R.id.ghichuthunhap)
                    val tienedt: EditText = alterchitieu.findViewById(R.id.tien_edit)
                    val btn_addthunhap : Button = alterchitieu.findViewById(R.id.btn_addthunhap)
                    btn_addthunhap.setOnClickListener {
                        val id = spinnerthunhap.selectedItemPosition
                        val imgdanhmuc: Int = data_Spinnerchitieu.Chitieu.list!![id].img
                        val tieudedanhmuc: String = data_Spinnerchitieu.Chitieu.list!![id].Tieude
                        val tientn: Int = tienedt.text.toString().toInt()
                        val ghichutn: String = (ghi_chu.text.toString())
                        val datetn: String = textv_date.text.toString()
                        val data = datathunhap(imgdanhmuc, tieudedanhmuc, datetn, tientn, ghichutn)
                        Chitieu_mdf().add(table,data)
                        if(bottomnavigation.getSelectedItemId() == bottomnavigation.menu.findItem(R.id.nav_Thunhap).itemId){
                            val list : ListView = findViewById(R.id.listview_thunhap)
                            load(list)
                        }else if(bottomnavigation.getSelectedItemId() == bottomnavigation.menu.findItem(R.id.nav_Chitieu).itemId){
                            val list : ListView = findViewById(R.id.lv_chitieu)
                            load2(list)
                        }else if(bottomnavigation.getSelectedItemId() == bottomnavigation.menu.findItem(R.id.nav_Tongquan).itemId){
                            val lv : ListView = findViewById(R.id.lv_all)
                            val piechar : PieChart = findViewById(R.id.pieChartall)
                            TongquanFragment().loadall(this,lv,piechar)
                        }else{
                        val listview : ListView = findViewById(R.id.lv_tkchitieu)
                        val piechar : PieChart = findViewById(R.id.pieChartchitieutk)
                        Fragment_tk_chitieu().loadct(this,listview,piechar)}
                        dialogchitieu.dismiss()
                    }
                }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

fun load(lv : ListView){
        val cursor : Cursor = Chitieu_mdf().findall("THUNHAP")
        val adapter = cursoradapter(this,cursor)
        lv.adapter = adapter
        adapter.notifyDataSetChanged()
}
    fun load2(lv : ListView){
        val cursor : Cursor = Chitieu_mdf().findall("CHITIEU")
        val adapter = chitieuadapter(this,cursor)
        lv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}
