package com.example.ecolift

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
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
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat


class PostFragment : Fragment() {

    private val postViewModel:EcoliftViewModel by activityViewModels{
        EcoliftViewModelFactory(
            (activity?.application as EcoliftApplication).database.postDao()
        )
    }

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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


        //Date picker code
        binding.dateIcon.setOnClickListener {
            openDatePicker()
        }
        binding.dateLayout.setOnClickListener {
            openDatePicker()
        }
        binding.dateText.setOnClickListener {
            openDatePicker()
        }

        //Time picker
        binding.timeIcon.setOnClickListener {
            openTimePicker()
        }
        binding.timeLayout.setOnClickListener{
            openTimePicker()
        }
        binding.timeText.setOnClickListener {
            openTimePicker()
        }

    }

    // after checking valid entry create a postperson instance and pass it to onviewcreated
    private fun createUser():PostPerson{
        return PostPerson(
            Pickup = binding.pickupText.text.toString(),
            Destination = binding.destinationText.text.toString(),
            Date = date,
            Time = binding.timeText.text.toString(),
            Name = binding.nameText.text.toString(),
            MobileNo = binding.mobileNoText.text.toString(),
            Email = binding.emailText.text.toString(),
            Seats = binding.seatText.text.toString().toInt(),
            Amount = binding.amountText.text.toString().toInt()
        )
    }

    // checking that is any form content is blank or not. if blank return false or true
    private fun isEntryValid():Boolean{

        return !(binding.pickupText.text.isBlank()
                || binding.destinationText.text.isBlank()
                || binding.dateText.text.isBlank()
                || binding.timeText.text.isBlank()
                || binding.nameText.text.isBlank()
                || binding.mobileNoText.text.isBlank()
                || binding.emailText.text.isBlank()
                || binding.seatText.text.isBlank()
                || binding.amountText.text.isBlank())
    }



    //Date picker
    //var to save date in database
    private var date:Long = 0

    @SuppressLint("SimpleDateFormat")
    fun openDatePicker(){
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        datePicker.show(childFragmentManager,"Tag")

        datePicker.addOnPositiveButtonClickListener {
            binding.dateText.text = SimpleDateFormat("EEEE, dd MMMM").format(datePicker.selection)
            date = datePicker.selection!!
        }

        datePicker.addOnNegativeButtonClickListener{
            binding.dateText.text = "Date"
        }

    }


    // Material Time Picker
    private fun openTimePicker() {
        val isSystem24hour = DateFormat.is24HourFormat(requireContext())
        val clockFormat = if (isSystem24hour) TimeFormat.CLOCK_12H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(10)
            .setTitleText("Set Time")
            .build()
        picker.show(childFragmentManager, "Tag")

        picker.addOnPositiveButtonClickListener {
            val ampm: String

            if (picker.hour > 12) {
                ampm = "PM"
//                hour = picker.hour - 12
//                Min = picker.minute
                binding.timeText.text = "${picker.hour - 12} : ${picker.minute}${ampm}"
            } else if (picker.hour == 12) {
                ampm = "PM"
//                hour = picker.hour
//                Min = picker.minute
                binding.timeText.text = "${picker.hour} : ${picker.minute}${ampm}"
            } else {
                ampm = "AM"
//                hour = picker.hour
//                Min = picker.minute
                binding.timeText.text = "${picker.hour} : ${picker.minute}${ampm}"
            }
        }
    }

}

