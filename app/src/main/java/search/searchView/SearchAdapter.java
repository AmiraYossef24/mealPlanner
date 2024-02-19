package search.searchView;

import static oneMealFragment.oneMealView.oneMealFragment.MEAL_ID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import homepage.view.Clickable;
import itemDetails.itemView.ItemDetails;
import model.Meal;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHanlder> {

    private final Context context;


    private final String TAG="SearchAdapter";
    private List<Meal> myList;

    private Clickable clickable;

    public SearchAdapter(Context context, List<Meal> myList, Clickable clickable) {
        this.context = context;
        this.myList = myList;
        this.clickable = clickable;
    }

    public void setMyList(List<Meal> meals){
        this.myList.clear();
        this.myList=meals;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHanlder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(recycleView.getContext());
        View view=inflater.inflate(R.layout.custom_country,recycleView,false);
        SearchAdapter.ViewHanlder viewHandler=new SearchAdapter.ViewHanlder(view);
        Log.i(TAG, ">>>>>>>onCreateViewHolder: ");
        return viewHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHanlder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(myList.get(position).getStrMealThumb().toString()).into(holder.imageView);
        holder.mealsName.setText(myList.get(position).getStrMeal());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ItemDetails.class);
                intent.putExtra(MEAL_ID,myList.get(position).getIdMeal());
                Log.i("TAG", "meal id: ");
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ViewHanlder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView mealsName;
        ImageView arrow;
        View card;
        LinearLayout linearLayout;


        public ViewHanlder(@NonNull View itemView) {
            super(itemView);
            card=itemView;
            linearLayout=itemView.findViewById(R.id.myLinearLayoutCalendarID);
            imageView=itemView.findViewById(R.id.imageCalendarID);
            mealsName=itemView.findViewById(R.id.nameCalendarID);
            arrow=itemView.findViewById(R.id.deleteCalendarIconID);




        }
    }
}

