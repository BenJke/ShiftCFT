package ve.develop.shiftcft.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ve.develop.shiftcft.databinding.FragmentRequestBinding
import ve.develop.shiftcft.model.CardDetail
import ve.develop.shiftcft.model.RequestAttempt
import ve.develop.shiftcft.viewmodels.RequestViewModel
import java.util.*


private const val TAG = "RequestFragment"

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener {
            binding.cardBinEditText.hideKeyboard()
            viewLifecycleOwner.lifecycleScope.launch {
                requestCardDetails()
            }

        }
        binding.historyButton.setOnClickListener {
            findNavController().navigate(RequestFragmentDirections.showHistory())
        }

        binding.detailsButton.setOnClickListener {
            if (viewModel.cardDetail != null) {
                findNavController().navigate(RequestFragmentDirections.showDetail(viewModel.cardDetail as CardDetail))
            } else {
                Toast.makeText(
                    this@RequestFragment.requireContext(),
                    "Wrong BIN",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun EditText.hideKeyboard() {
        clearFocus()
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getWindowToken(), 0)
    }


    private suspend fun saveDetailToHistory(status: String) {
        viewModel.saveToHistory(
            RequestAttempt(0L, Date(), status)
        )
    }

    private suspend fun requestCardDetails() {
        try {
            val requestString = binding.cardBinEditText.text.toString().replace(" ", "")
            if (requestString.length == 8) {
                Toast.makeText(
                    this@RequestFragment.requireContext(),
                    "Making Request",
                    Toast.LENGTH_SHORT
                )
                    .show()
                viewModel.cardDetail = viewModel.fetchCardDetails(requestString)
            } else {
                Toast.makeText(
                    this@RequestFragment.requireContext(),
                    "Please provide correct BIN",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.d(TAG, viewModel.cardDetail.toString())
            if (viewModel.cardDetail != null) {
                binding.detailsButton.isEnabled = true
                Toast.makeText(this@RequestFragment.requireContext(), "Success", Toast.LENGTH_SHORT)
                    .show()
                saveDetailToHistory("Sucess")
            }
        } catch (ex: Exception) {
            binding.detailsButton.isEnabled = false
            Toast.makeText(
                this@RequestFragment.requireContext(),
                "No Card Details",
                Toast.LENGTH_SHORT
            ).show()
            saveDetailToHistory("Failure")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}