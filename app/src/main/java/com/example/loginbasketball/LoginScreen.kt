package com.example.loginbasketball

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {

    Box(
        Modifier
            .background(color = Color(0xFF161616))
            .fillMaxSize()
            .padding(16.dp),
        Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Head()
            Spacer(modifier = Modifier.size(32.dp))
            Body()
            Spacer(modifier = Modifier.size(32.dp))
            SignUpSocial()
        }
    }

}

@Composable
fun Head() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TitleName()
        SubtitleDescription()
    }
}

@Composable
fun TitleName() {
    Text(
        text = "BasketballStars",
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFff4400)
    )
}

@Composable
fun SubtitleDescription() {
    Text(text = "Create Account", fontSize = 16.sp, color = Color.White)
}

@Composable
fun Body() {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var isEnable by rememberSaveable { mutableStateOf(false) }

    Column() {
        Email(email) {
            email = it
            isEnable = enableLogin(email, password, repeatPassword)
        }
        Password(password) {
            password = it
            isEnable = enableLogin(email, password, repeatPassword)
        }
        RepeatPassword(repeatPassword) {
            repeatPassword = it
            isEnable = enableLogin(email, password, repeatPassword)
        }
        ButtonSignUp(isEnable)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {

    Text(text = "Email: ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text(text = "Enter email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFF121212),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(imageVector = Icons.Rounded.Email, contentDescription = "email", tint = Color.Gray)
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {

    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    Text(
        text = "Password: ",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 14.dp)
    )
    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text(text = "**********") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFF121212),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val icons = if (passwordVisibility) {
                Icons.Filled.Warning
            } else {
                Icons.Filled.Lock
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = icons, contentDescription = "ShowPassword", tint = Color.Gray)
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPassword(repeatPassword: String, onTextChange: (String) -> Unit) {

    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    Text(
        text = "Password: ",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 14.dp)
    )
    TextField(
        value = repeatPassword,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text(text = "**********") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFF121212),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val icons = if (passwordVisibility) {
                Icons.Filled.Warning
            } else {
                Icons.Filled.Lock
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = icons, contentDescription = "ShowPassword", tint = Color.Gray)
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun ButtonSignUp(isEnable: Boolean) {
    Spacer(modifier = Modifier.size(32.dp))
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2B2B2B),
            disabledContainerColor = Color(0xFF535252),
            contentColor = Color(0xFFff4400),
            disabledContentColor = Color(0xFFff4400)
        )
    ) {
        Text(text = "Sign Up", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

fun enableLogin(email: String, password: String, repeatPassword: String) =
    Patterns.EMAIL_ADDRESS.matcher(email)
        .matches() && password.length >= 6 && repeatPassword.length >= 6

@Composable
fun SignUpSocial() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Sign up witch Google", color = Color.White, modifier = Modifier.padding(end = 12.dp))
        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google")
    }
}







