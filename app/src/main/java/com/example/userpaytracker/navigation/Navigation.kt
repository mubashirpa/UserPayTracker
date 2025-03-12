package com.example.userpaytracker.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.userpaytracker.R
import com.example.userpaytracker.presentation.home.HomeFragment
import com.example.userpaytracker.presentation.paymentDetails.PaymentDetailsFragment

fun AppCompatActivity.findNavController(
    @IdRes viewId: Int,
    startDestination: Screen = Screen.Home,
): NavController {
    val navHostFragment = supportFragmentManager.findFragmentById(viewId) as NavHostFragment
    val navController = navHostFragment.navController

    navController.graph =
        navController.createGraph(startDestination = startDestination) {
            fragment<HomeFragment, Screen.Home> {
                label = getString(R.string.label_home_screen)
            }
            fragment<PaymentDetailsFragment, Screen.PaymentDetails> {
                label = getString(R.string.label_payment_details_screen)
            }
        }

    return navController
}
