package com.example.artspaceapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artspaceapp.R
import com.example.artspaceapp.model.Artwork
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier,
    artSpaceViewModel: ArtSpaceViewModel = viewModel()
) {
    val uiState by artSpaceViewModel.uiState.collectAsState()

    ArtSpaceScreen(
        uiState = uiState,
        onPreviousClick = { artSpaceViewModel.showPreviousArtwork() },
        onNextClick = { artSpaceViewModel.showNextArtwork() },
        modifier = modifier
    )
}

@Composable
fun ArtSpaceScreen(
    uiState: ArtSpaceUiState,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> LandscapeLayout(
            art = uiState.currentArtwork,
            onPrev = onPreviousClick,
            onNext = onNextClick,
            modifier = modifier
        )

        else -> PortraitLayout(
            art = uiState.currentArtwork,
            onPrev = onPreviousClick,
            onNext = onNextClick,
            modifier = modifier
        )
    }
}

@Composable
private fun PortraitLayout(
    art: Artwork,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(
            art = art,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        ArtworkDescriptor(art = art)
        Spacer(modifier = Modifier.height(24.dp))
        DisplayController(onPrev = onPrev, onNext = onNext)
    }
}

@Composable
private fun LandscapeLayout(
    art: Artwork,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArtworkWall(
            art = art,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Spacer(modifier = Modifier.width(32.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkDescriptor(art = art)
            Spacer(modifier = Modifier.height(32.dp))
            DisplayController(onPrev = onPrev, onNext = onNext)
        }
    }
}

@Composable
fun ArtworkWall(art: Artwork, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(2.dp)),
            color = MaterialTheme.colorScheme.surface
        ) {
            Image(
                painter = painterResource(art.image),
                contentDescription = stringResource(
                    R.string.artwork_description,
                    stringResource(art.title)
                ),
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(art: Artwork, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(art.title),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(
                        R.string.artwork_info,
                        stringResource(art.artist),
                        art.year
                    ),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPrev: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onPrev, modifier = Modifier.weight(1f)) {
            Text(stringResource(R.string.previous))
        }
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceAppTheme {
        ArtSpaceScreen(
            uiState = ArtSpaceUiState(),
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 360)
@Composable
fun ArtSpaceLandscapePreview() {
    ArtSpaceAppTheme {
        LandscapeLayout(
            art = ArtSpaceUiState().currentArtwork,
            onPrev = {},
            onNext = {}
        )
    }
}
