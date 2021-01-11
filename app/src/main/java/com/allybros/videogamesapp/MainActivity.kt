package com.allybros.videogamesapp

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.allybros.videogamesapp.feature.games.GamesFragment
import com.allybros.videogamesapp.feature.favourites.PropertiesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.search_bar) as SearchView
        val navView = findViewById(R.id.nav_view) as BottomNavigationView

        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> {
                    toolbar.visibility = VISIBLE
                    title=resources.getString(R.string.games)
                    changeFragment(GamesFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.favorites-> {
                    title=resources.getString(R.string.favourites)
                    toolbar.visibility = GONE
                    changeFragment(PropertiesFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        if (savedInstanceState == null) {
            changeFragment(GamesFragment())
        }
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction();
        if (cleanStack) {
            clearBackStack();
        }
        ft.setCustomAnimations(
            R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        ft.replace(R.id.activity_base_content, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    fun clearBackStack() {
        val manager = supportFragmentManager;
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager;
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}