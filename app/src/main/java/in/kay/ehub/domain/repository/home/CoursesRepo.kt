package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.Courses




interface CoursesRepo {
    suspend fun getCourses():List<Courses>
}