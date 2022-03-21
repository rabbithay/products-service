package com.thay.services

import com.thay.dto.ProductReq
import com.thay.dto.ProductRes

interface ProductService {
    fun create(req: ProductReq): ProductRes
}
