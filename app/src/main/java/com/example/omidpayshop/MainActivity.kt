package com.example.omidpayshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.omidpayshop.ui.theme.OmidPayShopTheme
import com.example.omidpayshop.presentation.Product.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OmidPayShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Product()
                }
            }
        }
    }
}