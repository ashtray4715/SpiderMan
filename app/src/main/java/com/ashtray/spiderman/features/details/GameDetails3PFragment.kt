package com.ashtray.spiderman.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import com.ashtray.spiderman.databinding.FragmentGameDetails3pBinding

class GameDetails3PFragment : GPFragment() {

    private val viewModel by viewModels<GameDetails3PViewModel>()

    private var _binding: FragmentGameDetails3pBinding? = null
    private val binding get() = _binding!!

    override val log = GPLog("GameDetails3PFragment")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log.d("onCreateView: called")
        _binding = FragmentGameDetails3pBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}