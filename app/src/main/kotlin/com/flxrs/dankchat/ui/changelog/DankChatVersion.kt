package com.flxrs.dankchat.ui.changelog

import com.flxrs.dankchat.BuildConfig

data class DankChatVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
) : Comparable<DankChatVersion> {
    override fun compareTo(other: DankChatVersion): Int = COMPARATOR.compare(this, other)

    fun formattedString(): String = "$major.$minor.$patch"

    companion object {
        private val CURRENT = checkNotNull(fromString(BuildConfig.VERSION_NAME)) { "Invalid VERSION_NAME: ${BuildConfig.VERSION_NAME}" }
        private val COMPARATOR =
            Comparator
                .comparingInt(DankChatVersion::major)
                .thenComparingInt(DankChatVersion::minor)
                .thenComparingInt(DankChatVersion::patch)

        fun fromString(version: String): DankChatVersion? = version
            .split(".")
            .takeIf { it.size >= 3 }
            ?.let { parts ->
                val major = parts[0].toIntOrNull() ?: return@let null
                val minor = parts[1].toIntOrNull() ?: return@let null
                val patchPart = parts[2].takeWhile { it.isDigit() }
                val patch = patchPart.toIntOrNull() ?: return@let null
                DankChatVersion(major, minor, patch)
            }

        val LATEST_CHANGELOG = DankChatChangelog.entries.findLast { CURRENT >= it.version }
        val HAS_CHANGELOG = LATEST_CHANGELOG != null
    }
}
