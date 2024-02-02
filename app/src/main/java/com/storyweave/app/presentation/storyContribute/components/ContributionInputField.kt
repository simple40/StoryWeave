package com.storyweave.app.presentation.storyContribute.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ContributionInputField(
    contributionText: TextFieldValue,
    onContributionChange: (TextFieldValue) -> Unit,
    onSubmitClick: () -> Unit
) {
    Column {
        TextField(
            value = contributionText,
            onValueChange = {onContributionChange(it)},
            label = {Text("Add to the story")},
            modifier = Modifier
                .fillMaxWidth()
            )
        Spacer(modifier = Modifier.height(8.dp))
        SubmitButton(onSubmitClick)
    }
}

@Composable
fun SubmitButton(
    onSubmitClick: () -> Unit
) {
    Button(
        onClick = { onSubmitClick() }
    ) {
        Text("Submit Contribution")
    }
}