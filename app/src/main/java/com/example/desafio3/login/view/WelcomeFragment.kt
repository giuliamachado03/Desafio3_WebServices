package com.example.desafio3.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.desafio3.R
import com.google.android.material.textfield.TextInputLayout


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.textButtonCreateAccount).setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }

        view.findViewById<Button>(R.id.textButtonCreateAccount).setOnClickListener {

            val email = view.findViewById<TextInputLayout>(R.id.textFieldEmail)
            val password = view.findViewById<TextInputLayout>(R.id.textFieldPassword)

            val verificacao = verificarCampos(email, password)

            if (verificacao) {
                navController.navigate(R.id.action_welcomeFragment_to_comicsListFragment)
            }
        }
    }


    private fun verificarCampos(email: TextInputLayout?, pass: TextInputLayout?): Boolean {
        var auxiliar = mutableListOf<Boolean>(false, false)

        if (email?.editText?.text.toString() != "") auxiliar[0] = true
        if (email?.editText?.text.toString() != "") auxiliar[1] = true

        if (!auxiliar[0]) {
            email?.error = "O campo e-mail não pode estar vazio"
        }
        if (!auxiliar[1]) {
            pass?.error = "O campo senha não pode estar vazio"
        }

        if (auxiliar[0] && auxiliar[1]) {
            return true
        }
        return false
    }
}
