package huuphu.aprotrain.quanlychitieu2.Spinner

import huuphu.aprotrain.quanlychitieu2.R

class data_Spinnerthunhap(var img : Int, var Tieude: String) {
    object Thunhap{
        var img = intArrayOf(
            R.drawable.icontntienluong,
            R.drawable.icontntocap,
            R.drawable.iconlamthem,
            R.drawable.iconxoso,
            R.drawable.chothuenha,
            R.drawable.icontnchovay
        )
        var Tieude = arrayOf(
            "Tiền lương",
            "Tièn trợ cấp",
            "việc làm thêm",
            "Xổ số",
            "Cho thuê nhà",
            "Tiền lãi "
        )
        var list : ArrayList<data_Spinnerthunhap>? =  null
get() {
if (field != null)
return  field
        field =ArrayList()
        for (i in img.indices){
        val imgid = img[i]
        val tieude_tn = Tieude[i]
        val danhmucthunhap =data_Spinnerthunhap(imgid,tieude_tn)
        field!!.add(danhmucthunhap)
        }
    return field
}

    } }