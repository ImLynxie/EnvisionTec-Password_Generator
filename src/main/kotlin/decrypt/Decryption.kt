package decrypt

class Decryption {
    private fun swapCharacter(stringToSwap: CharArray, iPosA: Int, iPosB: Int) {
        val ch1 = stringToSwap[iPosA]
        val ch2 = stringToSwap[iPosB]

        stringToSwap[iPosA] = ch2
        stringToSwap[iPosB] = ch1
    }

    private fun getNextReturn(iInitialIndex: Int): Int {
        var iInitialIndex = iInitialIndex
        var num: Int
        do {
            ++iInitialIndex
            num = 2
            while (num < iInitialIndex && iInitialIndex % num != 0) ++num
        } while (num != iInitialIndex)
        return iInitialIndex
    }

    private fun getPrimaryIndex(iIndex: Int): Int {
        var iInitialIndex = 1
        for (index in 1 until iIndex) iInitialIndex = getNextReturn(iInitialIndex)
        return iInitialIndex
    }

    fun decrypt(charArrayToDecrypt: CharArray): String {
        for (index in charArrayToDecrypt.indices.reversed()) {
            val iPosA = getPrimaryIndex(index + 3) % charArrayToDecrypt.size
            swapCharacter(charArrayToDecrypt, iPosA, index)
        }
        println(String(charArrayToDecrypt))
        return String(charArrayToDecrypt)
    }

    fun encrypt(charArrayToEncrypt: CharArray): String {
        for (index in charArrayToEncrypt.indices) {
            val iPosA = getPrimaryIndex(index + 3) % charArrayToEncrypt.size
            swapCharacter(charArrayToEncrypt, iPosA, index)
        }
        return String(charArrayToEncrypt)
    }

    fun generateEncryptedCode(machineGeneratedCode: String): String {
        return encrypt(machineGeneratedCode.toCharArray())

    }
}
