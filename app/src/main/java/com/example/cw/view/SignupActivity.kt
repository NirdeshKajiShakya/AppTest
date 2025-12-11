package com.example.cw.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.model.UserModel
import com.example.cw.model.UserViewModel
import com.example.cw.repo.UserRepoImpl

class UserRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                RegisterScreen()
        }
    }
}

@Composable
fun RegisterScreen() {

    val userViewModel = remember { UserViewModel(UserRepoImpl()) }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var dateofbirth by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        // Title
        Text(
            text = "Create an Account",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            singleLine = true,
            label = { Text("Full Name", color = Color.Gray) }
        )

        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            singleLine = true,
            label = { Text("Email", color = Color.Gray) }
        )

        // Password Input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            singleLine = true,
            label = { Text("Password", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation()
        )

        // Gender Input
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            singleLine = true,
            label = { Text("Gender", color = Color.Gray) }
        )

        // DOB
        OutlinedTextField(
            value = dateofbirth,
            onValueChange = { dateofbirth = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            singleLine = true,
            label = { Text("Date of Birth", color = Color.Gray) }
        )

        Spacer(
            modifier = Modifier
                .height(24.dp)
        )

        // Sign Up Button
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    userViewModel.register(email, password) { isSuccess, message, userId ->
                        if (isSuccess) {
                            // Create user model with the provided data
                            val model = UserModel(
                                userID = userId,
                                firstName = fullName.substringBefore(" "),
                                lastName = fullName.substringAfter(" ", ""),
                                email = email,
                                gender = gender,
                                dob = dateofbirth
                            )
                            // Save user data to Firebase Realtime Database
                            userViewModel.addUserToDatabase(userId, model) { dbSuccess, dbMessage ->
                                if (dbSuccess) {
                                    Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                    // Navigate to login screen or home screen
                                } else {
                                    Toast.makeText(context, "Database error: $dbMessage", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Registration failed: $message", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
        ) {
            Text("Sign Up", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // Already have account
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = " Sign In",
                fontSize = 14.sp,
                color = Color(0xFF2563EB),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { /* Navigate to login */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
