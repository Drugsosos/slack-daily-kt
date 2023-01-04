package model

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn


@Entity(name = "posts")
data class Post (
    @Id
    @Column(name = "thread_ts", unique = true, nullable = false, length = 30)
    val thread: String,

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, updatable = false)
    val user: User,

)

