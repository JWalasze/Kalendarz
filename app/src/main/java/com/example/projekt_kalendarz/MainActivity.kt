package com.example.projekt_kalendarz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity(), CalenderAdapter.OnItemListener
{
    private lateinit var monthYearText: TextView
    private lateinit var calenderRecycleView: RecyclerView
    private lateinit var selectedDate: LocalDate
    private lateinit var dayTextInView: TextView
    private lateinit var view1: View

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.month_view)
        initWidgets()
        selectedDate = LocalDate.now()
        setMonthView()

        val monthFragment = FragmentTasksMonth()
        //view1.visibility = View.GONE
        //PAMIETAJ ŻE WIDOK PORTRET NIE MA FRAGMENTU DLATEGO WYWALA XDDD
        /*supportFragmentManager.beginTransaction().apply {
            replace(R.id.dayBackgroundRight, monthFragment)
            commit()
        }*/
        //DAC UKRYJ WIDOK CZY COS...VISIBILITY...
    }

    //Inicjalizacja widoków
    private fun initWidgets()
    {
        calenderRecycleView = findViewById(R.id.calenderRecycleView)
        monthYearText = findViewById(R.id.monthYearTV)
        view1 = findViewById(R.id.marginInsideBottom)
    }

    //Główna funkcja od tworzenia widoku miesięcznego, wywoływana za każdym razem jak idziemy
    //do innego miesiąca
    private fun setMonthView()
    {
        monthYearText.text = monthYearFromDate(selectedDate)
        val daysInMonth: ArrayList<String> = daysInMonthArray(selectedDate)
        val lengthMonth: Int = YearMonth.from(selectedDate).lengthOfMonth()

        val firstOfMonth: LocalDate  = selectedDate.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value
        val previousDaysNumber: Int = getPreviousDaysNumber(dayOfWeek)

        val calenderAdapter: CalenderAdapter =
            CalenderAdapter(
                this,
                daysInMonth,
                this,
                dayOfWeek,
                lengthMonth,
                selectedDate.monthValue,
                selectedDate.year
            )
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        calenderRecycleView.layoutManager = layoutManager
        calenderRecycleView.adapter = calenderAdapter
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String>
    {
        val daysInMonthArray: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()

        //Zwraca date pierwszego dnia miesiąca
        val firstOfMonth: LocalDate  = selectedDate.withDayOfMonth(1)
        //Wykorzystujemy to, aby wiedzieć, który to jest dzień tygodnia
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value
        var tempPreviousMonth: Int = getPreviousDaysNumber(dayOfWeek)

        for (i in 2..43)
        {
            when {
                i <= dayOfWeek -> {
                    daysInMonthArray.add(tempPreviousMonth.toString())
                    tempPreviousMonth += 1
                }
                i > daysInMonth + dayOfWeek -> {
                    daysInMonthArray.add((i - daysInMonth - dayOfWeek).toString())
                }
                else -> {
                    daysInMonthArray.add((i - dayOfWeek).toString())
                }
            }
        }
        return daysInMonthArray
    }

    private fun monthYearFromDate(date: LocalDate): String
    {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyy")
        return date.format(formatter)
    }

    private fun monthFromDate(date: LocalDate): String
    {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("M")
        return date.format(formatter)
    }

    private fun yearFromDate(date: LocalDate): String
    {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyy")
        return date.format(formatter)
    }

    fun previousMonthAction(view: View)
    {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View)
    {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    fun monthTasks(view: View)
    {
        val monthFragment = FragmentTasksMonth()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.dayBackgroundRight, monthFragment)
            commit()
        }
    }

    fun weekTasks(view: View)
    {
        val weekFragment = FragmentTasksWeek()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.dayBackgroundRight, weekFragment)
            commit()
        }
    }

    private fun getPreviousDaysNumber(dayOfWeek: Int): Int
    {
        selectedDate = selectedDate.minusMonths(1)
        val tempDaysInMonthPrevious: Int = YearMonth.from(selectedDate).lengthOfMonth()
        val tempPreviousMonth: Int = tempDaysInMonthPrevious - dayOfWeek + 2
        selectedDate = selectedDate.plusMonths(1)
        return tempPreviousMonth
    }

    override fun onItemClick(position: Int, dayText: String?)
    {
        val firstOfMonth: LocalDate  = selectedDate.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value
        val lengthMonth: Int = YearMonth.from(selectedDate).lengthOfMonth()

        if (dayText != "")
        {
            if (position + 2 > dayOfWeek && position + 2 <= lengthMonth + dayOfWeek)
            {
                val message: String = dayText + " " + monthYearFromDate(selectedDate)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                val intentDayView: Intent = Intent(applicationContext, DayActivity::class.java)
                intentDayView.putExtra("DAY_NUMBER", dayText)
                intentDayView.putExtra("MONTH_NUMBER", monthFromDate(selectedDate))
                intentDayView.putExtra("YEAR_NUMBER", yearFromDate(selectedDate))
                startActivity(intentDayView)
            }
        }
    }
}