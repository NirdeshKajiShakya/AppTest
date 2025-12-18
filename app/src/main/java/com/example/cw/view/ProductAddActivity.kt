package com.example.cw.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.model.ProductModel
import com.example.cw.model.ProductViewModel
import com.example.cw.repo.ProductRepoImpl

class ProductAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAddScreen()
        }
    }
}

@Composable
fun ProductAddScreen(){
    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }
    val context = LocalContext.current

    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productImage by remember { mutableStateOf("") }

    Scaffold(

    ) {
        padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item { InputFieldsForProductAdd(
                productName = productName,
                onProductNameChange = { productName = it },
                productDescription = productDescription,
                onProductDescriptionChange = { productDescription = it },
                productPrice = productPrice,
                onProductPriceChange = { productPrice = it },
                productImage = productImage,
                onProductImageChange = { productImage = it }
            ) }
            item {
                ButtonForAddingProduct(
                    productName = productName,
                    productDescription = productDescription,
                    productPrice = productPrice,
                    productImage = productImage,
                    productViewModel = productViewModel,
                    context = context,
                    onProductAdded = {
                        // Clear fields after successful addition
                        productName = ""
                        productDescription = ""
                        productPrice = ""
                        productImage = ""
                    }
                )
            }
        }
    }
}

@Composable
fun InputFieldsForProductAdd(
    productName: String,
    onProductNameChange: (String) -> Unit,
    productDescription: String,
    onProductDescriptionChange: (String) -> Unit,
    productPrice: String,
    onProductPriceChange: (String) -> Unit,
    productImage: String,
    onProductImageChange: (String) -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = productName,
            onValueChange = onProductNameChange,
            label = { Text(text = "Product Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = productDescription,
            onValueChange = onProductDescriptionChange,
            label = { Text(text = "Product Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = productPrice,
            onValueChange = onProductPriceChange,
            label = { Text(text = "Product Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = productImage,
            onValueChange = onProductImageChange,
            label = { Text(text = "Product Image URL") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Composable
fun ButtonForAddingProduct(
    productName: String,
    productDescription: String,
    productPrice: String,
    productImage: String,
    productViewModel: ProductViewModel,
    context: android.content.Context,
    onProductAdded: () -> Unit
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            // Validate input fields
            if (productName.isEmpty() || productDescription.isEmpty() || productPrice.isEmpty() || productImage.isEmpty()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Validate price is a valid number
                val price = productPrice.toDoubleOrNull()
                if (price == null || price <= 0) {
                    Toast.makeText(context, "Please enter a valid price", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                // Create product model with the provided data
                val model = ProductModel(
                    productID = "",
                    productName = productName,
                    productDescription = productDescription,
                    productPrice = price,
                    productImageURL = productImage
                )

                // Add product to Firebase Database
                productViewModel.addProduct(model) { isSuccess, message ->
                    if (isSuccess) {
                        Toast.makeText(context, "Product added successfully!", Toast.LENGTH_SHORT).show()
                        onProductAdded()
                    } else {
                        Toast.makeText(context, "Failed to add product: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    ) {
        Text(text = "Add Product")
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    ) {
        Text("View Products")
    }
}

@Preview
@Composable
fun ProductAddScreenPreview(){
    ProductAddScreen()
}