package com.example.fragmenttest

interface FragmentDataListener { // 3-1. interface 생성
    fun onDataReceived(data: String)
}