package com.example.fitnessapp.presentation.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.example.domain.core.NetworkError
import com.example.fitnessapp.BuildConfig
import com.example.fitnessapp.MobileNavigationDirections
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.extensions.activityNavController
import com.example.fitnessapp.presentation.extensions.navigateSafely
import com.example.fitnessapp.presentation.extensions.showToastLong
import com.example.fitnessapp.presentation.ui.state.UIState
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Base class for [Fragment]s that work with data
 */
abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        setupRequests()
        setupSubscribers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupSubscribers() {
    }

    protected fun View.clickBack(
        navController: NavController = findNavController()
    ) {
        setOnClickListener { navController.navigateUp() }
    }

    /**
     * Collect [UIState] with [launchRepeatOnLifecycle]
     *
     * @receiver [StateFlow] with [UIState]
     *
     * @param state optional, for working with all states
     * @param onError for error handling
     * @param onSuccess for working with data
     */
    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((UIState<T>) -> Unit)? = null,
        onError: ((error: NetworkError) -> Unit),
        onSuccess: ((data: T) -> Unit)
    ) {
        launchRepeatOnLifecycle(lifecycleState) {
            this@collectUIState.collect {
                state?.invoke(it)
                when (it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {}
                    is UIState.Error -> onError.invoke(it.error)
                    is UIState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    /**
     * Collect [PagingData] with [launchRepeatOnLifecycle]
     *
     * @receiver [Flow] with [PagingData]
     */
    protected fun <T : Any> Flow<PagingData<T>>.collectPaging(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (value: PagingData<T>) -> Unit
    ) {
        launchRepeatOnLifecycle(lifecycleState) { this@collectPaging.collectLatest { action(it) } }
    }

    /**
     * Setup views visibility depending on [UIState] states.
     *
     * @receiver [UIState]
     *
     * @param willShowViewIfSuccess whether to show views if the request is successful
     */
    protected fun <T> UIState<T>.setupViewVisibility(
        group: ViewGroup, loader: CircularProgressIndicator, willShowViewIfSuccess: Boolean = true
    ) {
        fun showLoader(isVisible: Boolean) {
            group.isVisible = !isVisible
            loader.isVisible = isVisible
        }

        when (this) {
            is UIState.Idle -> {}
            is UIState.Loading -> showLoader(true)
            is UIState.Error -> showLoader(false)
            is UIState.Success -> showLoader(!willShowViewIfSuccess)
        }
    }

    /**
     * Collect flow safely with [launchRepeatOnLifecycle]
     */
    protected fun <T> Flow<T>.collectSafely(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        collector: (T) -> Unit
    ) {
        launchRepeatOnLifecycle(state) {
            this@collectSafely.collect {
                collector(it)
            }
        }
    }

    /**
     * Extension function for setup errors from server side
     *
     * @receiver [NetworkError]
     */
    fun NetworkError.setupApiErrors() = when (this) {
        is NetworkError.Unexpected -> {
            activityNavController().navigateSafely(
                MobileNavigationDirections.actionGlobalDialogError(
                    getString(R.string.error),
                    if (BuildConfig.DEBUG) this.error
                    else  getString(R.string.something_went_wrong_try_again)
                )
            )
        }

        is NetworkError.Api -> {
            showToastLong(this.error ?: getString(R.string.error))
        }

        is NetworkError.Timeout -> {
            activityNavController().navigateSafely(
                MobileNavigationDirections.actionGlobalDialogError(
                    getString(R.string.error), getString(R.string.check_internet_connection)
                )
            )
        }

        else -> {}
    }

    /**
     * Launch coroutine with [repeatOnLifecycle] API
     *
     * @param state [Lifecycle.State][androidx.lifecycle.Lifecycle.State] in which `block` runs in a new coroutine. That coroutine
     * will cancel if the lifecycle falls below that state, and will restart if it's in that state
     * again.
     * @param block The block to run when the lifecycle is at least in [state] state.
     */
    private fun launchRepeatOnLifecycle(
        state: Lifecycle.State,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                block()
            }
        }
    }
}