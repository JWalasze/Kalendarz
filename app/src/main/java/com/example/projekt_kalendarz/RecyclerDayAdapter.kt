package com.example.projekt_kalendarz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerDayAdapter(
    private val context: Context,
    private val arrayTasks: ArrayList<Task>,
    private val taskNumber: Int ) : RecyclerView.Adapter<RecyclerDayAdapter.ViewHolder>()
{

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val name: TextView = view.findViewById(R.id.nameOfActivity)
        val startDate: TextView = view.findViewById(R.id.startDate)
        val endDate: TextView = view.findViewById(R.id.endDate)
        val localization: TextView = view.findViewById(R.id.localization)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(context).inflate(R.layout.day_view_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = arrayTasks[position].taskName
        viewHolder.startDate.text = arrayTasks[position].dateStart.toString()
        viewHolder.endDate.text = arrayTasks[position].dateEnd.toString()
        viewHolder.localization.text = arrayTasks[position].localization.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int
    {
        return taskNumber
    }
}