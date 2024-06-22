package com.example.chatappclient.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chatappclient.R
import com.example.chatappclient.common.navigateTo
import com.example.chatappclient.presentation.common.AuthHeader
import com.example.chatappclient.presentation.common.ErrorText
import com.example.chatappclient.presentation.common.Loader
import com.example.chatappclient.presentation.common.Routes
import com.example.chatappclient.presentation.common.TextFieldWithError
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = getViewModel()
) {
    val loginState = viewModel.loginState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        if (loginState.data != null)
            LaunchedEffect(key1 = true) {
                navigateTo(
                    navController = navController,
                    destination = Routes.FriendsList.route,
                    clearBackStack = true
                )
            }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            AuthHeader(
                title = "Welcome Back,",
                painterResource = R.drawable.bg_login_head
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val passwordVisible = remember { mutableStateOf(false) }

                TextFieldWithError(
                    label = "Email",
                    value = viewModel.emailState.text,
                    keyboardType = KeyboardType.Email,
                    isError = viewModel.emailState.error != null,
                    errorMessage = viewModel.emailState.error,
                    onValueChange = {
                        viewModel.onEmailChanged(it)
                        viewModel.emailState.validate()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithError(
                    label = "Password",
                    value = viewModel.passwordState.text,
                    keyboardType = KeyboardType.Password,
                    isError = viewModel.passwordState.error != null,
                    errorMessage = viewModel.passwordState.error,
                    onValueChange = {
                        viewModel.onPasswordChanged(it)
                        viewModel.passwordState.validate()
                    },
                    trailingIcon = {
                        val eyeIcon = if (passwordVisible.value) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val contentDescription =
                            if (passwordVisible.value) "Hide password" else "Show password"
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = eyeIcon, contentDescription = contentDescription)
                        }
                    },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    ClickableText(modifier = Modifier.align(Alignment.CenterEnd),
                        style = TextStyle(color = MaterialTheme.colorScheme.primary),
                        text = AnnotatedString(text = "Forgot Password?"),
                        onClick = {
                            navigateTo(navController, Routes.ForgotPassword.route)
                        })
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (loginState.error.isNotBlank())
                    ErrorText(loginState.error)

                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    onClick = {
                        viewModel.performLogin()
                    }) {
                    Text(text = "Login in", color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }

        val annotatedText = buildAnnotatedString {
            //append your initial text
            withStyle(style = SpanStyle(color = Color.Gray)) { append("Don't have account? ") }

            //Start of the pushing annotation which you want to color and make them clickable later
            pushStringAnnotation(
                tag = "SignUp", // provide tag which will then be provided when you click the text
                annotation = "SignUp"
            )

            //add text with your different color/style
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("Sign Up")
            }

            withStyle(style = SpanStyle(color = Color.Gray)) { append(".") }

            // when pop is called it means the end of annotation with current tag
            pop()
        }
        ClickableText(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(20.dp),
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "SignUp",// tag which you used in the buildAnnotatedString
                    start = offset, end = offset
                ).firstOrNull()?.let {
                    navigateTo(navController, Routes.SignUp.route)
                }
            })
    }
    Loader(isLoading = loginState.isLoading)
}

@Preview(showBackground = true)
@Composable
fun ScreenLoginPrev() {
    LoginScreen(rememberNavController())
}