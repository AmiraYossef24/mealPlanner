package countryFragment.countryView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import countryFragment.countryPresenter.CountryPresenter;
import countryFragment.countryPresenter.ICountryPresenter;
import homepage.presenter.FPresenter;
import homepage.presenter.IfPresenter;
import homepage.view.Clickable;
import homepage.view.HomeAdapter;
import model.Category;
import model.CategoryRepository;
import model.Country;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;

public class CountryFragment extends Fragment implements Clickable,ICountryFragment {
    RecyclerView recyclerView;
    CountryPresenter Presenter;
    CountryAdapter myAdapter;
    CategoryRepository repo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //myToolBar=requireActivity().findViewById(R.id.home_toolBarID);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.countryRecyclViewID);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new CountryAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(myAdapter);
        Presenter = new CountryPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getContext()), CategoryRemoteDataSource.getInstance()));
        Presenter.getAllArea();
        Log.i("TAG", "get all area from preseter is: " + Presenter.toString());
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

    public  void showAllArea(List<Meal> countries){
        myAdapter.setMyList(countries);
        myAdapter.notifyDataSetChanged();

    }

    public void showErrorMsg(String msg) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(msg).setTitle("Error Message!");
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}