package com.ezioapps.flagquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        tv_name.text = intent.getStringExtra(Constants.USER_NAME)
        val score = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        val totalQ = intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        tv_score.text = "your score is $score out of $totalQ"
    }
}