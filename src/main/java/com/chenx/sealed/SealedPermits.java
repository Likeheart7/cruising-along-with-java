package com.chenx.sealed;

// 默认sealed只能在当前文件内继承，且必须有子类
// 使用permit关键字可以指定子类，此时必须指定所有
//public sealed class SealedPermits permits OutsideSubclassForSealed, SubclassForSealed, DiffirentDirectory {
public sealed class SealedPermits permits OutsideSubclassForSealed, SubclassForSealed {
}
// 子类必须被non-sealed或final修饰
final class SubclassForSealed extends SealedPermits{

}

