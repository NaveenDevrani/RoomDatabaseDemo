package com.devcoder.roomdatabasedemo.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun next() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_contactListFragment)
        }, 2000)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slideAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
        binding.logo.startAnimation(slideAnimation)
        next()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}