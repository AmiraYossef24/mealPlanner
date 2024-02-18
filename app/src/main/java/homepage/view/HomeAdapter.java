package homepage.view;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import mealsByCategory.mealsByCategoryView.MealsByCategory;
import model.Category;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHandler>{

    private final Context context;
    private final String TAG="HomeAdapter";
    private List<Category> myList;

    private Clickable clickable;
    public static final String CATEGORY_NAME="CATEGORY_NAME";

    public HomeAdapter(Context context, List<Category> myList, Clickable clickable) {
        this.context = context;
        this.myList = myList;
        this.clickable = clickable;
    }

    public void setMyList(List<Category> list){
        this.myList=list;
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

        Picasso.get().load(myList.get(position).getStrCategoryThumb().toString()).into(holder.imageView);
        holder.name.setText(myList.get(position).getStrCategory());
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context, MealsByCategory.class);

               intent.putExtra(CATEGORY_NAME,myList.get(position).getStrCategory());
               Log.i(TAG, "the category name from home adapter is : "+myList.get(position).getStrCategory());
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ViewHandler extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;

        public View card;
        public LinearLayout linearLayout;


    public ViewHandler(@NonNull View itemView) {
        super(itemView);
        card=itemView;
        linearLayout=itemView.findViewById(R.id.myLinearLayoutID);
        imageView=itemView.findViewById(R.id.areaImageID);
        name=itemView.findViewById(R.id.areaNameID);






    }
}
}



