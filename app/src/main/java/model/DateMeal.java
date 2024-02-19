package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calendar")
public class DateMeal {
    @PrimaryKey
    @NonNull
    String mealId;
    String mealName;
    String imageUrl;
    String day;

    public DateMeal() {
    }

    public DateMeal(String mealId, String mealName, String imageUrl, String day) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.imageUrl = imageUrl;
        this.day = day;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
