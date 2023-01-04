package models

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn


@Entity(name = "attachments")
data class Attachment (
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, updatable = false)
    val answer: Answer,

    @Column(name = "attachment", nullable = false)
    val attachment: String
)

