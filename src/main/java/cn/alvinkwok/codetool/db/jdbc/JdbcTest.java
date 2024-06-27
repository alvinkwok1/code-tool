package cn.alvinkwok.codetool.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description
 * jdbc测试
 *
 * @author alvinkwok
 * @since 2024/6/19
 */
public class JdbcTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 编写一段jdbc执行SQL代码
        // 由于 SPI 的支持，现在已无需显示加载驱动了
        // 但在用户显示配置的情况下，进行主动加载
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://10.60.45.192:3306", "account", "Fingard@1");
        Connection conn = DriverManager.getConnection("jdbc:mysql://10.60.45.210:3306/yqs_crud_yqspay_3_0?serverTimezone=GMT%2B8&setUnicode=true&characterEncoding=utf8&allowMultiQueries=true", "yqs", "yqs123");
        Statement stmt = conn.createStatement();

        long start = 1000000000L;
        long id = start;
        for (; id < start + 1000L; id++) {
            // 插入机构数据
            String template = "INSERT INTO yqs_base_yqspay_3_0.organization (id, tenant_code, org_code, org_name, parent_org_code, short_org_name,\n" +
                    "                                              org_type, org_level, org_path, data_source, status, remark, version,\n" +
                    "                                              creator, updater, create_time, update_time, is_delete)\n" +
                    "VALUES (%s, 'QT330002', '%s', '%s', 'QT330001', null, '2', 2, '#QT330001#%s#', '1', '1', null,\n" +
                    "        1, 0, 0, '2024-03-04 17:24:57', '2024-03-04 17:24:57', 0);";
            String sql = String.format(template, id, id, id, id);

            // 插入账户
//            String template = "INSERT INTO account_tenant_dev.vacc_info (id, tenant_code, bank_vacc_num, fg_vacc_name, channel_product_code,\n" +
//                    "                                          channel_mch_id, fg_vacc_num, state, remark, type, role, balance,\n" +
//                    "                                          frozen_balance, property, result_info, version, create_time, update_time,\n" +
//                    "                                          creator, updater, system_state)\n" +
//                    "VALUES (%s, 'QT330001', 'J0%s', '刘亦菲%s', 'CITIC_EGJ', 'J04015400000000',\n" +
//                    "        '%s', '4', '', '2', '015001', 0, 0, '2', '[P6134]证件号和证件类型必须同时上送或同时不上送', 1,\n" +
//                    "        '2024-01-31 19:37:39', '2024-01-31 19:37:39', 0, 0, '1');\n";
//            String sql = String.format(template, id, id, id, id);

            stmt.execute(sql);
        }
    }

}
