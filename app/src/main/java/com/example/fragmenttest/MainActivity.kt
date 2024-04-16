package com.example.fragmenttest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragmenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentDataListener { // 3-2. interface 상속 받기

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            fragment1Btn.setOnClickListener {
                // [1] Activity -> FirstFragment
                val dataToSend = "Hello First Fragment! \n From Activity"
                val fragment = FirstFragment.newInstance(dataToSend)
                setFragment(fragment)
            }

            fragment2Btn.setOnClickListener {
                // [1] Activity -> SecondFragment
                val dataToSend = "Hello Second Fragment!\n From Activity"
                val fragment = SecondFragment.newInstance(dataToSend)
                setFragment(fragment)
            }
        }
        setFragment(FirstFragment())
    }

    private fun setFragment(frag : Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }
    // [3] SecondFragment -> Activity
    // 3-3. interface는 함수명만 제공하며, 구현체는 여기 메인액티비티에서 한다.
    override fun onDataReceived(data: String) {
        // 3-7. Fragment에서 받은 데이터를 처리[끝]
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}