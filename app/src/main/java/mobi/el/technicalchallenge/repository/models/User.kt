package mobi.el.technicalchallenge.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val email: String,
    val password: String,
    val age: Int
)