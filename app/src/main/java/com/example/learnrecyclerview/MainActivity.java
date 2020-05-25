package com.example.learnrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //Виджет RecyclerView не занимается размещением элементов на экране самостоятельно — он поручает эту задачу LayoutManager.
        // Объект LayoutManager управляет позиционированием элементов,
        // а также определяет поведение прокрутки.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        ArrayList<String> myDataset = new ArrayList<>();

        MyAdapter() {
            for (int i = 1; i <= 100; i++) {
                myDataset.add("text" + i);
            }
        }
//Адаптер отвечает за:
// - создание необходимых объектов ViewHolder (onCreateViewHolder);
// - связывание ViewHolder с данными из уровня модели (onBindViewHolder).
        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.textView.setText(myDataset.get(position));
        }

        @Override
        public int getItemCount() {
            return myDataset.size();
        }

        //ViewHolder делает только одно: он удерживает объект View
        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            MyViewHolder(@NonNull View v) {
                super(v);
                textView = v.findViewById(R.id.text_view); //вместо v можно было использовать itemView. Поле
                //itemView — главная причина для существования ViewHolder: в нем хранится ссылка на все представление View, переданное super(view)
            }
        }
    }
}
