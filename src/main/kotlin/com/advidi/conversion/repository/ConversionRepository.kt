package com.advidi.conversion.repository

import com.advidi.conversion.domain.Conversion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

@Transactional
interface ConversionRepository : JpaRepository<Conversion, Long> {

    fun findFirst2000ByPublishedOrderByTimestampAsc(published: Boolean): List<Conversion>

    @Modifying
    @Query("update Conversion c set c.published = :published where c.id = :id")
    fun updatePublished(@Param("id") id: Long, @Param("published") published: Boolean)

}

