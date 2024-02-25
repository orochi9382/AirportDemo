package com.airport.demo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airport.demo.R
import com.airport.demo.databinding.FragmentHomeBinding
import com.airport.demo.ui.airport.ArrivalFragment
import com.airport.demo.ui.airport.DepartureFragment
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = _binding?.tabLayout!!
        val viewPager = _binding?.viewPager!!


        _binding?.viewPager?.adapter = FragmentAdapter(requireActivity() )
        TabLayoutMediator(tabLayout,viewPager){tab, position ->

            val textView = TextView(tabLayout.context)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.layoutParams = layoutParams
            textView.gravity = Gravity.CENTER

            var icon = 0
            var text = ""
            when (position){
                0 -> {
                    icon = R.drawable.ic_airplane_take_off
                    text = "起飛班機"
                }
                1 -> {
                    icon = R.drawable.ic_airplane_landing
                    text = "抵達班機"
                }
                else -> 0
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(icon,0,0,0)
            textView.text = text
            tab.customView = textView
        }.attach()

    }



    class FragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> DepartureFragment()
                else -> ArrivalFragment()

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}