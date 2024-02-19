package getAllFavMeals.getAllFavMealsView;

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

import homepage.view.Clickable;
import model.Meal;

public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.ViewHandler> {




    private final Context context;

    Clickable clickable;
    private List<Meal> myList;
    private static final String TAG="RecycleView";


    public FavMealAdapter(Context context, List<Meal> myList, Clickable myClickable) {
        this.context = context;
        this.myList=myList;
        this.clickable=myClickable;
    }

    public void setMyList(List<Meal> list){
        this.myList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(recycleView.getContext());
        View view=inflater.inflate(R.layout.custom_country,recycleView,false);
        ViewHandler viewHandler=new ViewHandler(view);
        Log.i(TAG, ">>>>>>>onCreateViewHolder: ");
        return viewHandler;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(myList.get(position).getStrMealThumb().toString()).into(holder.imageView);
        holder.name.setText(myList.get(position).getStrMeal());
        holder.arrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickable.clickOnDelete(myList.get(position));
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
        ImageView arrowIcon;
        LinearLayout linearLayout;
        View card;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);

            card=itemView;
            linearLayout=itemView.findViewById(R.id.myLinearLayoutCalendarID);
            imageView=itemView.findViewById(R.id.imageCalendarID);
            name=itemView.findViewById(R.id.nameCalendarID);
            arrowIcon=itemView.findViewById(R.id.deleteCalendarIconID);
            arrowIcon.setImageResource(R.drawable.remove_icon);

        }
    }
}
