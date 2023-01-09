package model

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.GeneratedValue


@Entity(name = "questions")
data class Question (
    @Id
    @Column(name = "id")
    @GeneratedValue
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id", nullable=false, updatable=false)
    val channel: Channel,

    @Column(name = "text", nullable = false, updatable = false)
    val text: String
)

