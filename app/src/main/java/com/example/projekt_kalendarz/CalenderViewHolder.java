package com.example.projekt_kalendarz;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CalenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    private final CalenderAdapter.OnItemListener onItemListener;
    public ConstraintLayout myLayout;

    public CalenderViewHolder(@NonNull View itemView, CalenderAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        this.dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        this.myLayout = (ConstraintLayout)itemView.findViewById(R.id.cellId);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
