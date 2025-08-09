package com.chenx.record;

public class RecordClass {

    public static void main(String[] args) {
        Location address = new Location(105.3, 28.1);
        System.out.println(address.latitude());
        System.out.println(address.longitude());
        System.out.println(address);
        System.out.println(address.getClass().isRecord());

        System.out.println("--------");
        System.out.println(new Location("103.1:29.57"));
    }

    record Location(double latitude, double longitude) {
        // compact constructor
        // 会在生成的构造函数调用前调用，用于验证数据
        public Location {
            System.out.println("compact constructor");
            if (latitude < -90 || latitude > 190) {
                throw new RuntimeException("The location is out of permit;");
            }

            // 可以在这里修改入参实现修改属性值
            latitude = Math.round(latitude);
        }

        // 其他参数构造器
        public Location(String position) {
            // 必须调用canonical构造器
            this(Double.parseDouble(position.split(":")[0]),
                Double.parseDouble(position.split(":")[1]));
        }
    }
}
