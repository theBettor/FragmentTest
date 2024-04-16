package com.example.fragmenttest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmenttest.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    // [3] SecondFragment -> Activity
    // 3-4. Settings
    private var listener: FragmentDataListener? = null // interface를 가용한 변수 선언
    private var _binding: FragmentSecondBinding? = null //
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)

        // [3] SecondFragment -> Activity
        // 3-5. 메인 액티비와 연결하기.
        if (context is FragmentDataListener) {
            listener = context // 연결해버리기
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        // 메인액티비티와 연결(onAttach) 되어있어야 데이터를 보낼 수 있다.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
//        return inflater.inflate(R.layout.fragment_second, container, false)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
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
            // [2] Activity -> FirstFragment
            // 두 방법 모두 아래 코드로 구현 가능하다.
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // [1] Activity -> FirstFragment
        // [2] FirstFragment -> SecondFragment
        // 2-2. 두 방법 모두 아래 코드로 구현 가능하다. [끝]
        binding.tvFrag2Text.text = param1

        // [3] SecondFragment -> Activity
        // 3-6. 데이터 보내, MainActivity에 가보자.
        binding.btnSendActivity.setOnClickListener{
            val dataToSend = "Hello from SecondFragment!"
            listener?.onDataReceived(dataToSend)
        }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Binding 객체 해제
        _binding = null
        listener = null
    }
}