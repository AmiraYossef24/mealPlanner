package mealsByIngradiants.mealsByIngradiantView;

import static java.security.AccessController.getContext;

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

import countryFragment.countryView.CountryAdapter;
import homepage.view.Clickable;
import ingradiantsActivity.IngradiantActivity;
import mealsByCountry.mealsByCountryView.MealsByCountry;
import model.Meal;

public class IngradiantAdapter extends  RecyclerView.Adapter<IngradiantAdapter.ViewHandler> {
    private final Context context;

    String imageUrl1="www.themealdb.com/images/";
    String imageUrl2="/Lime-Small.png";

    public static final String INGRADIANT_NAME="INGRADIANT_NAME";
    private final String TAG="CountryAdapter";
    private List<Meal> myList;

    private Clickable clickable;

    public static final String AREA_NAME="AREA_NAME";

    public IngradiantAdapter(Context context, List<Meal> myList, Clickable clickable) {
        this.context = context;
        this.myList = myList;
        this.clickable = clickable;
    }

    public void setMyList(List<Meal> list){
        this.myList=list;
    }

    @NonNull
    @Override
    public IngradiantAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(recycleView.getContext());
        View view=inflater.inflate(R.layout.custom_country,recycleView,false);
        IngradiantAdapter.ViewHandler viewHandler=new IngradiantAdapter.ViewHandler(view);
        Log.i(TAG, ">>>>>>>onCreateViewHolder: ");
        return viewHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull IngradiantAdapter.ViewHandler holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(imageUrl1+myList.get(position).getStrIngredient1()+imageUrl2).into(holder.imageView);
        holder.name.setText(myList.get(position).getStrIngredient());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, IngradiantActivity.class);
                intent.putExtra(INGRADIANT_NAME,myList.get(position).getStrIngredient());
                //context.startActivity(intent);
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
            arrow=itemView.findViewById(R.id.deleteCalendarIconID);
            linearLayout=itemView.findViewById(R.id.myLinearLayoutCalendarID);

        }
    }
}
