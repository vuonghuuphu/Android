package huuphu.aprotrain.quanlychitieu2.tadlayout_thongke

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class viewpager_thongke_adapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return Fragment_tk_chitieu()
            1 -> return Fragment_tk_thunhap()
//            2 -> return Fragment_tk_tatca()
            else -> return Fragment_tk_thunhap()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String = ""
        when (position){
            0 ->title = "chi tiêu"
            1 -> title = "thu nhập"
//            2 ->title = "tat ca"
        }
        return title
    }
}