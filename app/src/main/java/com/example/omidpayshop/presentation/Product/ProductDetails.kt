package com.example.omidpayshop.presentation.Product

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.omidpayshop.presentation.ViewModel.ProductDetailsViewModel
import com.example.omidpayshop.data.Model.Entity.ProductEntity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun ProductDetails(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productId: Int,
    navController: androidx.navigation.NavHostController,
){
    val product by viewModel.product.collectAsState()

        product?.let{
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
            {
AsyncImage(
    model = it.imageUrl,
    contentDescription = "Product Image",
    modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
     )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${it.price}دلار ",
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge
                )
    }
  } ?: run{
      Box(modifier = Modifier.fillMaxSize(),
contentAlignment = Alignment.Center
          ){
          Text(
              text = "در حال بارگذاری...",
              style = MaterialTheme.typography.bodyLarge
          )
      }
  }
}