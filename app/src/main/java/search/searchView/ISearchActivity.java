package search.searchView;

import java.util.List;

import model.Meal;

public interface ISearchActivity {

    void showResult(List<Meal> meals);

    void showErrorMsg(String msg);
}
