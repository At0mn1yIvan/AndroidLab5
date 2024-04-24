package com.example.new_lab_5;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TasksActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private TaskAdapter taskAdapter;
    private List<Date> dates;
    private HashMap<Date, List<Task>> taskMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        expandableListView = findViewById(R.id.expandableListView);
        prepareTaskData();
        taskAdapter = new TaskAdapter(this, dates, taskMap);
        expandableListView.setAdapter(taskAdapter);
    }

    private void prepareTaskData() {
        dates = new ArrayList<>();
        taskMap = new HashMap<>();

        /*
        // Пример данных
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 5; i++) {
            Date date = calendar.getTime();
            dates.add(date);
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Task(date, "Задача " + (i + 1)));
            // Здесь можно добавить больше задач для каждой даты
            taskMap.put(date, tasks);
            calendar.add(Calendar.DATE, 1);
        }

         */

        Random random = new Random();

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 5; i++) {
            Date date = calendar.getTime();
            dates.add(date);
            List<Task> tasks = new ArrayList<>();

            // Добавление двух задач на случайные даты
            for (int j = 0; j < 2; j++) {
                // Генерация случайного числа от 1 до 5 для выбора дня в будущем
                int randomDay = random.nextInt(5) + 1;
                Calendar randomDate = Calendar.getInstance();
                randomDate.setTime(date);
                randomDate.add(Calendar.DATE, randomDay);
                Date randomTaskDate = randomDate.getTime();
                tasks.add(new Task(randomTaskDate, "Задача " + (j + 1)));
            }

            // Здесь можно добавить больше задач для каждой даты
            taskMap.put(date, tasks);
            calendar.add(Calendar.DATE, 7); // Добавление недели к текущей дате
        }
    }
}