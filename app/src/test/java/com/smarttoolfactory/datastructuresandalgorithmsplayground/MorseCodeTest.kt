package com.smarttoolfactory.datastructuresandalgorithmsplayground

import com.google.common.truth.Truth
import com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms.getMorseChars
import com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms.getMorseCharsRecursive
import org.junit.Test

class MorseCodeTest {

    @Test
    fun `Get morse codes from 3 char signal recursively`() {
        val signals = "..."
        val expected = listOf('S')

        val actual = getMorseCharsRecursive(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 4 char signal recursively`() {
        val signals = "-..-"
        val expected = listOf('X')

        val actual = getMorseCharsRecursive(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 1 missing char in 2 char signal recursively`() {
        val signals = "-?"
        val expected = listOf('N', 'M')

        val actual = getMorseCharsRecursive(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 2 missing chars in 4 char signal recursively`() {
        val signals = "-?.?"
        val expected = listOf('B', 'X', 'Z', 'Q')

        val actual = getMorseCharsRecursive(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 3 missing chars in 4 char signal recursively`() {
        val signals = "?-??"
        val expected = listOf('L', 'Ä', 'P', 'J', 'Z', 'Q', 'Ö', '¢')

        val actual = getMorseCharsRecursive(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 4 missing chars in 4 char signal recursively`() {
        val signals = "????"
        val expected = listOf(
            'H', 'V', 'F', 'Ü',
            'L', 'Ä', 'P', 'J',
            'B', 'X', 'C', 'Y',
            'Z', 'Q', 'Ö', '¢'
        )

        val actual = getMorseCharsRecursive(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 3 char signal`() {
        val signals = "..."
        val expected = listOf('S')

        val actual = getMorseChars(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 4 char signal`() {
        val signals = "-..-"
        val expected = listOf('X')

        val actual = getMorseChars(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 1 missing char in 2 char signal`() {
        val signals = "-?"
        val expected = listOf('N', 'M')

        val actual = getMorseChars(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 2 missing chars in 4 char signal`() {
        val signals = "-?.?"
        val expected = listOf('B', 'X', 'Z', 'Q')

        val actual = getMorseChars(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 3 missing chars in 4 char signal`() {
        val signals = "?-??"
        val expected = listOf('L', 'Ä', 'P', 'J', 'Z', 'Q', 'Ö', '¢')

        val actual = getMorseChars(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `Get morse codes from 4 missing chars in 4 char signal`() {
        val signals = "????"
        val expected = listOf(
            'H', 'V', 'F', 'Ü',
            'L', 'Ä', 'P', 'J',
            'B', 'X', 'C', 'Y',
            'Z', 'Q', 'Ö', '¢'
        )

        val actual = getMorseChars(signals)

        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }
}
