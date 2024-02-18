package countryFragment.countryView;

import java.util.List;

import model.Country;
import model.Meal;

public interface ICountryFragment {

    public void showErrorMsg(String msg);
    public  void showAllArea(List<Meal> countries);
}
