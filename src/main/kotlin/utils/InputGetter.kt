package utils

import java.io.File
import java.io.InputStream

object InputGetter {
    fun getFileInput(day: Int, year: Int, fileName: String = "input"): List<String> {
        val path = System.getProperty("user.dir")
        val fileNamePath = "$path/src/main/kotlin/year$year/day$day/$fileName.txt"

        val inputStream: InputStream = File(fileNamePath).inputStream()

        val inputString = inputStream.bufferedReader().use { it.readText() }

        return inputString.split("\n")
    }
}