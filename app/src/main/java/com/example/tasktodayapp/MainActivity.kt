package com.example.tasktodayapp



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapp.model.Tarefa.Tarefa
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent()
        }
    }
}

@Composable
fun MainScreenContent() {
    val scaffoldState = rememberScaffoldState()
    var scope = rememberCoroutineScope()
    //var tabIndex by remember { mutableStateOf(0) }
    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            TopAppBar(

                title = { Text(text = "TaskTodayApp")},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch{
                            scaffoldState.drawerState.open()
                        }
                    } ){
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu",
                            tint = Color(0xFF000000),

                            )
                    }
                },

                backgroundColor = Color(0xFF0099ff ),

                )


        },

        drawerBackgroundColor = Color(0xFFccffb3),
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,

        drawerContent = {
            Box(
                modifier = Modifier
                    .height(16.dp)
            ){
                Text(text = "Opções!!!")
            }

            Column(){
                Text(text = "Tarefas")
                Text(text = " Notificações")
                Text(text = "Opção X")
            }


        },

        content = {

                paddingValues -> Log.i("paddinVales", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color(0))
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())

                //val calendar = Calendar.getInstance()
                //listOf<Tarefa>(Tarefa("Estudar Kotlin ", "Cap 1", Date(), calendar.set{2023}))

                val tPyton = Tarefa(
                    "Estudar Pyton",
                    "Cap 1 ",
                    Date(),
                    Date(),
                    status = 0.0,
                    "#ff4d4d"
                )

                val tKotlin = Tarefa(
                    "Estudar  Kotlin",
                    "Cap 2 ",
                    Date(),
                    Date(),
                    status = 0.0,
                    "#ff4d4d"
                )

                val tJava = Tarefa(
                    "Estudar Java",
                    "Cap 6 ",
                    Date(),
                    Date(),
                    status = 0.0,
                    "#ff4d4d"
                )

                var minhaListaDeTarefas = listOf<Tarefa>(tPyton, tJava, tKotlin)
                MyTaskWidgetList(minhaListaDeTarefas)

            }
        },

        bottomBar = {

            BottomAppBar(
                content = {Text("BottomBar")},
                backgroundColor = Color(0xFF0000e6 )
            )



        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            icon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add Task"
                )
            },

            text = { Text(text = "ADD")},
            onClick = {},
        )}

    )

}

@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(
        action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), tarefaASerMostrada = it)}
    )
}

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFF000000),
            )
        }
    )
}


@Composable
fun MyTaskWidget(
    modificador: Modifier,
    tarefaASerMostrada: Tarefa
){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {

            Icon(
                imageVector = Icons.Default.Notifications,
                tint = Color(0xFF000080),
                contentDescription = "Icons  of a pendent taks"
            )

            Text(
                text = dateFormatter.format(tarefaASerMostrada.pzoFinal),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )


        Column(
            modifier = modificador
                .border(width = 1.dp, color = Color.Black)
                .padding(3.dp)
                .background(Color(android.graphics.Color.parseColor(tarefaASerMostrada.cor)))

        ) {
            Text(
                text = tarefaASerMostrada.nome,
                fontSize = 12.sp,
                color = Color(0xFF000000),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )


            Text(
                text = tarefaASerMostrada.detalhes.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF0000000 )
            )

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}


//@Preview(showBackground = true)

