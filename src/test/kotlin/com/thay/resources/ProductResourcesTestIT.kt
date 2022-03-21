package com.thay.resources

import com.thay.ProductServiceRequest
import com.thay.ProductsServiceGrpc
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
internal class ProductResourcesTestIT(
    private val productServiceBlockingStub: ProductsServiceGrpc.ProductsServiceBlockingStub
) {

    @Test
    fun `when ProductsServiceGrpc create method is called with valid data a success is returned`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("product name")
            .setPrice(10.99)
            .setQuantityInStock(9)
            .build()

        val response = productServiceBlockingStub.create(request)

        Assertions.assertEquals(1, response.id)
        Assertions.assertEquals("product name", response.name)
        Assertions.assertEquals(10.99, response.price)
        Assertions.assertEquals(9, response.quantityInStock)

    }
}