package com.thay.util

import com.thay.domain.Product
import com.thay.dto.ProductReq
import com.thay.utils.toDomain
import com.thay.utils.toProductRes
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductConverterUtilTest {

    @Test
    fun `when toProductRes is called, should return a ProductRes with all  data`(){
        val product = Product(id = 1, name = "product", price = 1.99, quantityInStock = 1)
        val productRes = product.toProductRes()

        Assertions.assertEquals(product.id, productRes.id)
        Assertions.assertEquals(product.name, productRes.name)
        Assertions.assertEquals(product.price, productRes.price)
        Assertions.assertEquals(product.quantityInStock, productRes.quantityInStock)
    }

    @Test
    fun `when toDomain is called, should return a ProductRes with all  data`(){
        val productReq = ProductReq(name = "product", price = 1.99, quantityInStock = 1)
        val product = productReq.toDomain()

        Assertions.assertEquals(null, product.id)
        Assertions.assertEquals(productReq.name, product.name)
        Assertions.assertEquals(productReq.price, product.price)
        Assertions.assertEquals(productReq.quantityInStock, product.quantityInStock)
    }
}