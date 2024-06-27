package com.perfectlypressed.android.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.perfectlypressed.android.base.domain.BaseViewModel
import com.perfectlypressed.android.base.presentation.BaseFragment
import com.perfectlypressed.android.splash.domain.SplashViewModel
import com.perfectlypressed.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun getBindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel.onInit()
    }

    override val viewModel: BaseViewModel
        get() = splashViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}