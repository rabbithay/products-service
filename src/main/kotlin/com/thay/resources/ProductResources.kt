package com.thay.resources

import com.thay.ProductServiceRequest
import com.thay.ProductServiceResponse
import com.thay.ProductsServiceGrpc
import com.thay.dto.ProductReq
import com.thay.exceptions.BaseBusinessException
import com.thay.services.ProductService
import com.thay.utils.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductResources (private val productService: ProductService): ProductsServiceGrpc.ProductsServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        try {
            val payload = ValidationUtil.validatePayload(request)
            val productReq = ProductReq(name = payload.name, price = payload.price, quantityInStock = payload.quantityInStock)
            val productRes = productService.create(productReq)

            val productResponse = ProductServiceResponse.newBuilder()
                .setId(productRes.id)
                .setName(productRes.name)
                .setPrice(productRes.price)
                .setQuantityInStock(productRes.quantityInStock)
                .build()

            responseObserver?.onNext(productResponse)
            responseObserver?.onCompleted()
        } catch (ex: BaseBusinessException){
            responseObserver?.onError(
                ex.statusCode()
                .toStatus()
                .withDescription(ex.errorMessage())
                .asRuntimeException()
            )
        }
    }
}