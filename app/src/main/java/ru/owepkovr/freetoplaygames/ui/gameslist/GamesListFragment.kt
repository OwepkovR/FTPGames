package ru.owepkovr.freetoplaygames.ui.gameslist

import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yanzhenjie.recyclerview.SwipeMenuCreator
import com.yanzhenjie.recyclerview.SwipeMenuItem
import freetoplaygames.R
import freetoplaygames.databinding.FragmentGamesListListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.owepkovr.freetoplaygames.arch.recycler.BaseDelegationAdapter
import ru.owepkovr.freetoplaygames.arch.ui.BaseFragment
import ru.owepkovr.freetoplaygames.data.model.ui.GameUIModel
import ru.owepkovr.freetoplaygames.ui.gameslist.adapter.GamesAdapterDelegates
import ru.owepkovr.freetoplaygames.ui.main.MainActivity

/**
 * A fragment representing a list of Items.
 */
class GamesListFragment : BaseFragment<GamesListViewModel>(R.layout.fragment_games_list_list) {

    private var records: MutableList<GameUIModel> = mutableListOf()
    private val adapter by lazy { createAdapter() }
    private val binding: FragmentGamesListListBinding by viewBinding()
    override val viewModel: GamesListViewModel by viewModel()

    override fun initViews() {
        setupMainToolbar(binding.toolbar)
        initObservers()

        val swipeMenuCreator = SwipeMenuCreator { leftMenu, rightMenu, position ->
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            val width = resources.getDimensionPixelSize(R.dimen.recycler_button)
            val widthDivider = resources.getDimensionPixelSize(R.dimen.recycler_divider)
            val shareItem = SwipeMenuItem(requireContext())
                .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray_bg))
                .setImage(R.drawable.ic_share)
                .setHeight(height)
                .setWidth(width)
            val divider = SwipeMenuItem(requireContext())
                .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.divider_color))
                .setHeight(height)
                .setWidth(widthDivider)
            val hideItem = SwipeMenuItem(requireContext())
                .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray_bg))
                .setImage(R.drawable.ic_hide)
                .setHeight(height)
                .setWidth(width)
            rightMenu.addMenuItem(hideItem)
            rightMenu.addMenuItem(divider)
            rightMenu.addMenuItem(shareItem)
        }
        binding.recyclerViewLayout.recyclerView.setSwipeMenuCreator(swipeMenuCreator)

        binding.recyclerViewLayout.recyclerView.adapter = adapter

        binding.recyclerViewLayout.swipeRefreshLayout.apply {
            setOnRefreshListener {
                isRefreshing = false
                records = mutableListOf()
                viewModel.getGamesList()
            }
        }

        viewModel.getGamesList()

        binding.toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).showDrawer(this)
        }
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