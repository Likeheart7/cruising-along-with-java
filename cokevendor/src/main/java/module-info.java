module com.cokevendor {
    requires com.restaurants.drinks;
    exports com.cokevendor.cocacola;
    // 通过provides为目标module提供特定的ServiceLoader依赖的接口的实现
    provides com.restaurants.products.Drink with
        com.cokevendor.cocacola.Coke,
        com.cokevendor.cocacola.DietCoke;
}