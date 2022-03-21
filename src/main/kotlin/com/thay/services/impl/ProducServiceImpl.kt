package com.thay.services.impl

import com.thay.dto.ProductReq
import com.thay.dto.ProductRes
import com.thay.exceptions.AlreadyExistsException
import com.thay.repository.ProductRepository
import com.thay.services.ProductService
import com.thay.utils.toDomain
import com.thay.utils.toProductRes
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun create(req: ProductReq): ProductRes {
        verifyName(req.name)
        val savedProduct = productRepository.save(req.toDomain())
        return savedProduct.toProductRes()
    }

    private fun verifyName(name: String) {
        productRepository.findByNameIgnoreCase(name)?.let {
            throw AlreadyExistsException(name)
        }
    }
}

