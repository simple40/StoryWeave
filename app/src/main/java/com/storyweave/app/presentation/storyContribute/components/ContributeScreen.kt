package com.storyweave.app.presentation.storyContribute.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.storyweave.app.presentation.storyRead.components.StoryContent
import com.storyweave.app.ui.theme.StoryWeaveTheme

@Composable
fun ContributeScreen(
    story:List<String>
) {
    var contributionText by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier
            .weight(1f)
        ){
            StoryContent(story = story)
        }

        Box(
            modifier = Modifier
                .weight(1f)
        ){
            ContributionInputField(
                contributionText = contributionText,
                onContributionChange = {contributionText = it}
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContributionScreenPreview() {
    StoryWeaveTheme {
        ContributeScreen(
            story = listOf(
                "Once upon a time...",
                "In a land far, far away...",
                "The protagonist embarked on a journey..."
            )
        )
    }
}