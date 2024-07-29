package com.kaleksandra.featuregamepad.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coreui.compose.painter
import com.kaleksandra.featuregamepad.R

@Composable
fun GamepadButtons(
    buttonSize: Dp = 36.dp,
    onPressed: (String) -> Unit,
    onReleased: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 20.dp + buttonSize),
            ) {
                RepeatingIconButton(
                    modifier = Modifier
                        .offset(y = 2.dp)
                        .size(buttonSize),
                    onPressed = { onPressed("d_up") },
                    onReleased = { onReleased("ds_up") }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        modifier = Modifier.size(26.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(buttonSize - 4.dp)
                ) {
                    RepeatingIconButton(
                        modifier = Modifier.size(buttonSize),
                        onPressed = { onPressed("d_left") },
                        onReleased = { onReleased("ds_left") }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowLeft,
                            modifier = Modifier.size(26.dp),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    RepeatingIconButton(
                        modifier = Modifier.size(buttonSize),
                        onPressed = { onPressed("d_right") },
                        onReleased = { onReleased("ds_right") }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            modifier = Modifier.size(26.dp),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                RepeatingIconButton(
                    modifier = Modifier
                        .offset(y = (-2).dp)
                        .size(buttonSize),
                    onPressed = { onPressed("d_down") },
                    onReleased = { onReleased("ds_down") }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        modifier = Modifier.size(26.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Row(modifier = Modifier.padding(top = 40.dp)) {
                    RepeatingButton(
                        modifier = Modifier.size(60.dp),
                        onPressed = { onPressed("md_0") },
                        onReleased = { onReleased("mu_0") }
                    ) {

                    }
                    RepeatingButton(
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .size(buttonSize),
                        onPressed = { onPressed("m_ll") },
                        onReleased = { onReleased("s_ll") }
                    ) {

                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RepeatingButton(
                    modifier = Modifier,
                    onPressed = { onPressed("m_l2") },
                    onReleased = { onReleased("s_l2") }
                ) {
                    Text("L2", fontSize = 12.sp)
                }
                RepeatingButton(
                    modifier = Modifier,
                    onPressed = { onPressed("m_l1") },
                    onReleased = { onReleased("s_l1") }
                ) {
                    Text("L1", fontSize = 12.sp)
                }
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RepeatingButton(
                    modifier = Modifier,
                    onPressed = { onPressed("m_r1") },
                    onReleased = { onReleased("s_r1") }
                ) {
                    Text("R1", fontSize = 12.sp)
                }
                RepeatingButton(
                    modifier = Modifier,
                    onPressed = { onPressed("m_r2") },
                    onReleased = { onReleased("s_r2") }
                ) {
                    Text("R2", fontSize = 12.sp)
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 20.dp + buttonSize),
            ) {
                RepeatingIconButton(
                    modifier = Modifier
                        .offset(y = 2.dp)
                        .size(buttonSize),
                    onPressed = { onPressed("m_y") },
                    onReleased = { onReleased("s_y") }
                ) {
                    Icon(
                        painter = painter(id = R.drawable.ic_triangle),
                        modifier = Modifier.size(26.dp),
                        contentDescription = null,
                        tint = Color(0xFF6BC55B)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(buttonSize - 4.dp)
                ) {
                    RepeatingIconButton(
                        modifier = Modifier.size(buttonSize),
                        onPressed = { onPressed("m_x") },
                        onReleased = { onReleased("s_x") }
                    ) {
                        Icon(
                            painter = painter(id = R.drawable.ic_square),
                            modifier = Modifier.size(26.dp),
                            contentDescription = null,
                            tint = Color(0xFFE7B1EB)
                        )
                    }
                    RepeatingIconButton(
                        modifier = Modifier.size(buttonSize),
                        onPressed = { onPressed("m_b") },
                        onReleased = { onReleased("s_b") }
                    ) {
                        Icon(
                            painter = painter(id = R.drawable.ic_circle),
                            modifier = Modifier.size(26.dp),
                            contentDescription = null,
                            tint = Color(0xFFD16969)
                        )
                    }
                }
                RepeatingIconButton(
                    modifier = Modifier
                        .offset(y = (-2).dp)
                        .size(buttonSize),
                    onPressed = { onPressed("m_a") },
                    onReleased = { onReleased("s_a") }
                ) {
                    Icon(
                        painter = painter(id = R.drawable.ic_x),
                        modifier = Modifier.size(26.dp),
                        contentDescription = null,
                        tint = Color(0xFFB1E6EB)
                    )
                }
                Row(modifier = Modifier.padding(top = 40.dp)) {
                    RepeatingButton(
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .size(buttonSize),
                        onPressed = { onPressed("") },
                        onReleased = { onReleased("") }
                    ) {

                    }
                    RepeatingButton(
                        modifier = Modifier.size(60.dp),
                        onPressed = { onPressed("m_rr") },
                        onReleased = { onReleased("s_rr") }
                    ) {

                    }
                }
            }
        }
        RepeatingIconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-2).dp)
                .size(buttonSize),
            onPressed = { onPressed("sp_1") },
            onReleased = { onReleased("sps_1") }
        ) {
            Icon(
                painter = painter(id = R.drawable.ic_ps),
                modifier = Modifier.size(24.dp),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
@Preview(
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 720,
    heightDp = 360,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = false
)
@Preview(
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 720,
    heightDp = 360,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
fun GamepadButtonsPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            GamepadButtons(36.dp, {}, {})
        }
    }
}