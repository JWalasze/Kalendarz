package com.example.projekt_kalendarz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder>
{
    private final Context context;
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private final int previousDaysNumber;
    private final int daysOfMonthLength;
    private final int monthValue;
    private final int yearValue;

    public CalenderAdapter(Context context, ArrayList<String> daysOfMonth, OnItemListener onItemListener, int previousDaysNumber, int length, int monthValue, int yearValue)
    {
        this.context = context;
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.previousDaysNumber = previousDaysNumber;
        this.daysOfMonthLength = length;
        this.monthValue = monthValue;
        this.yearValue = yearValue;
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (parent.getHeight() / 6);
        return new CalenderViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position)
    {
        LocalDateTime actualDate = LocalDateTime.now();
        if (Integer.toString(actualDate.getDayOfMonth()).equals(daysOfMonth.get(position))
                && actualDate.getMonthValue() == this.monthValue
                && actualDate.getYear() == this.yearValue)
        {
            holder.myLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.days_rectangle));
        }

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
