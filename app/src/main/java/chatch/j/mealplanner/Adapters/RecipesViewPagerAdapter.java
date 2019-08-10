package chatch.j.mealplanner.Adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import chatch.j.mealplanner.DessertsFragment;
import chatch.j.mealplanner.DrinksFragment;
import chatch.j.mealplanner.MealsFragment;
import chatch.j.mealplanner.OthersFragment;

/**
 * This is the adapter for the ViewPager used in the recipes fragment.
 * State pager adapter was chosen because of the possibility of having
 * many, many recipes in the future.
 */
public class RecipesViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numTabs;

    public RecipesViewPagerAdapter(FragmentManager fm) {
        super(fm);
        numTabs = 4;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MealsFragment mealsFragment = new MealsFragment();
                return mealsFragment;
            case 1:
                DessertsFragment dessertsFragment = new DessertsFragment();
                return dessertsFragment;
            case 2:
                DrinksFragment drinksFragment = new DrinksFragment();
                return drinksFragment;
            case 3:
                OthersFragment othersFragment = new OthersFragment();
                return othersFragment;
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return numTabs;
    }
}
