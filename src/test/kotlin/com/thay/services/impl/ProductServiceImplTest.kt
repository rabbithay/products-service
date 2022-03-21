package com.thay.services.impl

import com.thay.domain.Product
import com.thay.dto.ProductReq
import com.thay.exceptions.AlreadyExistsException
import com.thay.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

internal class ProductServiceImplTest {
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productService = ProductServiceImpl(productRepository)

    @Test
    fun `when create method is called with valid data a ProductRes is returned`() {
        val productInput = Product(id = null, name = "Product name", price = 10.00, quantityInStock = 10)
        val productOutput = Product(id = 1, name = "Product name", price = 10.00, quantityInStock = 10)

        `when`(productRepository.save(productInput))
            .thenReturn(productOutput)

        val productReq = ProductReq(name = "Product name", price = 10.00, quantityInStock = 10)

        val productRes = productService.create(productReq)

        assertEquals(productRes.name, productReq.name)
    }

    @Test
    fun `when create method is called with duplicated product name, throws AlreadyExistsException`() {
        val productInput = Product(id = null, name = "Product name", price = 10.00, quantityInStock = 10)
        val productOutput = Product(id = 1, name = "Product name", price = 10.00, quantityInStock = 10)

        `when`(productRepository.findByNameIgnoreCase(productInput.name))
            .thenReturn(productOutput)

        val productReq = ProductReq(name = "Product name", price = 10.00, quantityInStock = 10)

        Assertions.assertThrowsExactly(AlreadyExistsException::class.java){
            productService.create(productReq)
        }
    }
}

