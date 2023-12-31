package com.example.datastore.data.model

data class Course(
    val name: String,
    val description: String,
    val level: CourseLevel,
    val completed: Boolean
)

enum class CourseLevel { BEGINNER, ADVANCED }