package hospital.infra;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String RESOURCE_NAME = "java:jboss/datasources/PostgresDS";

    public Connection getConnection() throws SQLException, NamingException {
        return getDataSource().getConnection();
    }

    private DataSource getDataSource() throws NamingException {
        Context context = new InitialContext();
        return (DataSource) context.lookup(RESOURCE_NAME);
    }
}
//lembrar senha wildfly joaoj usuario tb