package mealsByCategory.mealsByCategoryView;

import static oneMealFragment.oneMealView.oneMealFragment.MEAL_ID;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import homepage.view.Clickable;
import itemDetails.itemView.ItemDetails;
import model.DateMeal;
import model.Meal;

public class MealsByCategoryAdapter extends RecyclerView.Adapter<MealsByCategoryAdapter.ViewHanlder> {

    private final Context context;


    private final String TAG="MealsByCategoryAdapter";
    private List<Meal> myList;
    String dayName;

    private Clickable clickable;

    public MealsByCategoryAdapter(Context context, List<Meal> myList, Clickable clickable) {
        this.context = context;
        this.myList = myList;
        this.clickable = clickable;
    }

    public void setMyList(List<Meal> meals){
        this.myList=meals;
    }

    @NonNull
    @Override
    public ViewHanlder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(recycleView.getContext());
        View view=inflater.inflate(R.layout.custom_card_item,recycleView,false);
        MealsByCategoryAdapter.ViewHanlder viewHandler=new MealsByCategoryAdapter.ViewHanlder(view);
        Log.i(TAG, ">>>>>>>onCreateViewHolder: ");
        return viewHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHanlder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(myList.get(position).getStrMealThumb().toString()).into(holder.imageView);
        holder.mealsName.setText(myList.get(position).getStrMeal());
        holder.cate.setText("code : "+myList.get(position).getIdMeal());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ItemDetails.class);
                intent.putExtra(MEAL_ID,myList.get(position).getIdMeal());
                Log.i("TAG", "meal id: ");
                context.startActivity(intent);
            }
        });

        holder.favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isFav = false;
                if (!isFav) {
                    holder.favIcon.setImageResource(R.drawable.fill_heart); // Set filled heart icon
                    clickable.clickOnMeal(myList.get(position));
                    Log.i(TAG, "added to fav from meals by category adapter: "+myList.get(position));
                    isFav = true; // Update state
                } else if(isFav){
                    holder.favIcon.setImageResource(R.drawable.heart); // Set empty heart icon
                    clickable.clickOnDelete(myList.get(position));
                    isFav = false; // Update state
                }
                // Call method to handle click action

            }

        });

        holder.calendrIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCalendar = false;
                if (!isCalendar) {
                    Calendar calendar=Calendar.getInstance();
                    int year=calendar.get(Calendar.YEAR);
                    int month=calendar.get(Calendar.MONTH);
                    int day=calendar.get(Calendar.DAY_OF_MONTH);

                    long currentTime = System.currentTimeMillis();
                    long maxTime = currentTime + (7 * 24 * 60 * 60 * 1000);
                    DatePickerDialog datePickerDialog=new DatePickerDialog(
                            context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            holder.calendrIcon.setImageResource(R.drawable.fill_calendar_days_icon);
                            calendar.set(year, month, dayOfMonth);
                            Date selectedDate = calendar.getTime();
                            dayName = convertDateToDayName(selectedDate);
                            clickable.clickOnCalendar(new DateMeal(myList.get(position).getIdMeal(),myList.get(position).getStrMeal(),
                                    myList.get(position).getStrMealThumb(),dayName));

                            Log.i("TAG", "item added to calendar from itemdetails: "+myList.get(position).getStrMeal()+dayName);
                            Toast.makeText(context, "Item added to your plan "+dayName, Toast.LENGTH_SHORT).show();
                        }
                    },year,month,day
                    );
                    datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Log.i("TAG", "Date picker dialog canceled");
                            holder.calendrIcon.setImageResource(R.drawable.calender_agenda_icon);



                        }
                    });

                    datePickerDialog.getDatePicker().setMinDate(currentTime);
                    datePickerDialog.getDatePicker().setMaxDate(maxTime);
                    datePickerDialog.show();
                    isCalendar = true;
                } else if(isCalendar){
                    holder.favIcon.setImageResource(R.drawable.heart);
                    clickable.clickOnDelete(myList.get(position));
                    isCalendar = false; // Update state
                }
            }
        });


    }

    private String convertDateToDayName(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        return sdf.format(date);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ViewHanlder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView mealsName;
        TextView cate;
        ImageView favIcon;
        ImageView calendrIcon;
        View card;
        LinearLayout linearLayout;


        public ViewHanlder(@NonNull View itemView) {
            super(itemView);
            card=itemView;
            linearLayout=itemView.findViewById(R.id.myLinearLayoutCalendarID);
            imageView=itemView.findViewById(R.id.mealImageID);
            mealsName=itemView.findViewById(R.id.mealNameID);
            cate=itemView.findViewById(R.id.categoryTxID);
            favIcon=itemView.findViewById(R.id.heartIconID);
            calendrIcon=itemView.findViewById(R.id.deletIconID);




        }
    }
}
