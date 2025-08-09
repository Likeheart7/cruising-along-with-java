package com.chenx.sealed.sub;

import com.chenx.sealed.SealedPermits;

// 在不使用module的情况下，所有子类必须和sealed类在同一个包下
// Class 'com.chenx.sealed.sub.DifferentDirectory' from another package not allowed to
// extend sealed class 'com.chenx.sealed.SealedPermits' in unnamed module
// 可以通过模块化实现
//public class DifferentDirectory extends SealedPermits {
//}
