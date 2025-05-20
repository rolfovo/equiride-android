// app/src/main/java/cz/rolfi/equiride/HorsesRepository.kt
package cz.rolfi.equiride

object HorsesRepository {
    fun getHorses(): List<Horse> {
        return listOf(
            Horse(1, "Punťa", 1.7, 3.5, 6.0),
            Horse(2, "Hnědák", 1.6, 3.2, 5.8),
            Horse(3, "Blesk", 1.8, 3.6, 6.3)
        )
    }
}
