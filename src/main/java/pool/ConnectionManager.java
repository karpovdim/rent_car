package pool;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {
    private static final String URL =  PropertiesUtil.getProperty("db.url");
    private static final String USER = PropertiesUtil.getProperty("db.username");
    private static final String PASS = PropertiesUtil.getProperty("db.password");
    private static final String PATH_JDBC_DRIVER = PropertiesUtil.getProperty("mysqlDriver");
    private static final int DEFAULT_POOL_SIZE = 5;
    private static Connection connection;
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnection;

    static {
        loadDriver();
        initConnectionPool();
    }

    private ConnectionManager() {
    }

    private static void loadDriver() {
        try {
            Class.forName(PATH_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); //TODO: CREATE CUSTOM EXCEPTION
        }
    }

    private static void initConnectionPool() {
        final String poolSize = PropertiesUtil.getProperty("db.poll.size");
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnection = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            connection = open();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close")
                            ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));
            pool.add(proxyConnection);
            sourceConnection.add(connection);
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = pool.take();
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //TODO: CREATE CUSTOM EXCEPTION
        }
    }

    public static void closePool() {
        for (Connection connection : sourceConnection) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //TODO: CREATE CUSTOM EXCEPTION
            }
        }
    }

    private static Connection open() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException e) {
                throw new RuntimeException(e); //TODO: CREATE CUSTOM EXCEPTION
            }
        }
        return connection;
    }
}
