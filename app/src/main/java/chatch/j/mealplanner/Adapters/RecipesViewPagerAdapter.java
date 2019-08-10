package chatch.j.mealplanner.Adapters;

import android.os.Bundle;

import androidx.annotation.Nullable;
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

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MEALS";
            case 1:
                return "DESSERTS";
            case 2:
                return "DRINKS";
            case 3:
                return "OTHERS";
            default:
                return null;
        }
    }
}
