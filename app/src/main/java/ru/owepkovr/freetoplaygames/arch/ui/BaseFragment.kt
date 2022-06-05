package ru.owepkovr.freetoplaygames.arch.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import freetoplaygames.R
import org.koin.android.ext.android.inject
import ru.owepkovr.freetoplaygames.arch.handler.ErrorMessageHandler
import ru.owepkovr.freetoplaygames.arch.navigation.NavigationRouter

abstract class BaseFragment<out T : BaseViewModel<*>>(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private val networkErrorMessageHandler: ErrorMessageHandler by inject()
    private var progressDialog: Dialog? = null

    abstract val viewModel: T

    open fun initViews() = Unit

    open fun getParams(): Bundle? = null

    open fun onSetParams(bundle: Bundle) = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>("params")
            ?.observe(viewLifecycleOwner) { result ->
                onSetParams(result)
            }

        initViews()
        initInnerObservers()
    }

    @MainThread
    protected inline fun <T> LiveData<T>.observe(crossinline onChanged: (T) -> Unit) {
        observe(owner = viewLifecycleOwner, onChanged = onChanged)
    }

    /**
     * Handles navigation events from a [ViewModel]
     */
    private fun initInnerObservers() {
        viewModel.navigationLive.observe(viewLifecycleOwner) {
            NavigationRouter.navigate(findNavController(), it, getParams())
        }
    }

    fun showProgressDialog(context: Context, cancelable: Boolean = false) {
        hideProgressDialog()
        if (progressDialog == null) {
            progressDialog = AlertDialog.Builder(context, R.style.ProgressDialog)
                .setView(R.layout.progress_bar_layout)
                .setCancelable(cancelable)
                .create()
        }
        progressDialog?.show()
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    fun showErrorMessage(message: String) {
        networkErrorMessageHandler.showErrorMessage(message)
    }
}