package calendar.calendarView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import getAllFavMeals.getAllFavMealsView.FavMealAdapter;
import homepage.view.Clickable;
import model.DateMeal;
import model.Meal;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHandler> {




    private final Context context;

    Clickable clickable;
    private List<DateMeal> myList;
    private static final String TAG="RecycleView";


    public CalendarAdapter(Context context, List<DateMeal> myList, Clickable myClickable) {
        this.context = context;
        this.myList=myList;
        this.clickable=myClickable;
    }

    public void setMyList(List<DateMeal> list){
        this.myList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(recycleView.getContext());
        View view=inflater.inflate(R.layout.custum_calendar_card,recycleView,false);
        CalendarAdapter.ViewHandler viewHandler=new CalendarAdapter.ViewHandler(view);
        Log.i(TAG, ">>>>>>>onCreateViewHolder: ");
        return viewHandler;

    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHandler holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(myList.get(position).getImageUrl().toString()).into(holder.imageView);
        holder.name.setText(myList.get(position).getMealName());
        holder.day.setText(myList.get(position).getDay());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickable.clickOnDeleteCalendar(myList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ViewHandler extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView day;
        ImageView delete;
        LinearLayout linearLayout;
        View card;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);

            card=itemView;
            linearLayout=itemView.findViewById(R.id.myLinearLayoutCalendarID);
            imageView=itemView.findViewById(R.id.imageCalendarID);
            name=itemView.findViewById(R.id.nameCalendarID);
            day=itemView.findViewById(R.id.dayNameID);
            delete=itemView.findViewById(R.id.deleteCalendarIconID);

        }
    }
}

