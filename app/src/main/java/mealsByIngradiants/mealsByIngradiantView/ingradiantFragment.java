package mealsByIngradiants.mealsByIngradiantView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.PoorConnectionActivity;
import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import countryFragment.countryPresenter.CountryPresenter;
import countryFragment.countryView.CountryAdapter;
import countryFragment.countryView.ICountryFragment;
import homepage.view.Clickable;
import mealsByIngradiants.mealsByIngradiantsPresenter.IngradiantPresenter;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;

public class ingradiantFragment extends Fragment implements Clickable, IingradiantFragment {
    RecyclerView recyclerView;
    IngradiantPresenter Presenter;
    IngradiantAdapter myAdapter;
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
        return inflater.inflate(R.layout.fragment_ingradiant, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.lastRecycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new IngradiantAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(myAdapter);
        Presenter = new IngradiantPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getContext()), CategoryRemoteDataSource.getInstance()));
        Presenter.getAllIngradiants();
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


    @Override
    public void showAllIngradiants(List<Meal> ingradiants){
        myAdapter.setMyList(ingradiants);
        myAdapter.notifyDataSetChanged();

    }
    @Override
    public void showErrorMsg(String msg) {

        startActivity(new Intent(getContext(), PoorConnectionActivity.class));

    }
}