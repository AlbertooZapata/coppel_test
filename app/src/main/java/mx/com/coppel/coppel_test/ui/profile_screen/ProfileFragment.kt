package mx.com.coppel.coppel_test.ui.profile_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import mx.com.coppel.coppel_test.R
import mx.com.coppel.coppel_test.data.model.Result
import mx.com.coppel.coppel_test.databinding.FragmentProfileBinding
import mx.com.coppel.coppel_test.ui.profile_screen.adapter.ItemsAdapter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var result: Result
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireArguments().let {
            result = it.getParcelable("result")!!
        }
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadImages()
        loadInformation()
        loadComics()
        loadSeries()
        loadStories()
    }

    private fun loadImages() {
        val fullUrl = "${result.thumbnail.path}.${result.thumbnail.extension}"
        binding.imgCharacterBlank.load(R.drawable.white_background) {
            transformations(CircleCropTransformation())
        }
        binding.imgCharacter.load(fullUrl) {
            transformations(CircleCropTransformation())
        }
        binding.imgBannerP.load(fullUrl) {
            transformations(BlurTransformation(requireContext(), 25F))
        }
    }

    private fun loadInformation() {
        binding.txtName.text = result.name
        binding.txtTotalComics.text = result.comics.items.size.toString()
        binding.txtTotalSeries.text = result.series.items.size.toString()
        binding.txtTotalStories.text = result.stories.items.size.toString()
        binding.ttxtDescription.text = result.description.ifEmpty { "(Sin descripción)" }
    }

    private fun loadComics() {

        if (result.comics.items.isNotEmpty()) {
            binding.txtNoComics.visibility = View.GONE
            binding.rvComics.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvComics.adapter = ItemsAdapter(requireContext(), result.comics.items)
        } else {
            binding.rvComics.visibility = View.GONE
            binding.txtNoComics.visibility = View.VISIBLE
        }
    }

    private fun loadSeries() {
        if (result.series.items.isNotEmpty()) {
            binding.txtNoSeries.visibility = View.GONE
            binding.rvSeries.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvSeries.adapter = ItemsAdapter(requireContext(), result.series.items)
        } else {
            binding.rvSeries.visibility = View.GONE
            binding.txtNoSeries.visibility = View.VISIBLE
        }
    }

    private fun loadStories() {

        if (result.stories.items.isNotEmpty()) {
            binding.txtNoStories.visibility = View.GONE
            binding.rvStories.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvStories.adapter = ItemsAdapter(requireContext(), result.stories.items)
        } else {
            binding.rvStories.visibility = View.GONE
            binding.txtNoStories.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}