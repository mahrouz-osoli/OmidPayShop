package com.example.omidpayshop.presentation.Product


import com.example.omidpayshop.presentation.ViewModel.ProductViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.omidpayshop.data.Model.Product.ProductResponseModel
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.TextRange

@Composable
fun ProductListItem(product: ProductResponseModel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = product.title,
                    style = MaterialTheme.typography.titleMedium, maxLines = 2)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${product.price} \$", style = MaterialTheme.typography.titleSmall, color = Color(0xFF388E3C))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Product(
    viewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (ProductResponseModel) -> Unit = {}
) {
    val filteredProducts by viewModel.filteredProducts.collectAsState()
    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val searchField by viewModel.searchField.collectAsState()
//    var searchField by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
            .padding(12.dp)) {

        Spacer(modifier = Modifier.height(26.dp))

        OutlinedTextField(
            value = searchField,
            onValueChange = {
                viewModel.updateSearchField(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "جستجوی نام کالا",
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            trailingIcon = {
                if (searchField.text.isNotEmpty()) {
                    IconButton(onClick = { viewModel.updateSearchField(TextFieldValue("")) }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear Icon")
                    }
                }
            },
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                textDirection = TextDirection.ContentOrLtr,
                fontSize = 16.sp
            ),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )


//        BasicTextField(
//            value = searchField,
//            onValueChange = { searchField = it },
//            textStyle = TextStyle(
//                textAlign = TextAlign.Right,
//                fontSize = 16.sp
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White, RoundedCornerShape(8.dp))
//                .padding(12.dp),
//            singleLine = true,
//            decorationBox = { innerTextField ->
//                if (searchField.text.isEmpty()) {
//                    Text("جستجوی نام کالا",
//                        color = Color.Gray,
//                        textAlign = TextAlign.Right)
//                }
//                innerTextField()
//            }
//        )

        Spacer(modifier = Modifier.height(12.dp))

//        val filteredProducts = if (searchField.text.isEmpty()) {
//            products
//        } else {
//            products.filter { it.title.contains(searchField.text, ignoreCase = true) }
//        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Text(
                text = "خطا در دریافت داده: $errorMessage",
                color = Color.Red,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        } else if (filteredProducts.isEmpty()) {
            Text(
                "هیچ کالایی پیدا نشد.",
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        } else {
            androidx.compose.foundation.lazy.LazyColumn {
                items(filteredProducts) { product ->
                    ProductListItem(product = product, onClick = { onProductClick(product) })
                }
            }
        }
    }
}

