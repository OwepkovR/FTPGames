package ru.owepkovr.freetoplaygames.ui.splash

import android.os.Bundle
import android.view.View
import freetoplaygames.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.owepkovr.freetoplaygames.arch.ui.BaseFragment

class SplashFragment : BaseFragment<SplashViewModel>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        auth()
    }

    private fun initObservers() {
        viewModel.stateLive.observe(this::renderData)
    }

    private fun auth() {
        viewModel.auth()
    }

    private fun renderData(state: SplashViewModelState) {
        if (state.isLoading) {
            showProgressDialog(requireContext())
        } else {
            hideProgressDialog()
        }

        if (state.authed) {
            hideProgressDialog()
            viewModel.navigateToList()
        }

        if (!state.errorMessage.isNullOrEmpty()) {
            showErrorMessage(state.errorMessage)
        }
    }
}