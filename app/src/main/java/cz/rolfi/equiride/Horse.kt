// app/src/main/java/cz/rolfi/equiride/Horse.kt
package cz.rolfi.equiride

data class Horse(
    val id: Int,
    val name: String,
    val walkSpeed: Double,
    val trotSpeed: Double,
    val gallopSpeed: Double
)
