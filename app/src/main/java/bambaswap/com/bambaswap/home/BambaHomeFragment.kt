package bambaswap.com.bambaswap.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import bambaswap.com.bambaswap.R
import bambaswap.com.bambaswap.databinding.FragmentBambaHomeBinding
import bambaswap.com.bambaswap.extensions.viewBinding

class BambaHomeFragment: Fragment(R.layout.fragment_bamba_home) {
    private val binding by viewBinding(FragmentBambaHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}