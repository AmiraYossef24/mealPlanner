package oneMealFragment.oneMealView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import DB.CategoryLocalDataSource;
import homepage.view.Clickable;
import itemDetails.itemView.ItemDetails;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;
import oneMealFragment.oneMealPresenter.OneMealPreseter;

public class oneMealFragment extends Fragment implements Clickable, IoneMealFragment {
    OneMealPreseter Presenter;
    CategoryRepository repo;

    CardView cardView;
    ImageView imageView;
    TextView name;
    TextView country;
    ImageView arrow;
    LinearLayout linearLayout;
    public static final String MEAL_ID ="MEAL_ID" ;
    public String mealId;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_one_meal, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        cardView=view.findViewById(R.id.oneMealCardId);

        imageView=view.findViewById(R.id.imageCalendarID);
        name=view.findViewById(R.id.nameCalendarID);
        country=view.findViewById(R.id.countryTxID);
        arrow=view.findViewById(R.id.arrowIconID);
        linearLayout=view.findViewById(R.id.lastLinearID);
        Presenter = new OneMealPreseter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getContext()), CategoryRemoteDataSource.getInstance()));
        Presenter.getRandomMeal();


        linearLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ItemDetails.class);
                intent.putExtra(MEAL_ID,mealId);
                Log.i("TAG", "meal id: "+mealId);
                startActivity(intent);
            }
        });
        Animation anim= AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);
        cardView.startAnimation(anim);
    }


    @Override
    public void showRandomMeal(List<Meal> meal) {


        Log.i("TAG", "the result list: "+meal.get(0).getStrMeal());
        if (!meal.isEmpty()) {
            Log.i("TAG", "inside if : "+meal.get(0).getStrMeal()+"and the id is "+meal.get(0).getIdMeal());
            this.mealId=meal.get(0).getIdMeal();
            Picasso.get().load(meal.get(0).getStrMealThumb().toString()).into(imageView);
            name.setText(meal.get(0).getStrMeal());
            country.setText(meal.get(0).getStrArea());
        } else {
            Log.i("TAG", "the list is empty: ");
            Toast.makeText(getContext(), "the list i empty", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void showErrorMsg(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg).setTitle("Error Message!");
        AlertDialog dialog = builder.create();
        dialog.show();
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

    }


}
