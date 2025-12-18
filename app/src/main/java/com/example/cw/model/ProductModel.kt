package com.example.cw.model

data class ProductModel(
    var productID: String = "",
    var productName: String = "",
    var productDescription: String = "",
    var productPrice: Double = 0.0,
    var productImageURL: String = ""
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "productID" to productID,
            "productName" to productName,
            "productDescription" to productDescription,
            "productPrice" to productPrice,
            "productImageURL" to productImageURL
        )
    }
}
