package com.example.todolist_app_project.screens


import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.R
import com.example.todolist_app_project.ui.theme.accentBlue
import com.example.todolist_app_project.ui.theme.lightBlue
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
//    emailLoginClick: () -> Unit,
//    guestLoginClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(lightBlue)
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        val buttonWidth = 300.dp
        Spacer(modifier = Modifier.height(18.dp))

        if (viewModel.error.value.isNotBlank()) {
            ErrorField(viewModel)
        }
        /*SignInWithEmailButton(buttonWidth, emailLoginClick)
        SignInWithGoogleButton(buttonWidth, viewModel)
        SignInWithGuestButton(buttonWidth, viewModel)*/
        Column(
            Modifier.padding(top = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailFieldElementLogin(viewModel)
            PasswordFieldElementLogin(viewModel)
            LogInButton(viewModel)
            OrOtherOptions(viewModel)
        }
    }
}

@Composable
fun EmailFieldElementLogin(viewModel: LoginViewModel) {
    val userEmail = viewModel.userEmail.value
    Column() {
        Text(
            text = "Email",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp),
            value = userEmail,
            label = { Text(text = stringResource(R.string.email)) },
            onValueChange = { viewModel.setUserEmail(it) }
        )
    }
}

@Composable
fun PasswordFieldElementLogin(viewModel: LoginViewModel) {
    val password = viewModel.password.value
    Column() {
        Text(
            text = "Password",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp),
            visualTransformation = PasswordVisualTransformation(),
            value = password,
            label = { Text(text = stringResource(R.string.password)) },
            onValueChange = { viewModel.setPassword(it) }
        )
    }

}

@Composable
fun LogInButton(viewModel: LoginViewModel) {
    Button(
        enabled = viewModel.isValidEmailAndPassword(),
        onClick = { viewModel.signInWithEmailAndPassword() },
        modifier = Modifier
            .width(200.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = accentBlue,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Login",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Composable
fun OrOtherOptions(viewModel: LoginViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Or",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
        )
    }

    Text(
        text = "Sign up Using:",
        style = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        ),
    )

    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)

    val launcher = registerGoogleActivityResultLauncher(viewModel)

    Row(
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            tint = Color.Unspecified,
            imageVector = ImageVector.vectorResource(id = R.drawable.google),
            contentDescription = "Google",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .clickable(onClick = {
                    val signInOptions =
                        GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(token)
                            .requestEmail()
                            .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, signInOptions)
                    launcher.launch(googleSignInClient.signInIntent)
                }),
        )
    }
}

@Composable
fun registerGoogleActivityResultLauncher(viewModel: LoginViewModel): ManagedActivityResultLauncher<Intent, ActivityResult> {
    // Callback
    return rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            viewModel.signInWithGoogleToken(account.idToken!!)
        } catch (e: ApiException) {
            Log.w(TAG, "Google sign in failed", e)
        }
    }
}

@Composable
fun ErrorField(viewModel: LoginViewModel) {
    Text(
        text = viewModel.error.value,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Red,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

/*@Composable
fun SignInWithGoogleButton(buttonWidth: Dp, viewModel: LoginViewModel) {
    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)

    val launcher = registerGoogleActivityResultLauncher(viewModel)

    OutlinedButton(
        modifier = Modifier.width(buttonWidth),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(R.color.grey),
            contentColor = MaterialTheme.colors.onSurface
        ),
        onClick = {
            val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(context, signInOptions)
            launcher.launch(googleSignInClient.signInIntent)
        }
    ) {
        SignInButtonRow(
            iconId = R.drawable.ic_baseline_login_24,
            buttonTextId = R.string.sign_in_with_google
        )
    }
@Composable
fun SignInWithEmailButton(buttonWidth: Dp, emailLoginClick: () -> Unit) {
    OutlinedButton(
        onClick = { emailLoginClick() },
        modifier = Modifier.width(buttonWidth),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(R.color.grey),
            contentColor = colorResource(R.color.white)
        )
    ) {
        SignInButtonRow(
            iconId = R.drawable.ic_baseline_mail_24,
            buttonTextId = R.string.sign_in_with_email
        )
    }
}

@Composable
fun SignInWithGuestButton(buttonWidth: Dp, viewModel: LoginViewModel) {
    OutlinedButton(
        onClick = { viewModel.signInAnonymously() },
        modifier = Modifier.width(buttonWidth),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(R.color.grey),
            contentColor = colorResource(R.color.white)
        )
    ) {
        SignInButtonRow(iconId = R.drawable.ic_baseline_login_24, buttonTextId = R.string.guest)
    }
}

@Composable
fun SignInButtonRow(@DrawableRes iconId: Int, @StringRes buttonTextId: Int) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp)
    ) {
        LoginButtonIcon(iconId)
        LoginButtonText(buttonTextId)
    }
}

@Composable
fun LoginButtonIcon(@DrawableRes painterResourceId: Int) {
    Icon(
        tint = Color.Unspecified,
        painter = painterResource(painterResourceId),
        contentDescription = null
    )
}

@Composable
fun LoginButtonText(@StringRes stringResourceId: Int) {
    Text(
        text = stringResource(stringResourceId),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.button,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    )
}
*/