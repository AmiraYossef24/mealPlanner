package homepage.view;

import java.util.List;

import model.Category;

public interface IhomeFragment {
    void showProducts(List<Category> products);

    void showErrorMsg(String msg);
}
