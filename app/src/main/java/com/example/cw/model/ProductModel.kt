package com.example.cw.model

data class ProductModel(
    val productID: String = "",
    val productName: String = "",
    val productDescription: String = "",
    val productPrice: Double = 0.0,
    val productImageURL: String = ""
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "ProductID" to productID,
            "ProductName" to productName,
            "ProductDescription" to productDescription,
            "ProductPrice" to productPrice,
            "ProductImageURL" to productImageURL
        )
    }
}
