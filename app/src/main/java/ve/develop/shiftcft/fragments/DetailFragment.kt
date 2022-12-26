package ve.develop.shiftcft.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.*
import ve.develop.shiftcft.R
import ve.develop.shiftcft.databinding.FragmentDetailBinding
import ve.develop.shiftcft.viewmodels.DetailViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.cardDetail = args.cardDetail
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCardDetails()
        domain_result.setOnClickListener {
            showUrlIntent()
        }
        number_result.setOnClickListener {
            callNumberIntent()
        }
        latitude_result.setOnClickListener {
            showMapIntent()
        }
        longitude_result.setOnClickListener {
            showMapIntent()
        }
    }

    private fun showMapIntent() {
        val gmmIntentUri = Uri.parse("geo:${viewModel.cardDetail.country.latitude},${viewModel.cardDetail.country.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun callNumberIntent() {
        val number = Uri.parse("tel:${number_result.text}")
        val sendIntent = Intent(Intent.ACTION_DIAL, number)
        val title: String = resources.getString(R.string.chooser_title)
        val chooser: Intent = Intent.createChooser(sendIntent, title)
        startActivity(chooser)
    }

    private fun showUrlIntent() {
        val sendIntent = Intent(Intent.ACTION_VIEW).apply {
            setData(Uri.parse("https://${domain_result.text}"))
        }
        val title: String = resources.getString(R.string.chooser_title)
        val chooser: Intent = Intent.createChooser(sendIntent, title)
        startActivity(chooser)
    }

    private fun setupCardDetails() {
        binding.apply {
            brandResult.text = viewModel.cardDetail.brand
            schemeResult.text = viewModel.cardDetail.scheme
            cardLengthResult.text = viewModel.cardDetail.number.length.toString()
            luhnResult.text = viewModel.cardDetail.number.luhn.toString()
            typeResult.text = viewModel.cardDetail.type
            prepaidResult.text = viewModel.cardDetail.prepaid.toString()
            nameResult.text = viewModel.cardDetail.bank.name
            domainResult.text = viewModel.cardDetail.bank.url
            numberResult.text = viewModel.cardDetail.bank.phone
            alphaTextView.text = viewModel.cardDetail.country.alpha2
            countryResult.text = viewModel.cardDetail.country.name
            latitudeResult.text = "Latitude : ${viewModel.cardDetail.country.latitude}"
            longitudeResult.text = "Longitude : ${viewModel.cardDetail.country.longitude}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}