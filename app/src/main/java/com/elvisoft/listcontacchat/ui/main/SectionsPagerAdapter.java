package com.elvisoft.listcontacchat.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.elvisoft.listcontacchat.AboutFragment;
import com.elvisoft.listcontacchat.ChatsFragment;
import com.elvisoft.listcontacchat.ContactsFragment;
import com.elvisoft.listcontacchat.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    int numtabs;

    public SectionsPagerAdapter(@NonNull FragmentManager fm, int behaivor) {
        super(fm,behaivor);
        numtabs=behaivor;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ContactsFragment();
            case 1:
                return new ChatsFragment();
            case 2:
                return new AboutFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return numtabs;
    }
}