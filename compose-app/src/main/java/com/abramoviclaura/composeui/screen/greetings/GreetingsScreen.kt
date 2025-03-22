package com.abramoviclaura.composeui.screen.greetings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.abramoviclaura.composeui.ui.theme.AndroidAnalysisUITheme
import com.abramoviclaura.composeui.ui.theme.PrimaryRed
import com.abramoviclaura.composeui.ui.theme.PrimaryWhite
import com.abramoviclaura.shared.R as SharedR

@Composable
fun GreetingsScreen() {
    val input = rememberSaveable { mutableStateOf("") }
    val username = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = SharedR.string.hello_user,
                username.value.ifBlank { stringResource(id = SharedR.string.user) }
            ),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(dimensionResource(SharedR.dimen.common_spacing_l)))

        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = PrimaryRed
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(SharedR.dimen.common_spacing_l)))

        Button(
            onClick = { username.value = input.value },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryRed,
                contentColor = PrimaryWhite
            )
        ) {
            Text(
                text = stringResource(id = SharedR.string.greet).uppercase(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(SharedR.dimen.common_spacing_m),
                    vertical = dimensionResource(SharedR.dimen.common_spacing_s)
                )
            )
        }
    }
}

@Preview
@Composable
private fun GreetingsScreenPreview() = AndroidAnalysisUITheme {
    GreetingsScreen()
}
