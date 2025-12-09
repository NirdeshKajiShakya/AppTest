package com.example.cw.repo

import com.example.cw.model.ProductModel

interface ProductRepo {
    fun getAllProducts(callback: (Boolean, String, List<Any>) -> Unit)

    fun getProductByID(productID: String, callback: (Boolean, String, Any?) -> Unit)

    fun addProduct(model : ProductModel, callback: (Boolean, String) -> Unit)

    fun deleteProduct(productID: String, callback: (Boolean, String) -> Unit)

    fun updateProduct(productID: String, model : ProductModel, callback: (Boolean, String) -> Unit)

    fun searchProducts(query: String, callback: (Boolean, String, List<Any>) -> Unit)
}