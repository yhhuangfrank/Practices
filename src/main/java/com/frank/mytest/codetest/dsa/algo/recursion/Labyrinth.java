//package com.example.jpademo.test.data.recursion;
//
///**
// * 1 1 1 1 1 1 1
// * 1 0 0 0 0 0 1
// * 1 0 0 0 0 0 1
// * 1 1 1 0 0 0 1
// * 1 0 0 0 0 0 1
// * 1 0 0 0 0 0 1
// * 1 0 0 0 0 0 1
// * 1 1 1 1 1 1 1
// */
//public class Labyrinth {
//    public static void main(String[] args) {
//
//        // 建立一道迷宮空間
//        int[][] table = new int[8][7]; // 8 rows 7 cols
//        for (int col = 0; col < 7; col++) {
//            table[0][col] = 1;
//            table[7][col] = 1;
//        }
//        for (int row = 0; row < 8; row++) {
//            table[row][0] = 1;
//            table[row][6] = 1;
//        }
//        // 設置檔版
//        table[3][1] = 1;
//        table[3][2] = 1;
////        table[1][2] = 1; // 多設置檔板，可看到回溯現象
////        table[2][2] = 1;
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 7; col++) {
//                System.out.print(table[row][col] + " ");
//            }
//            System.out.println();
//        }
//
////        setWay(table, 1, 1); // 起點為 (1,1)
//        setWay2(table, 1, 1); // 起點為 (1,1)
//        // 走過路線的地圖
//        System.out.println("走過路線的地圖為 --------->");
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 7; col++) {
//                System.out.print(table[row][col] + " ");
//            }
//            System.out.println();
//        }
//
//    }
//
//    /**
//     * 使用遞迴回溯法找路
//     * 1. table 表示地圖
//     * 2. i, j 表示位置，起始點為 (1,1)
//     * 3. 如果能走到 (6,5)，說明找到通路
//     * 4. 約定：當 table[i][j] = 0 表示該點沒有走過, 1 表示牆, 2 表示可以走, 3 表示該點已經走過但為死路
//     * 5. 行進策略：下 -> 右 -> 上 -> 左 (如果該點走不通，再回溯)
//     *
//     * @return true 表示找到通路， false 表示沒有
//     */
//    public static boolean setWay(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//                // 遵循策略先後順序 下 -> 右 -> 上 -> 左
//                if (setWay(table, i + 1, j)) {// 往下
//                    System.out.printf("往下i,j=%d%d\n", i, j);
//                    return true;
//                } else if (setWay(table, i, j + 1)) {// 往右
//                    System.out.printf("往右i,j=%d%d\n", i, j);
//                    return true;
//                } else if (setWay(table, i - 1, j)) { // 往上
//                    System.out.printf("往上i,j=%d%d\n", i, j);
//                    return true;
//                } else if (setWay(table, i, j - 1)) { // 往左
//                    System.out.printf("往左i,j=%d%d\n", i, j);
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//
//    // 修改策略： 上 -> 右 -> 下 -> 左
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    // 上 -> 右 -> 左 -> 下
//    public static boolean setWay3(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay3(table, i - 1, j)) {
//                    return true;
//                } else if (setWay3(table, i, j + 1)) {
//                    return true;
//                } else if (setWay3(table, i + 1, j)) {
//                    return true;
//                } else if (setWay3(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    // 上 -> 下 -> 左 -> 右
//    public static boolean setWay4(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    // 上 -> 下 -> 右 -> 左
//    public static boolean setWay5(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    // 上 -> 左 -> 下 -> 右
//    public static boolean setWay6(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    // 上 -> 左 -> 右 -> 下
//    public static boolean setWay7(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//    public static boolean setWay2(int[][] table, int i, int j) {
//        // 終點已經標示為 2
//        if (table[6][5] == 2) {
//            return true;
//        } else {
//            if (table[i][j] == 0) {
//                // 目前此點沒有走過，標示為 2 ，假定可以走
//                table[i][j] = 2;
//
//                if (setWay2(table, i - 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j + 1)) {
//                    return true;
//                } else if (setWay2(table, i + 1, j)) {
//                    return true;
//                } else if (setWay2(table, i, j - 1)) {
//                    return true;
//                } else {
//                    // 說明此點不能走
//                    table[i][j] = 3;
//                    return false;
//                }
//            } else { // 表示此點已經標示為 1 or 2 or 3
//                return false;
//            }
//        }
//    }
//}
