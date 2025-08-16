module com.restaurants.process {
    // 使用ServiceLoader动态加载需要使用uses声明具体SPI
    uses com.restaurants.products.Drink;
    requires com.restaurants.drinks;
}