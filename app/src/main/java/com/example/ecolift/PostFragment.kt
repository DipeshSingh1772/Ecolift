package com.example.ecolift

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ecolift.Database.PostPerson
import com.example.ecolift.databinding.FragmentPostBinding
import com.example.ecolift.viewModels.EcoliftApplication
import com.example.ecolift.viewModels.EcoliftViewModel
import com.example.ecolift.viewModels.EcoliftViewModelFactory


class PostFragment : Fragment() {

    private val postViewModel:EcoliftViewModel by activityViewModels{
        EcoliftViewModelFactory(
            (activity?.application as EcoliftApplication).database.postDao()
        )
    }

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postBtn.setOnClickListener {

            if(isEntryValid()){
                val person:PostPerson = createUser()        //get a person instance from createUser
                postViewModel.insertData_VM(person)         //request to viewmodel insert function with person data
                Toast.makeText(this.activity, "Data Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_postFragment_to_mainFragment)

            }
            else{
                Toast.makeText(this.activity,"Fill All Details",Toast.LENGTH_SHORT).show()
            }
        }

    }

    // after checking valid entry create a postperson instance and pass it to onviewcreated
    fun createUser():PostPerson{
        return PostPerson(
            Pickup = binding.pickupText.text.toString(),
            Destination = binding.destinationText.text.toString(),
            Date = binding.timeText.text.toString().toLong(),
            Time = binding.timeText.text.toString().toLong(),
            Name = binding.nameText.text.toString(),
            MobileNo = binding.mobilenoText.text.toString(),
            Email = binding.emailText.text.toString(),
            Seats = binding.seatText.text.toString().toInt(),
            Amount = binding.amountText.text.toString().toInt()
        )
    }

    // checking that is any form content is blank or not. if blank return false or true
    fun isEntryValid():Boolean{

        return !(binding.pickupText.text.isBlank()
                || binding.destinationText.text.isBlank()
                || binding.dateText.text.isBlank()
                || binding.timeText.text.isBlank()
                || binding.nameText.text.isBlank()
                || binding.mobilenoText.text.isBlank()
                || binding.emailText.text.isBlank()
                || binding.seatText.text.isBlank()
                || binding.amountText.text.isBlank())
    }

}

