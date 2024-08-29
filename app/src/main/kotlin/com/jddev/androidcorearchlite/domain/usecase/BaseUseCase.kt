package com.jddev.androidcorearchlite.domain.usecase

interface BaseUseCase<in Params, out Type> {
    suspend fun execute(params: Params): Type
}