package com.hayaqo.neardeal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.hayaqo.neardeal.fragment.FragDeal
import com.hayaqo.neardeal.fragment.FragStores
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)
        setTitle(R.string.app_name)

        ClastTest.nama = "asfd"

        val navDrawer = drawer_main
        val navView = nav_main
        val toggle = ActionBarDrawerToggle(
            this,
            navDrawer,
            R.string.drawer_open,
            R.string.drawer_close
        )

        toolbar_main.setNavigationOnClickListener {
            navDrawer.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener {

            if (it.itemId == R.id.menu_stores) {
                Toast.makeText(this, "List Stores",
                    Toast.LENGTH_SHORT).show()
                setFragment(FragStores())
            } else if (it.itemId == R.id.menu_deal) {
                Toast.makeText(this, "List Deal",
                    Toast.LENGTH_SHORT).show()
                setFragment(FragDeal())
            }

            it.setChecked(true)
            navDrawer.closeDrawer(GravityCompat.START)

            true
        }

        setFragment(FragStores())
    }

    fun setFragment(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_container, frag)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_map) {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}
