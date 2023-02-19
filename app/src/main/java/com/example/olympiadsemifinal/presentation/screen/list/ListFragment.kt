package com.example.olympiadsemifinal.presentation.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.olympiadsemifinal.R
import com.example.olympiadsemifinal.databinding.FragmentListBinding

import com.example.olympiadsemifinal.presentation.contract.navigation
import com.example.olympiadsemifinal.presentation.screen.factory
import com.example.olympiadsemifinal.presentation.screen.list.ListViewModel.Companion

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var servicesAdapter: ServicesAdapter
    private val viewModel: ListViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        setupRecyclerView()
        viewModel.serviceList.observe(viewLifecycleOwner) {
            servicesAdapter.serviceList = it
        }

        binding.retryButton.setOnClickListener {
            viewModel.getServiceList()
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                Companion.STATE_LOADING -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.INVISIBLE

                }
                Companion.STATE_SUCCESS -> {
                    binding.loadingProgressBar.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.INVISIBLE
                }
                Companion.STATE_ERROR -> {
                    binding.loadingProgressBar.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                }

                else -> throw IllegalArgumentException("Unknown State")
            }
        }


    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        servicesAdapter = ServicesAdapter()
        binding.recyclerView.adapter = servicesAdapter

        servicesAdapter.onItemClickListener = {
            navigation().showInfoScreen(it)
        }
    }

}