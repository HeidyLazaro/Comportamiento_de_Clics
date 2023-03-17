package net.heidylazaro.comportamientodeclics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.heidylazaro.comportamientodeclics.ui.theme.ComportamientoDeClicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComportamientoDeClicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

/*
Aumenta el tamaño de la fuente del texto para que sea más grande que el
predeterminado (como 18sp).

Agrega espacio adicional entre la etiqueta de texto y la imagen debajo,
de modo que no estén demasiado cerca entre sí (como 16dp).

Agrega un borde fino con esquinas un poco redondeadas alrededor de la
imagen para que los usuarios sepan que pueden presionar la imagen.
El color celeste del borde tiene un valor de color RGB (rojo, verde y azul)
de 105 para rojo, 205 para verde y 216 para azul
 */
@Composable
fun LemonApp() {
    var interaccion by remember{ mutableStateOf(1) }
    var interaccionLimon by remember{ mutableStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(interaccion){
            1->{
                LemonClickInteration(
                    label = R.string.limonero,
                    image = R.drawable.lemon_tree,
                    description = R.string.lemon_tree,
                    onImageClick = { interaccion=2
                                    interaccionLimon=(2..4).random()})

            }
            2->{
                LemonClickInteration(
                    label = R.string.limon,
                    image = R.drawable.lemon_squeeze,
                    description = R.string.lemon,
                    onImageClick = {
                        interaccionLimon--
                        if(interaccionLimon==0){
                            interaccion=3
                        }})
            }
            3->{
                LemonClickInteration(
                    label = R.string.vaso_de_limonada,
                    image = R.drawable.lemon_drink,
                    description = R.string.glass_of_lemonade,
                    onImageClick = { interaccion=4})
            }
            4->{
                LemonClickInteration(
                    label = R.string.vaso_vacio,
                    image = R.drawable.lemon_restart,
                    description = R.string.empty_glass,
                    onImageClick = { interaccion=1})
            }
        }
    }
}

@Composable
fun LemonClickInteration(label: Int,
                         image: Int,
                         description: Int,
                         onImageClick: ()-> Unit,
                         modifier: Modifier = Modifier){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(text = stringResource(label))
            Spacer(Modifier.height(16.dp))
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(description),
                modifier = Modifier
                    //.fillMaxWidth()
                    .clickable(onClick = onImageClick)
                    .border(width = 2.dp, color = Color.Blue)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComportamientoDeClicsTheme {
        LemonApp()
    }
}