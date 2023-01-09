package model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "channels")
data class Channel (
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 20)
    val id: String,

    @Column(name = "teamID", nullable = false, updatable = false, length = 20)
    val teamID: String,

    @Column(name = "cron", nullable = true, updatable = true)
    var cron: Int? = null,

    @Column(name = "cron_timezone", nullable = true, updatable = true)
    var cronTZ: String? = null,

    @Column(name = "team_lead", nullable = true, updatable = true)
    var teamLead: String? = null
)
