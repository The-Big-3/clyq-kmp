package org.big3.clyq.di

import org.big3.clyq.ui.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{AuthViewModel()}
}