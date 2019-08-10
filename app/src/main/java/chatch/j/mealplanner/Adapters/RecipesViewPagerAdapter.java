package chatch.j.mealplanner.Adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import chatch.j.mealplanner.MealsFragment;

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
        MealsFragment mealsFragment = new MealsFragment();
        position++;
        Bundle bundle = new Bundle();
        bundle.putString("Meals", "Fragment: " + position);
        mealsFragment.setArguments(bundle);
        return mealsFragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return numTabs;
    }
}
