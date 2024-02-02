package com.storyweave.app.presentation.storyRead.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.storyweave.app.ui.theme.Cream
import com.storyweave.app.ui.theme.StoryWeaveTheme

@Composable
fun ReadScreen(
    storyTitle: String,
    story: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(Cream.value))
            .padding(16.dp)



    ) {
        Text(
            text = storyTitle,
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        StoryContent(story = story)
    }
}

@Preview(showBackground = true)
@Composable
fun ReadScreenPreview() {
    StoryWeaveTheme{
        ReadScreen(
            storyTitle = "Demo Story",
            story = listOf(
                "Once upon a time...",
                "In a land far, far away...",
                "The protagonist embarked on a journey...",
                "They faced numerous challenges and overcame them...",
                "Throughout their adventure, they made new friends and encountered mysterious creatures...",
                "As the story unfolded, unexpected twists kept the readers on the edge of their seats...",
                "The journey took them to magical realms and enchanted forests...",
                "The protagonist discovered hidden powers within themselves...",
                "The climax of the story approached, leading to a final confrontation with the ultimate antagonist...",
                "In the end, the hero triumphed, and the land was saved from darkness...",
                "But the tale didn't end there; a new chapter began, promising more adventures and mysteries...",
                "And so, the story continued, weaving a tapestry of imagination and wonder...",
                "In another world, parallel to ours, a different saga unfolded...",
                "A lone wanderer set out on a quest to unlock the secrets of an ancient civilization...",
                "Mystical artifacts and forgotten prophecies guided their path...",
                "In the midst of chaos, alliances were forged and betrayals revealed...",
                "The echoes of ancient chants resonated through the air as the hero delved into the heart of darkness...",
                "The fate of worlds hung in the balance as the final battle approached...",
                "Across the cosmic expanse, a celestial dance of stars painted the canvas of the universe...",
                "Time itself seemed to bend as the hero confronted the enigmatic Timekeeper...",
                "Dimensions collided, creating a kaleidoscope of realities...",
                "The hero's journey intertwined with cosmic forces, shaping the destiny of existence...",
                "Legends whispered of a mythical city hidden beyond the edge of the world...",
                "Eldritch runes illuminated the path to the forgotten city of Eldoria...",
                "Ancient prophecies foretold of a chosen one who would unlock the city's secrets...",
                "The hero faced trials of mind and spirit, unlocking the ancient knowledge within...",
                "As dawn broke, the city's hidden wonders were revealed, rewriting the annals of history...",
                "But with every ending comes a new beginning, and the cycle of stories continued unabated...",
                "For in the realm of imagination, the possibilities were as boundless as the stars in the night sky..."
            )
        )
    }
}