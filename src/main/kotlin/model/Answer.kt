package model

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn


@Entity(name = "answers")
data class Answer (
    @Id
    @Column(name = "id")
    @GeneratedValue
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, updatable=false)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, updatable=false)
    val question: Question,

    @Column(name = "text", nullable=false, updatable=false)
    val text: String
)


