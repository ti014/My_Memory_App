package iuh.ad.phannguyenmaiphuong.mssv19469121.mymemory.models

enum class BoardSize(val numCard: Int){
    EASY(numCard = 8),
    MEDIUM(numCard =  18),
    HARD(numCard = 24);

    fun getWidth(): Int {
        return when (this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }

    fun getHeight(): Int {
        return numCard / getWidth()
    }

    fun getNumPairs(): Int {
        return numCard / 2
    }

}