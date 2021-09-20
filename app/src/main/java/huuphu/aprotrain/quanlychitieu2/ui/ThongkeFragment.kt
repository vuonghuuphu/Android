@file:Suppress("DEPRECATION")

package huuphu.aprotrain.quanlychitieu2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import huuphu.aprotrain.quanlychitieu2.R
import huuphu.aprotrain.quanlychitieu2.tadlayout_thongke.viewpager_thongke_adapter

@Suppress("DEPRECATION")
class ThongkeFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_thongke,container,false)
        val fm: FragmentManager? = fragmentManager
        val tabLayout : TabLayout = view.findViewById(R.id.tadlayout_thongke)
        val viewpage : ViewPager = view.findViewById(R.id.view_pager_thongke)
        val viewpagethongke = viewpager_thongke_adapter(fm!!, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewpage.adapter = viewpagethongke
        tabLayout.setupWithViewPager(viewpage)
        return view
    }
}