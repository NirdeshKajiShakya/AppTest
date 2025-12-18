package com.example.cw.model

import androidx.lifecycle.MutableLiveData
import com.example.cw.repo.ProductRepo

class ProductViewModel(val repo: ProductRepo) {

    private val _products = MutableLiveData<ProductModel?>()
    val products: MutableLiveData<ProductModel?> get() = _products


    private val _allProducts = MutableLiveData<List<ProductModel>?>()
    val allProducts: MutableLiveData<List<ProductModel>?> get() = _allProducts

    fun getAllProducts(){
        repo.getAllProducts{
            success, message, data ->
            if (success){
                _allProducts.postValue(data)
            } else {
                _allProducts.postValue(data)
            }
        }
    }

    fun getProductByID(productID: String){
        repo.getProductByID(productID){
            success, message, data ->
            if (success){
                _products.postValue(data as ProductModel?)
            } else {
                _products.postValue(null)
            }
        }
    }

    fun addProduct(model : ProductModel, callback: (Boolean, String) -> Unit){
        repo.addProduct(model, callback)
    }

    fun deleteProduct(productID: String, callback: (Boolean, String) -> Unit){
        repo.deleteProduct(productID, callback)
    }

    fun updateProduct(model : ProductModel, callback: (Boolean, String) -> Unit){
        repo.updateProduct(model, callback)
    }

    fun searchProducts(query: String, callback: (Boolean, String, List<Any>) -> Unit){
        repo.searchProducts(query, callback)
    }
}