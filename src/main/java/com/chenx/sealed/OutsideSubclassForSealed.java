package com.chenx.sealed;

// final终止继承，而non-sealed抵消sealed开启继承
// 更合适的选择是在sealed接口的同文件内声明一个新的non-sealed的接口来开启继承
// 本质上是对于开闭原则的遵守
public non-sealed class OutsideSubclassForSealed extends SealedPermits {
}
sealed interface DemoInterface permits DemoSubInterface {}

// 接口的继承必须声明sealed/non-sealed
non-sealed interface DemoSubInterface extends DemoInterface{}