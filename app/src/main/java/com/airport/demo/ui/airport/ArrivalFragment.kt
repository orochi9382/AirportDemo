package com.airport.demo.ui.airport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airport.demo.databinding.FragmentAirportInfoBinding
import com.airport.demo.di.AirPortFlyInjection
import com.airport.demo.model.AirPortFlyEntity
import com.airport.demo.ui.airport.adapter.AirPortAdapter
import com.airport.demo.utils.DialogUtil

class ArrivalFragment : Fragment() {
    private var _binding: FragmentAirportInfoBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<AirportFlyViewModel> {
        AirPortFlyInjection.provideViewModelFactory()
    }

    private lateinit var adapter : AirPortAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAirportInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.rv?.layoutManager = LinearLayoutManager(requireContext())
        adapter = AirPortAdapter(mutableListOf())
        _binding?.rv?.adapter = adapter
        _binding?.rv?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastPosition = findLastVisibleItemPosition(recyclerView)
                adapter.checkLoadMore(lastPosition)
            }
        })
    }

    fun findLastVisibleItemPosition(rv: RecyclerView): Int {
        val layoutManager = rv.layoutManager
        return if (layoutManager is LinearLayoutManager) {
            layoutManager.findLastVisibleItemPosition()
        } else {
            RecyclerView.NO_POSITION
        }
    }

    override fun onResume() {
        super.onResume()
        liveBinding()
        vm.requestFlyInfo("A")
    }

    override fun onPause() {
        super.onPause()
        vm.bindingAirportFlyEntity.removeObserver(arrivalObserver)
        vm.onMessageError.removeObserver(errorObserver)
        vm.loading.removeObserver(loadingObserver)
    }

    private fun liveBinding(){
        vm.bindingAirportFlyEntity.observe(viewLifecycleOwner, arrivalObserver)
        vm.onMessageError.observe(viewLifecycleOwner,errorObserver)
        vm.loading.observe(viewLifecycleOwner,loadingObserver)
    }

    private val arrivalObserver = Observer<List<AirPortFlyEntity>> {
        it.let{
            requireActivity().runOnUiThread {
                adapter.update(it)
            }
        }
    }

    private val errorObserver = Observer<Any> {
        it.let{
            requireActivity().runOnUiThread {
                DialogUtil().showDialog(requireActivity())
            }
        }
    }


    private val loadingObserver = Observer<Boolean> {
        it.let{
            requireActivity().runOnUiThread {
                if (it){
                    _binding?.progress?.visibility = View.VISIBLE
                }else{
                    _binding?.progress?.visibility = View.GONE
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}