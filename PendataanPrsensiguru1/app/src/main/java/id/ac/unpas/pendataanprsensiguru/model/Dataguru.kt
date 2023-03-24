package id.ac.unpas.pendataanprsensiguru.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dataguru(
    @PrimaryKey val id: String,
    val nama: String,
    val nim: String,
    val matapelajaran: String,
    val absen: String
)
