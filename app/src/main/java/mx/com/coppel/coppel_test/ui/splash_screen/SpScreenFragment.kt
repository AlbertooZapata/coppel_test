package mx.com.coppel.coppel_test.ui.splash_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import mx.com.coppel.coppel_test.R
import mx.com.coppel.coppel_test.databinding.FragmentSpScreenBinding
import kotlin.coroutines.CoroutineContext

class SpScreenFragment : Fragment() {

    private var _binding: FragmentSpScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            loadSpScreen()
        }
    }

    private suspend fun loadSpScreen() {
        delay(2000)
        val navController = findNavController()
        navController.popBackStack()
        navController.navigate(R.id.charactersFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}