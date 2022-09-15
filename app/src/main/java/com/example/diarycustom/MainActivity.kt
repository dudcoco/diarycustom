package com.example.diarycustom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), OnTabItemSelectedListener {
    lateinit var fragment1: DiarylistFragment
    lateinit var fragment2: DiarywriteFragment
    lateinit var fragment3: TodayFortuneFragment
    lateinit var fragment4: FortunecookieFragment
    lateinit var fragment5: SettingsFragment
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = DiarylistFragment()
        fragment2 = DiarywriteFragment()
        fragment3 = TodayFortuneFragment()
        fragment4 = FortunecookieFragment()
        fragment5 = SettingsFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener() { item ->
            run {
                when (item.itemId) {

                    R.id.tab1 -> {
                        Toast.makeText(applicationContext, "첫번쨰 탭 선택됨", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment1).commit()
                    }
                    R.id.tab2 -> {
                        Toast.makeText(applicationContext, "두번쨰 탭 선택됨", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment2).commit()
                    }
                    R.id.tab3 -> {
                        Toast.makeText(applicationContext, "세번쨰 탭 선택됨", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment3).commit()
                    }
                    R.id.tab4 -> {
                        Toast.makeText(applicationContext, "네번쨰 탭 선택됨", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment4).commit()
                    }
                    R.id.tab5 -> {
                        Toast.makeText(applicationContext, "다섯번쨰 탭 선택됨", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment5).commit()
                    }
                    else -> Toast.makeText(applicationContext, "선택 안됨", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }


    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> bottomNavigation!!.selectedItemId = R.id.tab1
            1 -> {
                fragment2 = DiarywriteFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment2).commit()
            }
            2 -> bottomNavigation!!.selectedItemId = R.id.tab3
            3 -> bottomNavigation!!.selectedItemId = R.id.tab4
            4 -> bottomNavigation!!.selectedItemId = R.id.tab5
        }
    }

    override fun showDiaryWriteFragment(item: Note?) {
        TODO("Not yet implemented")
    }

    private fun changeFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
    // 뒤로가기 버튼 누른 시간
    var backPressedTime: Long = 0

    // 뒤로가기 버튼 누르면 호출되는 함수
    override fun onBackPressed() {
        // 현재시간보다 크면 종료
        if(backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish() //액티비티 종료
        }
        else {
            Toast.makeText(applicationContext, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.",
            Toast.LENGTH_SHORT).show()
        }

        // 현재 시간 담기
        backPressedTime = System.currentTimeMillis()
    }


}

