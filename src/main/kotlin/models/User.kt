package models

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn


@Entity(name = "users")
data class User (
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 20)
    val user: String,

    @Column(name = "started", nullable = false, updatable = true)
    var started: Boolean = false,

    @Column(name = "question_index", nullable = false, updatable = true)
    var questionIndex: Int = 0,

    @ManyToOne
    @JoinColumn(name = "id", nullable=false, updatable=false)
    val channel: Channel,

    @Column(name = "real_name", nullable=true, updatable=true)
    var realName: String? = null
)

