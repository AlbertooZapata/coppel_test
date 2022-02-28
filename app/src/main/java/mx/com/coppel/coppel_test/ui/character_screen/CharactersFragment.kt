package mx.com.coppel.coppel_test.ui.character_screen

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mx.com.coppel.coppel_test.R
import mx.com.coppel.coppel_test.data.model.CharactersResponse
import mx.com.coppel.coppel_test.data.model.Result
import mx.com.coppel.coppel_test.databinding.FragmentCharactersBinding
import mx.com.coppel.coppel_test.ui.character_screen.adapter.CharactersAdapter
import mx.com.coppel.coppel_test.ui.character_screen.viewmodel.CharactersViewModel
import mx.com.coppel.coppel_test.util.AlertDialogUtil
import mx.com.coppel.coppel_test.util.ResourceState
import mx.com.coppel.coppel_test.util.StringUtil


@AndroidEntryPoint
class CharactersFragment : Fragment(), CharactersAdapter.OnCharacterClickListener {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val charactersViewModel by viewModels<CharactersViewModel>()
    private val allResults: MutableList<Result> = mutableListOf()
    private var limitOfItems = 0
    private lateinit var loadingAlert: AlertDialog
    private lateinit var errorAlert: AlertDialog
    private lateinit var infoAlert: AlertDialog
    private lateinit var firstAlert: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenState()
        loadingAlert =
            AlertDialogUtil.loadAlertDialog(requireContext(), getString(R.string.loading_text_str))
        errorAlert =
            AlertDialogUtil.errorAlertDialog(requireContext(), getString(R.string.error_text_str))
        infoAlert =
            AlertDialogUtil.infoAlertDialog(requireContext(), getString(R.string.info_text_str))
        firstAlert =
            AlertDialogUtil.infoAlertDialog(requireContext(), getString(R.string.info_tools_str))
        charactersViewModel.getCharacters()
    }

    private fun setScreenState() {
        charactersViewModel.verifyScreenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResourceState.Default -> {
                    Log.d("flow", "Default")
                }
                is ResourceState.Loading -> {
                    loadingAlert.show()
                }
                is ResourceState.Success -> {
                    loadingAlert.dismiss()
                    showToolsModal()
                    val characters = state.data
                    loadInformation(characters)
                    Log.d("flow", "Success, total: ${allResults.size}")
                }
                is ResourceState.Failure -> {
                    Log.d("flow", "Error loading list: ${state.exception.message}")
                    loadingAlert.dismiss()
                    errorAlert.show()
                }
            }
        }
    }

    private fun showToolsModal() {
        if (charactersViewModel.showFirstAlert.value!!) {
            firstAlert.show()
        }
        charactersViewModel.updateShowFirstAlert()
    }

    private fun loadInformation(characters: CharactersResponse) {
        limitOfItems = characters.data.total
        if (allResults.isEmpty()) {
            allResults.addAll(characters.data.results)
            fillRecyclerView()
        } else {
            allResults.addAll(characters.data.results)
            if (binding.rvCharacters.adapter == null) {
                fillRecyclerView()
            } else {
                binding.rvCharacters.adapter!!.notifyItemRangeInserted(
                    charactersViewModel.offset.value!!,
                    charactersViewModel.limit.value!! - 1
                )
            }
        }
    }

    private fun fillRecyclerView() {
        binding.rvCharacters.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvCharacters.adapter = CharactersAdapter(requireContext(), allResults, this)
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {

                    if (charactersViewModel.offset.value!!.plus(StringUtil.RESPONSE_TOTAL_ITEMS) <= limitOfItems) {
                        charactersViewModel.addMoreItems()
                    } else {
                        if (!charactersViewModel.reachTheLimit.value!!) {
                            infoAlert.show()
                            charactersViewModel.updateReachTheLimit()
                        }
                    }
                }
            }
        })
        binding.rvCharacters.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onItemClick(result: Result) {

        val bundle = Bundle()
        bundle.putParcelable("result", result)
        findNavController().navigate(
            R.id.action_charactersFragment_to_profileFragment,
            bundle
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}