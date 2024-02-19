package mealsByCountry.mealsByCountryView;

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

public class MealsByCountryAdapter extends RecyclerView.Adapter<MealsByCountryAdapter.ViewHanlder> {

    private final Context context;


    private final String TAG="MealsByCategoryAdapter";
    private List<Meal> myList;

    private Clickable clickable;

    public MealsByCountryAdapter(Context context, List<Meal> myList, Clickable clickable) {
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
        ViewHanlder viewHandler=new ViewHanlder(view);
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
                    isFav = true; // Update state
                } else if(isFav){
                    holder.favIcon.setImageResource(R.drawable.heart); // Set empty heart icon
                    clickable.clickOnDelete(myList.get(position));
                    isFav = false; // Update state
                }
                // Call method to handle click action

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
        TextView cate;
        ImageView favIcon;
        ImageView deleteIcon;
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
            deleteIcon=itemView.findViewById(R.id.deletIconID);




        }
    }
}
