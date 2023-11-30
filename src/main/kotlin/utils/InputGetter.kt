package utils

import java.io.File
import java.io.InputStream

object InputGetter {
    fun getFileInput(day: Int, year: Int): List<String> {
        val path = System.getProperty("user.dir")
        val fileNamePath = "$path/src/main/kotlin/year$year/day$day/input.txt"

        val inputStream: InputStream = File(fileNamePath).inputStream()

        val inputString = inputStream.bufferedReader().use { it.readText() }

        return inputString.split("\n")
    }
}