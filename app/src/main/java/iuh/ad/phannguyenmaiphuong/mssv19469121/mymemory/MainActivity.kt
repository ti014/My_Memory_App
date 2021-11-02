package iuh.ad.phannguyenmaiphuong.mssv19469121.mymemory

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iuh.ad.phannguyenmaiphuong.mssv19469121.mymemory.models.BoardSize
import iuh.ad.phannguyenmaiphuong.mssv19469121.mymemory.models.MemoryCard
import iuh.ad.phannguyenmaiphuong.mssv19469121.mymemory.models.MemoryGame
import iuh.ad.phannguyenmaiphuong.mssv19469121.mymemory.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView

    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: MemoryBoardAdapter
    private var boardSize: BoardSize = BoardSize.HARD


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

//        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
//        val randomizedImages = (chosenImages + chosenImages).shuffled()
//        val memoryCard = randomizedImages.map { MemoryCard(it) }

        memoryGame = MemoryGame(boardSize)

        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards,object : MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
//                Log.i(TAG, "Card Clicked $position")
                updateGameWithFlip(position)
            }
        })
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateGameWithFlip(position: Int) {
        memoryGame.flipCard(position)
        adapter.notifyDataSetChanged()
    }
}