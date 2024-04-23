package dev.retrotv.data.enums

enum class OperatingSystem(private val label: String) {
    WINDOWS("Windows"),
    LINUX("Linux"),
    UNIX("Unix"),
    DARWIN("Darwin"),
    SOLARIS("Solaris"),
    OTHERS("Others");

    fun label(): String {
        return label
    }
}