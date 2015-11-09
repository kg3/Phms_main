package mobi.idappthat.test_noteapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobi.idappthat.test_noteapp.Fragments.NotesFragment;

/**
 * Created by Cameron on 11/6/15.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    private FragmentHolder fragments[] = {
            new FragmentHolder("Notes", new NotesFragment()),
            new FragmentHolder("Todo", new NotesFragment())
    };

    @Override
    public Fragment getItem(int position) {
        return fragments[position].getFragment();
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getTitle();
    }
}

class FragmentHolder {

    private String title;
    private Fragment fragment;

    public FragmentHolder(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
