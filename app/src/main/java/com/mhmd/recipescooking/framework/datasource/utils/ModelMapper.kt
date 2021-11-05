package com.mhmd.recipescooking.framework.datasource.utils

interface ModelMapper <T, Model>{

    fun mapToModel(model: T): Model

    fun mapFromModel(model: Model): T
}