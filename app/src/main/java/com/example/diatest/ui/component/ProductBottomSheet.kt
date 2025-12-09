// Создайте файл ProductBottomSheet.kt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.diatest.model.CartItem
import com.example.domain.entity.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductBottomSheet(
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