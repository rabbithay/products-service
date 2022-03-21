package com.thay.util

import com.thay.ProductServiceRequest
import com.thay.utils.ValidationUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValidationUtilTest {

    @Test
    fun `when validadetPayload method is called with valid data, should not throw a exception`(){
        // Arrange
        val request = ProductServiceRequest.newBuilder()
            .setName("product name")
            .setPrice(10.99)
            .setQuantityInStock(9)
            .build()

        // Act - Assert
        Assertions.assertDoesNotThrow {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validadetPayload method is called with invalid product name, should throw a exception`(){
        // Arrange
        val request = ProductServiceRequest.newBuilder()
            .setName("")
            .setPrice(10.99)
            .setQuantityInStock(9)
            .build()

        // Act - Assert
        Assertions.assertThrowsExactly(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validadetPayload method is called with invalid product price, should throw a exception`(){
        // Arrange
        val request = ProductServiceRequest.newBuilder()
            .setName("product name")
            .setPrice(-10.99)
            .setQuantityInStock(9)
            .build()

        // Act - Assert
        Assertions.assertThrowsExactly(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validadetPayload method is called with no payload, should throw a exception`(){
        // Act - Assert
        Assertions.assertThrowsExactly(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(null)
        }
    }

}