package com.example.topipenttila.windowshopper;

import android.app.Service;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by topipenttila on 04/03/17.
 */


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SpecialsFragment specialsFragment = new SpecialsFragment();
                return specialsFragment;
            case 1:
                ProductsFragment productsFragment = new ProductsFragment();
                return productsFragment;
            case 2:
                ShopsFragment shopsFragment = new ShopsFragment();
                return shopsFragment;
            case 3:
                ServicesFragment servicesFragment = new ServicesFragment();
                return servicesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}