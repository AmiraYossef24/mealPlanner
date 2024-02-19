package countryFragment.countryView;

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

import java.util.List;

import homepage.view.Clickable;
import mealsByCountry.mealsByCountryView.MealsByCountry;
import model.Meal;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHandler> {
    private final Context context;
    private final String TAG="CountryAdapter";
    private List<Meal> myList;

    private Clickable clickable;

    public static final String AREA_NAME="AREA_NAME";

    public CountryAdapter(Context context, List<Meal> myList, Clickable clickable) {
        this.context = context;
        this.myList = myList;
        this.clickable = clickable;
    }

    public void setMyList(List<Meal> list){
        this.myList=list;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(recycleView.getContext());
        View view=inflater.inflate(R.layout.custom_country,recycleView,false);
        ViewHandler viewHandler=new ViewHandler(view);
        Log.i(TAG, ">>>>>>>onCreateViewHolder: ");
        return viewHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {

        Log.i(TAG, "first country is : "+myList.get(position).getStrArea());
        holder.name.setText(myList.get(position).getStrArea());
        String areaName = myList.get(position).getStrArea();
        if (areaName.equals("American")) {
            holder.imageView.setImageResource(R.drawable.american);
        } else if (areaName.equals("British")) {
            holder.imageView.setImageResource(R.drawable.british);
        } else if (areaName.equals("Canadian")) {
            holder.imageView.setImageResource(R.drawable.canada);
        } else if (areaName.equals("Chinese")) {
            holder.imageView.setImageResource(R.drawable.china);
        } else if (areaName.equals("Croatian")) {
            holder.imageView.setImageResource(R.drawable.croatian);
        } else if (areaName.equals("Dutch")) {
            holder.imageView.setImageResource(R.drawable.dutch);
        } else if (areaName.equals("Egyptian")) {
            holder.imageView.setImageResource(R.drawable.egypt);
        } else if (areaName.equals("Filipino")) {
            holder.imageView.setImageResource(R.drawable.filipino);
        } else if (areaName.equals("French")) {
            holder.imageView.setImageResource(R.drawable.french);
        } else if (areaName.equals("Greek")) {
            holder.imageView.setImageResource(R.drawable.greece);
        } else if (areaName.equals("Indian")) {
            holder.imageView.setImageResource(R.drawable.india);
        } else if (areaName.equals("Irish")) {
            holder.imageView.setImageResource(R.drawable.irish);
        } else if (areaName.equals("Italian")) {
            holder.imageView.setImageResource(R.drawable.italy);
        } else if (areaName.equals("Jamaican")) {
            holder.imageView.setImageResource(R.drawable.jamaican);
        } else if (areaName.equals("Japanese")) {
            holder.imageView.setImageResource(R.drawable.jamaican);
        } else if (areaName.equals("Kenyan")) {
            holder.imageView.setImageResource(R.drawable.kenyan);
        } else if (areaName.equals("Malaysian")) {
            holder.imageView.setImageResource(R.drawable.malaysian);
        } else if (areaName.equals("Mexican")) {
            holder.imageView.setImageResource(R.drawable.mexican);
        } else if (areaName.equals("Moroccan")) {
            holder.imageView.setImageResource(R.drawable.morocco);
        } else if (areaName.equals("Polish")) {
            holder.imageView.setImageResource(R.drawable.poland);
        } else if (areaName.equals("Portuguese")) {
            holder.imageView.setImageResource(R.drawable.portuguese);
        } else if (areaName.equals("Russian")) {
            holder.imageView.setImageResource(R.drawable.russia);
        } else if (areaName.equals("Thai")) {
            holder.imageView.setImageResource(R.drawable.thailand);
        } else if (areaName.equals("Tunisian")) {
            holder.imageView.setImageResource(R.drawable.tunisia);
        } else if (areaName.equals("Turkish")) {
            holder.imageView.setImageResource(R.drawable.turkey);
        } else if (areaName.equals("Vietnamese")) {
            holder.imageView.setImageResource(R.drawable.vietnam);
        } else if (areaName.equals("Unknown")){
            holder.imageView.setImageResource(R.drawable.help);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MealsByCountry.class);
                intent.putExtra(AREA_NAME,myList.get(position).getStrArea());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "nubmer of list is : "+myList.size());
        return myList.size();
    }


    class ViewHandler extends RecyclerView.ViewHolder{


        TextView name;
        ImageView imageView;
        View layout;
        LinearLayout linearLayout;
        ImageView arrow;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            name=itemView.findViewById(R.id.nameCalendarID);
            imageView=itemView.findViewById(R.id.imageCalendarID);
            arrow=itemView.findViewById(R.id.arrowIconID);
            linearLayout=itemView.findViewById(R.id.myLinearLayoutCalendarID);

        }
    }
}
