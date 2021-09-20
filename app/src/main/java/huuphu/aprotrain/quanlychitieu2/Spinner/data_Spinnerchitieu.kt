package huuphu.aprotrain.quanlychitieu2.Spinner

import huuphu.aprotrain.quanlychitieu2.R

class data_Spinnerchitieu (var img : Int, var Tieude: String) {
    object Chitieu{
        var img = intArrayOf(
            R.drawable.anuong,
            R.drawable.quanao,
            R.drawable.baby,
            R.drawable.benhvien,
            R.drawable.diennuoc,
            R.drawable.nuoc,
            R.drawable.xang,
            R.drawable.giohang,
            R.drawable.hochanh,
            R.drawable.suachua,
            R.drawable.soxoct
        )
        var Tieude = arrayOf(
            "Ăn uống",
            "Quần áo",
            "Con cái",
            "Bệnh viện",
            "Tiền điện",
            "Tiền nước",
            "Xăng xe",
            "Giỏ hàng",
            "Học hành",
            "Sửa chữa",
            "Sổ số"

        )
        var list : ArrayList<data_Spinnerchitieu>? =  null
            get() {
                if (field != null)
                    return field
                field = ArrayList()
                for (i in img.indices) {
                    val imgid = img[i]
                    val tieude_tn = Tieude[i]
                    val danhmucchitieu = data_Spinnerchitieu(imgid, tieude_tn)
                    field!!.add(danhmucchitieu)
                }
                return field
            }}}