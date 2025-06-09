package com.example.molar2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun StyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordToggle: (() -> Unit)? = null
) {
    val backgroundColor = Color(0xFF113543)
    val textColor = Color(0xFFB0BEC5)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(label, color = textColor) },
            singleLine = true,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            trailingIcon = if (isPassword && onPasswordToggle != null) {
                {
                    IconButton(onClick = onPasswordToggle) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = textColor
                        )
                    }
                }
            } else null,
            colors = TextFieldDefaults.textFieldColors(
                textColor = textColor,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = textColor
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RegistrationScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var lrn by remember { mutableStateOf("") }
    var gradeLevel by remember { mutableStateOf("") }
    var section by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var streetAddress by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var agreeToTerms by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D232E))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Text("REGISTRATION", color = Color(0xFFFFA726), fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            StyledTextField(value = firstName, onValueChange = { firstName = it }, label = "First Name")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = middleName, onValueChange = { middleName = it }, label = "Middle Name")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = lastName, onValueChange = { lastName = it }, label = "Last Name")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = birthDate, onValueChange = { birthDate = it }, label = "Birthdate (MM/DD/YY)")
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text("Gender:", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(selected = gender == "Male", onClick = { gender = "Male" }, colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFFA726)))
                Text("Male", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(selected = gender == "Female", onClick = { gender = "Female" }, colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFFA726)))
                Text("Female", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = email, onValueChange = { email = it }, label = "Email Address")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = mobileNumber, onValueChange = { mobileNumber = it }, label = "Mobile Number")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = lrn, onValueChange = { lrn = it }, label = "LRN")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = gradeLevel, onValueChange = { gradeLevel = it }, label = "Grade Level")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = section, onValueChange = { section = it }, label = "Section")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = schoolName, onValueChange = { schoolName = it }, label = "School Name")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = streetAddress, onValueChange = { streetAddress = it }, label = "Street Address")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = city, onValueChange = { city = it }, label = "City")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = province, onValueChange = { province = it }, label = "Province")
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(value = zipCode, onValueChange = { zipCode = it }, label = "ZIP Code")
            Spacer(modifier = Modifier.height(8.dp))

            StyledTextField(
                value = password,
                onValueChange = { password = it },
                label = "Create Password",
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordToggle = { passwordVisible = !passwordVisible }
            )
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                isPassword = true,
                passwordVisible = confirmPasswordVisible,
                onPasswordToggle = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Checkbox(
                    checked = agreeToTerms,
                    onCheckedChange = { agreeToTerms = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFFFFA726))
                )
                Text("I agree to terms & condition", color = Color.LightGray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF113543), shape = RoundedCornerShape(12.dp))
            ) {
                Button(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit", color = Color(0xFFFFA726), fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    RegistrationScreen(navController = navController)
}
