package com.example.olympiadsemifinal.presentation.screen.info


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.olympiadsemifinal.R
import com.example.olympiadsemifinal.databinding.FragmentInfoBinding
import com.example.olympiadsemifinal.domain.model.Service


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var service: Service


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        service = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arguments?.getSerializable(
                SERVICE_INFO,
                Service::class.java
            )
                ?: throw IllegalArgumentException("Can't launch without service info")

            else -> @Suppress("DEPRECATION") (arguments?.getSerializable(SERVICE_INFO)
                ?: throw IllegalArgumentException("Can't launch without service info")) as Service
        }


        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        binding.nameTextView.text = service.name
        binding.descriptionTextView.text = service.description
        binding.urlTextView.text =
            Html.fromHtml("<u>${service.service_url}</u>", Html.FROM_HTML_MODE_COMPACT)

        binding.urlTextView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(service.service_url))
            startActivity(browserIntent)
        }

        Glide.with(binding.iconImageView)
            .load(service.icon_url)
            .placeholder(R.drawable.loading_icon)
            .error(R.drawable.question_icon)
            .into(binding.iconImageView)
    }

    fun newInstance(service: Service): InfoFragment {
        val args = Bundle()
        args.putSerializable(SERVICE_INFO, service)
        val fragment = InfoFragment()
        fragment.arguments = args
        return fragment
    }

    companion object {

        private const val SERVICE_INFO = "SERVICE_INFO"

    }
}