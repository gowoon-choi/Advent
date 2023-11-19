package com.hbd.data.mapper

interface MapperToData<Domain, Data> {
    fun Domain.toData(): Data
}

interface MapperToDomain<Data,Domain> {
    fun Data.toDomain(): Domain
}