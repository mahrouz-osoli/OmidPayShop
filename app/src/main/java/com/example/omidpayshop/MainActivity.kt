package com.example.omidpayshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import com.example.omidpayshop.ui.theme.OmidPayShopTheme
import com.example.omidpayshop.presentation.Product.Product
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.omidpayshop.presentation.Product.ProductDetails


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OmidPayShopTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "productList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("productList") {
                            Product(navController = navController)
                        }
                        composable(
                            route = "productDetails/{productId}",
                            arguments = listOf(navArgument("productId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                            ProductDetails(productId = productId, navController = navController)
                        }
                    }
                }
            }
        }
    }
}