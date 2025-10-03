package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import java.sql.PreparedStatement;
public class V5__insert_admin_user extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        try (PreparedStatement stmt = context.getConnection()
                .prepareStatement( "INSERT INTO app_user (username, password, role_id) VALUES (?, ?, ?)" ))
        {
            stmt.setString( 1, "admin");
            stmt.setString( 2, "$2a$10$...hashedpassword..." ); // bcrypt hash
            stmt.setInt(3, 2); // role_id ADMIN
            stmt.execute();
        }
    }
}