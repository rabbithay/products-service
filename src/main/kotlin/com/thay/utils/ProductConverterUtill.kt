package com.thay.utils

import com.thay.domain.Product
import com.thay.dto.ProductReq
import com.thay.dto.ProductRes

fun Product.toProductRes(): ProductRes {
    return ProductRes(
        id = id!!,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}

fun ProductReq.toDomain(): Product {
    return Product(
        id = null,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}