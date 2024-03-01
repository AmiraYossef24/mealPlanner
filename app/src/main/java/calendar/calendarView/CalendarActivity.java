package calendar.calendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.example.mealplanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import DB.CategoryLocalDataSource;
import calendar.calendarPresenter.CalendarPresenter;
import calendar.calendarPresenter.ICalendarPresenter;
import homepage.view.Clickable;
import io.reactivex.rxjava3.core.Observable;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;
import androidx.lifecycle.Observer;


public class CalendarActivity extends AppCompatActivity implements Clickable,ICalendarActivity {
        RecyclerView recyclerView;

        CalendarView myCalendar;
        CalendarAdapter adapter;
        ImageView back;
        ICalendarPresenter presenter;
        private static final String TAG="RecycleView";
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_calendar);
                back=findViewById(R.id.returnArrow3ID);
                recyclerView = findViewById(R.id.calendarRecycleID);
                myCalendar = findViewById(R.id.calendarViewID);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new CalendarAdapter(this, new ArrayList<>(), this);
                presenter = new CalendarPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
                recyclerView.setAdapter(adapter);
                presenter.getAllPlans();
                back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                finish();
                        }
                });




        }


        public void showAllPlans(LiveData<List<DateMeal>> mealsLiveData) {
                mealsLiveData.observe(this, new Observer<List<DateMeal>>() {
                        @Override
                        public void onChanged(List<DateMeal> meals) {
                                if (meals != null) {
                                        myCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                                @Override
                                                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                                        Calendar selectedCalendar = Calendar.getInstance();
                                                        selectedCalendar.set(year, month, dayOfMonth);
                                                        Date selectedDate = selectedCalendar.getTime();
                                                        String nameOfSelectedDay = convertDateToDayName(selectedDate).toLowerCase();
                                                        List<DateMeal> filterList = new ArrayList<>();
                                                        for (DateMeal meal : meals) {
                                                                if (nameOfSelectedDay.equals(meal.getDay().toLowerCase())) {
                                                                        filterList.add(meal);
                                                                }
                                                        }
                                                        adapter.setMyList(filterList);
                                                        adapter.notifyDataSetChanged();
                                                }
                                        });
                                } else {
                                        Log.e("TAG", "meals LiveData is null");
                                }
                        }
                });
        }

        private String convertDateToDayName(Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
                return sdf.format(date);
        }

        @Override
        public void clickMethod(Category p) {

        }

        @Override
        public void clickOnMeal(Meal meal) {

        }

        @Override
        public void clickOnDelete(Meal meal) {

        }

        @Override
        public void clickOnCalendar(DateMeal meal) {

        }

        @Override
        public void clickOnDeleteCalendar(DateMeal meal) {
                presenter.deleteFromPlans(meal);
        }
}