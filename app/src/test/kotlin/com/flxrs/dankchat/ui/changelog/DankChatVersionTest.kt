package com.flxrs.dankchat.ui.changelog

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DankChatVersionTest {

    @Test
    fun `fromString should parse valid version strings`() {
        assertEquals(DankChatVersion(4, 1, 0), DankChatVersion.fromString("4.1.0"))
        assertEquals(DankChatVersion(1, 2, 3), DankChatVersion.fromString("1.2.3"))
    }

    @Test
    fun `fromString should parse version strings with suffixes`() {
        assertEquals(DankChatVersion(4, 1, 0), DankChatVersion.fromString("4.1.0-DSamtch"))
        assertEquals(DankChatVersion(4, 1, 0), DankChatVersion.fromString("4.1.0-debug"))
        assertEquals(DankChatVersion(1, 2, 3), DankChatVersion.fromString("1.2.3a"))
        assertEquals(DankChatVersion(1, 2, 3), DankChatVersion.fromString("1.2.3.4"))
    }

    @Test
    fun `fromString should return null for invalid version strings`() {
        assertNull(DankChatVersion.fromString("invalid"))
        assertNull(DankChatVersion.fromString("1.2"))
        assertNull(DankChatVersion.fromString("1.2.a"))
        assertNull(DankChatVersion.fromString("a.b.c"))
    }
}
