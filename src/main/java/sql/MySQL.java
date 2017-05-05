package sql;

import java.sql.*;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/11 16:26:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class MySQL {
    private static Connection connection = null;
    private Statement statement = null;

    public static final String CANT_FIND = "can't find";

    static {
        createConnection();
    }

    public static void createConnection() {
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://192.168.0.22:33306/HIVE"+"?useUnicode=true&characterEncoding=utf-8";
        try {
            connection = DriverManager.getConnection(url, "hiveuser", "H1vePassw0rd!#%&");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static String formatName(String cityId, String cityNm, String provinceNm) {
        cityNm = cityNm.split("#")[0];
        provinceNm = provinceNm.split("#")[0];

        String result = CANT_FIND;

        try {
            String sqlA = "SELECT NM_CN,CD FROM CM_CAT_SYS WHERE PRNT_NM_CN = '"+provinceNm+"'"+" AND CD = '001" + cityId + "' AND METH_CD = '2500' AND EDIT_FLAG <> '1100.210'";
            System.out.println("SQL语句sqlA是"+sqlA);
            ResultSet resultSet = statement.executeQuery(sqlA);
            int i = 0;
            while (resultSet.next()) {
                if (i > 0) {
                    System.out.println("根据CD搜索的sql有问题");
                }
                result = resultSet.getString(1)+"#"+resultSet.getString(2);
                i++;
            }
            if (result.equals(CANT_FIND)) {
                String sqlB = "SELECT NM_CN,CD,EDIT_FLAG FROM CM_CAT_SYS WHERE PRNT_NM_CN = '"+provinceNm+"'"+" AND NM_CN LIKE '%" + cityNm.subSequence(0, 2) + "%' AND METH_CD = '2500'";
                System.out.println("SQL语句sqlB是"+sqlB);
                resultSet = statement.executeQuery(sqlB);
                i = 0;
                while (resultSet.next()) {
                    if (i > 0) {
                        System.out.println("根据NM_CN搜索的sql有问题");
                    } else {
                        if (resultSet.getString(3).equals("1100.210")) {
                            String sqlC = "SELECT NM_CN,CD FROM CM_CAT_SYS WHERE PRNT_NM_CN = '"+provinceNm+"'"+" AND CD = '" + resultSet.getString(2) + "' AND METH_CD = '2500' AND EDIT_FLAG <> '1100.210'";
                            System.out.println("SQL语句sqlC是"+sqlC);
                            resultSet = statement.executeQuery(sqlC);
                            int j = 0;
                            while (resultSet.next()) {
                                if (j > 0) {
                                    System.out.println("根据CD搜索的sql有问题");
                                }
                                result = resultSet.getString(1)+"#"+resultSet.getString(2);
                                j++;
                            }
                        } else {
                            result = resultSet.getString(1)+"#"+resultSet.getString(2);
                        }
                    }
                    i++;
                }
            }
            if (!resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }*/

    public String formatStName(String sourceNm) {
        String result = CANT_FIND;

        String sql = null;
        try {
            String ctyNm = sourceNm.subSequence(0, 2).toString();
            if (ctyNm.equals("张家")||ctyNm.equals("阿拉")) {
                ctyNm = sourceNm.subSequence(0, 3).toString();
            }
            sql = "SELECT PRNT_NM_CN FROM CM_CAT_SYS WHERE NM_CN LIKE '" + ctyNm + "%' AND CD LIKE '001%' AND TR_LEVEL = '3' AND METH_CD = '2500' AND EDIT_FLAG <> '1100.210'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int i = 0;
            while (resultSet.next()) {
                if (i > 0) {
                    System.out.println("根据NM搜索的sql有多值,sql语句是"+sql);
                }
                result = resultSet.getString(1);
                i++;
            }
            if (!resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result.equals(CANT_FIND)) {
            System.out.println("根据NM搜索的sql有问题,sql语句是"+sql);
        }
        return result;
    }

    public void close() {
        try {
            if (!statement.isClosed()) {
                statement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
