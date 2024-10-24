package dev.sanjaygangwar.tempproject.models.localdbclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userData")
data class userData(
    @PrimaryKey
    var id: Int,
    val name: String?="sa"
)