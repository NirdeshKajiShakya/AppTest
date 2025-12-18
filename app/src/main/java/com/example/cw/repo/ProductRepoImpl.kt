package com.example.cw.repo

import com.example.cw.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ProductRepoImpl : ProductRepo {
    
    val database = FirebaseDatabase.getInstance()
    val ref: DatabaseReference = database.getReference("Products")

    override fun getAllProducts(callback: (Boolean, String, List<ProductModel>) -> Unit) {
        ref.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val productList = mutableListOf<ProductModel>()
                for (snapshot in it.result.children) {
                    val product = snapshot.getValue(ProductModel::class.java)
                    product?.let { p -> productList.add(p) }
                }
                callback(true, "Products retrieved successfully", productList)
            } else {
                callback(false, "${it.exception?.message}", emptyList())
            }
        }
    }

    override fun getProductByID(
        productID: String,
        callback: (Boolean, String, Any?) -> Unit
    ) {
        ref.child(productID).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val product = it.result.getValue(ProductModel::class.java)
                callback(true, "Product retrieved successfully", product)
            } else {
                callback(false, "${it.exception?.message}", null)
            }
        }
    }

    override fun addProduct(
        model : ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        val id = ref.push().key
        if (id == null) {
            callback(false, "Failed to generate product ID")
            return
        }
        model.productID = id

        ref.child(id).setValue(model).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Product added successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun deleteProduct(
        productID: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productID).removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Product deleted successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun updateProduct(
        model : ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(model.productID).updateChildren(model.toMap()).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Product updated successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun searchProducts(
        query: String,
        callback: (Boolean, String, List<Any>) -> Unit
    ) {
        ref.orderByChild("productName").startAt(query).endAt(query + "\uf8ff").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val productList = mutableListOf<Any>()
                    for (snapshot in it.result.children) {
                        val product = snapshot.getValue(ProductModel::class.java)
                        product?.let { p -> productList.add(p) }
                    }
                    callback(true, "Search completed successfully", productList)
                } else {
                    callback(false, "${it.exception?.message}", emptyList())
                }
            }
    }

}