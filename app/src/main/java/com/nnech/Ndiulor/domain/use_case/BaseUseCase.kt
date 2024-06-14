package com.nnech.Ndiulor.domain.use_case

interface BaseUseCase<in Parameter, out Result> {
    operator fun invoke(params: Parameter): Result
}