package com.advidi.conversion.repository

import com.advidi.conversion.domain.Conversion
import org.springframework.data.jpa.repository.JpaRepository

interface ConversionRepository : JpaRepository<Conversion, Long>

