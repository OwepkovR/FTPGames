package ru.owepkovr.freetoplaygames.ui.gameslist

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import freetoplaygames.R
import freetoplaygames.databinding.FragmentGamesListListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.owepkovr.freetoplaygames.arch.recycler.BaseDelegationAdapter
import ru.owepkovr.freetoplaygames.arch.ui.BaseFragment
import ru.owepkovr.freetoplaygames.data.model.ui.GameUIModel
import ru.owepkovr.freetoplaygames.ui.gameslist.adapter.GamesAdapterDelegates

/**
 * A fragment representing a list of Items.
 */
class GamesListFragment : BaseFragment<GamesListViewModel>(R.layout.fragment_games_list_list) {

    private var records: MutableList<GameUIModel> = mutableListOf()
    private val adapter by lazy { createAdapter() }
    private val binding: FragmentGamesListListBinding by viewBinding()
    override val viewModel: GamesListViewModel by viewModel()

    override fun initViews() {
        initObservers()
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                isRefreshing = false
                records = mutableListOf()
                viewModel.getGamesList()
            }
        }

        viewModel.getGamesList()
    }

    private fun initObservers() {
        viewModel.stateLive.observe(this::renderData)
    }

    private fun createAdapter() = BaseDelegationAdapter(
        GamesAdapterDelegates.gamesDelegate(
            this::showDetail
        )
    )

    fun showDetail(item: GameUIModel) {

    }

    fun renderData(state: GamesListViewState) {
        if (state.data != null) {
            records = state.data as MutableList<GameUIModel>
        }
        adapter.setItems(records)
        if (state.isLoading) {
            showProgressDialog(requireContext())
        } else {
            hideProgressDialog()
        }
        if (state.errorMessage != null) {
            Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}