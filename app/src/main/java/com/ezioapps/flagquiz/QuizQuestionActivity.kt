package com.ezioapps.flagquiz

import android.content.Intent
import android.graphics.Typeface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*



class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener{


    var score : Int = 0
    private lateinit var questionsList:ArrayList<Question>
    var selectedOption : Int = 0
    val options = ArrayList<TextView>(4)
    private var currentPosition = 1
    lateinit var question:Question
    var user_name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        user_name = intent.getStringExtra(Constants.USER_NAME)

        score = 0
        questionsList = Constants.getQuestions()
        question = questionsList[currentPosition-1]

        setQuestion()


        options.add(0, optionOne)
        options.add(1,optionTwo)
        options.add(2,optionThree)
        options.add(3,optionFour)

        for (option in options){
            option.setOnClickListener(this)
        }
        buttonSubmit.setOnClickListener(this)
        buttonNextQuestion.setOnClickListener(this)

    }

    private fun setDefaultBorder(){

        for (option in options){

            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
            option.setTextColor(getColor(R.color.design_default_color_secondary))
            option.typeface = Typeface.DEFAULT
        }
    }
    private fun selectedOption(selectedOption: Int){
        setDefaultBorder()


        val nowOption = options[selectedOption-1]
        nowOption.setTypeface(Typeface.DEFAULT_BOLD)
        nowOption.setTextColor(getColor(R.color.colorPrimaryDark))
        nowOption.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)


    }
    private fun setQuestion(){

        setDefaultBorder()

        question = questionsList[currentPosition-1]
        progressBar.progress = currentPosition
        flagImageView.setImageResource(question.image)
        progressText.text = "$currentPosition/10"
        optionOne.text =  question.optionOne
        optionTwo.text =  question.optionTwo
        optionThree.text =  question.optionThree
        optionFour.text =  question.optionFour
        buttonNextQuestion.visibility=View.GONE
        buttonSubmit.visibility = View.VISIBLE

    }

    override fun onClick(v: View?) {
        when (v) {
            optionOne -> {
                selectedOption=1


            }
            optionTwo -> {
                selectedOption =2

            }
            optionThree -> {
                selectedOption = 3

            }
            optionFour -> {
                selectedOption = 4
            }


        }

        selectedOption(selectedOption)


        if (v==buttonSubmit){
            val optionNow = options[selectedOption-1]
            optionNow.setBackgroundColor(getColor(R.color.colorRed))
            optionNow.setTextColor(getColor(R.color.colorWhite))
            options[question.correctAnswer -1].setBackgroundColor(getColor(R.color.colorGreen))
            options[question.correctAnswer -1].setTextColor(getColor(R.color.colorWhite))
            if (selectedOption == question.correctAnswer){
                score+=1
            }
            buttonSubmit.visibility = View.GONE
            buttonNextQuestion.visibility = View.VISIBLE



        }
        if (v==buttonNextQuestion){
            currentPosition++
            if (currentPosition<=questionsList.size)
             setQuestion()
            else{
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra(Constants.USER_NAME,user_name)
                intent.putExtra(Constants.CORRECT_ANSWERS,score.toString())
                intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size.toString())
                startActivity(intent)
            }

        }
    }




}