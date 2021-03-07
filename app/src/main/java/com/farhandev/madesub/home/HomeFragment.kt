package com.farhandev.madesub.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhandev.madesub.core.data.source.Resource
import com.farhandev.madesub.core.ui.NewsAdapter
import com.farhandev.madesub.databinding.FragmentHomeBinding
import com.farhandev.madesub.detail.DetailNewsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val newsAdapter = NewsAdapter()
            newsAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.news.observe(viewLifecycleOwner, { news ->
                if (news != null){
                    when (news) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            newsAdapter.setData(news.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Failed Load Data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvNews){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }
}