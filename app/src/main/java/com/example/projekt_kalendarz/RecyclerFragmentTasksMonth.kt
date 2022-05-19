package com.example.projekt_kalendarz

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class RecyclerFragmentTasksMonth(
    private val context: Context,
    private val arrayTasks: ArrayList<Task>,
    private val taskNumber: Int ) : RecyclerView.Adapter<RecyclerDayAdapter.ViewHolder>()
{
    class FragmentViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerDayAdapter.ViewHolder
    {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerDayAdapter.ViewHolder, position: Int)
    {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int
    {
        TODO("Not yet implemented")
    }
}
