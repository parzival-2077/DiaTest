package com.example.diatest.ui

import CartViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.local.DataSource
import com.example.diatest.model.CartItem
import com.example.domain.entity.Product
import com.example.diatest.ui.theme.DiaTestTheme

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.HorizontalDivider
import com.example.diatest.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiaTestTheme {
                // Оберните приложение в ViewModel
                val cartViewModel: CartViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiaTestApp(cartViewModel = cartViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    onBackClick: () -> Unit,
    onProductAdded: () -> Unit,
    modifier: Modifier = Modifier
) {
    var productName by remember { mutableStateOf("") }
    var carbohydrates by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var showCategoryDropdown by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val categories = listOf(
        "Напитки",
        "Молочные продукты",
        "Мучные изделия",
        "Кондитерские изделия",
        "Фрукты",
        "Овощи",
        "Мясо и рыба",
        "Другое"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Добавить продукт",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            if (showError) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = "Заполните все обязательные поля",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Форма добавления продукта
            OutlinedTextField(
                value = productName,
                onValueChange = {
                    productName = it
                    showError = false
                },
                label = { Text("Название продукта *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                isError = showError && productName.isEmpty()
            )

            OutlinedTextField(
                value = carbohydrates,
                onValueChange = {
                    carbohydrates = it
                    showError = false
                },
                label = { Text("Углеводы на 100г *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                suffix = { Text("г") },
                isError = showError && (carbohydrates.isEmpty() || carbohydrates.toDoubleOrNull() == null)
            )

            // Выбор категории
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = {},
                    label = { Text("Категория *") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(
                            onClick = { showCategoryDropdown = !showCategoryDropdown }
                        ) {
                            Icon(
                                if (showCategoryDropdown) Icons.Default.Close else Icons.Default.ArrowDropDown,
                                contentDescription = "Выбрать категорию"
                            )
                        }
                    },
                    isError = showError && category.isEmpty()
                )

                // Выпадающий список категорий
                if (showCategoryDropdown) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            categories.forEach { cat ->
                                Text(
                                    text = cat,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            category = cat
                                            showCategoryDropdown = false
                                            showError = false
                                        }
                                        .padding(16.dp),
                                    fontSize = 16.sp
                                )
                                if (cat != categories.last()) {
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }
            }

            // Кнопка сохранения
            Button(
                onClick = {
//                    // Валидация данных
//                    if (productName.isNotEmpty() &&
//                        carbohydrates.isNotEmpty() &&
//                        carbohydrates.toDoubleOrNull() != null &&
//                        category.isNotEmpty()) {
//
//                        // Создаем новый продукт
//                        val newProduct = Product(
//                            id = DataSource.getNextId(),
//                            name = productName,
//                            imageResId = R.drawable.dairy_products, // Стандартное изображение
//                            ugl = carbohydrates.toDouble(),
//                            category = category
//                        )
//
//                        // Сохраняем в DataSource
//                        DataSource.addProduct(newProduct)
//
//                        // Уведомляем о добавлении
//                        onProductAdded()
//
//                        // Закрываем экран
//                        onBackClick()
//                    } else {
//                        showError = true
//                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF43B05C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Добавить продукт",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}



@Composable
fun ProductsScreen(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
//    var searchQuery by remember { mutableStateOf("") }
//    var selectedProduct by remember { mutableStateOf<Product?>(null) }
//    val allProducts by remember { mutableStateOf(DataSource.loadProducts()) } // Используем remember
//
//    val filteredProducts = remember(searchQuery, allProducts) {
//        if (searchQuery.isBlank()) {
//            allProducts
//        } else {
//            allProducts.filter { product ->
//                product.name.contains(searchQuery, ignoreCase = true) ||
//                        product.category.contains(searchQuery, ignoreCase = true)
//            }
//        }
//    }
//
//    Column(modifier = modifier.padding(horizontal = 16.dp)) {
//        SearchBar(
//            query = searchQuery,
//            onQueryChange = { searchQuery = it },
//            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
//        )
//
//        ProductList(
//            productList = filteredProducts,
//            onProductClick = { product ->
//                selectedProduct = product
//            },
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//
//    // Показываем BottomSheet при выборе продукта
//    selectedProduct?.let { product ->
//        ProductBottomSheet(
//            product = product,
//            onDismiss = { selectedProduct = null },
//            onAddToCart = { cartItem ->
//                cartViewModel.addToCart(cartItem)
//            }
//        )
//    }
}

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("History Screen")
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {


            // Имя пользователя
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                // Аватар
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Пользователь",
                    tint = Color.White,
                    modifier = Modifier
                        .background(
                            color = Color(0xFF43B05C),
                            shape = CircleShape
                        )
                        .size(64.dp)
                        .padding(12.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Имя и фамилия
                Text(
                    text = "Фамилия Имя",
                    style = MaterialTheme.typography.headlineSmall
                )

                // Кнопка редактирования
                IconButton(
                    onClick = { /* Редактирование */ },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Изменить",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Раздел "Личные данные"
            Text(
                text = "Личные данные",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF4F946B),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Список личных данных
            PersonalDataItem(label = "Пол", value = "какой пол")
            PersonalDataItem(label = "Год рождения", value = "хххх")
            PersonalDataItem(label = "Рост", value = "ххх см")
            PersonalDataItem(label = "Вес", value = "ххх кг")

            // Разделительная линия
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFF4F946B)
            )

            // Раздел "Индивидуальные параметры"
            Text(
                text = "Индивидуальные параметры",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF4F946B),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Список индивидуальных параметров
            PersonalDataItem(
                label = "XE единицы на углеводы",
                value = "1 XE = X g"
            )
            PersonalDataItem(
                label = "Множитель инсулина",
                value = "1"
            )
        }
    }
}

@Composable
private fun PersonalDataItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,

        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,

            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val cartItems = cartViewModel.cartItems // Убрали collectAsState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Заголовок
        Text(
            text = "Корзина",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (cartItems.isEmpty()) {
            // Пустая корзина
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Пустая корзина",
                        modifier = Modifier.size(100.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    )
                    Text(
                        text = "Корзина пуста",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Добавьте продукты из списка",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        } else {
            // Список продуктов в корзине
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { item ->
                    CartItemCard(
                        item = item,
                        onRemove = { cartViewModel.removeFromCart(item.productId) },
                        onQuantityChange = { newQuantity ->
                            cartViewModel.updateQuantity(item.productId, newQuantity)
                        }
                    )
                }
            }

            // Итоговая информация
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Всего продуктов:")
                        Text("${cartItems.size} шт")
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Общий вес:")
                        Text("${cartItems.sumOf { it.quantity }} г")
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Всего углеводов:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${String.format("%.1f", cartViewModel.getTotalCarbohydrates())} г",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4F946B)
                        )
                    }

                    Button(
                        onClick = { /* Оформление заказа */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF43B05C), // основной цвет
                            contentColor = Color.Black // цвет контента (текста/иконки)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Рассчитать инсулин")
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onRemove: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var tempQuantity by remember { mutableStateOf(item.quantity.toString()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Изображение
            Image(
                painter = painterResource(item.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Информация
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "${String.format("%.1f", (item.carbohydrates / 100) * item.quantity)} г углеводов",
                    fontSize = 12.sp,
                    color = Color(0xFF4F946B),
                    modifier = Modifier.padding(top = 2.dp)
                )

                Text(
                    text = item.category,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            // Количество и кнопки
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${item.quantity} г",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { showEditDialog = true }
                )

                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Удалить",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }

    // Диалог редактирования количества
    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Изменить количество") },
            text = {
                OutlinedTextField(
                    value = tempQuantity,
                    onValueChange = { tempQuantity = it },
                    label = { Text("Граммы") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        try {
                            val newQuantity = tempQuantity.toInt()
                            if (newQuantity > 0) {
                                onQuantityChange(newQuantity)
                                showEditDialog = false
                            }
                        } catch (e: NumberFormatException) {
                            // Обработка ошибки
                        }
                    }
                ) {
                    Text("ОК")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showEditDialog = false }
                ) {
                    Text("Отмена")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Поиск",
                tint = Color(0xFF4F946B)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Очистить поиск",
                        tint = Color(0xFF4F946B)
                    )
                }
            }
        },
        placeholder = {
            Text(
                "Поиск продуктов...",
                color = Color(0xFF4F946B)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                // Действие при нажатии на поиск
            }
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun ProductCards(
    product: Product,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
//    Card(
//        modifier = modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant,
//            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
//        ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Row(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(60.dp)
//                        .padding(end = 16.dp)
//                ) {
//                    Image(
//                        painter = painterResource(product.imageResId),
//                        contentDescription = product.name,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clip(RoundedCornerShape(12.dp)),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Text(
//                        text = product.name,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier.padding(bottom = 4.dp),
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//
//                    Text(
//                        text = "${product.ugl} гр углеводов · ${product.category}",
//                        fontSize = 12.sp,
//                        color = Color(0xFF4F946B),
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }
//            }
//
//            IconButton(
//                onClick = { onProductClick(product) },
//                modifier = Modifier
//                    .background(
//                        color = Color(0xFF43B05C),
//                        shape = CircleShape
//                    )
//                    .size(35.dp)
//            ) {
//                Icon(
//                    Icons.Default.Add,
//                    contentDescription = "Добавить",
//                    tint = Color.White,
//                    modifier = Modifier.size(32.dp)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductList(
//    productList: List<Product>,
//    onProductClick: (Product) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    if (productList.isEmpty()) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "Продукты не найдены",
//                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//            )
//        }
//    } else {
//        LazyColumn(modifier = modifier) {
//            items(productList) { productsCard ->
//                ProductCards(
//                    product = productsCard,
//                    onProductClick = onProductClick,
//                    modifier = Modifier.padding(vertical = 4.dp)
//                )
//            }
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductBottomSheet(
    product: Product,
    onDismiss: () -> Unit,
    onAddToCart: (CartItem) -> Unit
) {
//    var quantity by remember { mutableStateOf("100") }
//    var quantityError by remember { mutableStateOf(false) }
//
//    val calculatedCarbs = remember(quantity) {
//        try {
//            val grams = quantity.toInt()
//            (product.ugl * grams / 100)
//        } catch (e: NumberFormatException) {
//            0.0
//        }
//    }
//
//    ModalBottomSheet(
//        onDismissRequest = onDismiss,
//        sheetState = rememberModalBottomSheetState(),
//        containerColor = MaterialTheme.colorScheme.surface,
//        contentColor = MaterialTheme.colorScheme.onSurface,
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 24.dp, vertical = 20.dp)
//        ) {
//            // Заголовок
//            Text(
//                text = product.name,
//                style = MaterialTheme.typography.titleLarge,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            // Изображение продукта
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(bottom = 24.dp)
//            ) {
//                Image(
//                    painter = painterResource(product.imageResId),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Crop
//                )
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                Column {
//                    Text(
//                        text = "${product.ugl} г углеводов на 100г",
//                        color = Color(0xFF4F946B),
//                        fontSize = 14.sp
//                    )
//                    Text(
//                        text = product.category,
//                        fontSize = 12.sp,
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//                    )
//                }
//            }
//
//            // Поле ввода количества
//            OutlinedTextField(
//                value = quantity,
//                onValueChange = {
//                    quantity = it
//                    quantityError = it.isNotEmpty() && !it.all { char -> char.isDigit() }
//                },
//                label = { Text("Количество (грамм)") },
//                isError = quantityError,
//                supportingText = {
//                    if (quantityError) {
//                        Text("Введите число")
//                    } else {
//                        Text("${String.format("%.1f", calculatedCarbs)} г углеводов")
//                    }
//                },
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Number
//                ),
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 32.dp)
//            )
//
//            // Кнопки стандартных порций
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                modifier = Modifier.padding(bottom = 32.dp)
//            ) {
//                listOf(50, 100, 150, 200).forEach { standardAmount ->
//                    OutlinedButton(
//                        onClick = { quantity = standardAmount.toString() },
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text("${standardAmount}г")
//                    }
//                }
//            }
//
//            // Кнопка добавления
//            Button(
//                onClick = {
//                    try {
//                        val grams = quantity.toInt()
//                        if (grams > 0) {
//                            val cartItem = CartItem(
//                                productId = product.id,
//                                name = product.name,
//                                imageResId = product.imageResId,
//                                carbohydrates = product.ugl,
//                                category = product.category,
//                                quantity = grams
//                            )
//                            onAddToCart(cartItem)
//                            onDismiss()
//                        }
//                    } catch (e: NumberFormatException) {
//                        quantityError = true
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF43B05C), // основной цвет
//                    contentColor = Color.Black // цвет контента (текста/иконки)
//                ),
//
//                enabled = quantity.isNotEmpty() && !quantityError
//            ) {
//                Text(
//                    "Добавить в корзину",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        }
//    }
}

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    PRODUCT("product", "Продукты", Icons.AutoMirrored.Filled.List, "Products"),
    HISTORY("history", "История", Icons.Default.Star, "History"),
    CART("cart", "Корзина", Icons.Default.ShoppingCart, "Cart"),
    PROFILE("profile", "Профиль", Icons.Default.Person, "Profile"),
    ADD_PRODUCT("add_product", "Добавить продукт", Icons.Default.Add, "Add Product")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        composable(Destination.PRODUCT.route) {
            ProductsScreen(cartViewModel = cartViewModel)
        }
        composable(Destination.HISTORY.route) {
            HistoryScreen()
        }
        composable(Destination.CART.route) {
            CartScreen(cartViewModel = cartViewModel)
        }
        composable(Destination.PROFILE.route) {
            ProfileScreen()
        }
        composable(Destination.ADD_PRODUCT.route) {
            AddProductScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onProductAdded = {
                    // Можно добавить уведомление или обновление списка
                    // Но ProductsScreen уже автоматически перерисуется при возврате
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaTestApp(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    // Используем currentDestination для определения активного экрана
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = when (currentDestination?.route) {
                            Destination.PRODUCT.route -> "Продукты"
                            Destination.HISTORY.route -> "История"
                            Destination.CART.route -> "Корзина"
                            Destination.PROFILE.route -> "Профиль"
                            Destination.ADD_PRODUCT.route -> "Добавить продукт"
                            else -> "Продукты"
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    // Показываем кнопку добавления только на экране продуктов
                    if (currentDestination?.route == Destination.PRODUCT.route) {
                        IconButton(
                            onClick = {
                                navController.navigate(Destination.ADD_PRODUCT.route)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Добавить продукт"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            // Показываем нижнюю панель только на основных экранах
            if (currentDestination?.route in listOf(
                    Destination.PRODUCT.route,
                    Destination.HISTORY.route,
                    Destination.CART.route,
                    Destination.PROFILE.route
                )) {
                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets
                ) {
                    // Создаем список только основных экранов (без ADD_PRODUCT)
                    val mainDestinations = listOf(
                        Destination.PRODUCT,
                        Destination.HISTORY,
                        Destination.CART,
                        Destination.PROFILE
                    )

                    mainDestinations.forEach { destination ->
                        NavigationBarItem(
                            selected = currentDestination?.route == destination.route,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(Destination.PRODUCT.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                // Для иконки корзины добавляем BadgedBox
                                if (destination.route == Destination.CART.route &&
                                    cartViewModel.cartItems.isNotEmpty()) {
                                    BadgedBox(
                                        badge = {
                                            Badge {
                                                Text(
                                                    text = if (cartViewModel.cartItems.size > 99)
                                                        "99+"
                                                    else
                                                        cartViewModel.cartItems.size.toString(),
                                                    fontSize = 10.sp
                                                )
                                            }
                                        }
                                    ) {
                                        Icon(
                                            destination.icon,
                                            contentDescription = destination.contentDescription
                                        )
                                    }
                                } else {
                                    Icon(
                                        destination.icon,
                                        contentDescription = destination.contentDescription
                                    )
                                }
                            },
                            label = {
                                Text(destination.label)
                            }
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            startDestination = Destination.PRODUCT,
            cartViewModel = cartViewModel,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiaPreview() {
    val cartViewModel = CartViewModel()
    DiaTestTheme {
        DiaTestApp(cartViewModel = cartViewModel)
    }
}