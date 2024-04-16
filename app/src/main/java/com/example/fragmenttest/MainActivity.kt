package com.example.fragmenttest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragmenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.apply {
//            fragment1Btn.setOnClickListener{
//                setFragment(FirstFragment())
//            }
//            fragment2Btn.setOnClickListener {
//                setFragment(SecondFragment())
//            }
//        }
//        setFragment(FirstFragment())
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
}