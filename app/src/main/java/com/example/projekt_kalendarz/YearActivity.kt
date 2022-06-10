package com.example.projekt_kalendarz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.time.LocalDate
import kotlin.properties.Delegates

class YearActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var yearName: TextView
    private lateinit var selectedDate: LocalDate
    private lateinit var buttonJan: Button
    private lateinit var buttonFeb: Button
    private lateinit var buttonMarch: Button
    private lateinit var buttonApril: Button
    private lateinit var buttonMay: Button
    private lateinit var buttonJune: Button
    private lateinit var buttonJuly: Button
    private lateinit var buttonAugust: Button
    private lateinit var buttonSeptember: Button
    private lateinit var buttonOctober: Button
    private lateinit var buttonNovember: Button
    private lateinit var buttonDecember: Button
    private lateinit var buttonNextYear: Button
    private lateinit var buttonPreviousYear: Button
    private lateinit var buttonViewMonth: Button
    private lateinit var buttonViewYear: Button
    private lateinit var buttonViewDay: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.year_view)

        val myMonthIntent: Intent = intent
        var myBundle = Bundle()
        myBundle = myMonthIntent.extras!!
        
        val selectedDateYear: Int = myBundle.getInt("SELECTED_DATE_YEAR")
        val selectedDateMonthValue: Int = myBundle.getInt("SELECTED_DATE_MONTH")

        initWidgets()

        selectedDate = LocalDate.of(selectedDateYear, selectedDateMonthValue, 1)
        yearName.text = selectedDate.year.toString()

        buttonJan.setOnClickListener(this)
        buttonFeb.setOnClickListener(this)
        buttonMarch.setOnClickListener(this)
        buttonApril.setOnClickListener(this)
        buttonMay.setOnClickListener(this)
        buttonJune.setOnClickListener(this)
        buttonJuly.setOnClickListener(this)
        buttonAugust.setOnClickListener(this)
        buttonSeptember.setOnClickListener(this)
        buttonOctober.setOnClickListener(this)
        buttonNovember.setOnClickListener(this)
        buttonDecember.setOnClickListener(this)
        buttonPreviousYear.setOnClickListener(this)
        buttonNextYear.setOnClickListener(this)
        buttonViewMonth.setOnClickListener(this)
        buttonViewYear.setOnClickListener(this)
        buttonViewDay.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        outState.putInt("SELECTED_DATE_YEAR", selectedDate.year)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        val yearsToAdd: Int = selectedDate.year - savedInstanceState.getInt("SELECTED_DATE_YEAR")
        selectedDate = selectedDate.minusYears(yearsToAdd.toLong())
        yearName.text = selectedDate.year.toString()
    }

    private fun initWidgets()
    {
        yearName = findViewById(R.id.monthNameYearView)
        buttonJan = findViewById(R.id.buttonJan)
        buttonFeb = findViewById(R.id.buttonFeb)
        buttonMarch = findViewById(R.id.buttonMar)
        buttonApril = findViewById(R.id.buttonApr)
        buttonMay = findViewById(R.id.buttonMay)
        buttonJune = findViewById(R.id.buttonJun)
        buttonJuly = findViewById(R.id.buttonJul)
        buttonAugust = findViewById(R.id.buttonAug)
        buttonSeptember = findViewById(R.id.buttonSep)
        buttonOctober = findViewById(R.id.buttonOct)
        buttonNovember = findViewById(R.id.buttonNov)
        buttonDecember = findViewById(R.id.buttonDec)
        buttonPreviousYear = findViewById(R.id.previousYear)
        buttonNextYear = findViewById(R.id.nextYear)
        buttonViewMonth = findViewById(R.id.viewMonth)
        buttonViewYear = findViewById(R.id.viewYear)
    }

    override fun onClick(view: View)
    {
        when (view.id)
        {
            R.id.buttonJan -> setResult(selectedDate.year, 1)
            R.id.buttonFeb -> setResult(selectedDate.year, 2)
            R.id.buttonMar -> setResult(selectedDate.year, 3)
            R.id.buttonApr -> setResult(selectedDate.year, 4)
            R.id.buttonMay -> setResult(selectedDate.year, 5)
            R.id.buttonJun -> setResult(selectedDate.year, 6)
            R.id.buttonJul -> setResult(selectedDate.year, 7)
            R.id.buttonAug -> setResult(selectedDate.year, 8)
            R.id.buttonSep -> setResult(selectedDate.year, 9)
            R.id.buttonOct -> setResult(selectedDate.year, 10)
            R.id.buttonNov -> setResult(selectedDate.year, 11)
            R.id.buttonDec -> setResult(selectedDate.year, 12)
            R.id.previousYear ->
            {
                selectedDate = selectedDate.minusYears(1)
                yearName.text = selectedDate.year.toString()
            }
            R.id.nextYear ->
            {
                selectedDate = selectedDate.plusYears(1)
                yearName.text = selectedDate.year.toString()
            }
            R.id.viewYear ->
            {
                Toast.makeText(this, R.string.odpowiedz2, Toast.LENGTH_LONG).show()
            }
            R.id.viewDay ->
            {
                Toast.makeText(this, R.string.odpowiedz2, Toast.LENGTH_LONG).show()
            }
            R.id.viewMonth -> {setResult(selectedDate.year, selectedDate.monthValue)}
        }
    }

    private fun setResult(year: Int, month: Int)
    {
        val myMonthIntent = Intent(applicationContext, MainActivity::class.java)

        myMonthIntent.putExtra("NEW_YEAR", year)
        myMonthIntent.putExtra("NEW_MONTH", month)
        setResult(RESULT_OK, myMonthIntent)
        finish()
    }
}