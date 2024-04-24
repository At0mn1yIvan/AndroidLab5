package com.example.new_lab_5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private Stack<Fragment> stack = new Stack<>();
    private TextView textViewStack;
    private FragmentManager fragmentManager;
    private Intent task2 = null;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStack = findViewById(R.id.textView);
        Button buttonBack = findViewById(R.id.backBtn);
        Button buttonForward = findViewById(R.id.forwardBtn);
        fragmentManager = getSupportFragmentManager();

        PageFragment initialPage = new PageFragment(1, "Задача № 1 6:05:2024");
        stack.push(initialPage);
        updateStack();
        updateFragment();

        buttonBack.setOnClickListener(v -> {
            if (stack.size() > 1) {
                stack.pop();
                updateStack();
                updateFragment();
            }
        });

        buttonForward.setOnClickListener(v -> {
            if (stack.size() <= 4)
            {
                PageFragment pageFragment = new PageFragment(stack.size() + 1,
                        "Задача № " + (stack.size() + 1) + " " + ((stack.size() + 2) * 3)
                                + ".05.2024");
                stack.push(pageFragment);
                updateStack();
                updateFragment();
            }
        });

        Button createPage = findViewById(R.id.createPageBtn);
        Button deletePage = findViewById(R.id.deletePageBtn);
        createPage.setOnClickListener(v -> {
            if (task2 == null) {
                task2 = new Intent(this, SecondActivity.class);
                startActivityForResult(task2, 1);
            } else {
                startActivityForResult(task2, 1);
            }
        });
        deletePage.setOnClickListener(v -> {
            if (task2 != null) {
                finishActivity(1);
            }
        });

        Button textDialog = findViewById(R.id.textDialogBtn);
        Button dataDialog = findViewById(R.id.dataDialogBtn);
        Button timeDialog = findViewById(R.id.timeDialogBtn);
        textDialog.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Введите текст");

            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String userInput = input.getText().toString();
                    TextView text = findViewById(R.id.textDialogTextView);
                    text.setText(userInput);
                }
            });

            builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });
        dataDialog.setOnClickListener(v -> {
            new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    TextView text = findViewById(R.id.dataDialogTextView);
                    text.setText(day + ":" + month + ":" + year);
                }
            },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                    .show();
        });
        timeDialog.setOnClickListener(v -> {
            new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    TextView text = findViewById(R.id.timeDialogTextView);
                    text.setText(hourOfDay + ":" + minute);
                }
            },
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE), true)
                    .show();
        });



        Button webBtn = findViewById(R.id.webBtn);

        webBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, WebActivity.class));
        });

        Button swipeBtn = findViewById(R.id.clickerBtn);
        swipeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SwipeActivity.class));
        });

        Button taskBtn = findViewById(R.id.listTasksBtn);
        taskBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, TasksActivity.class));
        });

    }
    private void updateStack() {
        textViewStack.setText("Глубина стека: " + stack.size());

    }

    private void updateFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!stack.empty()) {
            fragmentTransaction.replace(R.id.frameLayout_container, stack.peek());
        } else {
            fragmentTransaction.remove(stack.peek());
        }
        fragmentTransaction.commit();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(v.getId(), 1, 0, "Пункт 1");
        menu.add(v.getId(), 2, 0, "Пункт 2");
        menu.add(v.getId(), 3, 0, "Пункт 3");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                Log.i("Меню", "Пункт 1");
                break;
            case 2:
                Log.i("Меню", "Пункт 2");
                break;
            case 3:
                Log.i("Меню", "Пункт 3");
                break;
        }
        return super.onContextItemSelected(item);
    }

}
