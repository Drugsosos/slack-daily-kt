package models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "channels")
data class Channel (
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 20)
    val id: String,

    @Column(name = "team", nullable = false, updatable = false, length = 20)
    val team: String,

    @Column(name = "name", nullable = false, updatable = true)
    var name: String,

    @Column(name = "cron", nullable = true, updatable = true)
    var cron: Int? = null,

    @Column(name = "cron_timezone", nullable = true, updatable = true)
    var cronTZ: String? = null
)
