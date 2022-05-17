package com.example.projekt_kalendarz;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private final int previousDaysNumber;
    private final int daysOfMonthLength;

    public CalenderAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, int previousDaysNumber, int length)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.previousDaysNumber = previousDaysNumber;
        this.daysOfMonthLength = length;
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.12);
        return new CalenderViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position)
    {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        if (position < previousDaysNumber - 1 || position > daysOfMonthLength + previousDaysNumber - 2)
        {
            holder.dayOfMonth.setTextColor(Color.parseColor("#A9A9A9"));
        }
        else
        {
            holder.dayOfMonth.setTextSize(30);
        }
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}
