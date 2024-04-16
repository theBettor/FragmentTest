package com.example.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmenttest.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private val binding by lazy { FragmentFirstBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1-2. onCreate로 와서 newInstance에서 만든 argument가 null이 날때 번들의 {데이터 param1}을 가용할 param1에 넣는다?
        // param1이라는 전역변수에 'Hello First Fragment ~~' 이게 들어온 것
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // [1] Activity -> FirstFragment
        binding.tvFrag1Text.text = param1
        // 1-3. 그럼 이제 출력은 여기서 한다. [끝]
        // 당연히 앱을 실행하면 이건 안뜨고 Frag1을 눌러야만 텍스트가 출력되어진다.

        // [2] FirstFragment -> SecondFragment
        // 2-1.replaec로 fragment2로 교체하며, SecondFragment로 가보자.
        binding.btnGofrag2.setOnClickListener{
            val dataToSend = "Hello Fragment2! \n From Fragment1"
            val fragment2 = SecondFragment.newInstance(dataToSend)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment2)
                .addToBackStack(null)
                .commit()
        }
    }


    companion object { // 1-1. newInstance는 메인에서 쓰이는 것.
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            // [1] Activity -> FirstFragment
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
