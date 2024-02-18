package homepage.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import homepage.presenter.FPresenter;
import homepage.presenter.IfPresenter;
import model.Category;
import model.CategoryRepository;
import model.Meal;
import network.CategoryRemoteDataSource;

public class homeFragment extends Fragment implements Clickable, IhomeFragment {
    Toolbar myToolBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    RecyclerView recyclerView;
    IfPresenter Presenter;
    HomeAdapter myAdapter;
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
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.myRecyclViewID);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter=new HomeAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(myAdapter);
        Presenter= new FPresenter(this,CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getContext()), CategoryRemoteDataSource.getInstance()));

        Presenter.getAllProduct();
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
    public void clickOnCalendar(Meal meal) {

    }


    @Override
    public void showProducts(List<Category> products) {
        myAdapter.setMyList(products);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(msg).setTitle("Error Message!");
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}